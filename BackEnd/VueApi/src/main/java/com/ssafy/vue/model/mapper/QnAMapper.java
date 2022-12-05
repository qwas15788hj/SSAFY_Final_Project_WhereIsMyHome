package com.ssafy.vue.model.mapper;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.http.HttpStatus;

import com.ssafy.vue.model.QnADto;
import com.ssafy.vue.model.QnAParameterDto;

@Mapper
public interface QnAMapper {
	
	public List<QnADto> listArticle(QnAParameterDto qnAParameterDto) throws SQLException;
	public QnADto getArticle(int board_no) throws SQLException;
	public int getBoardTotalCount() throws SQLException;
	public int writeArticle(QnADto qnADto) throws SQLException;
	public int modifyArticle(QnADto qnADto) throws SQLException;
	public int deleteArticle(int board_no) throws SQLException;
	public QnADto getAnswer(int board_no) throws SQLException;
	public int writeAnswer(QnADto qnADto) throws SQLException;

}
