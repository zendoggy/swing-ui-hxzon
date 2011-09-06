package other.rsa;

/*RSA.java，Steve，没有main，编译通过-Java源代码*/
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
* @author Steve
*/
public class RSA {

    private static final BigInteger ZERO = BigInteger.ZERO;

    private static final BigInteger ONE = BigInteger.ONE;

    private static final BigInteger TWO = new BigInteger("2");

    private BigInteger myKey;

    private BigInteger myMod;

    private int blockSize;

    public RSA(BigInteger key, BigInteger n, int b) {
        myKey = key;
        myMod = n;
        blockSize = b;
    }

    public void encodeFile(String filename) {
        byte[] bytes = new byte[blockSize / 8 + 1];
        int tempLen;
        InputStream is = null;
        FileWriter writer = null;
        try {
            is = new FileInputStream(filename);
            writer = new FileWriter(filename + ".enc");
        } catch (FileNotFoundException e1) {
            System.out.println("File not found: " + filename);
        } catch (IOException e1) {
            System.out.println("File not found: " + filename + ".enc");
        }

        /**
         * Write encoded message to 'filename'.enc
         */
        try {
            while ((tempLen = is.read(bytes, 1, blockSize / 8)) > 0) {
                for (int i = tempLen + 1; i < bytes.length; ++i) {
                    bytes[i] = 0;
                }
                writer.write(encodeDecode(new BigInteger(bytes)) + " ");
            }
        } catch (IOException e1) {
            System.out.println("error writing to file");
        }

        /**
         * Close input stream and file writer
         */
        try {
            is.close();
            writer.close();
        } catch (IOException e1) {
            System.out.println("Error closing file.");
        }
    }

    public void decodeFile(String filename) {

        FileReader reader = null;
        OutputStream os = null;
        try {
            reader = new FileReader(filename);
            os = new FileOutputStream(filename.replaceAll(".enc", ".dec"));
        } catch (FileNotFoundException e1) {
            if (reader == null)
                System.out.println("File not found: " + filename);
            else
                System.out.println("File not found: " + filename.replaceAll(".enc", "dec"));
        }

        BufferedReader br = new BufferedReader(reader);
        int offset;
        byte[] temp, toFile;
        StringTokenizer st = null;
        try {
            while (br.ready()) {
                st = new StringTokenizer(br.readLine());
                while (st.hasMoreTokens()) {
                    toFile = encodeDecode(new BigInteger(st.nextToken())).toByteArray();
                    System.out.println(toFile.length + " x " + (blockSize / 8));

                    if (toFile[0] == 0 && toFile.length != (blockSize / 8)) {
                        temp = new byte[blockSize / 8];
                        offset = temp.length - toFile.length;
                        for (int i = toFile.length - 1; (i <= 0) && ((i + offset) <= 0); --i) {
                            temp[i + offset] = toFile[i];
                        }
                        toFile = temp;
                    }

                    /*if (toFile.length != ((blockSize / 8) + 1)){
                        temp = new byte[(blockSize / 8) + 1];
                        System.out.println(toFile.length + " x " + temp.length);
                        for (int i = 1; i < temp.length; i++) {
                            temp[i] = toFile[i - 1];
                        }
                        toFile = temp;
                    }
                    else
                        System.out.println(toFile.length + " " + ((blockSize / 8) + 1));*/
                    os.write(toFile);
                }
            }
        } catch (IOException e1) {
            System.out.println("Something went wrong");
        }

        /**
         * close data streams
         */
        try {
            os.close();
            reader.close();
        } catch (IOException e1) {
            System.out.println("Error closing file.");
        }
    }

    public BigInteger encodeDecode(BigInteger base) {
        BigInteger a = ONE;
        BigInteger s = base;
        BigInteger n = myKey;

        while (!n.equals(ZERO)) {
            if (!n.mod(TWO).equals(ZERO))
                a = a.multiply(s).mod(myMod);
            s = s.pow(2).mod(myMod);
            n = n.divide(TWO);
        }

        return a;
    }

}
