package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {
	//로그 객체 선언
	private static final Logger log = LoggerFactory.getLogger(BoardDAOImpl.class);
	//DB연결
	private SqlSession sql;
	private final String NS = "BoardMapper."; //namespace.id
	
	public BoardDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}
			
	@Override
	public int insert(BoardVO bvo) {
		log.info("dao insert start");
		log.info("다오임플 bvo는" + bvo);
		int isOk = sql.insert(NS+"add",bvo);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int getTotalCount(PagingVO pgvo) {
		log.info("보드 다오의 임플의 PagingVO는 " + pgvo);
		return sql.selectOne(NS+"cnt",pgvo);
	}

	@Override
	public List<BoardVO> getPageList(PagingVO pgvo) {
		log.info("보드 다오 임플의 PagingVO는 " + pgvo);
		return sql.selectList(NS+"page",pgvo);
	}

	@Override
	public int hitcount(int bno) {
		log.info("다오 임플의 hitcount(int bno)");
		int isOk = sql.update(NS+"hit", bno);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public BoardVO selectOne(int bno) {
		log.info("dao selectOne 진입");
		return sql.selectOne(NS+"one",bno);
	}

	@Override
	public int update(BoardVO bvo) {
		log.info("dao임플의 update()진입");
		int isOk = sql.update(NS+"upd", bvo);
		log.info("dao임플의 isOk " + isOk);
		log.info("dao임플의 bvo "+ bvo);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public String getFileName(int bno) {
		// TODO Auto-generated method stub
		return sql.selectOne(NS+"fileName",bno);
	}

	@Override
	public int delete(int bno) {
		log.info("dao임플의 delete(int bno) 진입");
		int isOk = sql.delete(NS+"del",bno);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}
	
	

}
