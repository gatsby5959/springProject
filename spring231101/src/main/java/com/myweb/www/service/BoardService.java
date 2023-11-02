package com.myweb.www.service;

import java.util.List;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
//import com.myweb.www.domain.BoardVO;
//import com.myweb.www.domain.FileVO;
//import com.myweb.www.domain.PagingVo;
import com.myweb.www.domain.PagingVO;

public interface BoardService {

//	List<BoardVO> getList();

//	BoardVO SelectOneForModify(long bno);
//
//	int remove(long bno);
//
//	Object getList(PagingVO pagingVO);<---이렇게 보이는걸 아래처럼 바꿔야함
	List<BoardVO> getList(PagingVO pagingVO);
//
//	int getTotalCount(PagingVo pagingVO);
//
	int write(BoardDTO boardDTO);
//
//	List<FileVO> getFileList(long bno);
//
//	BoardDTO detail2(long bno);
//
//	int removefile(String uuid);
//
//	int modifyFile(BoardDTO bdto);


}
