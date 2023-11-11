package com.myweb.www.service;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myweb.www.domain.ChatDTO;
import com.myweb.www.repository.BoardDAO;
import com.myweb.www.repository.ChatDAO;
import com.myweb.www.repository.CommentDAO;
import com.myweb.www.repository.FileDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ChatServiceImpl implements ChatService {

	
	private ChatDAO chatdao;
	
	@Autowired
	public ChatServiceImpl(ChatDAO chatdao) {
		this.chatdao=chatdao;
	}
	
	
	@Override
	public int submit(ChatDTO chatDTO) {
		// TODO Auto-generated method stub
		log.info("ChatServiceImpl의 chatDTO>>> " + chatDTO);
		int isUp = chatdao.submit(chatDTO);
		return isUp;
	}

}
