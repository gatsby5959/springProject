package com.myweb.www.controller;

import java.lang.ProcessBuilder.Redirect;
import java.security.Principal;
import java.util.List;
import java.util.Objects;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myweb.www.domain.BoardDTO;
import com.myweb.www.domain.BoardVO;
import com.myweb.www.domain.ChatDTO;
import com.myweb.www.domain.CommentVO;
import com.myweb.www.domain.FileVO;
import com.myweb.www.domain.PagingVO;

import com.myweb.www.handler.FileHandler;
import com.myweb.www.handler.PagingHandler;
import com.myweb.www.service.BoardService;
import com.myweb.www.service.ChatService;
import com.myweb.www.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/chaturl/*")
@Controller
public class ChatController {
// 폴더명 : board / mapping : board
	// mpapping => /board/register
	// 목적지 => /board/register

	@Inject
	private ChatService chatsv;
	
//	private BoardService bsv;
//
//	private FileHandler fh;
//	
//	private CommentService csv;
	
	@Autowired
	public ChatController(ChatService chatsv) {
		this.chatsv = chatsv;
//		this.fh = fh;
	}

	// 채팅글쓰기 jsp로 이동
	@GetMapping("/chat")
	public String register() {// jsp에서 온 매핑이랑 뷰로 들어가는 매핑이 같아서(이름이 같아서) void로 하면 왔던 곳으로 가라고 할 수 있음
		log.info("겟 /chat 진입");
		return "/chatfolder/chat"; // 이렇게 해도 됨(뷰로 들어가는 매핑)
	}
	
	
	
	


	@PostMapping(value ="/chat" , consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public ResponseEntity<String> write(@RequestBody ChatDTO chatdto) 
	{
		log.info(">>>>>>chatdto>> "+chatdto.toString());
		int isOk = -888;	
		if(chatdto.getFromID() == null || chatdto.getFromID().equals("") 
//				|| chatdto.getToID() == null ||	chatdto.getToID().equals("")
				||chatdto.getChatContent().equals("")){
			isOk = 0;
		}else {
			isOk = chatsv.submit(chatdto);
		}

		log.info(">>컨트롤러 chatsv submit >>" + (isOk>0? "OK":"FAIL"));
		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
						: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}

//	@PostMapping(value = "/post", consumes = "application/json", produces = MediaType.TEXT_PLAIN_VALUE)
//	public ResponseEntity<String> post(@RequestBody CommentVO cvo) {
//		int isOk = csv.addComment(cvo);
//
//		return isOk > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
//				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	
}
