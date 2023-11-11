package com.ezen.myProject.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.myProject.domain.BoardVO;
import com.ezen.myProject.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo); // 함수명 자체가 아이디 임

	List<BoardVO> getList();
	List<BoardVO> getList(PagingVO pgvo);

	BoardVO getDetail(int bno);

	void readCount(@Param("bno")int bno, @Param("cnt")int cnt);

	int update(BoardVO bvo);

	int remove(int bno);

	int getTotalCount(PagingVO pgvo);

	int selectBno();

//	Object boardcountupdate();

	

}
