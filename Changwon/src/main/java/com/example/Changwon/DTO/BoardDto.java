package com.example.Changwon.DTO;

import java.sql.Date;

public class BoardDto {
	private int num;		//번호
	private String id;		//등록자
	private String title;	//제목
	private Date bdate;		//등록일
	private String content; //내용
	private int hit;		//조회수
	private String img;		//이미지
	
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date date) {
		this.bdate = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
}
