package example.JAM.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import example.JAM.util.DBUtil;
import example.JAM.util.SecSql;

public class ArticleDao {
	
	private Connection conn;
	
	public ArticleDao(Connection conn) {
		this.conn = conn;
	}
	public int doWrite(String title, String body) {
		SecSql sql = SecSql.from("INSERT INTO article");
		sql.append("SET regDate = NOW()");
		sql.append(", updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);
		
		return DBUtil.insert(conn, sql);
	}
	
	public List<Map<String, Object>> showList() {
		SecSql sql = SecSql.from("SELECT * FROM article");
		sql.append(" ORDER BY id DESC");
		
		return  DBUtil.selectRows(conn, sql);
	}
	
	public Map<String, Object> showDetail(int id) {
		SecSql sql = SecSql.from("SELECT * FROM article");
		sql.append(" WHERE id = ?", id);
		return DBUtil.selectRow(conn, sql);
	}
	
	public int getArticleCnt(int id) {
		SecSql sql = SecSql.from("SELECT COUNT(*) FROM article");
		sql.append("WHERE id = ?", id);
		
		return DBUtil.selectRowIntValue(conn, sql);
	}
	public void doModify(String title, String body, int id) {
		SecSql sql = SecSql.from("UPDATE article");
		sql.append("SET updateDate = NOW()");
		sql.append(", title = ?", title);
		sql.append(", `body` = ?", body);
		sql.append("WHERE id = ?", id);
		
		DBUtil.update(conn, sql);
	}

	public int doDelete(int id) {
		SecSql sql = SecSql.from("DELETE FROM article");
		sql.append("WHERE id = ?", id);
		return DBUtil.delete(conn, sql);
	}



}
