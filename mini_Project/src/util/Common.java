package util;

import java.util.Scanner;


public class Common {
	static Scanner scanner = new Scanner(System.in);
	
	public static int nextInt(String text) {
		return Integer.parseInt(nextLine(text));
	}
	
	public static String nextLine(String text) {
		System.out.print(text);
		return scanner.nextLine();
	}
}
