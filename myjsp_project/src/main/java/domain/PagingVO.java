package domain;

public class PagingVO {

	private int pageNo; //current화면에 출력되는 페이지네이션 번호
	private int qty; //한페이지당 보여줄 게시글 수
	
	//검색 멤버변수
	private String type; //검색대상
	private String keyword; //검색어
	
	public PagingVO() { //페이지네이션을 클릭하기 전 기본 리스트 출력
		this(1,10);
	}
	public PagingVO(int pageNo, int qty) {//클릭하면 자동으로 담아주는 값
		this.pageNo=pageNo;
		this.qty=qty;
	}
	public int getPageStart() {
		return (pageNo-1)*qty; //DB에서 조회할 시작 페이지 구하기
	}
	//type이 여러개 들어올 때 값을 배열로 리턴
	public String[] getTypeToArray() { //이건 멀까?list.jsp에서 받기 시작해서 여기 type을 타고 배열로 변환되서 boardMapper.xml에서 typeToArray로 사용함  
		return (this.type==null)?new String[] {}:this.type.split("");
	}
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	@Override
	public String toString() {
		return "PagingVO [pageNo=" + pageNo + ", qty=" + qty + ", type=" + type + ", keyword=" + keyword + "]";
	}

	
	
}
