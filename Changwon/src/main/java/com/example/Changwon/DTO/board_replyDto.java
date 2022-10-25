package com.example.Changwon.DTO;

import java.util.Date;

public class board_replyDto {
	int num;		//댓글 번호
	String content;	//댓글 내용
	Date regist_day;//댓글단 날짜
	int board_num;	//댓글게시판 번호
	String member_id;//댓글 작성자
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegist_day() {
		return regist_day;
	}
	public void setRegist_day(Date regist_day) {
		this.regist_day = regist_day;
	}
	public int getBoard_num() {
		return board_num;
	}
	public void setBoard_num(int board_num) {
		this.board_num = board_num;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
}
