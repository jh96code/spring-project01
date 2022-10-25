package com.example.Changwon.DTO;

import java.time.LocalDate;
import java.util.Date;


public class orderlistDto {
	int orderNum; // 주문 번호 (자동생성)
	Date orderAt; //주문시간
	
	int getNum; //구매 수량
	String userId; //구매한 아이디
	String buygoods; //구매한 상품명
	
	
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	
	public Date getOrderAt() {
		return orderAt;
	}
	public void setOrderAt(Date orderAt) {
		this.orderAt = orderAt;
	}
	public int getGetNum() {
		return getNum;
	}
	public void setGetNum(int getNum) {
		this.getNum = getNum;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBuygoods() {
		return buygoods;
	}
	public void setBuygoods(String buygoods) {
		this.buygoods = buygoods;
	}
	
	
}
