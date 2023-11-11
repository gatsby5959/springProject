package com.ezen.myProject.service;

import com.ezen.myProject.domain.MemberVO;

public interface MemberService {

	int signup(MemberVO mvo);

	MemberVO isUser(MemberVO mvo);

	int modify(MemberVO mvo);

}
