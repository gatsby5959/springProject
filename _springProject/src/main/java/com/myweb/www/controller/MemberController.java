package com.myweb.www.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public void list() {
			//231030전경환
		 
	
	}
	
}
