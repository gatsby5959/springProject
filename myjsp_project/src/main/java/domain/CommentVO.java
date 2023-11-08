package domain;

public class CommentVO {
//	CREATE TABLE `comment` (
//			  `cno` int NOT NULL AUTO_INCREMENT,
//			  `bno` int NOT NULL,
//			  `writer` varchar(200) NOT NULL DEFAULT 'unknown',
//			  `content` varchar(1000) DEFAULT NULL,
//			  `regdate` datetime DEFAULT CURRENT_TIMESTAMP,
//			  PRIMARY KEY (`cno`)
//			) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
	
	private int cno;
	private int bno;
	private String writer;
	private String content;
	private String regdate;
	

	public CommentVO() {}
	
	//삭제
	public CommentVO(int cno) {
		super();
		this.cno = cno;
	}

	//등록
	public CommentVO(int bno, String writer, String content) {
		super();
		this.bno = bno;
		this.writer = writer;
		this.content = content;
	}
	
	//수정용   (삭제도?...)
	public CommentVO(int cno, String content) {
		super();
		this.cno = cno;
		this.content = content;
	}

	//리스트볼때... (전체다 있음)
	public CommentVO(int cno, int bno, String writer, String content, String regdate) {
		super();
		this.cno = cno;
		this.bno = bno;
		this.writer = writer;
		this.content = content;
		this.regdate = regdate;
	}
	
	public int getCno() {
		return cno;
	}
	public void setCno(int cno) {
		this.cno = cno;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "CommentVO [cno=" + cno + ", bno=" + bno + ", writer=" + writer + ", content=" + content + ", regdate="
				+ regdate + "]";
	}
	
	
	
}//class 끝
