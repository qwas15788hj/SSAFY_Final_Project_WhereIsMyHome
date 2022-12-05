package com.ssafy.vue.model;

public class QnADto {
	
	private int board_no;
	private String userid;
	private String title;
	private String content;
	private String time;
	private String answer_content;
	private String answer_time;
	
	public QnADto() {
		
	}
	
	public QnADto(int board_no, String userid, String title, String content, String time, String answer_content,
			String answer_time) {
		super();
		this.board_no = board_no;
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.time = time;
		this.answer_content = answer_content;
		this.answer_time = answer_time;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getAnswer_content() {
		return answer_content;
	}

	public void setAnswer_content(String answer_content) {
		this.answer_content = answer_content;
	}

	public String getAnswer_time() {
		return answer_time;
	}

	public void setAnswer_time(String answer_time) {
		this.answer_time = answer_time;
	}

	@Override
	public String toString() {
		return "QnADto [board_no=" + board_no + ", userid=" + userid + ", title=" + title + ", content=" + content
				+ ", time=" + time + ", answer_content=" + answer_content + ", answer_time=" + answer_time + "]";
	}
	
	
}
