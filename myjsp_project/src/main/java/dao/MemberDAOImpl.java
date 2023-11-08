package dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import orm.DatabaseBuilder;


public class MemberDAOImpl implements MemberDAO {

	private static final Logger log = LoggerFactory.getLogger(MemberDAOImpl.class);
	private SqlSession sql;
	private final String NS = "MemberMapper.";
	
	public MemberDAOImpl() {
		new DatabaseBuilder(); //디비연결
		sql = DatabaseBuilder.getFactory().openSession(); //디비연결하면 세션이 오픈?되는듯
	}
	
	
	@Override
	public int insert(MemberVO mvo) {
		log.info("join check3");
		int isOk = sql.insert(NS+"add", mvo); //select빼고 다 커밋 필요
		if(isOk>0){
			sql.commit();
		}
		return isOk;
	}


	@Override
	public MemberVO login(MemberVO mvo) {
		log.info("login check3");
		return sql.selectOne(NS+"login",mvo);
	}


	@Override
	public int lastLogin(String id) {
		log.info("lastLogin check3");
		int isOk = sql.update(NS+"last", id);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}


	@Override
	public List<MemberVO> getList() {
		log.info("getList check 3");
		return sql.selectList(NS+"list");
	}


	@Override
	public int update(MemberVO mvo) {
		log.info("update 3");
		int isOk = sql.update(NS+"update", mvo);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}


	@Override
	public int delete(String id) {
		log.info("delete check 3");
		int isOk = sql.update(NS+"delete",id);
		if(isOk>0) {
			sql.commit();
		}
		return isOk;
	}

}
