package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

	@Inject
	private CommentDAO cdao;
	

	@Override
	public int post(CommentVO cvo) {
		log.info("여긴 CommentServiceImpl입니다. 댓글등록(post) check 2 "+ cvo); //여기서 2개 나눠줘야함
		//기존 게시글에 대한 내용을 DB에 저장    같은 내용을 저장
		int isOk = cdao.insert(cvo);

		return isOk;
	}


//	@Override
//	public List<CommentVO> getList(int bno) {
//		// TODO Auto-generated method stub
//		log.info("여긴 CommentServiceImpl입니다.(2) List<CommentVO> getList(int bno) "+ bno);
//		return cdao.getList(bno);
//	}


	@Override
	public int remove(int cno) {
		// TODO Auto-generated method stub
		log.info("여긴 CommentServiceImpl입니다.(2) int remove(int cno) "+ cno);
		return cdao.delete(cno);
	}


//	@Override
//	public int update(CommentVO cvo) {
//		log.info("여긴 댓글 update관련 CommentServiceImpl입니다.(2) int remove(int cvo) "+ cvo);
//		return cdao.update(cvo);
//	}


	@Override
	public int modify(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.update(cvo);
	}


	@Override
	public PagingHandler getList(int bno, PagingVO pgvo) {
		log.info("PagingHandler진입");
		// totalCount 구하기
		int totalCount = cdao.selectOneBnoTotalCount(bno);
		log.info("PagingHandlerint토탈카운트"+totalCount);
		// Comment List 찾아오기
		List<CommentVO> list = cdao.selectListPaging(bno,pgvo);
		log.info("PagingHandlerlist"+totalCount);
		// pagingHandler 값 완성해서 리턴
		PagingHandler ph = new PagingHandler(pgvo, totalCount, list);
		log.info("ph는"+ph);
		return ph;
	}

}
