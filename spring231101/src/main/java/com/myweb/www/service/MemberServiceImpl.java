package com.myweb.www.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.repository.MemberDAO;
import com.myweb.www.security.MemberVO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
	@Inject 
	private MemberDAO mdao;

	@Override
	public int register(MemberVO mvo) {
		// TODO Auto-generated method stub
		return 0;
	}
	




	@Override
	public boolean updateLastLogin(String authEmail) {
		// TODO Auto-generated method stub
		return mdao.updateLastLogin(authEmail) > 0 ? true : false;
	}


	@Override
	public List<MemberVO> getList() {
		// TODO Auto-generated method stub
		return null;
	}




}
