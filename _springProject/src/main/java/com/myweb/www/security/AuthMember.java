package com.myweb.www.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class AuthMember extends User { //생성자? 가져올떄 2번때 좀 짧은애 자동생성
	private static final long serialVersionUID = 1L;
	
	private MemberVO mvo; //나중에 아디 비번만 쓸꺼임(2개만...)
	
	public AuthMember(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}
	
	public AuthMember(MemberVO mvo) {
		super( mvo.getEmail(), mvo.getPwd(),
				mvo.getAuthVOList()
				.stream()
				.map(authVO-> new SimpleGrantedAuthority(authVO.getAuth()))
				.collect(Collectors.toList()) );
		this.mvo = mvo;
	}

}
