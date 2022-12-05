package com.ssafy.vue.model.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.ssafy.vue.model.QnADto;
import com.ssafy.vue.model.QnAParameterDto;
import com.ssafy.vue.model.mapper.QnAMapper;

@Service
public class QnAServiceImpl implements QnAService {
	
	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<QnADto> listArticle(QnAParameterDto qnAParameterDto) throws Exception {
		int start = qnAParameterDto.getPg() == 0 ? 0 : (qnAParameterDto.getPg() - 1) * qnAParameterDto.getSpp();
		qnAParameterDto.setStart(start);
		return sqlSession.getMapper(QnAMapper.class).listArticle(qnAParameterDto);
	}

	@Override
	public QnADto getArticle(int board_no) throws Exception {
		return sqlSession.getMapper(QnAMapper.class).getArticle(board_no);
	}

	@Override
	public int getBoardTotalCount() throws Exception {
		int page = sqlSession.getMapper(QnAMapper.class).getBoardTotalCount();
		int result = 0;
		if(page==0) result=1;
		else {
			result = (page/20)+1;
			if(page%20==0) result -= 1;
		}
		return result;
	}

	@Override
	public boolean writeArticle(QnADto qnADto) throws Exception {
		System.out.println("QnA writeArticle Service 호출!!!!!!!");
		if(qnADto.getTitle() == null || qnADto.getContent() == null) {
			throw new Exception();
		}
		return sqlSession.getMapper(QnAMapper.class).writeArticle(qnADto) == 1;
	}

	@Override
	public boolean modifyArticle(QnADto qnADto) throws Exception {
		return sqlSession.getMapper(QnAMapper.class).modifyArticle(qnADto) == 1;
	}

	@Override
	public boolean deleteArticle(int board_no) throws Exception {
		return sqlSession.getMapper(QnAMapper.class).deleteArticle(board_no) == 1;
	}

	@Override
	public boolean writeAnswer(QnADto qnADto) throws Exception {
		return sqlSession.getMapper(QnAMapper.class).writeAnswer(qnADto) == 1;
	}

//	@Override
//	public QnADto getAnswer(int board_no) throws Exception {
//		return sqlSession.getMapper(QnAMapper.class).getArticle(board_no);
//	}
	
}
