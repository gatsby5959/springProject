package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

//	List<BoardVO> getList();

	BoardVO getDetail(int bno);

	int update(BoardVO bvo);

	int remove(int bno);

	void readCount(int bno);

	List<BoardVO> getList(PagingVO pagingVO);

	int getTotalCount(PagingVO pagingVO);

}
