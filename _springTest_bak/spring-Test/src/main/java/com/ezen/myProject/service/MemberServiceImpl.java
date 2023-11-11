package com.ezen.myProject.service;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ezen.myProject.domain.MemberVO;
import com.ezen.myProject.repository.MemberDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	private MemberDAO mdao;

	//password Encode 하기 위한 security 디펜던시 추가.
	@Inject
	BCryptPasswordEncoder passwordEncoder; //빈xml추가생성하고 webxml에 설정추가하고 등 했음
	

	@Inject
	HttpServletRequest request;
	
	
	@Override
	public int signup(MemberVO mvo) {
		log.info("멤버 check 2 "+mvo);
		//아이디가 중복되면 회원가입 실패
		// 중복이 아니라면 아이디를 주고 DB에서 일치하는 유저를 달라고 요청함
		// 일치하는 유저가 없다면 가입1 유저가 없으면 실패0
		MemberVO temp = mdao.getUser(mvo.getId());
		if(temp != null) { //아이디가 중복되면 이쪽으로 들어와서 그냥 끝남
			return 0; 
		}
		
		//아이디가 중복되지 않아서 회원가입을 진행~!!
		//여기를 내가 함
		//password가 null이면, 혹은 값이 없다면... 가입 불가
		if(mvo.getId()==null || mvo.getId() == "") {
			return 0;
		}
		if(mvo.getPw()==null || mvo.getPw() == "") {
			return 0;
		}
		
		//회원가입 진행
		//암호화(encode) / matches(원래비번, 암호화된 비번) => true / false
		String pw = mvo.getPw();
		
		String encodePw = passwordEncoder.encode(pw); // 패스워드 암호화  //시큐리테어서 제공하는 패스워드 인코딩
		//멤버 객체에 암호화된 패스워드로 변경
		mvo.setPw(encodePw);
		
		//회원가입
		int isOk = mdao.insert(mvo);
		return isOk;
		
	}


	@Override
	public MemberVO isUser(MemberVO mvo) {
		// 로그인 유저 확인 메서드
		// 아이디를 주고, 해당 아이디의 객체 가져오기
		MemberVO temp = mdao.getUser(mvo.getId());	//회원가입할 때 사용했던 메서드 호출
		
		//해당 아이디의 객체가 없는 경우
		if(temp == null) {
			return null;
		}
		
		//passwordencoder.matches(원래 비번, 암호화 된 비번) : 매치가 되는지 체크
		//맞으면 true / 아니면 false
		if(passwordEncoder.matches(mvo.getPw(), temp.getPw())) {
			return temp;
		}else {
			return null;
		}
		
	}


	@Override
	public int modify(MemberVO mvo) {
		// mvo 객체에서 pw의 값이 있는지 체크
		// mvo의 pw각체가 없다면 기존 값으로 setting
		if(mvo.getPw() == null || mvo.getPw().length()==0)
		{
			MemberVO sesMVO = (MemberVO)request.getSession().getAttribute("ses");
			mvo.setPw(sesMVO.getPw());
		}else {
			String setpw = passwordEncoder.encode(mvo.getPw());
			mvo.setPw(setpw);
		}
		
		log.info(">>수정 후" + mvo);
		return mdao.update(mvo);
	}
}
