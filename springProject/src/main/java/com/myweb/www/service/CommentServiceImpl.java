package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.repository.CommentDAO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
	
	private final CommentDAO cdao;

	@Override
	public int post(CommentVO cvo) {
		log.info("post check");
		return cdao.post(cvo);
	}

//	@Override
//	public List<CommentVO> getList(long bno) {
//		
//		return cdao.getList(bno);
//	}

	@Override
	public int remove(long cno) {
		
		return cdao.remove(cno);
	}

	@Override
	public int edit(CommentVO cvo) {
		
		return cdao.edit(cvo);
	}

	@Override
	public PagingHandler getList(long bno, PagingVO pgvo) {
		// totalCount 구하기
		int totalCount = cdao.selectOneBnoTotalCount(bno);
		// Comment List 찾아오기
		List<CommentVO> list = cdao.selectListPaging(bno, pgvo);
		PagingHandler ph = new PagingHandler(pgvo, totalCount, list);
		// PagingHandler 값 완성 리턴
		return ph;
	}

}
