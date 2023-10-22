package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.CommentVO;
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


	@Override
	public List<CommentVO> getList(int bno) {
		// TODO Auto-generated method stub
		log.info("여긴 CommentServiceImpl입니다.(2) List<CommentVO> getList(int bno) "+ bno);
		return cdao.getList(bno);
	}


	@Override
	public int remove(int cno) {
		// TODO Auto-generated method stub
		log.info("여긴 CommentServiceImpl입니다.(2) int remove(int cno) "+ cno);
		return cdao.delete(cno);
	}

}
