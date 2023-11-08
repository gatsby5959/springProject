package controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.BoardVO;
import domain.PagingVO;
import handler.FileHandler;
import handler.PagingHandler;
import net.coobird.thumbnailator.Thumbnails;
import service.BoardService;
import service.BoardServiceImpl;
import service.CommentService;


@WebServlet("/brd/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//로그 객체 선언
	private static final Logger log = LoggerFactory.getLogger(BoardController.class);
	//reqeustDespatcher객체
	private RequestDispatcher rdp;
	//service interface
	private BoardService bsv;
	private CommentService csv;

	private String destPage; //destPage : 목적지 주소 저장변수
	
	private int isOk;
	
	private String savePath; //파일경로를 저장할 변수
	
    public BoardController() {
//        super();
    	//bsv의 객체 연결
    	bsv = new BoardServiceImpl();
    }
    
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     log.info("BoardController의 서비스 함수 시작11111111111111111111111111111");
    //encoding 설정, contentType설정 요청경로파악
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    response.setContentType("utf-8/html; charset=UTF-8");
    
    //jsp에서 오는 요청주소
    String uri = request.getRequestURI(); // /brd/register
    String path = uri.substring(uri.lastIndexOf("/")+1);
    log.info("path>>>>> "+path);
    log.info("switch_case문 바로 위");
    switch(path) {
    case "register":
    	try {
			log.info("register진입");
			//목적지 주소 설정
			destPage = "/board/register.jsp";
		} catch (Exception e) {
			e.printStackTrace();
		}
    	break; //register끝
    	
    case "insert":
    	try {
			//이미지파일을 업로드할 물리적경로 설정(업로드할때 설정)
    		savePath = getServletContext().getRealPath("/_fileUpload");//상대경로가져오기 (없으면 안됨)
    		File fileDir = new File(savePath);
    		log.info("파일저장위치(savePath):"+savePath);
    		//D:\전경환\_myjsp_workspace_paging_com\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\myjsp_project\_fileUpload
    	
    		//파일 객체를 생성하기 위한 객체(파일에 대한 정보를 설정)
    		DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
    		fileItemFactory.setRepository(fileDir);//저장할 위치 set(file객체로 지정)(이미지셋은 이렇게...)
    		fileItemFactory.setSizeThreshold(5*1024*1024); //저장을 위한 임시메모리용량 5메가(모자르면 자동으로 늘려줌 바이트단위)
    		BoardVO bvo = new BoardVO();
    		
    		//multipart/form-data형식으로 넘어온 reqeust객체를 다루기 쉽게 변환해주는 객체형식으로 저장
    		ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory); //요기서 fileItemFactory 담아야함
    		
    		List<FileItem> itemList = fileUpload.parseRequest(request);//apach.common꺼 
    		//DB로 넘기기 위한 BoardVO객체로 변환 (경로와 파일이름만...) 이미지는 저장   이미지는 따로 파일 업로드 위치에 업로드 될것임
    	
    		//bvo로 변환 예정
    		//리스트에서 빼올것임
    		for(FileItem item : itemList) {
    			switch(item.getFieldName()) {
    			case "title":
    				bvo.setTitle(item.getString("utf-8"));//인코딩형식을 담아서 변환 한글떄문
    				break;
    			case "writer":
    				bvo.setWriter(item.getString("utf-8"));
    				break;
    			case "content":
    				bvo.setContent(item.getString("utf-8"));
    				break;
    			case "image_file":
    				//이미지 저장 처리 필요
    				//이미지가 필수X 없는 경우에도 처리
    				//이미지가 있는지 체크
    				if(item.getSize()>0) { //데이터 크기가 있다면 이미지 있음처리
    					String fileName = item.getName()	//가끔 경로를 포함해서들어오는 케이스때문에
    							.substring(item.getName().indexOf("/")+1);//파일이름만 분리
    				//시스템 현재시간_파일이름.jpg
    				fileName= System.currentTimeMillis()+"_"+fileName;
    				
    				//파일 객체 생성 : D:~/fileUpload/시간_cat2.jpg
    				//에러생기면 보면 아래 경로에 폴더 하나가 안보일 거임 그냥 윈도우 탐색기에서 만들어 버리기
    				//예전경로--> D:\전경환\_jsp_workspace_paging\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\jsp_project\_fileUpload\1695114911351_Aclass.jpg
    		
    				log.info("fileDir은 " + fileDir );
    				log.info("fileName은 "  + fileName );
    				File uploadFilePath = new File(fileDir+File.separator+fileName);
    				log.info("파일경로+이름 >>" + uploadFilePath);
    				
    				//저장
    				try {
						item.write(uploadFilePath); // 자바 객체를 디스크에 쓰기
						bvo.setImage_File(fileName); //디비에 텍스트 넣기?
						
						//썸네일 작업 : 트래픽 과다사용 방지
						Thumbnails.of(uploadFilePath).size(60,60)
								.toFile(new File(fileDir+File.separator+"_th_"+fileName));
					} catch (Exception e) {
						log.info(">>> file writer on disk 에러입니다");
						e.printStackTrace();
					}
    				}//이미지가 있으면 실행끝
    				break;//이미지파일 끝 (case "insert":안)
    				
    			}//리스트의 for문의 스위치문끝
    		}//for문긑
    		isOk = bsv.register(bvo);
    		log.info(">>>>insert >> " + (isOk>0?"OK":"Fail" ) );
    		destPage="pageList"; //pageList가 없으면 계속 서비스 무한루프돔
    		
    	} catch (Exception e) {
			e.printStackTrace();
		}
    	break;  //case "insert" : 끝
    	
    	
    case "pageList":
    	try {
    	//jsp에서 파라미터 받기
    	PagingVO pgvo = new PagingVO();
    	log.info("페이징전?");
    	if(request.getParameter("pageNo")!=null) {
    		log.info("request.getParameter(\"pageNo\")가 있는 경우 페이징 진입");
    		int pageNo = Integer.parseInt(request.getParameter("pageNo"));//대소문자 조심
    		int qty = Integer.parseInt(request.getParameter("qty"));
    		log.info("pageNo는 "+pageNo+" qty는 "+qty);
    		pgvo = new PagingVO(pageNo, qty); //값이 있으면
    	}
    	
    	//검색어 받기
    	pgvo.setType(request.getParameter("type"));//넘긴 키가  "type", "typeee132" 등으로 이름자체가 없어도 null로 됨 에러는 안남
    	pgvo.setKeyword(request.getParameter("keyword")); //기역우는 .jsp에서 type keyword라는 값으로 넘길 예정
    	log.info("type: "+ pgvo.getType() + "  keyword: "+ pgvo.getKeyword());
    	
    	//PagingVO totalCount
    	int totalCount = bsv.getTotalCount(pgvo); //DB에 전체 카운트 요청
    	log.info("전체게시글 수 " + totalCount );
    	//bsv pgvo주고 ,limit적용한 row10개 가져오기.
    	List<BoardVO> list = bsv.getPageList(pgvo);
    	log.info("pagestart "+pgvo.getPageNo());//DB에서 조회할 시작row인덱스 구하기
    	request.setAttribute("list", list); //다음페이지jsp페이지에서 list라고 쓰면 인식하기 시작
    	log.info("list를 request에 key를 list로 set해줌 "+ list);
    	//페이지 정보를 list.jsp로 보내기
    	log.info("ph = new PagingHandler("+pgvo+","+totalCount+") 직전임");
    	PagingHandler ph = new PagingHandler(pgvo, totalCount);
    	log.info("ph는 페이지 하단 번호를 관리하는 부분이고 값은 " + ph +" 입니다.");
    	request.setAttribute("ph", ph);
    	log.info("paging 끝~!!");
    	destPage="/board/list.jsp";
		} catch (Exception e) {
			log.info("pageList 에러");
			e.printStackTrace();
		}
    	break; //pageList끝
    
    case "count":
    	log.info("case count진입");
    	try {
			int bno = Integer.parseInt(request.getParameter("bno")); //list.jsp에서 bno를 겟으로 넘겨서 여기서 받음 //request셋개념(겟방식?)으로 넘긴듯 //<a href="/brd/count?bno=${bvo.bno}">${bvo.title}</a>
			log.info("hitcount직전");
			bsv.hitcount(bno);
			
			destPage="/brd/detail";
		} catch (Exception e) {
			log.info("count 에러");
			e.printStackTrace();
		}
    	break; //case count끝
    	
    case "detail":
    	log.info("case detail진입");
    	try {
    		int bno = Integer.parseInt(request.getParameter("bno")); //위 196줄에서 받은것을 다시 사용 가능한듯 리퀘스트영역에 셋하면 계속 유지되는 듯
    		BoardVO bvo = bsv.detailview(bno); //select * from board 실행목적
			log.info("case detail의 bvo : " + bvo);
			request.setAttribute("bvo", bvo); //request 키 벨류 로 저장함. 바로뒤에 detail.jsp에서 쓰려고...
			destPage = "/board/detail.jsp";
		
    	} catch (Exception e) {
			log.info("detail 에러");
			e.printStackTrace();
		}
    	break; // case "detail": 끝
    	
    case "modify":
    	try {
        	log.info("modify진입");
        	int bno = Integer.parseInt(request.getParameter("bno")); //위 196줄에서 받은것을 다시 사용 가능한듯 리퀘스트영역에 셋하면 계속 유지되는 듯
        	BoardVO bvo = bsv.getDetail(bno);
    		request.setAttribute("bvo", bvo);
    		destPage="/board/modify.jsp";
		} catch (Exception e) {
			log.info("modify 에러");
			e.printStackTrace();
		}
    	break;//modify끝
    	
    
    case "edit" :
    	log.info("edit진입");
		try {
			//파일 저장 경로 설정
			savePath = getServletContext().getRealPath("/_fileUpload"); // 이경로로 들어가게끔 해주세요

			File fileDir = new File(savePath);
			//디스크에 기록할 파일 정보를 setting 하는 객체
			DiskFileItemFactory fileItemFactory = new DiskFileItemFactory();
			fileItemFactory.setRepository(fileDir); //저장 경로 설정
			fileItemFactory.setSizeThreshold(2*1024*1024); //임시저장용량 2메가 //
			
			BoardVO bvo = new BoardVO();
			
			ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);
			log.info(">> update 준비 >");
			
			List<FileItem> itemList = fileUpload.parseRequest(request);
			
			String old_file = null; //수정하기전 원래 그림파일
			
			for(FileItem item : itemList) {
				switch(item.getFieldName()) {
				case "bno":
					bvo.setBno(Integer.parseInt(item.getString("utf-8")));
					break;
				case "title":
					bvo.setTitle(item.getString("utf-8"));
					break;
				case "content":
					bvo.setContent(item.getString("utf-8"));
					break;
				case "image_file":
					//수정 이전 파일
					old_file = item.getString("utf-8"); //여기 안타면 null
					break;
				case "new_file":
					//새로운 파일이 있는지 확인
					if(item.getSize()>0) {
						//기존 파일 삭제 (기존파일이 있을 경우우)
						if(old_file != null) {
							//기존 파일 삭제 (기존 파일이 있을 경우)
							FileHandler fileHandler = new FileHandler();
							isOk = fileHandler.deleteFile(old_file, savePath);
						}
						//new 파일의 경로와 파일명 (다시)생성
						String fileName = item.getName().substring(
								item.getName().lastIndexOf(File.separator)+1);	
						
						log.info("new_fileName"+fileName);
						
						//실제 저장될 파일 이름  시스템 현재 시간_파일이름.jpg
						fileName = System.currentTimeMillis()+"_"+fileName;
						
						//파일 객체 생성 : D:~
						File uploadFilePath = new File(fileDir+File.separator+fileName);
						//저장
						try {
							item.write(uploadFilePath);
							bvo.setImage_File(fileName);
							
							//썸내일 작업 : 트레픽 과다 사용 방지
							Thumbnails.of(uploadFilePath)
							.size(60, 60)
							.toFile(new File(fileDir+File.separator+"_th_"+fileName));
							
						} catch (Exception e) {
							log.info(">> new File save  error>");
							e.printStackTrace();
						}
					}else {//새로운 파일이 없다면... 기존 파일을 다시 담기
						bvo.setImage_File(old_file);
					}
					break;
				}//end ofswitch 
			}//end of for
			log.info("bvo는 " + bvo);
			isOk = bsv.modifyForEdit(bvo);
//			destPage = "detail?bno="+bno;
			destPage = "pageList"; // 중간에 대문자 조심
			//관련 사진 부분
//			D:\전경환\_jsp_workspace_paging\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\jsp_project\_fileUpload
			
									
//			230919주석처리이하
//			log.info("edit 진입");
//			int bno = Integer.parseInt(request.getParameter("bno")); //modify.jsp에서 누르는 순간 /brd/edit로 보냄
//			String title = request.getParameter("title");
//			String content = request.getParameter("content");
//			
//			BoardVO bvo = new BoardVO(bno,title,content);
//			log.info("bvo는 " + bvo);
//			isOk = bsv.modifyForEdit(bvo);
//			log.info((isOk>0)?"OK":"Fail");
//			destPage = "detail?bno="+bno;
			
		} catch (Exception e) {
			log.info("edit 에러");
			e.printStackTrace();
		}
		break; 	// case "edit" : 끝
		
    
		// 사진관련 부분 참고
//		D:\전경환\_jsp_workspace_paging\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\jsp_project\_fileUpload
	
    case "remove" :
		try {
			log.info("컨트롤러 리무브 들어옴");
			int bno = Integer.parseInt(request.getParameter("bno")); //197줄부터 이렇게 쉽게 볼러옴 목록페이지 에서 클릭시 부터 bno는 쉽게 쓸수 있음 
			//삭제할 bno의 image_file Name을 불러오기
			String fileName = bsv.getFileName(bno);  //파일 이름 불러오기 이 다음 path설정해야함...
			//savePath 생성
			savePath = getServletContext().getRealPath("/_fileUpload");
			//파일 핸들러에서 삭제 요청
			FileHandler filehandler = new FileHandler();
			isOk = filehandler.deleteFile(fileName, savePath); // 삭제해줄꺼임  인트 리턴해줄꺼임
			log.info(   isOk>0? "file remove Ok" : "Fail"   );
			isOk = bsv.remove(bno);
			log.info(   isOk>0? "OK" : "Fail"   );
			destPage="pageList";
		} catch (Exception e) {
			log.info("remove 에러");
			e.printStackTrace();
		}
		break;
    	
    	
    }//switch case문끝
    
    //.jsp파일이 아니라면> 다시 컨트롤러의 case문 탐색
    rdp = request.getRequestDispatcher(destPage);
    rdp.forward(request, response); //대충 위에 꺼랑 한세트

    log.info("서비스함수 끝222222222222222222222222222222222222222222");
    }//service함수 끝
    


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("보드컨트롤로의 두겟");
		service(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("보드컨트롤로의 두포스트");
		service(request, response);
	}

}
