package com.myweb.www;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.myweb.www.security.MemberVO;
import com.myweb.www.service.MemberService;

@RunWith(SpringJUnit4ClassRunner.class) // "SpringJUnit4ClassRunner.class"가 실행되게 하는 거
@ContextConfiguration(classes = { com.myweb.www.config.RootConfig.class })
public class MemberTest {
   @Inject
   private MemberService msv;

   @Test
   public void insertBoard() {
      for (int i = 0; i < 200; i++) {
         MemberVO mvo = new MemberVO();
         mvo.setEmail("testUserEmail" + i);
         mvo.setNickName("testUserNickName" + i);
         mvo.setPwd("testUserPwd" + i);
         msv.register(mvo);
      }
   }
}