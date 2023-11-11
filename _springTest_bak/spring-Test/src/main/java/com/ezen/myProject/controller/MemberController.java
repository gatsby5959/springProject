package com.ezen.myProject.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.myProject.domain.MemberVO;
import com.ezen.myProject.service.MemberService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequestMapping("/member/*")
@Controller
public class MemberController {

	@Inject
	private MemberService msv;
	
	@GetMapping("/signup")
	public String signupGet() {
		return "/member/signup";
	}
	
	@PostMapping
	public String signupPost(MemberVO mvo) {
		log.info(">>> 회원가입 객체 >> " + mvo);
		//서비스에 msv에게 레지스터 해달라고 하고 디비 등록까지
//		return "index";
		
		int isOk = msv.signup(mvo);
		log.info(">>>> member register >>" + (isOk>0? "OK":"FAIL"));
//		return "redirect:/member/list";
		return "index";
	}
	
	@GetMapping("/login")
	public String loginGet() {
		return "/member/login";
	}

	@PostMapping("/login") //로그인 창에서 아이디 암호를 치고 버튼 클릭하면 동작
	public String loginPost(MemberVO mvo, HttpServletRequest request, Model m) { //이미 관련 클랙스 대기중 변수값만 맞으면 탁탁 찾아서 맞춰줌 스프링 똘똘
		log.info(">>> 로그인 사용자 >> " + mvo);
		log.info(">>> HttpServletRequest >> " + request);
		log.info(">>> Model >> " + m);
		//mvo 객체를 db에서 일치하는지 체크(를 해가지고 올께요)
		MemberVO loginmvo = msv.isUser(mvo);
		
		//DB에서 가져온 loginmvo가 null이 아니라면 세션에 저장
		if(loginmvo != null) {
			HttpSession ses = request.getSession(); //싱글톤
			ses.setAttribute("ses", loginmvo); // 세션 에 로그인 객체 저장
			ses.setMaxInactiveInterval(60*10); //로그인 유지 시간
		}else {
			m.addAttribute("msg_login",1);   //로그인 관련 정보를 전송하는 부분
		}
		return "index";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest request, Model m) {
		request.getSession().removeAttribute("ses"); // 세션 객체 삭제
		request.getSession().invalidate(); //세션 끊기
		m.addAttribute("msg_logout", 1); // msg_logout의  값을 1로 보냄
		return "index";
		
	}
	
	//인덱스 페이지에 세션에 정보를 보여주기 로그인 아이디 뒤에 이메일도 적기  
	//씨if통해서 로그인이 되어야 글쓰기 등등 보이기 로그아웃상태에서는 안보이기
	
	//본인아이디 작성자에 등록하나 해주시고요
	
	@GetMapping("/modify")
	public String modifyGet() {
		return "/member/modify";
	}
	
	@PostMapping("/modify")
	public String modifyPost(MemberVO mvo, RedirectAttributes re) {
		log.info(">>> modify mvo>>" +mvo);
		int isOk = msv.modify(mvo);
		log.info(">>>modify ? >>"+(isOk>0?"OK":"Fail"));
		re.addFlashAttribute("msg_modify", 2);//

		return "redirect:/member/logout";
	}
}
