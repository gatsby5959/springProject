package com.myweb.www.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.FileVO;
import com.myweb.www.repository.MemberDAO;
import com.myweb.www.security.MemberVO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
	@Inject 
	private MemberDAO mdao;

	@Transactional
	@Override
	public int register(MemberVO mvo) {
		//기존 메서드 활용
		int isOk = mdao.insertMember(mvo); //bno 등록
		return mdao.insertAuthInit(mvo.getEmail());
	}
}
