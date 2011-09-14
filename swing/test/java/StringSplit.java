package test.java;

public class StringSplit {

	public static void main(String[] args) {
		String s=":123:::3";
		String[] s1=s.split(":");
		for(int i=0;i<s1.length;i++){
			System.out.println(s1[i]);
			System.out.println(s1[i].equals(""));
		}
	}

}
