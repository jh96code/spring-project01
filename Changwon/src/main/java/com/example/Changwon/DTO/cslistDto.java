package com.example.Changwon.DTO;

import java.sql.Date;

public class cslistDto {
	int num;				//문의 번호
	String title;			//문의 제목
	String category;		//문의 유형
	String content;			//문의 내용
	String reply_content;	//문의답변 내용
	String member_id;		//문의 등록한 아이디
	String reply_member_id;	//문의답변 등록한 아이디
	Date send_day;			//문의 등록 날짜
	Date reply_day;			//문의 답변 등록 날짜
	
	public String getReply_member_id() {
		return reply_member_id;
	}
	public void setReply_member_id(String reply_member_id) {
		this.reply_member_id = reply_member_id;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public Date getSend_day() {
		return send_day;
	}
	public void setSend_day(Date send_day) {
		this.send_day = send_day;
	}
	public Date getReply_day() {
		return reply_day;
	}
	public void setReply_day(Date reply_day) {
		this.reply_day = reply_day;
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
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	
}
