package com.myweb.www.handler;

import com.myweb.www.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class PagingHandler {

	//1~10 /11~20 / 21~30 ...
	//화면에 보여지는 시작 페이지네이션 번호
	private int startPage;
	private int endPage;
	private int realEndPage;
	private boolean prev, next; //이전, 다음의 존재 여부
	
	private int totalCount; //총글수
	private PagingVO pgvo;
	
	//현재 페이지 값 가져오는 용도	/	totalCount DB에서 조회 매개변수로 입력받기
	public PagingHandler(PagingVO pgvo, int totalCount) {
		//pgvo에서의.qty는 한페이지에 보이는 게시글의 수 10개 
		this.pgvo = pgvo;
		this.totalCount = totalCount;
	
		// 1~10 / 11~20 / 21~30      
		// 1~10중에서는 화면 전환 되어도 무조건 당시 보이는 끝 번호는 10이어야함
		// 1 2 3 4  ... 10 / 10 나머지를 올려(ceil) 1로 만듬 * 10 
		// 화면에 표시 되어야 하는 페이지의 개수 1 2 3 4 5 6 7 8 9 10
		// 화면에 표시 되어야 하는 페이지의 개수(화면에 10개 미만일때도 처리!)   (1 2 3 4 5 6 7) 7개   
		this.endPage = (int)Math.ceil(pgvo.getPageNo()/(double)pgvo.getQty())*pgvo.getQty();
		
		this.startPage = endPage - 9;
		
		//전체 글수에서  / 한페이지에 표시되는 게시글 수 pgvo.getQty() => 올림
		this.realEndPage = (int)Math.ceil(totalCount/(double)pgvo.getQty());
		if(realEndPage < endPage) {
			this.endPage = realEndPage;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
	}
	
}
