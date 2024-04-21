package example.JAM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import example.JAM.dto.Article;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("====== 프로그램 시작 ======");
		
		Scanner sc = new Scanner(System.in);
		List<Article> articles = new ArrayList<>();
		int lastArticleId = 0;
		
		while(true) {
			
			System.out.printf("명령어 : ");
			String  cmd = sc.nextLine().trim();
			
			if (cmd.equals("exit")){
				System.out.println("===== 프로그램 종료 ======");
				break;
			}
			
			if (cmd.equals("article write")) {
				
				System.out.println("===== 게시글 생성 =====");
				lastArticleId ++;
				
				System.out.print("제목 : ");
				String title = sc.nextLine();
				System.out.print("내용 : ");
				String body = sc.nextLine();
				
				
				Article article = new Article(lastArticleId, title, body);
				articles.add(article);
				System.out.printf("%d번 글이 생성되었습니다. \n", lastArticleId);
				
			}
			
			else if (cmd.equals("article list")) {
				
				
				System.out.println("===== 게시글 목록 =====");

				if (articles.size() == 0) {
					System.out.println("존재하는 게시물이 없습니다.");
					continue;
				}
				
				System.out.println("번호:      제목 :     내용 : ");
				for(int i = articles.size()-1; i >= 0; i--) {
					Article article = articles.get(i);
					System.out.printf("%d   / %s    /%s     \n", article.id, article.title, article.body);
				}
				
			}else {
				System.out.println("존재하지 않는 명령어 입니다.");
			}
		}
	}
}


