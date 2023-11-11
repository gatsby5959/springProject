package com.ezen.myProject.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.myProject.domain.BoardDTO;
import com.ezen.myProject.domain.BoardVO;
import com.ezen.myProject.domain.FileVO;
import com.ezen.myProject.domain.PagingVO;
import com.ezen.myProject.repository.BoardDAO;
import com.ezen.myProject.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {
	
	@Inject
	private BoardDAO bdao;
	
	@Inject
	private FileDAO fdao;

	@Override
	public int register(BoardDTO bdto) {
		log.info("register check 2 "+bdto); //여기서 2개 나눠줘야함
		//기존 게시글에 대한 내용을 DB에 저장 같은 내용을 저장
		int isOk = bdao.insert(bdto.getBvo());
		//------- 파일 저장 라인
		if(bdto.getFlist()==null) {
			//파일 값이 null이면 저장 없음.
			isOk *= 1; //성공한 것으로 침
		}else {
			//bvo의 값이 들어가고, 파일의 개수가 있다면...
			if(isOk > 0 && bdto.getFlist().size()>0) {
				//fvo의 bno는 아직 설정되기 전.
				//현재 시점에서 bno는 아직 결정되지 않음. ==> db insert ai의 의해 자동 생성
				int bno = bdao.selectBno(); //방금 저장된 bno가져오기
				//flit의 모든 fileVO에 방금 가져온 bno를 set
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno); // 같은 번호를 가짐
					log.info(">>> fvo >> " + fvo);
					//파일 저장
					isOk *= fdao.insertFile(fvo);
					
//					fdao.countupinsertFile(fvo); //231013			
				}
//				log.info("> bdto.getBvo()는 " + bdto.getBvo());
				log.info("> bdto.getBvo()에 없는! 방금생성된 bno는 따로 불러와서 이거임--->" + bno);
				log.info("> 파일의 갯수는 " + bdto.getFlist().size());
//				fdao.countupinsertFile3(bdto.getBvo(),bdto.getFlist().size());
				fdao.countupinsertFile4(bno,bdto.getFlist().size());
//				fdao.countupinsertFile2(fvo,);
			}
		}
		return isOk;
	}

//	@Override
//	public List<BoardVO> getList() {
//		log.info("파라미터 없는! list check 2 ");
//		return bdao.getList();
//	}
	
	@Override
	public List<BoardVO> getList(PagingVO pgvo) {
		log.info("list check 2 ");
		return bdao.getList(pgvo);
	}
	
	@Override
	public int getTotalCount(PagingVO pgvo) {
		log.info("service getTotalCount check2");
		return bdao.getTotalCount(pgvo);
	}

	@Override
	public BoardVO getDetail(int bno) {
		//read_count + 1
//		int cnt = 1;
		bdao.readCount(bno, 1);
		return bdao.getDetail(bno);
	}

	@Override
	public int modify(BoardVO bvo) {
		// 수정할 때 들어가는 부당 read_count 2개 빼주기
		
		bdao.readCount(bvo.getBno(), -2);
		return bdao.update(bvo);
	}

	@Override
	public int remove(int bno) {
		// TODO Auto-generated method stub
		return bdao.remove(bno);
	}

	@Override
	public BoardDTO getDetailFile(int bno) {
		// detail bvo, file 같이 가져오기.
		bdao.readCount(bno, 1); //리드카운트 올리기
		BoardDTO bdto = new BoardDTO();
		bdto.setBvo(getDetail(bno));	//bdao bvo호출
		bdto.setFlist(fdao.getFileList(bno));	//bdao bvo호출
		return bdto;
	}


	@Override
	public int removefile(String uuid) {
		return fdao.removefile(uuid);
	}

	@Override
	public int modifyFile(BoardDTO bdto) {
		// TODO Auto-generated method stub
		log.info("ttttttttttttt   ",bdto.getBvo().getBno());
		bdao.readCount(bdto.getBvo().getBno(), -2);
		log.info("bdto는 "+bdto);
		log.info("파일갯수"+bdto.getBvo().getFileCount());
		int isOk = bdao.update(bdto.getBvo()); //기존 bvo update
		if(bdto.getFlist()==null) {
			isOk *= 1;
		}else {
			if(isOk > 0   &&    bdto.getFlist().size()>0) {
				int bno = bdto.getBvo().getBno();
				//모든 fvo에 bno set
				for(FileVO fvo : bdto.getFlist()) {
					fvo.setBno(bno);
					isOk *= fdao.insertFile(fvo);
				}
			}
		}
		return isOk;
	}


//	@Override
//	public void boardcountupdate() {
//		// TODO Auto-generated method stub
//		 bdao.boardcountupdate();
//	}




}
