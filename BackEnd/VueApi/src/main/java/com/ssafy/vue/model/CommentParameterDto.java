package com.ssafy.vue.model;

public class CommentParameterDto {

	private int pg; //현재 페이지 번호
	private int spp; //페이지당 글 갯수
	private int start; //페이지의 시작 글번호
	private String aptCode;
	
	public CommentParameterDto() {
		// TODO Auto-generated constructor stub
	}

	public CommentParameterDto(int pg, int spp, int start, String aptCode) {
		super();
		this.pg = pg;
		this.spp = spp;
		this.start = start;
		this.aptCode = aptCode;
	}

	public int getPg() {
		return pg;
	}

	public void setPg(int pg) {
		this.pg = pg;
	}

	public int getSpp() {
		return spp;
	}

	public void setSpp(int spp) {
		this.spp = spp;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public String getAptCode() {
		return aptCode;
	}

	public void setAptCode(String aptCode) {
		this.aptCode = aptCode;
	}

	@Override
	public String toString() {
		return "CommentParameterDto [pg=" + pg + ", spp=" + spp + ", start=" + start + ", aptCode=" + aptCode + "]";
	}
	
	

}

