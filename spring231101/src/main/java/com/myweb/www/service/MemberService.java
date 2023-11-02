package com.myweb.www.service;

import java.util.List;

import com.myweb.www.security.MemberVO;

public interface MemberService {

	boolean updateLastLogin(String authEmail);

	int register(MemberVO mvo);
	
	List<MemberVO> getList();
	
}
