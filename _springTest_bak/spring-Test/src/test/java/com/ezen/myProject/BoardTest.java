package com.ezen.myProject;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ezen.myProject.domain.BoardVO;
import com.ezen.myProject.repository.BoardDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j //사실 안 넣어도 됨
@RunWith(SpringJUnit4ClassRunner.class) //실행되게 하는 개체
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml") //디비정보가 있는 컨텍스트
public class BoardTest {
	
	@Inject //객체생성
	private BoardDAO  bdao;

	@Test
	public void insertBoard() {
		log.info(">>>> Test Insert In >> ");
		for(int i=0; i<300; i++) {
			BoardVO bvo = new BoardVO();
			bvo.setTitle("Test Title "+i );
			bvo.setWriter("Test Writer " );
			bvo.setContent("Test Content "+i);
			
			bdao.insert(bvo);  //bvo줄테니까 넣어줘~
		}
	}
}
