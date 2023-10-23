package com.myweb.www.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.PagingVO;

public interface CommentDAO {

	int insert(CommentVO bvo);

	List<CommentVO> getList(int bno);

	int delete(int cno);

	int update(CommentVO cvo);

	int selectOneBnoTotalCount(int bno);

	List<CommentVO> selectListPaging(@Param("bno")int bno, @Param("pgvo")PagingVO pgvo);

	
}
