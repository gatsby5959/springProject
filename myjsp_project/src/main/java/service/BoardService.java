package service;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardService {

	int register(BoardVO bvo);

	int getTotalCount(PagingVO pgvo);

	List<BoardVO> getPageList(PagingVO pgvo);

	int hitcount(int bno);

	BoardVO detailview(int bno);

	BoardVO getDetail(int bno);

	int modifyForEdit(BoardVO bvo);

	String getFileName(int bno);

	int remove(int bno);

}
