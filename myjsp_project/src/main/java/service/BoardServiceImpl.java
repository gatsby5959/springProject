package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.BoardDAO;
import dao.BoardDAOImpl;
import domain.BoardVO;
import domain.PagingVO;

public class BoardServiceImpl implements BoardService {

	//로그 객체 선언
	private static final Logger log = LoggerFactory.getLogger(BoardServiceImpl.class);
	//DAO객체 생성
	private BoardDAO bdao; //interface
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl(); //클래스
	}
	
	@Override
	public int register(BoardVO bvo) {
		log.info("service register check2");
		return bdao.insert(bvo);
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		log.info("service getTotalCount check2");
		return bdao.getTotalCount(pgvo);
	}

	@Override
	public List<BoardVO> getPageList(PagingVO pgvo) {
		log.info("getPageList2");
		return bdao.getPageList(pgvo);
	}

	@Override
	public int hitcount(int bno) {
		log.info("hitcount(int bno)2");
		return bdao.hitcount(bno);
	}

	@Override
	public BoardVO detailview(int bno) {
		log.info("detailview2");
		return bdao.selectOne(bno); //어쩃든 BoardVO객체 1개만 받아오면 One을함 One에 많이 들은건 다른 문제?개념?
	}

	@Override
	public BoardVO getDetail(int bno) {
		log.info("getDetailForModi 서비스임플");
		return  bdao.selectOne(bno); // 이래뵈도 전부 가져옴 1개 로우의 모든 정보를 다 가져옴 (위에 거랑 같음) 
	}

	@Override
	public int modifyForEdit(BoardVO bvo) {
		log.info("modifyForEdit 서비스임플");
		return bdao.update(bvo);
	}

	@Override
	public String getFileName(int bno) {
		// TODO Auto-generated method stub
		return bdao.getFileName(bno);
	}

	@Override
	public int remove(int bno) {
		CommentServiceImpl csv = new CommentServiceImpl(); //보드서비스임플에서 코멘트서비스임플을 일단 생성
		log.info("서비스임플의 안의 remove(int bno)2");
		int cnt = csv.commentCount(bno);   //230920 2연속 삭제시 디비 트랜젠션 지랄나서 추가
		log.info("cnt는 "+cnt);
		if(cnt>0) {
			int isOk = csv.deleteAll(bno);
		}
		//댓글 미리 지우기 추가 csv임... 코맨트 관련 서비스 쪽임
		return bdao.delete(bno); // 이건 bdao임
	}

}
