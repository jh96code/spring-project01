package com.example.Changwon.DTO;

public class addressDto {
	private int addressNum; //주소 목록 번호
	private String addressId; //회원 아이디
	private String addressName; // 수령인 
	private String addressPhone; // 수령 전화번호
	private String addressAdr; // 수령 주소
	
	
	public int getAddressNum() {
		return addressNum;
	}
	public void setAddressNum(int addressNum) {
		this.addressNum = addressNum;
	}
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getAddressName() {
		return addressName;
	}
	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}
	public String getAddressPhone() {
		return addressPhone;
	}
	public void setAddressPhone(String addressPhone) {
		this.addressPhone = addressPhone;
	}
	public String getAddressAdr() {
		return addressAdr;
	}
	public void setAddressAdr(String addressAdr) {
		this.addressAdr = addressAdr;
	}

	
	
	
}
