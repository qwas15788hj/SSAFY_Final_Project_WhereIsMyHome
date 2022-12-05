package com.ssafy.vue.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.vue.model.BoardDto;
import com.ssafy.vue.model.QnADto;
import com.ssafy.vue.model.QnAParameterDto;
import com.ssafy.vue.model.service.QnAService;

@RestController
@RequestMapping("/qna")
public class QnAController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private QnAService qnaService;
	
	//QnA 목록 호출
	@GetMapping
	public ResponseEntity<List<QnADto>> listArticle(QnAParameterDto qnAParameterDto) throws Exception {
		logger.info("QnA listArticle - 호출");
		return new ResponseEntity<List<QnADto>>(qnaService.listArticle(qnAParameterDto), HttpStatus.OK);
	}
	
	//QnA 해당 글 호출
	@GetMapping("/{board_no}")
	public ResponseEntity<QnADto> getArticle(@PathVariable("board_no") int board_no) throws Exception {
		logger.info("QnA getArticle - 호출 : " + board_no);
		return new ResponseEntity<QnADto>(qnaService.getArticle(board_no), HttpStatus.OK);
	}
	
	//QnA 전체 조회
	@GetMapping("/count")
	public ResponseEntity<?> getBoardTotalCount() throws Exception {
		logger.info("QnA getBoardTotalCount - 호출");
		int result = 0;
		HttpStatus status = HttpStatus.ACCEPTED;
		result = qnaService.getBoardTotalCount();
		System.out.println("QnA 전체 수:>>>>" + result);
		return new ResponseEntity<Integer>(result, status);
	}
	
	//QnA 작성
	@PostMapping
	public ResponseEntity<?> writeArticle(@RequestBody QnADto qnADto) throws Exception {
		logger.info("QnA writeArticle - 호출");
		if (qnaService.writeArticle(qnADto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	//QnA 수정
	@PutMapping
	public ResponseEntity<?> modifyArticle(@RequestBody QnADto qnADto) throws Exception {
		logger.info("QnA modifyArticle - 호출 {}", qnADto);
		if(qnaService.modifyArticle(qnADto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
	//QnA 글 삭제
	@DeleteMapping("/{board_no}")
	public ResponseEntity<?> deleteArticle(@PathVariable("board_no") int board_no) throws Exception {
		logger.info("QnA deleteArticle - 호출");
		if (qnaService.deleteArticle(board_no)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
//	//QnA 답변 가져오기
//	@GetMapping("/{board_no}")
//	public ResponseEntity<?> getAnswer(@PathVariable("board_no") int board_no) throws Exception {
//		logger.info("QnA getAnswer - 호출");
//		return new ResponseEntity<QnADto>(qnaService.getAnswer(board_no), HttpStatus.OK);
//	}
	
	//QnA 답변 적기
	@PutMapping("/answer")
	public ResponseEntity<?> writeAnswer(@RequestBody QnADto qnADto) throws Exception {
		logger.info("QnA writeAnswer - 호출");
		if(qnaService.writeAnswer(qnADto)) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);			
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.OK);
	}
	
}
