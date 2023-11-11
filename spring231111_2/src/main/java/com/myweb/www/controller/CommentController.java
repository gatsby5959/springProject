package com.myweb.www.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/comment/*")
@RestController
public class CommentController {
	private CommentService csv;

	@Autowired
	public CommentController(CommentService csv) {
		this.csv = csv;
	}

	// 댓글 등록
	@PostMapping(value = "/post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> post(@RequestBody CommentVO cvo) {
		int isOk = csv.addComment(cvo);

		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	// 댓글 리스트 출력
	@GetMapping(value = "/list/{bno}/{page}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PagingHandler> list(@PathVariable("bno") long bno,@PathVariable("page") int page) {
		log.info(">>>>>>> bno / page >>> "+bno +"  "+page);
		PagingVO pgvo = new PagingVO(page,5); //qty=5
		PagingHandler list = csv.getList(bno,pgvo);
		log.info(">>>>>>> ph List >>>>"+list);
		return new ResponseEntity<PagingHandler>(list, HttpStatus.OK);

	}

	// 댓글 삭제
//	@DeleteMapping(value = "/remove/{cno}}", produces = MediaType.TEXT_PLAIN_VALUE)
//	public ResponseEntity<String> remove(@PathVariable("cno") long cno) {
//		
//		int isOk = csv.remove(cno);
//
//		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
//				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
//
//	}
	
	@DeleteMapping(value="/remove/{cno}/{writer}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> erase(@PathVariable("cno")long cno, @PathVariable("writer")String writer, 
			Principal principal){
		log.info(">>>>>> comment delete >> writer>  "+writer);
		String username = principal.getName(); 
		log.info(">>>>>> comment delete >> username>  "+username);
		int isOk = 0;
		if(writer.equals(username)) {
			 isOk = csv.remove(cno);
		}
		return isOk > 0 ?  new ResponseEntity<String>("1", HttpStatus.OK) :
			new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	
	

	// 댓글 수정(모달창)
//	@PutMapping(value = "/modify", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
//	public ResponseEntity<String> modify(@RequestBody CommentVO cvo, Principal principal) {
//		log.info("프린시펄>>"+principal);
//		String username = principal.getName();
//		log.info("프린시펄에서 따온 username>>"+username);
//
//		int isOk = 0;
//		if(제이손에서 writer만 어떻게추출하고 싶은 부분.equals(username)) {
//		isOk = csv.modify(cvo);
//		}
//		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
//				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
//
//	}
	
	
	@PutMapping(value = "/modify", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> modify(@RequestBody CommentVO cvo, Principal principal) {
	    log.info("프린시펄>>" + principal);
	    String username = principal.getName();
	    log.info("프린시펄에서 따온 username>>" + username);

	    String writer = cvo.getWriter(); // CommentVO 객체에서 writer 추출
	    log.info("JSON에서 추출한 writer>> " + writer);

	    int isOk = 0;
	    if(writer.equals(username)) {
	        isOk = csv.modify(cvo);
	    }
	    return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
	            : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	

}
