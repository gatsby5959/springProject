package com.myweb.www.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/*")
@RequiredArgsConstructor
public class BoardController {
	// 폴더명 : board / mapping : board
	// mapping => /board/register
	// 목적지 => /board/register
	
	private final BoardService bsv;
	
	private final FileHandler fh;
	
	@GetMapping("/register")
	public void register() {
		log.info(">>> start ");
	}
	
	@PostMapping("/register")
	public String resisterPost(BoardVO bvo, @RequestParam(name="files", required = false)MultipartFile[] files) {
//		log.info("bvo : {}",bvo);
		List<FileVO> flist = null;
		// file upload handler 생성
		if(files[0].getSize() > 0) {
			flist = fh.uploadFiles(files);
		}
		
		int isOk = bsv.insert(new BoardDTO(bvo, flist));
		log.info(">>> board register "+(isOk > 0? "OK" : "Fail"));
		return "redirect:/board/list";
	}
	
	// paging 추가
	@GetMapping("/list")
	public String list(Model model, PagingVO pgvo) {
//		log.info("pgvo : {} ",pgvo);
		List<BoardVO> list = bsv.getList(pgvo);
		model.addAttribute("list",list);
		// 페이징 처리
		// 총 페이지 개수 totalCount
		int totalCount = bsv.getTotalCount(pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount);
		model.addAttribute("ph", ph);
		return "/board/list";
	}
	
//	@GetMapping({"/detail","modify"})
//	public void detail(Model model, @RequestParam("bno")long bno) {
//		BoardVO bvo = bsv.getDetail(bno);
//		//디티오로 받아서 디티오로 보낸다.
//		model.addAttribute("bvo", bvo);
//	}
	
	@GetMapping({"/detail","modify"})
	public void detail(Model model, @RequestParam("bno")long bno) {
		List<FileVO> flist = null;
		BoardDTO bdto = bsv.getDetail2(bno);
		//디티오로 받아서 디티오로 보낸다. 근데... bno만 받아서 dto로 보내는듯...
		model.addAttribute("bdto", bdto);
	}
	
	
	@GetMapping("/cntdetail")
	public String cntdetail(Model model, @RequestParam("bno")long bno) {
		BoardVO bvo = bsv.cntdetail(bno);
		model.addAttribute("bvo", bvo);
		return "/board/detail";
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO bvo, RedirectAttributes re) {
		log.info("bvo : {}",bvo);
		int isOk = bsv.modify(bvo);
		re.addAttribute("bno", bvo.getBno());
		re.addFlashAttribute("isOk", isOk);
		return "redirect:/board/detail";
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("bno") long bno) {
		int isOk = bsv.remove(bno);
		log.info(">>> board remove "+(isOk > 0? "OK" : "Fail"));
		return "redirect:/board/list";
	}
	
}
