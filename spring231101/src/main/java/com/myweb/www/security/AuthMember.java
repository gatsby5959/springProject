package com.myweb.www.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@ToString
public class AuthMember extends User {
	private static final long serialVersionUID = 1L;
	
	private MemberVO mvo; //나중에 아디 비번만 쓸꺼임(2개만...)
	
	public AuthMember(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		// TODO Auto-generated constructor stub
	}
	
	
	//231030
	public AuthMember(MemberVO mvo) { //여기서 아이디 암호를 다 일단 완성함
		super( mvo.getEmail(), mvo.getPwd(),
				mvo.getAuthVOList()
				.stream()
				.map(authVO-> new SimpleGrantedAuthority(authVO.getAuth()))
				.collect(Collectors.toList()) );
		this.mvo = mvo;
	}


}
