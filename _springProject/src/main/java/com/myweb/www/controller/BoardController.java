package com.myweb.www.controller;

import java.lang.ProcessBuilder.Redirect;
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
import com.myweb.www.domain.PagingVo;
import com.myweb.www.handler.FileHandler;
import com.myweb.www.handler.PagingHandler;
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
//	@PostMapping("/register")
//	public String write(BoardVO bvo,
//			@RequestParam(name="files", required = false)MultipartFile[] files) {
//		log.info(">>> bvo >>> files " + bvo +"  "+files);
//		List<FileVO> flist = null;
//		//file upload handler 생성
//		if(files[0].getSize()>0) {
//			flist = fh.uploadFiles(files);
//		}		
//		int isOk = bsv.write(new BoardDTO(bvo,flist));
//		return "redirect:/board/list"; // :컨트롤러에서 list로 getMapping되어있는 메서드로 이동
//	}
	
	
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
		
		int isOk = bsv.write(bdto);
	
		log.info(">>>> board register >>" + (isOk>0? "OK":"FAIL"));
	
		return "redirect:/board/list";
	}
	
	

//	// 리스트 출력
//	@GetMapping("/list")
//	public String list(Model model) {
//		List<BoardVO> list = bsv.getList();
//		model.addAttribute("list", list);
//
//		return "/board/list";
//	}
	// 리스트 출력(paging 추가)
	@GetMapping("/list")
	public String list(Model model, PagingVo pagingVO) {
		log.info(">>>>>>pagingVO >>" + pagingVO);

		// 이렇게 하면 service에서 return값 설정해주면 됨
		model.addAttribute("list", bsv.getList(pagingVO));

		/* 페이징 처리 */
		// 총 페이지 갯수
		int totalCount = bsv.getTotalCount(pagingVO);
		PagingHandler ph = new PagingHandler(pagingVO, totalCount);
		model.addAttribute("ph", ph);
		return "/board/list";
	}

	//전경환주석처리------------------------------------------------231025_00:40S
	// bvo 가지고 디테일 jsp 이동
//	@GetMapping("/detail")
//	public String detail(Model model, @RequestParam("bno") long bno) {
//
//		log.info("detail bno>>>>>>>>>>>>>>>" + bno);
//		BoardVO bvo = bsv.detail(bno);
//		log.info("detail bvo>>>>>>>>>>" + bvo);
//		List<FileVO> flist = bsv.getFileList(bno);	
//		log.info("flist>>"+flist);
//		BoardDTO bdto = new BoardDTO(bvo,flist);
//		log.info("bdto>> "+ bdto);
//	
//		
//		model.addAttribute("bdto", bdto);
//		return "/board/detail";
//	}
	//전경환주석처리------------------------------------------------231025_00:40E
//전경환추가------------------------------------------------------231025_00:40S
	@GetMapping({"/detail","modify"})
	public void detail(Model model, @RequestParam("bno")long bno) {
//		BoardVO bvo = bsv.detail(bno);
		BoardDTO bdto = bsv.detail2(bno);
		//bno를 받아서 dto로 바꾸고 보낸다.
//		log.info("bto는 "+ bto);
		model.addAttribute("bvo", bdto.getBvo()); 
		model.addAttribute("bdto", bdto); //이러면void니 detail.jsp로 모델에 쌓아서 날아가는듯 
	}
//전경환추가------------------------------------------------------231025_00:40E	
	
	
	// bno로 bvo찾은다음 model에 담아서 수정 페이지로 이동  //전경환 주석처리 위에 있는것 같음231025_00:40
//	@GetMapping("/modify")
//	public String modify(Model model, @RequestParam("bno") long bno) {
//		BoardVO bvo = bsv.SelectOneForModify(bno);
//		model.addAttribute("bvo", bvo);
//		log.info("/modify의 model>>> "+ model);
//		return "/board/modify";
//	} 231025_00:40

	// 수정
	@PostMapping("/modify")
	public String modify(BoardVO bvo, RedirectAttributes reAttr,
			@RequestParam(name="files", required=false)MultipartFile[] files) {
//		int isOk = bsv.modify(bvo);
//		
//		red.addAttribute("bno", bvo.getBno()); //Flash삭제 1번쓰고 사라지는 값같은게 Flash라 그냥 없애버림 (뭔가안되서..) //전경환 수정231025_00:40
////		//위와 같이 하면 return "redirect:/board/detail?bno="+bvo.getBno(); 하는 거와 같음
//		red.addFlashAttribute("isOk", isOk);
//		return "redirect:/board/detail";
		
		
		
		log.info("모디파이가 포스트를 탐");
		log.info(">>>> modify bvo >> " + bvo);
		
		
		
		List<FileVO> flist = null;
		if(files[0].getSize()>0) {
			//기존 파일은 이미 DB에 등록완료 삭제할 파일은 비동기로 이미 삭제 완료
			//새로 추가할 파일만 추가 
			//file이 존재함
			flist = fh.uploadFiles(files); //fvo 구성 List로 리턴
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

	// 삭제
	@GetMapping("/remove")
	public String remove(@RequestParam("bno") long bno, RedirectAttributes red) {
		int reisOk = bsv.remove(bno);
		red.addFlashAttribute("reisOk", reisOk);
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
