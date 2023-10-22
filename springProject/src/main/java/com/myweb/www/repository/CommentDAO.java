package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.CommentVO;

public interface CommentDAO {

	int insert(CommentVO bvo);

	List<CommentVO> getList(int bno);

	int delete(int cno);

	
}
