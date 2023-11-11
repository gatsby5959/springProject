package com.myweb.www.repository;


import com.myweb.www.domain.ChatDTO;

public interface ChatDAO {

	int submit(ChatDTO cdto);
}
