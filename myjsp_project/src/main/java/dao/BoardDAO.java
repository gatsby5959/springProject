package dao;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	int getTotalCount(PagingVO pgvo);

	List<BoardVO> getPageList(PagingVO pgvo);

	int hitcount(int bno);

	BoardVO selectOne(int bno);

	int update(BoardVO bvo);

	String getFileName(int bno);

	int delete(int bno);

}
