package com.ezen.myProject.handler;

import com.ezen.myProject.domain.PagingVO;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class PagingHandler {

	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	private int totalCount;
	private PagingVO pgvo; //여기 시작 페이지 넘버등이 잇음
	
	public PagingHandler(PagingVO pgvo, int totalCount) {
		this.pgvo = pgvo;
		this.totalCount = totalCount;
		// 1 2 3    ... 10 => 10  / pageNo 1~10 까지는 endPage가 10
		// 11 12 13 ... 20 => 20 / pageNo 11~20 까지는 endPage가 20
		this.endPage = (int)Math.ceil(pgvo.getPageNo()/(double)pgvo.getQty()) * pgvo.getQty();
		this.startPage = endPage - 9;
		
		int realEndPage = (int)Math.ceil(totalCount/(double)pgvo.getQty());
		
		//endPage는 10, 20, 30... 형식으로만 구성.
		//readEndPage는 실제의 마지막 페이지.  (28이라면 28에서 끝나야함)
		if(realEndPage <  this.endPage) {
			this.endPage = realEndPage;
		}
		
		//startPage : 1, 11, 21 ...
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEndPage;
	}

}
