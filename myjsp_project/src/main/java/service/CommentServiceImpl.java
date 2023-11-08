package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dao.CommentDAO;
import dao.CommentDAOImpl;
import domain.CommentVO;

public class CommentServiceImpl implements CommentService {
	private static final Logger log = LoggerFactory.getLogger(CommentServiceImpl.class);
	private CommentDAO cdao;
	
	public CommentServiceImpl() {
		cdao = new CommentDAOImpl();
	}
	
	@Override
	public int post(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.insert(cvo);
	}

	@Override
	public List<CommentVO> getList(int bno) {
		// TODO Auto-generated method stub
		return cdao.getList(bno);
	}

	@Override
	public int modify(CommentVO cvo) {
		// TODO Auto-generated method stub
		return cdao.update(cvo);
	}

	@Override
	public int remove(int cno) {
		// TODO Auto-generated method stub
		return cdao.delete(cno);
	}

	public int commentCount(int bno) {
		// BoardServiceImple에서 보내온 댓글 개수 메서드
		return cdao.commentCount(bno);
	}

	public int deleteAll(int bno) {
		log.info("CommentServiceImpl deleteAll입니당");
		return cdao.deleteAll(bno);
	}

}
