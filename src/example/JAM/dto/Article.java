package example.JAM.dto;

public class Article {
	public int id;
	public String regData;
	public String updateDate;
	public String title;
	public String body;
	
	public Article(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}
	
	public Article(int id, String regData, String updateDate, String title, String body) {
		this.id = id;
		this.regData = regData;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
	}		

}