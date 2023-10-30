package com.myweb.www.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.repository.MemberDAO;
import com.myweb.www.security.AuthMember;
import com.myweb.www.security.AuthVO;
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

	@Override
	public boolean updateLastLogin(String authEmail) {
		// TODO Auto-generated method stub
		return mdao.updateLastLogin(authEmail) > 0 ? true : false;
	}

	@Override
	public List<MemberVO> getList() {
		// TODO Auto-generated method stub

		return mdao.selectAll();
		
	}

	@Transactional
	@Override
	public AuthMember detail(String email) {
		// TODO Auto-generated method stub
		List<AuthVO> testAutVoList = new ArrayList<>();
		AuthVO initialAuth = new AuthVO("temptest.test.test", "tempadminauth"); // 임시이메일 주소와 권한을 설정
		testAutVoList.add(initialAuth);
		MemberVO mvo = new MemberVO(email, email, email, email, email, testAutVoList ); //여기까지 임시로 지나칠려는 의도
		AuthMember amdto = new AuthMember(mvo); //새로 넣어줄려는 의도
		amdto.setMvo(mdao.selectOne(email));	//bdao bvo호출 select * from board where bno=#{bno}
		
		return amdto;
	}
//	@Transactional
//	@Override
//	public BoardDTO detail2(long bno) {
//		bdao.readCount(bno);
//		BoardDTO bdto = new BoardDTO();
//		bdto.setBvo(bdao.selectOne(bno));	//bdao bvo호출 select * from board where bno=#{bno}
//		
//		bdto.setFlist(fdao.getFileList(bno));	//bdao bvo호출
//
////      DTO클래스는 아래와 같음
////		public class BoardDTO {
////			private BoardVO bvo;
////			private List<FileVO> flist;
////			
////		}
//
//		return bdto;
//	}
	
}
