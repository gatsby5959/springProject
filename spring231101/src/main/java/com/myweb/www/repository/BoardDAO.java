package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> selectAll();

	BoardVO selectOne(long bno);

	int update(BoardVO bvo);

	int delete(long bno);

	long selectOneBno();

	int getTotalCount(PagingVO pagingVO);

	List<BoardVO> getList(PagingVO pagingVO);

	void readCount(long bno);

	int updateCommentCount();

	int updateFileCount();

	
}
