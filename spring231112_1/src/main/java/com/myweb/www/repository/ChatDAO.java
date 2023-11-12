package com.myweb.www.repository;


import java.util.List;

import com.myweb.www.domain.ChatDTO;

public interface ChatDAO {

	int submit(ChatDTO cdto);

	List<ChatDTO> selectAll();
}
