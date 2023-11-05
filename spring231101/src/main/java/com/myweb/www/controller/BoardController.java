package com.myweb.www.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.FileHandler;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.BoardService;
import com.myweb.www.service.CommentService;

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
	
	private CommentService csv;
	
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
		int totalCount = bsv.getTotalCount(pagingVO);
		PagingHandler ph = new PagingHandler(pagingVO, totalCount);
		model.addAttribute("ph", ph);
		log.info("totalCount는" + totalCount);
		log.info("ph는" + ph);
		log.info("겟메핑 /list 완전히 지나침");
		return "/board/list";
	}
	
	
	//전경환추가------------------------------------------------------231025_00:40S
		@GetMapping({"/detail","modify"})
		public void detail(Model model, @RequestParam("bno")long bno) {
			//GetMapping진입
			log.info("Model model은 " + model);
			log.info("@RequestParam(\"bno\")long bno는 "+ bno);

			BoardDTO bdto = bsv.detail2(bno);
			//bno를 받아서 dto로 바꾸고 보낸다.
			log.info("bto는 "+ bdto);
			model.addAttribute("bvo", bdto.getBvo()); 
			model.addAttribute("bdto", bdto); //이러면void니 detail.jsp로 모델에 쌓아서 날아가는듯 
		}
	//전경환추가------------------------------------------------------231025_00:40E	
	

		
		// 수정
		@PostMapping("/modify")
		public String modify(BoardVO bvo, RedirectAttributes reAttr,
				@RequestParam(name="files", required=false)MultipartFile[] files) {

			log.info( "모디파이가 포스트를 탐(modify.jsp에서 버튼 눌렸을것임)" ) ;
			log.info(">>>> modify bvo >> " + bvo);
			
			List<FileVO> flist = null;
			if(files[0].getSize() > 0) {
				//기존 파일은 이미 DB에 등록완료 삭제할 파일은 비동기로 이미 삭제 완료
				//새로 추가할 파일만 추가 
				//file이 존재함
				flist = fh.uploadFiles(files); //fvo 구성 List로 리턴
			}
			log.info(">>>> flist.length >> " + files.length);
			BoardDTO bdto = new BoardDTO(bvo,flist);
			log.info("bdto = {}", bdto);
			int isOk = bsv.modifyFile(bdto);
			return "redirect:/board/detail?bno="+bvo.getBno();
			
		}	
		
		@GetMapping("/remove")
		public String remove(@RequestParam("bno") long bno, RedirectAttributes red) {
			log.info("컨트롤러 /remove진입");
			int reisOk = bsv.remove(bno);
			red.addFlashAttribute("reisOk", reisOk);
			return "redirect:/board/list";
		}
		
		@DeleteMapping(value="/file/{uuid}", produces = MediaType.TEXT_PLAIN_VALUE)
		public ResponseEntity<String> removeFile(@PathVariable("uuid")String uuid ){
			log.info(">>> uuid >>" + uuid);
			int isOk = -999; //만약 -999가 나중에서 또 찍혀 있으면뭔가 잘 안된것...
				isOk = bsv.removefile(uuid);
				log.info("isOk는 "+ isOk);
			return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK): 
				new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);  //아니면 스트링값을 0 주고 서버에러값 넣어줌
		}
		
	
	
}
