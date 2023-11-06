package com.myweb.www.repository;

import java.util.List;


import com.myweb.www.security.AuthVO;
import com.myweb.www.security.MemberVO;

public interface MemberDAO {

	int insertMember(MemberVO mvo);

	int insertAuthInit(String email);

	MemberVO selectEmail(String username);

	List<AuthVO> selectAuths(String username);

	int updateLastLogin(String authEmail);

	List<MemberVO> selectAll();

	MemberVO selectOne(String email);

	
	int modify(MemberVO mvo);
	
	int modifyPwdEmpty(MemberVO mvo);

	int remove(String email);

	void removeAuth(String email);

	MemberVO selectOne2(String email);

}
