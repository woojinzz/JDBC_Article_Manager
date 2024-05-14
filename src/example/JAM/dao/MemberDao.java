package example.JAM.dao;

import java.sql.Connection;

import example.JAM.util.DBUtil;
import example.JAM.util.SecSql;

public class MemberDao {
	
	private Connection conn;

	public MemberDao(Connection conn) {
		this.conn = conn;
	}

	public boolean isLoginDup(String loginId) {
		
		SecSql sql = SecSql.from("SELECT COUNT(*) > 0 FROM member");
		sql.append("WHERE loginId = ?", loginId);
	
		DBUtil.selectRowBooleanValue(conn, sql);
		
		return false;
	}

	public void dojoin(String loginId, String loginPw, String loginName) {
		
		SecSql sql = SecSql.from("INSERT INTO `member`");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", loginId = ?", loginId);
		sql.append(", loginPw = ?", loginPw);
		sql.append(", `name` = ?", loginName);
	
		DBUtil.insert(conn, sql);
		
	}

}
