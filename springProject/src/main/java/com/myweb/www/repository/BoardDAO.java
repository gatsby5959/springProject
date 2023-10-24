package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(long bno);
	BoardVO cntdetail(long bno);
	void readcount(long bno);

	int update(BoardVO bvo);

	int remove(long bno);

	int getTotalCount(PagingVO pgvo);

	void commentCount();

	long selectOneBno();

	void FileCount();

	BoardDTO getDetail2(long bno);

	



}
