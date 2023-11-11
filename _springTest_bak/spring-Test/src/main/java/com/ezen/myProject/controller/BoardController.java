package com.ezen.myProject.controller;

import java.io.File;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.myProject.domain.BoardDTO;
import com.ezen.myProject.domain.BoardVO;
import com.ezen.myProject.domain.FileVO;
import com.ezen.myProject.domain.MemberVO;
import com.ezen.myProject.domain.PagingVO;
import com.ezen.myProject.handler.FileHandler;
import com.ezen.myProject.handler.PagingHandler;
import com.ezen.myProject.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j //로그는 롬복에서 지원함
@RequestMapping("/board/*")
@Controller
public class BoardController {
	@Inject
	private FileHandler fhd;
	

	@Inject  //new로 객체 생성의미 (autowired써도 됨)  원래 제공하는 건 bean   인젝터나 오토와이더느는 사용자가 의도적으로 만든것 어자피 둘다 같은 의미임
	private BoardService bsv;
	
	@GetMapping("/register")
	public String boardRegisterGet() {
		return "/board/register";   //.jsp안써도 됨 서블릿컨텍스트.xml에서 설정잇음
	}
	
//	@PostMapping("/register")
//	public String register(BoardVO bvo) {
//		log.info(">>>>>>"+bvo.toString());
//		int isOk = bsv.register(bvo);
//		log.info(">>>> board register >>" + (isOk>0? "OK":"FAIL"));
//		return "redirect:/board/list";
//	}
	
	//required는 필수 값은 아니라는 뜻 널 들어와도 그냥 이해해라 라느 ㄴ뜻 예외 밸생 안함 //231012
	@PostMapping("/register")
	public String register(BoardVO bvo,
			@RequestParam(name="files", required = false)MultipartFile[] files) {// 첨부파일관련 추가 //register.jsp에 input에 name이 files있음
		log.info(">>>>>>"+bvo.toString());
		log.info(">>>> files >>"+ files);
		List<FileVO>flist = null;
		
		//files가 null일수 있음 첨부파일이 있는 경우면 fhd호출
		if(files[0].getSize()>0) {
			//첫번째 파일의 size가 0보다 크다면...
			//flist에 파일 객체 담기
			flist = fhd.uploadFiles(files);
		}else {
			log.info("file null");
		}
		
		BoardDTO bdto = new BoardDTO(bvo, flist);  //bvo랑 flist담기
		
		int isOk = bsv.register(bdto);
	
		log.info(">>>> board register >>" + (isOk>0? "OK":"FAIL"));
	
		return "redirect:/board/list";
	
	}
	
	
	@GetMapping("/list")
	public String list(Model model, PagingVO pgvo) {
		log.info(">>> PagingVO >>" + pgvo); //널 같은거 신경쓰지 않아도 알아서 할것임
		//getList(pgvo); //수정
//		List<BoardVO> list = bsv.getList();
		List<BoardVO> list = bsv.getList(pgvo); //리스트를 받아서 ph? 처리 함?
//		bsv.boardcountupdate(); //보드테이블 댓글갯수 파일갯수 업데이트용; //jgh231013
		log.info(">>> getList >>"+list);
		model.addAttribute("list", list); //셋어트리뷰트와 같은 역할 것
		int totalCount = bsv.getTotalCount(pgvo); //등록
		log.info("토탈카운트 "+totalCount);
		PagingHandler ph = new PagingHandler(pgvo, totalCount); //ph는 여기 잇는데???
		log.info("ph는 "+ph);
		model.addAttribute("ph",ph);
		return "/board/list"; //내가? 갈곳은 리스트입니당~
	}
	
	
	
	@GetMapping({"/detail","/modify"})// jsp에서 ? 전까지 잘라서 딱 맞는거 찾음
	public void detail(Model model, @RequestParam("bno") int bno) { // 이건 왔던 곳으로 다시 가라 라는 뜻
		log.info(">>>> detail bno >> " + bno);
		log.info("모디파이나 디테일이 겟을 탐");
		//파일내용도 포함해서 같이 가져가야 함.
//		BoardVO bvo = bsv.getDetail(bno);
		BoardDTO bdto = bsv.getDetailFile(bno);
		log.info("bdto는 " + bdto);
		model.addAttribute("boardDTO", bdto);
	}
	
	//수정할 때 들어가는 부당 read_count 2개 빼주기
	@PostMapping({"/modify"})
	public String modify(BoardVO bvo, RedirectAttributes reAttr,
			@RequestParam(name="files", required=false)MultipartFile[] files) {
		log.info("모디파이가 포스트를 탐");
		log.info(">>>> modify bvo >> " + bvo);
		
		
		
		List<FileVO> flist = null;
		if(files[0].getSize()>0) {
			//기존 파일은 이미 DB에 등록완료 삭제할 파일은 비동기로 이미 삭제 완료
			//새로 추가할 파일만 추가 
			//file이 존재함
			flist = fhd.uploadFiles(files); //fvo 구성 List로 리턴
//			bvo.setFileCount(flist.size());
		}
		log.info(">>>> flist.length >> " + files.length);
		BoardDTO bdto = new BoardDTO(bvo,flist);
		log.info("bdto = {}", bdto);
		int isOk = bsv.modifyFile(bdto);
//		int isOk = bsv.modify(bvo);
		
//		log.info(">>>> board modify >> "+ (isOk>0? "OK" : "FAIL"));
//		reAttr.addFlashAttribute(null, reAttr);
		return "redirect:/board/detail?bno="+bvo.getBno();
	}
	


	@GetMapping({"/remove"})
	public String remove(@RequestParam("bno")int bno, RedirectAttributes reAttr) {
		log.info(">>> remove bno >>" + bno);
		int isOk = bsv.remove(bno);
		reAttr.addFlashAttribute("isOk", isOk);
		return "redirect:/board/list";
	}
	
	@DeleteMapping(value="/file/{uuid}", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> removeFile(@PathVariable("uuid")String uuid ){
		
		log.info(">>> uuid >>" + uuid);
		int isOk = 0;
			isOk = bsv.removefile(uuid);
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK): 
			new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);  //아니면 스트링값을 0 주고 서버에러값 넣어줌
	
	}
	
}
