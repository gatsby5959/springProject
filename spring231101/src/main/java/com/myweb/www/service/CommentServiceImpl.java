package com.myweb.www.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {
	
	private CommentDAO cdao;
	
	@Autowired
	public CommentServiceImpl(CommentDAO cdao) {
		this.cdao =cdao;
	}

	public int addComment(CommentVO cvo) {
		// TODO Auto-generated method stub
		log.info("addComment진입 "+ cvo);
		return cdao.insert(cvo);
	}

	@Transactional
	@Override
	public PagingHandler getList(long bno, PagingVO pgvo) {
		// TODO Auto-generated method stub
		// totalCount 구하기
		int totalCount = cdao.selectOneBnoTotalCount(bno);
		// Comment List 찾아오기
		List<CommentVO> list = cdao.selectListPaging(bno,pgvo);
		// pagingHandler 값 완성 후 리턴
		PagingHandler ph = new PagingHandler(pgvo, totalCount,list);
		
		return ph;
	}

	@Override
	public int modify(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.update(cvo);
	}

	@Override
	public int remove(long cno) {
		// TODO Auto-generated method stub
		return cdao.delete(cno);
	}

}