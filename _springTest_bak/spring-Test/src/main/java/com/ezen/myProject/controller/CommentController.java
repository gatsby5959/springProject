package com.ezen.myProject.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Delete;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ezen.myProject.domain.CommentVO;
import com.ezen.myProject.domain.MemberVO;
import com.ezen.myProject.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/comment/*")
@RestController //컨트롤러 써도 상관은 없지만 레스트 떠보려고 레스트 쓰기~
public class CommentController {

	@Inject
	private CommentService csv;
	
	//값을 통으로 받아올꺼임
	
	//ResponseEntity 얘가 알아서 객체도 가져오고 할꺼임  (일반적으로 보통 사용함)
	//@RequestBody   얘가 알아서 request바디를 커먼트vo객체로 바꿔좀
	//body값 추출을 맞아서 해줄꺼임
	//지네릭 값으로 내보낼 값 하나 String 넣어줘야함
	//value = "mappling name" , consumes : 가져오는 데이터의 형태
	//produces : 보내는 데이터의 형식 / 나가는 데이터 타입 : MediaType.
	//json : application/json          text : text_plain
	@PostMapping(value="/post" , consumes = "application/json" , 
			produces=MediaType.TEXT_PLAIN_VALUE)   //포스트는 이것저것 많아서 이건 밸류 입니다 라고 고정해야함 (딴 컨트롤러의 딴건 그냥 생량한것뿐)
	public ResponseEntity<String> post(@RequestBody CommentVO cvo){
		//파라미터 값이 리퀘스트 안에 있음 그래서 위 에것 씀
		
		log.info(">>>>>cvo >>> "+cvo);
		//DB연결
		int isOk = csv.post(cvo);
		
		//231013추가시 코멘트 올리기
		int isOk2 = csv.commentcountup(cvo);
		
		
		//리턴스 response의 통신 상태를 같이 리턴
		return isOk > 0 ? //삼항연산자  스트링 보내려고 함
				new ResponseEntity<String>("1",HttpStatus.OK) //서버가 정상이라 스트링 1 보냄
					: new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR); //서버내부에서 에러가 나서 0보냄
	}  
	
	
	@GetMapping(value="/{bno}", produces = MediaType.APPLICATION_JSON_VALUE) //조회하면 겟
	public ResponseEntity<List<CommentVO>> spread(@PathVariable("bno") int bno){
		log.info(">>> comment List bno >>" + bno);
		//DB요청
		List<CommentVO> list = csv.getList(bno);
		return new ResponseEntity<List<CommentVO>>(list, HttpStatus.OK); //아닐떄는 안보냄
	}
	
	
	//클래스에 @RestController를 쓰면 옆의 어노테이션 사용가능  @PutMapping  @DeleteMapping
	
	
	
	//수정관련  //받는것  cno로 준걸 cno로 받음  // 받는것 
	@PutMapping(value="/{cno}", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> edit(@PathVariable("cno")int cno, 
			@RequestBody CommentVO cvo, HttpServletRequest req){ 
		//isok만 뜰껏임  //보내는?
		log.info("컨트롤러진입" +cno +" "+ cvo);
		HttpSession ses = req.getSession();
		MemberVO mvo = (MemberVO)ses.getAttribute("ses");
		int isOk= 0;
		if(ses != null) {
			mvo =(MemberVO)ses.getAttribute("ses");
			if(mvo.getId().equals(cvo.getWriter())) {
				isOk = csv.edit(cvo);
			}else {
				return new ResponseEntity<String>("2", HttpStatus.OK);
			}
		}
//		int isOk = csv.edit(cvo);
		return isOk > 0? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	} 

	
	
	//삭제관련  
//	@PutMapping(value="/delete{cno}", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	                                 //들어오는 데이터 없으니 conumes없어도 됨  나가는 값 있으니 produces는 있으니 적어돔
	@DeleteMapping(value="/{cno}/{writer}", produces = MediaType.TEXT_PLAIN_VALUE) 
	public ResponseEntity<String> delete(@PathVariable("cno")int cno,
			@PathVariable("writer")String writer ,HttpServletRequest req ){ 
		
		log.info("댓글 컨트롤러 진입 cno >> " +cno+" writer >"+writer );
		
		int isOk = 0;
		HttpSession ses = req.getSession();
		if(ses != null) {
			MemberVO mvo = (MemberVO)ses.getAttribute("ses");
			if(mvo.getId().equals(writer)) {
				isOk = csv.remove(cno);
			}else {
				return new ResponseEntity<String>("2",HttpStatus.OK);
			}
		}
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);  //아니면 스트링값을 0 주고 서버에러값 넣어줌
	}

}


















