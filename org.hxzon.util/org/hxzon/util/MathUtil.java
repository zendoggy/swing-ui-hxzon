package org.hxzon.util;

public class MathUtil {

	public static int[] average(int array[], int num) {
		int len = array.length;
		int[] result = new int[(len + num - 1) / num];
		for (int i = 0; i < len; i += num) {
			for (int j = 0; j < num; j++) {
				result[i / num] += array[i + j];
			}
			result[i / num] /= num;
		}
		return result;
	}

	public static int max(int array[]) {
		int result = Integer.MIN_VALUE;
		for (int i : array) {
			result = (result > i) ? result : i;
		}
		return result;
	}

	public static int min(int array[]) {
		int result = Integer.MAX_VALUE;
		for (int i : array) {
			result = (result < i) ? result : i;
		}
		return result;
	}

	public static void testAverage() {
		int a[] = { 1, 2, 3, 4, 5, 6, 7, 7, 7, 12, 13, 14, 22, 21, 23 };
		int result[] = average(a, 3);
		for (int i : result) {
			System.out.println(i);
		}
	}

	public static void testMax() {
		int a[] = { 1, 2, 3, 4, 5, 6, 7, 7, 66, 7, 12, 13, 14, 22, 21, 23 };
		int result = max(a);
		System.out.println(result);
	}

	public static void testMin() {
		int a[] = { 7, 66, 7, 12, 13, 6, 14, 22, 21, 23 };
		int result = min(a);
		System.out.println(result);
	}

	public static void main(String args[]) {
//		testAverage();
//		testMax();
		testMin();
	}
}
