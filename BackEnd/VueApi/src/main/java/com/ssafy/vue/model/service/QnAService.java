package com.ssafy.vue.model.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.ssafy.vue.model.QnADto;
import com.ssafy.vue.model.QnAParameterDto;

public interface QnAService {

	public List<QnADto> listArticle(QnAParameterDto qnAParameterDto) throws Exception;
	public QnADto getArticle(int board_no) throws Exception;
	public int getBoardTotalCount() throws Exception;
	public boolean writeArticle(QnADto qnADto) throws Exception;
	public boolean modifyArticle(QnADto qnADto) throws Exception;
	public boolean deleteArticle(int board_no) throws Exception;
//	public QnADto getAnswer(int board_no) throws Exception;
	public boolean writeAnswer(QnADto qnADto) throws Exception;

}
