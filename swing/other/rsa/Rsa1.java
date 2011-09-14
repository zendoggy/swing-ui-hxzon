package other.rsa;

/*Rsa1.java-pki实验-Java源代码
*
*--------------------Configuration: <Default>--------------------
请输入素数p: 
37
请输入素数q: 
44
请输入素数q: 
491
这两个素数的乘积为p*q：18167
所得的小于Ｎ并且与Ｎ互素的整数的个数为m=(p-1)(q-1)：17640
请输入一个公钥的值，这个值要求小于m并且和m互质： 
673
gcd:1
gcd:1
gcd:1
gcd:1
gcd:1
gcd:1
gcd:1
gcd:1
公钥为：673
value:    17641
value:    35281
value:    52921
value:    70561
value:    88201
value:    105841
value:    123481
value:    141121
value:    158761
value:    176401
value:    194041
value:    211681
value:    229321
value:    246961
value:    264601
value:    282241
value:    299881
value:    317521
value:    335161
value:    352801
value:    370441
value:    388081
value:    405721
value:    423361
value:    441001
value:    458641
value:    476281
value:    493921
value:    511561
value:    529201
value:    546841
value:    564481
value:    582121
value:    599761
value:    617401
value:    635041
value:    652681
value:    670321
value:    687961
value:    705601
value:    723241
value:    740881
value:    758521
value:    776161
value:    793801
value:    811441
value:    829081
value:    846721
value:    864361
value:    882001
value:    899641
value:    917281
value:    934921
value:    952561
value:    970201
value:    987841
value:    1005481
value:    1023121
value:    1040761
value:    1058401
value:    1076041
value:    1093681
value:    1111321
value:    1128961
value:    1146601
value:    1164241
value:    1181881
value:    1199521
value:    1217161
value:    1234801
value:    1252441
value:    1270081
value:    1287721
value:    1305361
value:    1323001
value:    1340641
value:    1358281
value:    1375921
value:    1393561
value:    1411201
value:    1428841
value:    1446481
value:    1464121
value:    1481761
value:    1499401
value:    1517041
value:    1534681
value:    1552321
value:    1569961
value:    1587601
value:    1605241
value:    1622881
value:    1640521
value:    1658161
value:    1675801
value:    1693441
value:    1711081
value:    1728721
value:    1746361
value:    1764001
value:    1781641
value:    1799281
value:    1816921
value:    1834561
value:    1852201
value:    1869841
value:    1887481
value:    1905121
value:    1922761
产生的一个私钥为：2857
请输入明文：//字母不成功
12345
输入明文为: 12345
所得的密文为：3806
解密后所得的明文为：12345
Process completed.
*/
import java.io.*;

public class Rsa1 {
    private int p = 0;
    private int q = 0;
    private long n = 0;
    private long m = 0;

    private long public_key = 0;//公匙
    private long private_key = 0;//密匙
    private long text = 0;//明文
    private long secretword = 0;//密文
    private long word = 0;//解密后明文
//判断是否为素数

    public boolean primenumber(long t) {
        long k = 0;
        k = (long) Math.sqrt((double) t);
        boolean flag = true;
        outer: for (int i = 2; i <= k; i++) {
            if ((t % i) == 0) {
                flag = false;
                break outer;
            }
        }
        return flag;
    }

//输入PQ
    public void inputPQ() throws Exception {
        do {
            System.out.println("请输入素数p: ");
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            String br = stdin.readLine();
            this.p = Integer.parseInt(br);
        } while (!primenumber(this.p));
        do {
            System.out.println("请输入素数q: ");
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            String br = stdin.readLine();
            this.q = Integer.parseInt(br);
        } while (!primenumber(this.q));
        this.n = this.p * this.q;
        this.m = (p - 1) * (q - 1);
        System.out.println("这两个素数的乘积为p*q：" + this.n);
        System.out.println("所得的小于Ｎ并且与Ｎ互素的整数的个数为m=(p-1)(q-1)：" + this.m);
    }

//求最大公约数
    public long gcd(long a, long b) {
        long gcd;
        if (b == 0)
            gcd = a;
        else
            gcd = gcd(b, a % b);
        System.out.println("gcd:" + gcd);
        return gcd;

    }

//输入公匙
    public void getPublic_key() throws Exception {
        do {
            System.out.println("请输入一个公钥的值，这个值要求小于m并且和m互质： ");
            BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
            String br = stdin.readLine();
            this.public_key = Long.parseLong(br);
        } while ((this.public_key >= this.m) || (this.gcd(this.m, this.public_key) != 1));
        System.out.println("公钥为：" + this.public_key);
    }

//计算得到密匙
    public void getPrivate_key() {
        long value = 1;
        outer: for (long i = 1;; i++) {
            value = i * this.m + 1;
            System.out.println("value:    " + value);
            if ((value % this.public_key == 0) && (value / this.public_key < this.m)) {
                this.private_key = value / this.public_key;
                break outer;
            }
        }
        System.out.println("产生的一个私钥为：" + this.private_key);
    }

//输入明文
    public void getText() throws Exception {
        System.out.println("请输入明文：");
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String br = stdin.readLine();
        this.text = Long.parseLong(br);
    }

//加密、解密计算
    public long colum(long y, long n, long key) {
        long mul;
        if (key == 1)
            mul = y % n;
        else
            mul = y * this.colum(y, n, key - 1) % n;
        return mul;
    }

//加密后解密
    public void pascolum() throws Exception {
        this.getText();
        System.out.println("输入明文为: " + this.text);
//加密
        this.secretword = this.colum(this.text, this.n, this.public_key);
        System.out.println("所得的密文为：" + this.secretword);
//解密
        this.word = this.colum(this.secretword, this.n, this.private_key);
        System.out.println("解密后所得的明文为：" + this.word);

    }

    public static void main(String[] args) throws Exception {
        Rsa1 t = new Rsa1();
        t.inputPQ();
        t.getPublic_key();
        t.getPrivate_key();
        t.pascolum();
    }
}
