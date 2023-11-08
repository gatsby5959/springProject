package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import orm.DatabaseBuilder;

public class CommentDAOImpl implements CommentDAO {

	private static final Logger log = LoggerFactory.getLogger(CommentDAOImpl.class);
	private SqlSession sql;
	private final String NS = "CommentMapper.";
	private int isOk;
	
	public CommentDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}
	
	@Override
	public int insert(CommentVO cvo) {
		isOk = sql.insert(NS+"add",cvo);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public List<CommentVO> getList(int bno) {
		return sql.selectList(NS+"list",bno);
	}

	@Override
	public int update(CommentVO cvo) {
		isOk = sql.update(NS+"up",cvo);
		if(isOk>0) sql.commit();
		return isOk;
	}

	@Override
	public int delete(int cno) {
		log.info("dao임플의  delete(int bno)진입");
		log.info("cno는 "+cno);
		int isOk = sql.delete(NS+"del",cno);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int commentCount(int bno) {
		// TODO Auto-generated method stub
		return sql.selectOne(NS+"cnt", bno);
	}

	@Override
	public int deleteAll(int bno) {
		isOk = sql.delete(NS+"delAll",bno);
		if(isOk>0) {
			sql.commit();
		};
		return isOk;
	}

	//이제 메서드 시작
	
	
	
	
	
}//public class CommentDAOImpl implements CommentDAO {끝







