package com.myweb.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.FileHandler;
import com.myweb.www.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/board/*")
@Controller
public class BoardController {
	// 폴더명 : board / mapping : board
		// mpapping => /board/register
		// 목적지 => /board/register
	
	private BoardService bsv;

	private FileHandler fh;
	
//	private CommentService csv;
	
	@Autowired
	public BoardController(BoardService bsv,FileHandler fh) {
		this.bsv = bsv;
		this.fh = fh;
	}

	// 글쓰기 jsp로 이동
	@GetMapping("/register")
	public String register() {// jsp에서 온 매핑이랑 뷰로 들어가는 매핑이 같아서(이름이 같아서) void로 하면 왔던 곳으로 가라고 할 수 있음
		return "/board/register"; // 이렇게 해도 됨(뷰로 들어가는 매핑)
	}
	
	// 글등록
	
	@PostMapping("/register")
	public String write(BoardVO bvo,
			@RequestParam(name="files", required = false)MultipartFile[] files) {// 첨부파일관련 추가 //register.jsp에 input에 name이 files있음
		log.info(">>>>>>bvo>> "+bvo.toString());
		log.info(">>>> files >>"+ files);
		List<FileVO>flist = null;
		
		//files가 null일수 있음 첨부파일이 있는 경우면 fhd호출
		if(files[0].getSize()>0) {
			//첫번째 파일의 size가 0보다 크다면...
			//flist에 파일 객체 담기
			log.info("uploadFiles 파일 시작 전 files "+files);
			flist = fh.uploadFiles(files);
		}else {
			log.info("file null");
		}
		
		BoardDTO bdto = new BoardDTO(bvo, flist);  //bvo랑 flist담기
		log.info("bdto는"+bdto);
		int isOk = bsv.write(bdto);
	
		log.info(">>>> board register >>" + (isOk>0? "OK":"FAIL"));
	
		return "redirect:/board/list";
	}
	
	
	// 리스트 출력 페이지은 안된 상태
//	@GetMapping("/list")
//	public String list(Model model) {
//		List<BoardVO> list = bsv.getList();
//		model.addAttribute("list", list);
//
//		return "/board/list";
//	}
	
	// 리스트 출력(paging 추가)
	@GetMapping("/list")
	public String list(Model model, PagingVO pagingVO) {
		log.info(">>>>>>pagingVO >>" + pagingVO);

		//댓글 수 구하기는 글
		
		// 이렇게 하면 service에서 return값 설정해주면 됨
		model.addAttribute("list", bsv.getList(pagingVO));

		/* 페이징 처리 */
		// 총 페이지 갯수            //이하 잠시 주석처리
//		int totalCount = bsv.getTotalCount(pagingVO);
//		PagingHandler ph = new PagingHandler(pagingVO, totalCount);
//		model.addAttribute("ph", ph);
		log.info("겟메핑 /list 완전히 지나침");
		return "/board/list";
	}
	

	
	
	
}
