package com.myweb.www.service;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVo;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService{
	private BoardDAO bdao;
	private CommentService csv;
	private FileDAO fdao;

	@Autowired
	public BoardServiceImpl(BoardDAO bdao,CommentService csv,FileDAO fdao) {
		this.bdao=bdao;
		this.csv=csv;
		this.fdao=fdao;
	
	}

//	@Override
//	public int write(BoardVO bvo) {
//		return bdao.insert(bvo);
//	}

	@Override
	public List<BoardVO> getList() {
		return bdao.selectAll();
	}



	@Override
	public int modify(BoardVO bvo) {
		return bdao.update(bvo);
	}

	@Override
	public BoardVO SelectOneForModify(long bno) {
		return bdao.SelectOneForModify(bno);
	}

	@Override
	public int remove(long bno) {		
		csv.deleteCommentAll(bno);//게시글 지우기전 댓글 먼저 지우기
		return bdao.delete(bno);
	}

	@Override
	public List<BoardVO> getList(PagingVo pagingVO) {
		
		return bdao.getList(pagingVO);
	}

	@Override
	public int getTotalCount(PagingVo pagingVO) {
		return bdao.getTotalCount(pagingVO);
	}

	@Override
	public int write(BoardDTO bdto) {
		/*bvo, flist 가져와서 각자 db에 저장*/
		
		//기존 메서드 활용
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
		
		return isUp;
	}

	@Override
	public List<FileVO> getFileList(long bno) {
		return fdao.getFileList(bno);
	}

	@Override
	public BoardVO detail(long bno) {
//		bdao.readCount(bno); //detail2에서 카운트 해줘서 여기서는 빼자
		return bdao.selectOne(bno);
	}
	@Override
	public BoardDTO detail2(long bno) {
		bdao.readCount(bno);
		BoardDTO bdto = new BoardDTO();
		bdto.setBvo(detail(bno));	//bdao bvo호출 select * from board where bno=#{bno}
		bdto.setFlist(fdao.getFileList(bno));	//bdao bvo호출

//      DTO클래스는 아래와 같음
//		public class BoardDTO {
//			private BoardVO bvo;
//			private List<FileVO> flist;
//			
//		}

		return bdto;
	}

	@Override
	public int removefile(String uuid) {
		// TODO Auto-generated method stub
		return fdao.removefile(uuid); //여기서 fdao로 방향을 틀어버림
	}

	@Override
	public int modifyFile(BoardDTO bdto) {
		
		log.info("ttttttttttttt   ",bdto.getBvo().getBno());
//		bdao.readCount(bdto.getBvo().getBno(), -2);
		log.info("bdto는 "+bdto);
		log.info("파일갯수"+bdto.getBvo().getFileCount());
		int isOk = bdao.update(bdto.getBvo()); //기존 bvo update
		if(bdto.getFlist()==null) {
			isOk *= 1;
		}else {
			if(isOk > 0   &&    bdto.getFlist().size()>0) {
				int bno = (int)bdto.getBvo().getBno();
				//모든 fvo에 bno set
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					isOk *= fdao.insertFile(fvo);
				}
			}
		}
		return isOk;
	}

}
