package example.JAM.service;

import java.sql.Connection;

import example.JAM.dao.MemberDao;

public class MemberService {

	private MemberDao memberDao;
	
	public MemberService(Connection conn) {
		this.memberDao = new MemberDao(conn);
	}

	public boolean isLoginIdDUp(String loginId) {
		return memberDao.isLoginDup(loginId);
	}

	public void dojoin(String loginId, String loginPw, String loginName) {
		memberDao.dojoin(loginId, loginPw, loginName);
	
	}

}
