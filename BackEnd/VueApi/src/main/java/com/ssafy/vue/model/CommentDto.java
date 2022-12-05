package com.ssafy.vue.model;

public class CommentDto {
	private String comment_no;
	private String aptCode;
	private String user_id;
	private String content;
	private String time;
	
	public String getComment_no() {
		return comment_no;
	}
	public void setComment_no(String comment_no) {
		this.comment_no = comment_no;
	}
	public String getAptcode() {
		return aptCode;
	}
	public void setAptcode(String aptCode) {
		this.aptCode = aptCode;
	}
	public String getUserid() {
		return user_id;
	}
	public void setUserid(String userid) {
		this.user_id = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "CommentDto [comment_no=" + comment_no + ", aptCode=" + aptCode + ", userid=" + user_id + ", content="
				+ content + ", time=" + time + "]";
	}
	
	
}
