package com.myweb.www.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.myweb.www.repository.MemberDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthMemberService implements UserDetailsService {
	//231030
	@Inject
	private MemberDAO mdao;
	
	
	//로그인시 동작 (컨트롤로에서 정보 담고 오는듯) (securityConfig도 통해서)
	@Override                                //이멜 들어옴
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { //231030
		// username DB에 설정되어 있는 email인지를 체크해서
		// 인증하여 해당 객체를 AuthMember로 리턴
		MemberVO mvo = mdao.selectEmail(username);   //디비야 일단 유저네임=아이디 이거 있니? 하고 가져옴
		if(mvo == null) {
			throw new UsernameNotFoundException(username);//231030
		}
		
		mvo.setAuthVOList(mdao.selectAuths(username));
		log.info(">>>>>> userDetail >> " + mvo);
		
		
		return new AuthMember(mvo); //여기서 아이디 암호 함께만드는듯

	}

}
