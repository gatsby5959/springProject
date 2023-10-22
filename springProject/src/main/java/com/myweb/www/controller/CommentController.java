package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j //로그는 롬복에서 지원함   
@RequestMapping("/comment/*") //들어오는것 잡고
@RestController
public class CommentController {
	
	@Inject  //new로 객체 생성의미 (autowired써도 됨)  원래 제공하는 건 bean   인젝터나 오토와이더느는 사용자가 의도적으로 만든것 어자피 둘다 같은 의미임
	private CommentService csv;
	
	//값을 통으로 받아올꺼임
	//ResponseEntity 얘가 알아서 객체도 가져오고 할꺼임  (일반적으로 보통 사용함)
	//@RequestBody   얘가 알아서 request바디를 커먼트vo객체로 바꿔좀
	//body값 추출을 맞아서 해줄꺼임
	//지네릭 값으로 내보낼 값 하나 String 넣어줘야함
	//value = "mappling name" , consumes : 가져오는 데이터의 형태
	//produces : (생산해서?)보내는 데이터의 형식 / 나가는 데이터 타입 : MediaType.
	//json : application/json          text : text_plain
	@PostMapping(value="/post" , consumes = "application/json" , produces = MediaType.TEXT_PLAIN_VALUE)   //포스트는 이것저것 많아서 이건 밸류 입니다 라고 고정해야함 (딴 컨트롤러의 딴건 그냥 생략한것뿐)
	public ResponseEntity<String> post(@RequestBody CommentVO cvo){
		//파라미터 값이 리퀘스트 안에 있음 그래서 위 에것 씀
		log.info("여긴 코맨트컨트롤러에요 시작함~");
		log.info(">>>>>cvo >>> "+cvo);
		//DB연결
		int isOk = csv.post(cvo);
		
		//리턴스 response의 통신 상태를 같이 리턴
		return isOk > 0 ? //삼항연산자  스트링 보내려고 함
				new ResponseEntity<String>("1",HttpStatus.OK) //서버가 정상이라 스트링 3 보냄 다시 js보내는 부분
					: new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR); //서버내부에서 에러가 나서 0보냄
	}
	
	
	
	
	
	@GetMapping(value="/{bno}", produces = MediaType.APPLICATION_JSON_VALUE) //조회하면 겟
	public ResponseEntity<List<CommentVO>> spread(@PathVariable("bno") int bno){
		log.info(">>> comment List bno >>" + bno);
		//DB요청
		List<CommentVO> list = csv.getList(bno);
		return new ResponseEntity<List<CommentVO>>(list, HttpStatus.OK); //아닐떄는 JS로 안보냄  되면 JS로 다시 보냄
	}
	
	
	
	
	
	
	
	//삭제관련  
//	@PutMapping(value="/delete{cno}", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	                                 //들어오는 데이터 없으니 conumes없어도 됨  나가는 값 있으니 produces는 있으니 적어돔
	@DeleteMapping(value="/{cno}/{writer}", produces = MediaType.TEXT_PLAIN_VALUE) 
	public ResponseEntity<String> delete(@PathVariable("cno")int cno,
					@PathVariable("writer")String writer ,HttpServletRequest req ){ 
		
		log.info("댓글 컨트롤러 진입 cno >> " +cno+" writer >"+writer );
		
		int isOk = 0;

				isOk = csv.remove(cno);
		log.info("댓글 컨트롤러dml isOk 값은 >> " + isOk );
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);  //아니면 스트링값을 0 주고 서버에러값 넣어줌
	}
	
	
	
	
	

	
}
