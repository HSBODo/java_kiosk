package main;



import service.ReserveServiceImpl;

import util.Common;

public class Main {
	public static void main(String[] args) {
			ReserveServiceImpl rs = new ReserveServiceImpl();
			boolean flag = true;
		
		while (flag) {
			try {
				System.out.println("=========================================");
				System.out.println("    _ __ ___   _____   _(_) ___  ___ \r\n" + 
						  "   | '_ ` _ \\ / _ \\ \\ / / |/ _ \\/ __|\r\n" + 
						  "   | | | | | | (_) \\ V /| |  __/\\__ \\\r\n" + 
						  "   |_| |_| |_|\\___/ \\_/ |_|\\___||___/");
				System.out.println();
				System.out.println("       휴먼영화관 예약시스템입니다.       ");
				System.out.println("=========================================");
				System.out.println("    원하는 서비스의 번호를 입력하세요.    ");
				int b = Common.nextInt("    ① 회원가입  ②로그인  ③종료    ");
				switch(b) {
				case 1:
					rs.input();
					break;
				case 2:
					rs.login();
					
					break;
				case 3: 
					System.exit(0);
					break;
				}
			}catch (RuntimeException e) {
				System.out.println();
				System.out.println("     "+"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println("     "+"xxx올바른 숫자를 입력하세요.xxx");
				System.out.println("     "+"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
				System.out.println();
			}
		}
	}
}
