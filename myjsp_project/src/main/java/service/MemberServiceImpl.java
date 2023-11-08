package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.MemberDAO;
import dao.MemberDAOImpl;
import domain.MemberVO;

public class MemberServiceImpl implements MemberService {
	
	private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
	private MemberDAO mdao;
	
	public MemberServiceImpl() {
		mdao = new MemberDAOImpl(); //매퍼 및 디비연결위함
	}
	
	
	@Override
	public int register(MemberVO mvo) {
		log.info("join check 2");
		return mdao.insert(mvo);
	}


	@Override
	public MemberVO login(MemberVO mvo) {
		log.info("login check 2");
		return mdao.login(mvo);
	}


	@Override
	public int lastLogin(String id) {
		log.info("lastLogin check 2");
		return mdao.lastLogin(id);
	}


	@Override
	public List<MemberVO> getList() {
		log.info("getList 2");
		return mdao.getList();
	}


	@Override
	public int update(MemberVO mvo) {
		log.info("update 2");
		return mdao.update(mvo);
	}


	@Override
	public int remove(String id) {
		log.info("remove 2");
		return mdao.delete(id);
	}

}
