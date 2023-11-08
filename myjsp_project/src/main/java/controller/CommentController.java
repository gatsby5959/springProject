package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.CommentVO;
import service.CommentService;
import service.CommentServiceImpl;


@WebServlet("/cmt/*")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(CommentController.class);
	//비동기방식 => 페이지 이동방식 X => //destPage X     RequestDispatcher X
	private CommentService csv;
	private int isOk;
	
    public CommentController() {
//        super();
    	csv = new CommentServiceImpl();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//response의 setContentType는 설정하지 않음 이거 쓰면 제이슨 파일은(이) 안들어옴

		String uri = request.getRequestURI();//
		//  /brd/detail?bno=1;  => 동기방식 주소체계
		//  동기방식에서는 get, post 2가지방식
		
		//  /cmt/list/10 , /cmt/post     /cmt/update => RestAPI방식(비동기에서 많이 사용)
		//  get은 리스트를 보여줄 때 사용, post는 등록할 때사용   , put=>update할때      delete -> delete할때 사용
		//  스프링 때는 위 내용을 맞추지만 지금은 그냥 겟과 포스트 방식만 쓸것임
		
		String pathUri = uri.substring("/cmt/".length());	//post, list/10 일수도 있고
		String path = pathUri;
		String pathVar = ""; //없으면 공백처리함
		if(pathUri.contains("/")) {	//패스값을 달고 왔다면..
			path = pathUri.substring(0,pathUri.lastIndexOf("/")); //list
			pathVar =pathUri.substring(pathUri.lastIndexOf("/")+1); //10
		}
		log.info(">>> uri > "+ uri);
		log.info(">>> pathUri > "+ pathUri);
		log.info(">>> path > "+ path);
		log.info(">>> pathVar > "+ pathVar);
		
		switch(path) {
		case "post": //(이게 결국 0 1을 다시 받고 리턴하는 듯함)
			try {
				//JSON 방식으로 화면에서 보낸 데이터를 받을 준비
				//String형태로 값을 받아 객체로 변환 
				//json-simple-1.1.1 라이브러리를 사용하여
				//Json 형태의 스트링을 객체 형태로 변환(해서 디비에 담을꺼에요)
				StringBuffer sb = new StringBuffer(); //String누적 객체
				//append
				String line = "";
				BufferedReader br = request.getReader(); //cmtData를 읽어오는 객체   board_detail.js에서 config객체에 body: JSON.stringify(cmtData)로 보냄
				while((line=br.readLine())!=null) { //널이 아닐때까지 읽어라
					sb.append(line);
				}
				log.info(">>>>sb : "+sb.toString());
				
				//객체로 변환
				JSONParser parser = new JSONParser();
				JSONObject jsonObj = (JSONObject)parser.parse(sb.toString());
				
				//CommentVO 형태로 변환
				int bno = Integer.parseInt(jsonObj.get("bno").toString());
				String writer = jsonObj.get("writer").toString();
				String content = jsonObj.get("content").toString();
				
				//csv DB로 저장
				CommentVO cvo = new CommentVO(bno, writer, content);
				log.info(">>> cvo " + cvo);
				isOk = csv.post(cvo);
				log.info("isOk >>>> " + isOk);
				log.info((isOk>0)?"OK":"Fail");
				
				//화면에 출력
				PrintWriter out = response.getWriter();
				out.print(isOk);
				
			} catch (Exception e) {
				log.info(">> Comment > post > error ");
				e.printStackTrace();
			}
			break;//case "post":문끝
			
			
		case "list"://(이게 결국 뭔가를 리턴? out.print하는 듯함)
			try {
				log.info("list 진입 ");
				int bno = Integer.parseInt(pathVar);
				List<CommentVO> list = csv.getList(bno);
				log.info(">>>> comment > List > " + list );
				//json 형태로 변환 => 화면에 전송
				JSONObject[] jsonObjArr = new JSONObject[list.size()];
				//'[{...},{...},{...},{...},{...},{...},{...}]'
				//0:{bno: 471, cno: 30, regdate: '2023-09-20 19:32:03', writer: '1234', content: '111'}
				//1:{bno: 471, cno: 31, regdate: '2023-09-20 19:32:19', writer: '1234', content: '123'}
				JSONArray jsonList = new JSONArray();
				for(int i = 0; i<list.size(); i++) {
					jsonObjArr[i] = new JSONObject(); // key:value
					jsonObjArr[i].put("cno",list.get(i).getCno());
					jsonObjArr[i].put("bno",list.get(i).getBno());
					jsonObjArr[i].put("writer",list.get(i).getWriter());
					jsonObjArr[i].put("content",list.get(i).getContent());
					jsonObjArr[i].put("regdate",list.get(i).getRegdate());
					//제이슨을
					//리스트에 넣어줌
				
					jsonList.add(jsonObjArr[i]);
				}
				
				String jsonData = jsonList.toJSONString(); //전송용
				
				//전송 객체에 싣고 화면으로 전송
				PrintWriter out = response.getWriter();
				out.print(jsonData);
				log.info("out.print(jsonData)는 " + jsonData);
				//[
				//{
				// bno:"1"
				// cno:"2"
				//},
				//{
				// bno:"1"
				// cno:"2"
				//},
				//{
				// bno:"1"
				// cno:"2"
				//}
				//]
				//뿌리는건 스프레드가 함
				
			} catch (Exception e) {
				log.info(">> Comment > list > error ");
				e.printStackTrace();
			}
			break;//case "list":
			
		case "modify":
			try {
				log.info("modify 진입 ");
				//post와동일부분S///////////////////////////////////////////////////////////////////////////
				StringBuffer sb = new StringBuffer(); //String누적 객체
				String line = "";
				BufferedReader br = request.getReader(); //cmtData를 읽어오는 객체   board_detail.js에서 config객체에 body: JSON.stringify(cmtData)로 보냄
				while((line=br.readLine())!=null) { //널이 아닐때까지 읽어라
					sb.append(line);
				}
				log.info(">>>>sb : "+sb.toString());
				
				//Json형태의 객체로 변환
				JSONParser parser = new JSONParser();
				JSONObject jsonobj = (JSONObject)parser.parse(sb.toString());
				//post와동일부분E//////////////////////////////////////////////////////////////////////////
				int cno = Integer.parseInt(jsonobj.get("cno").toString());
				String writer = jsonobj.get("writer").toString();
				String content = jsonobj.get("content").toString();
				
				CommentVO cvo = new CommentVO(cno, content);
				isOk = csv.modify(cvo);
				log.info("isOk는 "+ isOk );
				log.info(">>>>> modify >"+(isOk>0?"OK":"Fail"));
				
				PrintWriter out = response.getWriter(); //외부로 출력하려고? 쓰는듯
				out.print(isOk);
				log.info("isOk값은 "+isOk);
				
			} catch (Exception e) {
				log.info(">>>> Comment > modify > error");
				e.printStackTrace();
			}
			break; //modify끝
			
		
		case "remove":
			try {
				//만약 쿼리스트링으로 보내면
				//int cno = Integer.parseInt(request.getParameter("cno"));
				
				int cno = Integer.parseInt(pathVar);
				isOk = csv.remove(cno);
				log.info("리무부컨트롤러의 isOk는 "+ isOk);
				log.info(">>>>> remove >"+(isOk>0?"OK":"Fail"));
				
				PrintWriter out = response.getWriter();
				out.print(isOk);
				
			}catch(Exception e) {
				log.info(">>>> Comment > remove > error");
				e.printStackTrace();
			}
			break; //remove
			
			
		}//switch(path) { 끝
		
	}//service끝


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("코멘트 두겟");
		service(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("코멘트 두포스트");
		service(request,response);
	}

}
