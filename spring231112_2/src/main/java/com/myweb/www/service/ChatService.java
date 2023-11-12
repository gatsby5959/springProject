package com.myweb.www.service;


import java.util.List;

import com.myweb.www.domain.ChatDTO;

public interface ChatService {

	int submit(ChatDTO chatDTO);

	List<ChatDTO> getList();
}
