package com.topsec.tsm.datastructure;

public class Recursion {
	// 递归相关算法
	private Recursion() {
	}

	public static void main(String[] args) {
		System.out.print("进制转换: ");
		DecimalToBinary(100, 2);
		System.out.println(" \n求前n项和: " + sum(10));
		System.out.println("求阶乘： " + factorial(5));
		System.out.println("求x的n次幂： " + power(2, 5));
		char x = 'x', y = 'y', z = 'z';
		hanio(5, x, y, z);
		int[] b = new int[3];
		int n = b.length-1;
		coding(b, n);
	}

	// 10进制转换成其他进制
	public static void DecimalToBinary(int num, int hex) {
		if (num == 0) {
			return;
		} else {
			DecimalToBinary(num / hex, hex);
			System.out.print(num % hex);
		}
	}

	// 求前n项和
	public static int sum(int num) {
		if (num > 0) {
			return num + sum(num - 1);
		} else {
			return 0;
		}
	}

	public static int sum1(int num) {
		int result;
		if (num > 0) {
			result = num + sum1(num - 1);
		} else {
			return 0;
		}
		return result;

	}

	// 求阶乘
	public static int factorial(int num) {
		if (num == 1) {
			return 1;
		}
		return num * factorial(num - 1);
	}

	public static int power(int x, int n) {

		if (n == 0) {
			return 1;
		}
		return x * power(x, n - 1);

	}

	// 汉诺塔，代价是2^n
	public static void hanio(int n, char x, char y, char z) {
		if (n == 1)// 如果x上只有一个盘子，直接移动到z
			move(n, x, z);
		else {
			hanio(n - 1, x, z, y);// 首先把n-1个盘子借助z从x到y
			move(n, x, z);// 空出来了以后，把第n个盘子从x移动到z
			hanio(n - 1, y, x, z);// 然后把剩余的盘子从y借助x移动到z

		}

	}

	// 辅助方法：把n号盘子从x移动到y
	private static void move(int n, char x, char y) {
		System.out.println("move " + n + " from " + x + " to " + y);

	}

	// 编写一个算法输出n个布尔量的所有可能组合。
	private static void print(int[] b) {
		for (int i = 0; i < b.length; i++) {
			System.out.print(b[i]);
		}
		System.out.println();

	}

	public static void coding(int[] b, int n) {
		if (n == 0) {//n=0表示只有一个元素，只打印0和1
			b[n]=0;print(b);
			b[n]=1;print(b);
		}
		else {
			b[n]=0;coding(b, n-1);
			b[n]=1;coding(b, n-1);
		}

	}

}
