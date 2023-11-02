package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

	@Inject 
	private BoardDAO bdao;
	
//	@Inject 
//	private CommentDAO cdao;
	@Inject 
	private FileDAO fdao;
	
	@Autowired
	public BoardServiceImpl(BoardDAO bdao,FileDAO fdao) {//CommentDAO cdao,
		this.bdao=bdao;
//		this.cdao=cdao;
		this.fdao=fdao;
	
	}
	
	
	@Override
	public int write(BoardDTO bdto) {
		// TODO Auto-generated method stub
		log.info("BoardServiceImpl의  bdao.insert(bdto.getBvo());직전");
		int isUp = bdao.insert(bdto.getBvo()); //bno 등록
		
		if(bdto.getFlist()==null) {
			return isUp;
		}else if (isUp>0 && bdto.getFlist().size()>0) {//bvo가 잘 등록되었고, 등록할 파일이 존재 한다면
//			long bno = bdao.selectOneBno(); //가장 마지막에 등록된 bno(방금 등록된)
//		
//			//모든 파일에 bno를 세팅
//			for(FileVO fvo : bdto.getFlist()) {
//				fvo.setBno(bno);
//				log.info("디비에 file인서트 직전");
//				log.info(""+fvo);
//				isUp*=fdao.insertFile(fvo);
//			}
			log.info("첨부파일 is 99로 그냥 임시저장");
			isUp = 99;
		}
		log.info("isUp는 "+isUp);
		return isUp;
	}

//	@Transactional
	@Override
	public List<BoardVO> getList(PagingVO pagingVO) {
		// TODO Auto-generated method stub
//		int isOk = bdao.updateCommentCount();
//		isOk = bdao.updateFileCount();
		return bdao.selectAll();
	}

}
