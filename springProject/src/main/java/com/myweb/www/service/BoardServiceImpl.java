package com.myweb.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.FileDAO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

	private final BoardDAO bdao;
	
	private final FileDAO fdao;
	
	private final BoardDTO bdto;

//	@Override
//	public int insert(BoardVO bvo) {
//		log.info("insert check");
//		return bdao.insert(bvo);
//	}

	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		bdao.commentCount();
		bdao.FileCount();
		return bdao.getList(pgvo);
	}

	@Override
	public BoardVO getDetail(long bno) {
		return bdao.getDetail(bno);
	}
	@Override
	public BoardVO cntdetail(long bno) {
		bdao.readcount(bno);
		return bdao.cntdetail(bno);
	}

	@Override
	public int modify(BoardVO bvo) {
		return bdao.update(bvo);
	}

	@Override
	public int remove(long bno) {
		
		return bdao.remove(bno);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		
		return bdao.getTotalCount(pgvo);
	}

	@Override
	public int insert(BoardDTO bdto) {
		// bvo, flist 가져와서 각자 db에 저장
		int isUp = bdao.insert(bdto.getBvo()); // bno 등록
		
		if(bdto.getFlist() == null) {
			return isUp;
		}
		
		// bvo insert 후, 파일도 있다면...
		if(isUp > 0 && bdto.getFlist().size() > 0) {
			long bno = bdao.selectOneBno(); // 가장 마지막에 등록된 bno
			// 모든 파일에 bno set
			for(FileVO fvo : bdto.getFlist()) {
				fvo.setBno(bno);
				isUp *= fdao.insertFile(fvo);
			}
		}
		return isUp;
	}

	@Override
	public BoardDTO getDetail2(long bno) {
		// TODO Auto-generated method stub
		return bdao.getDetail2(bno);
	}

	
}
