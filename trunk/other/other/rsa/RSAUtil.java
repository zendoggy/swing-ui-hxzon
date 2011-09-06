package other.rsa;

/*
* RSAUtil.java
* Created on 2007年11月15日, 下午9:17
*
* http://www.bouncycastle.org/latest_releases.html
*/
/**
* @author will
*/
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

/**
* 　　* RSAUtil 工具类。提供加密，解密，生成密钥对等方法。
* 　　* 需要到http://www.bouncycastle.org下载bcprov-jdk15-138.jar。
*/
public class RSAUtil {
    /**
     * 生成密钥对
     * @return KeyPair
     * @throws EncryptException
     */

    public static KeyPair generateKeyPair() throws Exception {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
            //getInstance(String algorithm, Provider provider) 如果该可从指定的提供程序得到算法，则生成实现了该指定算法的 KeyPairGenerator 对象。

            final int KEY_SIZE = 1024;//没什么好说的了，这个值关系到块加密的大小，可以更改，但是不要太大，否则效率会低
            keyPairGen.initialize(KEY_SIZE, new SecureRandom());
            //initialize(AlgorithmParameterSpec params, SecureRandom random) 使用给定参数集合和随机源初始化密钥对生成器。

            KeyPair keyPair = keyPairGen.genKeyPair();
            return keyPair;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 生成公钥
     * @param modulus
     * @param publicExponent
     * @return RSAPublicKey
     * @throws EncryptException
     */
    public static RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) throws Exception {
        KeyFactory keyFac = null;
        try {
            keyFac = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
            //getInstance(String algorithm, Provider provider) 为指定提供程序中的指定算法生成 KeyFactory 对象
        } catch (NoSuchAlgorithmException ex) {
            throw new Exception(ex.getMessage());
        }

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
        try {
            return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
            //generatePublic(KeySpec keySpec) 根据所提供的密钥规范（密钥材料）生成公钥对象。

        } catch (InvalidKeySpecException ex) {
            throw new Exception(ex.getMessage());
        }

    }

    /**
     * 生成私钥
     * @param modulus
     * @param privateExponent
     * @return RSAPrivateKey
     * @throws EncryptException
     */
    public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) throws Exception {
        KeyFactory keyFac = null;
        try {
            keyFac = KeyFactory.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
        } catch (NoSuchAlgorithmException ex) {
            throw new Exception(ex.getMessage());
        }

        RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(privateExponent));
        try {
            return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
        } catch (InvalidKeySpecException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /**
     * 加密
     * @param key 公钥
     * @param data 待加密的明文数据
     * @return 加密后的数据
     * @throws Exception
     */
    public static byte[] encrypt(Key key, byte[] data) throws Exception {
        try {
            Cipher cipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());
            /**
             *public class Cipherextends Object此类提供了针对加密和解密的密码 cipher 功能。它构成了 Java Cryptographic Extension (JCE) 框架的核心。 
             * 为创建 Cipher 对象，应用程序调用 Cipher 的 getInstance 方法并将请求的转换 名称传递给它。作为可选项，也可以指定提供程序的名称。 
             * 转换 是描述为产生某种输出而在给定的输入上执行的操作（或一组操作）的字符串。转换始终包括加密算法的名称（例如，DES），后面可能跟有一个反馈模式和填充方案。 
             * 转换具有下面的形式：
             * “算法/模式/填充”或 
             * “算法” 
             * （后一种情况下，使用此模式和填充方案的特定于提供程序的默认值）。例如，以下是有效的转换：     
             * Cipher c = Cipher.getInstance("DES/CBC/PKCS5Padding");
             * 在流 cipher 模式下（例如，DES 为 CFB 或 OFB 模式）请求块 cipher 时，用户可选择指定一次处理的位数，方法是将此数追加到模式名称中，如在 "DES/CFB8/NoPadding" 和 "DES/OFB32/PKCS5Padding" 转换中所示。如果未指定该数，则将使用特定于提供程序的默认值。（例如，"SunJCE" 提供程序使用默认的 64 位）。
             */
            //getInstance(String transformation, String provider)
            //创建一个实现指定转换的 Cipher 对象，该转换由指定的提供程序提供。

            cipher.init(Cipher.ENCRYPT_MODE, key);
            //ENCRYPT_MODE           用于将 cipher 初始化为加密模式的常量。
            //init(int opmode, Key key)           用密钥初始化此 cipher。

            int blockSize = cipher.getBlockSize();
            //getBlockSize
            //public final int getBlockSize()返回块的大小（以字节为单位）。
            //返回：
            //块的大小（以字节为单位），如果基础算法不是块 cipher，则返回 0
            //获得加密块大小，如:加密前数据为128个byte，
            //而key_size=1024 加密块大小为127 byte,加密后为128个byte;因此共有2个加密块，第一个127
            //byte第二个为1个byte
            int outputSize = cipher.getOutputSize(data.length);//获得加密块加密后块大小
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;

            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0;
            while (data.length - i * blockSize > 0) {
                if (data.length - i * blockSize > blockSize)
                    cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
                else
                    cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
                //这里面doUpdate方法不可用，查看源代码后发现每次doUpdate后并没有什么实际动作除了把
                //byte[]放到ByteArrayOutputStream中，而最后doFinal的时候才将所有的byte[]进行加密，可是到
                //了此时加密块大小很可能已经超出了OutputSize所以只好用dofinal方法。
                i++;
            }
            return raw;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 解密
     * @param key 密钥
     * @param raw 已经加密的数据
     * @return 解密后的明文
     * @throws Exception
     */
    public static byte[] decrypt(Key key, byte[] raw) throws Exception {

        try {

            Cipher cipher = Cipher.getInstance("RSA", new org.bouncycastle.jce.provider.BouncyCastleProvider());

            cipher.init(cipher.DECRYPT_MODE, key);

            int blockSize = cipher.getBlockSize();

            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);

            int j = 0;

            while (raw.length - j * blockSize > 0) {

                bout.write(cipher.doFinal(raw, j * blockSize, blockSize));

                j++;

            }

            return bout.toByteArray();
        } catch (Exception e) {

            throw new Exception(e.getMessage());
        }
    }

    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        File file = new File(RSAUtil.class.getResource("test.txt").toURI());
        FileInputStream in = new FileInputStream(file);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        //ByteArrayOutputStream() 
        // 创建一个新的字节数组输出流。

        byte[] tmpbuf = new byte[1024];
        int count = 0, count1 = 0;

        while ((count = in.read(tmpbuf)) != -1) {
            bout.write(tmpbuf, 0, count);
            tmpbuf = new byte[1024];
            count1++;
        }
        System.out.println("count1=" + count1);
        in.close();
        //public void write(byte[] b, int off, int len)将指定字节数组中从偏移量 off 开始的 len 个字节写入此字节数组输出流。 
        //覆盖：
        //类 OutputStream 中的 write
        //参数：
        //b - 数据。
        //off - 数据的初始偏移量。
        //len - 要写入的字节数。

        byte[] orgData = bout.toByteArray();
        KeyPair keyPair = RSAUtil.generateKeyPair();

        RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey priKey = (RSAPrivateKey) keyPair.getPrivate();

        byte[] pubModBytes = pubKey.getModulus().toByteArray();
        byte[] pubPubExpBytes = pubKey.getPublicExponent().toByteArray();
        byte[] priModBytes = priKey.getModulus().toByteArray();
        byte[] priPriExpBytes = priKey.getPrivateExponent().toByteArray();

        RSAPublicKey recoveryPubKey = generateRSAPublicKey(pubModBytes, pubPubExpBytes);
        RSAPrivateKey recoveryPriKey = generateRSAPrivateKey(priModBytes, priPriExpBytes);

        byte[] raw = RSAUtil.encrypt(priKey, orgData);
        file = new File(RSAUtil.class.getResource("encrypt_result.txt").toURI());
        OutputStream out = new FileOutputStream(file);
        out.write(raw);
        out.close();

        byte[] data = RSAUtil.decrypt(recoveryPubKey, raw);
        file = new File(RSAUtil.class.getResource("decrypt_result.txt").toURI());
        out = new FileOutputStream(file);
        out.write(data);
        out.flush();
        out.close();
    }
}
