package com.myweb.www.repository;

import java.util.List;

import com.myweb.www.domain.FileVO;

public interface FileDAO {

	int insertFile(FileVO fvo);

	List<FileVO> getFileList(long bno);

	int removefile(String uuid);

	List<FileVO> selectListAllFiles();
	

//	int deleteBnoFileAll(long bno);


}
