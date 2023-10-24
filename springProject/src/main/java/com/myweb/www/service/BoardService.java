package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

//	int insert(BoardVO bvo);

	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(long bno);
	BoardDTO getDetail2(long bno);
	BoardVO cntdetail(long bno);

	int modify(BoardVO bvo);

	int remove(long bno);

	int getTotalCount(PagingVO pgvo);

	int insert(BoardDTO boardDTO);

	


}
