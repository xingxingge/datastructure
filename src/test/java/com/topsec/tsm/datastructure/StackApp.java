package com.topsec.tsm.datastructure;

import com.topsec.tsm.datastructure.stack.Stack;
import com.topsec.tsm.datastructure.stack.StackSLinked;


/**
 * 栈的应用举例
 * @author HuangXing
 *
 */
public class StackApp {

	/** 1.进制转换 **/
	public static void baseConversion(int number, int Hex) {
		StackSLinked<Integer> sl = new StackSLinked<Integer>();
		while (number > 0) {
			sl.push(number % Hex);
			number = number / Hex;
		}
		while (!sl.isEmpty()) {
			System.out.print(sl.pop());
		}
		System.out.println("");
	}

	/** 2. 括号匹配检测 */

	public static boolean bracketMatch(String str) {
		Stack<Character> s = new StackSLinked<Character>();
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			// System.out.println(Integer.valueOf(c).intValue());
			// System.out.printf("%d\n",Integer.valueOf(c));
			switch (c) {
			case '{':
			case '[':
			case '(':
				s.push(c);
				break;
			case '}':
				if (!s.isEmpty() && (char) s.pop() == '{') {
					break;
				} else
					return false;
			case ']':
				if (!s.isEmpty() && (char) s.pop() == '[') {
					break;
				} else
					return false;
			case ')':
				if (!s.isEmpty() && (char) s.pop() == '(') {
					break;
				} else
					return false;
			default:
				return false;

			}
		}
		if (s.isEmpty()) {
			return true;
		} else
			return false;
	}

	/** 3.迷宫求解 */
	public  static void cell() {
		char[][] my = { { '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' },
				{ '1', '0', '0', '1', '1', '1', '0', '0', '1', '1' },
				{ '1', '0', '0', '1', '1', '0', '0', '1', '0', '1' },
				{ '1', '0', '0', '0', '0', '0', '0', '1', '0', '1' },
				{ '1', '0', '0', '0', '0', '1', '1', '0', '0', '1' },
				{ '1', '0', '0', '1', '1', '1', '0', '0', '0', '1' },
				{ '1', '0', '0', '0', '0', '1', '0', '1', '0', '1' },
				{ '1', '0', '1', '1', '0', '0', '0', '1', '0', '1' },
				{ '1', '1', '0', '0', '0', '0', '1', '0', '0', '1' },
				{ '1', '1', '1', '1', '1', '1', '1', '1', '1', '1' } };
		int sx = 8;
		int sy = 8;
		int ex = 1;
		int ey = 7;
		MazeExit me = new MazeExit();
		me.mazeExit(my, sx, sy, ex, ey);
	}

	public static void main(String[] xargs) {
		// 1进制转换
		baseConversion(100, 2);
		baseConversion(100, 8);
		baseConversion(100, 16);
		// 2.括号匹配监测
		System.out.println(bracketMatch("[[[]]]{()[]}"));
		System.out.println(bracketMatch("[[[]]]{([]}"));
		// 3.迷宫求解
		cell();
		System.out.println("test");
	}

}
