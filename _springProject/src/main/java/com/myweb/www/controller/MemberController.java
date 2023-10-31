package com.myweb.www.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.PagingVo;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.security.AuthMember;
import com.myweb.www.security.MemberVO;
import com.myweb.www.service.MemberService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping("/member/**")
@Controller
public class MemberController {
    
	@Inject
	private BCryptPasswordEncoder bcEncoder;
	
	@Inject
	private MemberService msv;
	
	@GetMapping("/register")
	public void register() {
	}
	
	@PostMapping("/register")
	public String register(MemberVO mvo) {
		mvo.setPwd(bcEncoder.encode(mvo.getPwd())); //암호화해서 넣음
		log.info(">>>>register >> mvo{} >" +mvo);
		int isOk = msv.register(mvo);
		return "index";
	}
	
	
	@GetMapping("/login")
	public void login() {
	}
	
	@PostMapping("/login")
	public String loginPost(HttpServletRequest request, RedirectAttributes re) {
		//로그인 실패시 다시 로그인 페이지에 돌아와 오류 메시지 전송
		//로그인 정보 저장 //리다이렉트용RedirectStrategy //리퀘스트디스페쳐랑 비슷한?
		//다시 로그인 유도
		log.info(">>> errMsg"+request.getAttribute("errMsg")); //231030
		re.addAttribute("email",request.getAttribute("email"));
		re.addAttribute("errMsg", request.getAttribute("errMsg"));
		return "redirect:/member/login";  //이러면 다시 로그인.jsp 페이지를 가서 값을 줘야 하겠죠?
	}
	
	
	@GetMapping("/list")
	public String list(Model model) {
		//231030전경환
		model.addAttribute("list", msv.getList()); 
		log.info("모델은 "+model);
		return "/member/list";
	
	}

//이하 보드 컨트롤러 그냥 복붙해서 참고중인 상태임 231030
//리스트 출력(paging 추가)
//	@GetMapping("/list")
//	public String list(Model model, PagingVo pagingVO) {
//		log.info(">>>>>>pagingVO >>" + pagingVO);
//
//		//댓글 수 구하기는 글
//
//		// 이렇게 하면 service에서 return값 설정해주면 됨
//		model.addAttribute("list", bsv.getList(pagingVO));
//
//		/* 페이징 처리 */
//		// 총 페이지 갯수
//		int totalCount = bsv.getTotalCount(pagingVO);
//		PagingHandler ph = new PagingHandler(pagingVO, totalCount);
//		model.addAttribute("ph", ph);
//		log.info("겟메핑 /list 탐");
//		return "/board/list";
//	}
	

	@GetMapping({"/detail"})
	public void detail(Model model, @RequestParam("email")String email) {

		MemberVO mvo = msv.detail2(email);
		log.info("mvo는 "+ mvo);
		model.addAttribute("mvo",mvo);	//이러면void니 detail.jsp로 모델에 쌓아서 날아가는듯 
	}
	

	

	@GetMapping("/modify")
	public void modify(@RequestParam("email") String email, Model m) {
		log.info(">>>> 겟메핑 modify>>> email >>> " + email);
		log.info(">>>> modify>>> email >>> " + email);
		m.addAttribute("mvo", msv.detail2(email));
		log.info("m "+m);
		log.info("모디파이 겟메핑 끝");
	}
	
	@PostMapping({"/modify"})
	public void modify(MemberVO mvo, Model m , HttpServletRequest req, HttpServletResponse res) {
		log.info(">>>> 포스트 메핑 modify>>> mvo >>> " + mvo);
		log.info("2222222222222222222222");
		int isOk = 3;
//		log.info("mvo.getPwd().isEmpty()의 값 "+mvo.getPwd().isEmpty());
		if(mvo.getPwd()==null||mvo.getPwd().isEmpty()) {
			log.info("if(mvo.getPwd().isEmpty())"+"진입");
			isOk = msv.modifyPwdEmpty(mvo);
		}else {
			log.info("mvo.getPwd().isEmpty()의 else{}"+"진입");
			mvo.setPwd(bcEncoder.encode(mvo.getPwd()));
			isOk = msv.modify(mvo);
		}
		log.info("if문 지난 위치");
		logout(req,res);
		
		m.addAttribute("isOk",isOk);
		log.info(">>>> 포스트 메핑 modify 끝");	
	}
	
	
	
	
	@GetMapping("/remove")
	public String removeMember(@RequestParam("email") String email, Model m,
			HttpServletRequest req, HttpServletResponse res) {
		log.info(">>>modify >> email >>"+email);
		int isOk = msv.remove(email);
		logout(req,res);
		m.addAttribute("isOkDel", isOk);
		return "index";
		
	}
	
	
	private void logout(HttpServletRequest req, HttpServletResponse res) {
		Authentication authentication = SecurityContextHolder
				.getContext().getAuthentication();
		new SecurityContextLogoutHandler().logout(req,res,authentication);
				
	}
	
	
}
