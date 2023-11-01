package com.myweb.www.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.repository.MemberDAO;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class MemberServiceImpl implements MemberService {
	@Inject 
	private MemberDAO mdao;


	@Override
	public boolean updateLastLogin(String authEmail) {
		// TODO Auto-generated method stub
		return false;
	}

}
