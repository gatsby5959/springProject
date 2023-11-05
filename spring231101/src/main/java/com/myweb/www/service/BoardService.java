package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
//import com.myweb.www.domain.BoardVO;
//import com.myweb.www.domain.FileVO;
//import com.myweb.www.domain.PagingVo;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

	int write(BoardDTO boardDTO);
	
//	List<BoardVO> getList();	
//	Object getList(PagingVO pagingVO);<---이렇게 보이는걸 아래처럼 바꿔야함
	List<BoardVO> getList(PagingVO pagingVO);
	
	BoardDTO detail2(long bno);
	
	int modifyFile(BoardDTO bdto);

	int remove(long bno);

	int getTotalCount(PagingVO pagingVO);

	int removefile(String uuid);

//	BoardVO SelectOneForModify(long bno);	
	
//	List<FileVO> getFileList(long bno);

//	int removefile(String uuid);
//


}
