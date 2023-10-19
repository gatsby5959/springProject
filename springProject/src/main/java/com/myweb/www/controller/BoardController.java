package com.myweb.www.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;



@Slf4j //로그는 롬복에서 지원함   
@RequestMapping("/board/*") //들어오는것 잡고
@Controller
public class BoardController {
	//현재 내 보드 폴더 명이 board / mapping도 보드 라서
	// mapping 이 들어올 때  /board/register
	// 목적지 와 같으면 /board/register  같으면 void로 써서 보낼수도 잇음
	
	@Inject  //new로 객체 생성의미 (autowired써도 됨)  원래 제공하는 건 bean   인젝터나 오토와이더느는 사용자가 의도적으로 만든것 어자피 둘다 같은 의미임
	private BoardService bsv;
	
	
	@GetMapping("/register")
	public String boardRegisterGet() {
		return "/board/register";   //.jsp안써도 됨 서블릿컨텍스트.xml에서 설정잇음
	}

	
	//required는 필수 값은 아니라는 뜻 널 들어와도 그냥 이해해라 라느 ㄴ뜻 예외 밸생 안함 //231012
	@PostMapping("/register")
	public String register(BoardVO bvo) {	

		log.info("bvo는 "+bvo);
		int isOk = bsv.register(bvo);
		log.info(">>>> board register >>" + (isOk>0? "OK":"FAIL"));
		return "redirect:/board/list";
	}
	
	
//	@GetMapping("/list")
//	public String list(Model model) { //, PagingVO pgvo
//		List<BoardVO> list = bsv.getList();
//		log.info(">>> getList >>"+list);
//		model.addAttribute("list", list); //셋어트리뷰트와 같은 역할 것
//		return "/board/list"; 
//	}
	
	
	@GetMapping("/list")
	public String list(Model model, PagingVO pagingVO) {
		log.info(">>> PagingVO >>" + pagingVO); //널 같은거 신경쓰지 않아도 알아서 할것임
		List<BoardVO> list = bsv.getList(pagingVO); //리스트를 받아서 ph? 처리 함?
		model.addAttribute("list", list); //이게 jsp포 보내는 역할!! 셋어트리뷰트와 같은 역할 것 jsp에 싣어서 보내야하는데 안보내고 객체아 안싫었음 얘가 보내는 역할 jsp로
		//페이징 처리
		// 총 페이지 갯수 totalCount(검색포함)
		int totalCount = bsv.getTotalCount(pagingVO);
		log.info("토탈카운트 "+totalCount);
		PagingHandler ph = new PagingHandler(pagingVO, totalCount); //ph는 여기 잇는데???
		log.info("ph는 "+ph);
		model.addAttribute("ph",ph); //이게 jps로 넘겨주는데 일음을 "ph"로 넘겨줌
		return "/board/list"; //내가? 갈곳은 리스트입니당~
	}
	
	
	
	
	@GetMapping({"/detail","/modify"})// jsp에서 ? 전까지 잘라서 딱 맞는거 찾음
	public void detail(Model model, @RequestParam("bno") int bno) { // 이건 왔던 곳으로 다시 가라 라는 뜻
		log.info(">>>>디테일진입 detail bno >> " + bno);
		log.info("모디파이나 디테일이 겟을 탐");
		
		BoardVO bvo = bsv.getDetail(bno);

		// 원래는 파일내용도 포함해서 같이 가져가야 함.
		//		BoardDTO bdto = bsv.getDetailFile(bno);
		log.info("bvo는 " + bvo);
		model.addAttribute("bvo", bvo);
	}
	
	
	
	
	//수정페이지에서 수정할 때 들어가는 버튼          // 부당 read_count 2개 빼주기
	@PostMapping({"/modify"})
	public String modify(BoardVO bvo, RedirectAttributes reAttr,
			@RequestParam(name="files", required=false)MultipartFile[] files) {
		log.info("모디파이가 포스트를 탐");
		log.info(">>>> 포스트로 호출된 modify의 bvo >> " + bvo);
			
		int isOk = bsv.modify(bvo);

		return "redirect:/board/detail?bno="+bvo.getBno();
	}
	
	
	
	@GetMapping({"/remove"})
	public String remove(@RequestParam("bno")int bno, RedirectAttributes reAttr) {
		log.info(">>>컨트롤러 remove bno >>" + bno);
		int isOk = bsv.remove(bno);
//		reAttr.addFlashAttribute("isOk", isOk);
		return "redirect:/board/list";
	}
	
	
	
	

}
