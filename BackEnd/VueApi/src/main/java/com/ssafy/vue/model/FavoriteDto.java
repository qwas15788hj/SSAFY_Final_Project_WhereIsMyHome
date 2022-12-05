package com.ssafy.vue.model;

public class FavoriteDto {
	
	private String userid;
	private String deal_no;
	
	public FavoriteDto() {
		// TODO Auto-generated constructor stub
	}

	public FavoriteDto(String userid, String deal_no) {
		super();
		this.userid = userid;
		this.deal_no = deal_no;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getDeal_no() {
		return deal_no;
	}

	public void setDeal_no(String deal_no) {
		this.deal_no = deal_no;
	}

	@Override
	public String toString() {
		return "FavoriteDto [userid=" + userid + ", deal_no=" + deal_no + "]";
	}
	
	
}
