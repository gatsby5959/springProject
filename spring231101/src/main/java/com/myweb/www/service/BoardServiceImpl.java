package com.myweb.www.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.CommentDAO;
import com.myweb.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

	@Inject 
	private BoardDAO bdao;
	
	@Inject 
	private CommentDAO cdao;
	@Inject 
	private FileDAO fdao;
	
	@Autowired
	public BoardServiceImpl(BoardDAO bdao,FileDAO fdao,CommentDAO cdao) {
		this.bdao=bdao;
		this.cdao=cdao;
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
			long bno = bdao.selectOneBno(); //가장 마지막에 등록된 bno(방금 등록된)
		
			//모든 파일에 bno를 세팅
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				log.info("디비에 file인서트 직전");
				log.info(""+fvo);
				isUp*=fdao.insertFile(fvo);
			}

		}
		log.info("isUp는 "+isUp);
		return isUp;
	}

//	@Transactional
	@Override
	public List<BoardVO> getList(PagingVO pagingVO) {
		// TODO Auto-generated method stub
		int isOk = bdao.updateCommentCount();
		isOk = bdao.updateFileCount();
		return bdao.getList(pagingVO);
	}


	@Override
	public BoardDTO detail2(long bno) {
		bdao.readCount(bno);
		BoardDTO bdto = new BoardDTO();
//      DTO클래스는 아래와 같음
//		public class BoardDTO {
//			private BoardVO bvo;
//			private List<FileVO> flist;			
//		}
		
		bdto.setBvo(bdao.selectOne(bno));	//bdao bvo호출 select * from board where bno=#{bno}
		
		bdto.setFlist(fdao.getFileList(bno));	//bdao bvo호출

		return bdto;
	}


	@Override
	public int modifyFile(BoardDTO bdto) {
		log.info("보드서비스임플 modifyFile() ", bdto.getBvo().getBno());
//		bdao.minusReadCount(bdto.getBvo().getBno());
		log.info("bdto는 "+bdto);
		int isOk = bdao.update(bdto.getBvo()); //기존 bvo update
		if(bdto.getFlist()==null) {
			isOk *= 1;
		}else {
			if(isOk > 0   &&    bdto.getFlist().size()>0) {
				long bno = (long)bdto.getBvo().getBno();
				//모든 fvo에 bno set
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					isOk *= fdao.insertFile(fvo);
				}
			}
		}
		return isOk;
	}


	@Transactional
	@Override
	public int remove(long bno) {
		// TODO Auto-generated method stub
		//이하2개는 그냥 카스케이드로 함
//		 cdao.deleteCommentAll(bno);//게시글 지우기전 댓글 먼저 지우기
//		 fdao.deleteBnoFileAll(bno);//게시글 지우기파일도 먼저 지우기
		log.info("removed의isOk ");
		return  bdao.delete(bno)  ; //게시글 지우기
	}


	@Override
	public int getTotalCount(PagingVO pagingVO) {
		return bdao.getTotalCount(pagingVO);
	}
	
	@Transactional
	@Override
	public int removefile(String uuid) {
		// TODO Auto-generated method stub
		return fdao.removefile(uuid); //여기서 fdao로 방향을 틀어버림
	}


}
