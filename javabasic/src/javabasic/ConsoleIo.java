package javabasic;

import java.util.Scanner;

public class ConsoleIo {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();
		
		System.out.println(i);//블로킹 메소드라 한다.

		
	}//main
}//class
