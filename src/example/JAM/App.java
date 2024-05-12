package example.JAM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import example.JAM.dto.Article;
import example.JAM.util.DBUtil;
import example.JAM.util.SecSql;

public class App {
	
	public void run() {
		
		Scanner sc = new Scanner(System.in);
		int lastArticleId = 0;
		
		
		System.out.println("==프로그램 시작==");
		
		Connection conn = null;

		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://127.0.0.1:3306/JAM?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
			conn = DriverManager.getConnection(url, "root", "qwer");
			while(true) {
				
				System.out.printf("명령어 : ");
				String  cmd = sc.nextLine().trim();
				
				if (cmd.equals("exit")){
					break;
				}
				
				if (cmd.equals("article write")) {
					
					System.out.println("===== 게시글 생성 =====");
					lastArticleId ++;
					
					System.out.print("제목 : ");
					String title = sc.nextLine().trim();
					System.out.print("내용 : ");
					String body = sc.nextLine().trim();
					
					SecSql sql = SecSql.from("INSERT INTO article");
					sql.append("SET regDate = NOW()");
					sql.append(", updateDate = NOW()");
					sql.append(", title = ?", title);
					sql.append(", `body` = ?", body);
				
					int id = DBUtil.insert(conn, sql);
					System.out.printf("%d번 글이 생성되었습니다. \n", id);
					
				} else if (cmd.equals("article list")) {
					
					System.out.println("===== 게시글 목록 =====");
					
					List<Article> articles = new ArrayList<>();
					
					SecSql sql = SecSql.from("SELECT * FROM article");
					sql.append(" ORDER BY id DESC");
					
					List<Map<String, Object>> articleListMap = DBUtil.selectRows(conn, sql);
					
					for (Map<String, Object> articleMap : articleListMap) {
						articles.add(new Article(articleMap));
					
					}
					
					if (articles.size() == 0) {
						System.out.println("존재하는 게시물이 없습니다.");
						continue;
					}
					
					System.out.println("번호:      제목 :     내용 :     날짜");
					for(Article article : articles) {
						System.out.printf("%d   / %s    /%s     / %s \n", article.id, article.title, article.body, article.regData);
					}
					
				}
				else if (cmd.startsWith("article detail ")) {
					
					int id = Integer.parseInt(cmd.split(" ")[2]);
					
					SecSql sql = SecSql.from("SELECT * FROM article");
					sql.append(" WHERE id = ?", id);
					
					Map<String, Object> articleMap = DBUtil.selectRow(conn, sql);
					
					if (articleMap.isEmpty()) {
						System.out.println("존재하는 게시물이 없습니다.");
						continue;
					}
					
					Article article = new Article(articleMap);
					
					System.out.printf("==== %d 게시물 상세보기 ======",id);
					System.out.printf("번호 : %d\n", article.id);
					System.out.printf("작성일 : %s\n", article.regData);
					System.out.printf("수정일 : %s\n", article.updateDate);
					System.out.printf("제목 : %s\n", article.title);
					System.out.printf("내용 : %s\n", article.body);		
					
					
				}
				else if (cmd.startsWith("article modify ")) {
					
				
					int id = Integer.parseInt(cmd.split(" ")[2]);
					
					SecSql sql = SecSql.from("SELECT id FROM article");
					sql.append(" WHERE id = ?", id);
					
					int articleCnt = DBUtil.selectRowIntValue(conn, sql);
					
					if (articleCnt == 0) {
						System.out.printf("%d 번 게시글이 존재하지 않습니다.\n", id);
						continue;
					}
					System.out.println("==== 게시물 수정 ======");
					
					System.out.print("수정할 제목 : ");
					String title = sc.nextLine().trim();
					System.out.print("수정할 내용 : ");
					String body = sc.nextLine().trim();

					sql = SecSql.from("UPDATE article");
					sql.append("SET  updateDate = NOW()");
					sql.append(", title = ?", title);
					sql.append(", `body` = ?", body);
					sql.append("WHERE id = ?", id);
					
					DBUtil.update(conn, sql);
					System.out.printf("%d 번 게시물이 수정되었습니다.\n", id);
					
					
				} else if (cmd.startsWith("article delete ")) {
					
					int id = Integer.parseInt(cmd.split(" ")[2]);
					
					SecSql sql = SecSql.from("SELECT id FROM article");
					sql.append(" WHERE id = ?", id);
					
					int articleCnt = DBUtil.selectRowIntValue(conn, sql);
					
					if (articleCnt == 0) {
						System.out.printf("%d 번 게시글이 존재하지 않습니다.\n", id);
						continue;
					}
					
					System.out.println("===== 게시글 삭제 =====");

					sql = SecSql.from("DELETE FROM article");
					sql.append("WHERE id = ?", id);
				
					DBUtil.delete(conn, sql);
					System.out.printf("%d 번 게시글을 삭제했습니다.\n", id);
				}
				else if (cmd.equals("member join")) {
					
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
						
						SecSql sql = SecSql.from("SELECT COUNT(*) > 0 FROM member");
						sql.append("WHERE loginId = ?", loginId);
						
						boolean isLoginIdDup = DBUtil.selectRowBooleanValue(conn, sql);
						
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
					
					SecSql sql = SecSql.from("INSERT INTO `member`");
					sql.append("SET regDate = NOW()");
					sql.append(", updateDate = NOW()");
					sql.append(", loginId = ?", loginId);
					sql.append(", loginPw = ?", loginPw);
					sql.append(", `name` = ?", loginName);
				
					DBUtil.insert(conn, sql);
					System.out.printf("%s 님 회원가입을 축하합니다.!\n", loginName);
				}

				else {
					System.out.println("존재하지 않는 명령어 입니다.");
				}
			}
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("에러: " + e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		sc.close();
		System.out.println("==프로그램 종료==");
	}

		
}




