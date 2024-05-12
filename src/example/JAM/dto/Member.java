package example.JAM.dto;

import java.time.LocalDateTime;
import java.util.Map;

public class Member {
	public int id;
	public LocalDateTime regData;
	public LocalDateTime updateDate;
	public String title;
	public String body;
	
	public Member(int id, String title, String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}
	
	public Member(int id, LocalDateTime regData, LocalDateTime updateDate, String title, String body) {
		this.id = id;
		this.regData = regData;
		this.updateDate = updateDate;
		this.title = title;
		this.body = body;
	}		
	
	public Member(Map<String, Object> articleMap) {
		this.id = (int) articleMap.get("id");
		this.regData = (LocalDateTime) articleMap.get("regDate");
		this.updateDate = (LocalDateTime) articleMap.get("updateDate");
		this.title = (String) articleMap.get("title");
		this.body = (String) articleMap.get("body");
		
	}




}