package com.ezen.myProject.repository;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ezen.myProject.domain.BoardDTO;
import com.ezen.myProject.domain.BoardVO;
import com.ezen.myProject.domain.FileVO;

public interface FileDAO {

	int insertFile(FileVO fvo);

	void countupinsertFile(FileVO fvo);


//	void countupinsertFile3( @Param("bvo")BoardVO bvo,  @Param("size")int size);
//	void countupinsertFile4(@Param("bno")int bno, @Param("size")int size);
	void countupinsertFile4(@Param("bno")int bno, @Param("size")int size);

	List<FileVO> getFileList(int bno);

	int removefile(String uuid);
	
}
