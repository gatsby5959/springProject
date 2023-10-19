package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO bdao;


	@Override
	public int register(BoardVO bvo) {
		log.info("register check 2 "+ bvo); //여기서 2개 나눠줘야함
		//기존 게시글에 대한 내용을 DB에 저장 같은 내용을 저장
		int isOk = bdao.insert(bvo);

		return isOk;
	}


//	@Override
//	public List<BoardVO> getList() {
//		log.info("파라미터 없는! list check 2 ");
//		return bdao.getList();
//	}


	@Override
	public BoardVO getDetail(int bno) {
		// TODO Auto-generated method stub
		bdao.readCount(bno);
		return bdao.getDetail(bno);
	}


	@Override
	public int modify(BoardVO bvo) {
		// 수정할 때 들어가는 부당 read_count 2개 빼주기
//		bdao.readCount();
		return bdao.update(bvo);
	}


	@Override
	public int remove(int bno) {
		// TODO Auto-generated method stub
		return bdao.remove(bno);
	}


	@Override
	public List<BoardVO> getList(PagingVO pagingVO) {
		// TODO Auto-generated method stub
		log.info("서비스임플 "+pagingVO);
		return bdao.getList(pagingVO);
	}


//	@Override
//	public int getTotalCount() {
//		// TODO Auto-generated method stub
//		return bdao.getTotalCount();
//	}


	@Override
	public int getTotalCount(PagingVO pagingVO) {
		// TODO Auto-generated method stub
		return bdao.getTotalCount(pagingVO);
	}
	

}
