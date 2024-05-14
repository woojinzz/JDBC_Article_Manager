package example.JAM.controller;

import java.sql.Connection;
import java.util.Scanner;

import example.JAM.service.MemberService;
import example.JAM.util.DBUtil;
import example.JAM.util.SecSql;

public class MemberController { 
	
	private MemberService memberService;
	
	private Scanner sc;
	
	public MemberController (Connection conn, Scanner sc) {
		this.sc = sc;
		this.memberService = new MemberService(conn);
	}
		
	public void dojoin() {
		String loginId = null;
		String loginPw = null;
		String loginName = null;
		String loginPwChk = null;
		
		System.out.println("===== 회원가입 =====");
		
		while (true) {
			System.out.print("아이디 : ");
			loginId = sc.nextLine().trim();
			
			if(loginId.length() == 0) {
				System.out.println("아이디를 입력해 주세요");
				continue;
			}
			boolean isLoginIdDup = memberService.isLoginIdDUp(loginId); 
			
			if(isLoginIdDup) {
				System.out.printf("%s 아이디는 중복됩니다.\n", loginId);
				continue;
				
			}
			System.out.printf("%s 는 사용 가능한 아이디 입니다. \n", loginId);
			break;
		}
		
		while (true) {
			System.out.print("비밀번호 : ");
			loginPw = sc.nextLine().trim();
			
			if(loginPw.length() == 0) {
				System.out.println("비밀번호를 입력해 주세요");
				continue;
			}
			
			System.out.print("비밀번호 확인: ");
			loginPwChk = sc.nextLine().trim();
			
			if (loginPw.equals(loginPwChk) == false) {
				System.out.println("비밀번호가 일치하지 않습니다.!");
				continue;
			}
			break;
		}
		
		while (true) {
			System.out.print("이름 : ");
			loginName = sc.nextLine().trim();
			
			if(loginName.length() == 0) {
				System.out.println("이름을 입력해 주세요");
				continue;
			}
			break;
		}
		
		memberService.dojoin(loginId, loginPw, loginName);
		
		System.out.printf("%s 님 회원가입을 축하합니다.!\n", loginName);
	}


			

}
