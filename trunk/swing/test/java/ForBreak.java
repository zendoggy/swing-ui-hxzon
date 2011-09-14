package test.java;

public class ForBreak {

    public static void main(String[] args) {
        int i=1;
        For1: for (;; i++) {
            For2: for (;; i+=2) {
                if(i>9){
                    System.out.println("i>9:"+i);
                    i-=20;
                    System.out.println("i-20:"+i);
                    break;//jump out inner
                }
                if (i % 10 == 0) {
                    System.out.println("i%10:"+i);
                    break For1;//jump to for1 next;
                } else if (i % 5 == 0) {
                    System.out.println("i%5:"+i);
                    break For2;//jump to for2 next;
                }else{
                    System.out.println(i);
                }
            }//end for2
        System.out.println("for2 next");
        }//end for1
        System.out.println("for1 next");
        System.out.println("exit");
        //-------------------
        System.out.println("---------------");
        i=1;
        for (;; i++) {
            if(i>50){
                break;
            }
            For2: for (;; i+=2) {
                 if (i % 10 == 0) {
                    System.out.println("i%10:"+i);
                    break ;//jump out inner;
                } else if (i % 5 == 0) {
                    System.out.println("i%5:"+i);
                    break For2;//jump to for2 next;
                }else{
                    System.out.println(i);
                }
            }//end for2
        System.out.println("for2 next");
        }//end for1
        System.out.println("for1 next");
        System.out.println("exit");
        //-------------------
        System.out.println("---------------");
        i=1;
        for (;; i++) {
            if(i>50){
                break;
            }
            For2: for (;; i+=2) {
                 if (i % 10 == 0) {
                    System.out.println("i%10:"+i);
                    continue ;//jump out inner;
                } else if (i % 5 == 0) {
                    System.out.println("i%5:"+i);
                    break For2;//jump to for2 next;
                }else{
                    System.out.println(i);
                }
            }//end for2
        System.out.println("for2 next");
        }//end for1
        System.out.println("for1 next");
        System.out.println("exit");
    }

}
