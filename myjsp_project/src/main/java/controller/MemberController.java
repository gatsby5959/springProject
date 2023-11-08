package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import service.MemberService;
import service.MemberServiceImpl;


@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    private RequestDispatcher rdp;
    private String destPage;
    private int isOk;
    
    private MemberService msv; //인터페이스 생성
    
    
    public MemberController() {
//        super();
        msv = new MemberServiceImpl();
    }


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("MemberController의 서비스함수 시작");
		request.setCharacterEncoding("utf-8"); 
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String uri = request.getRequestURI();
		log.info("uri는 " + uri);
		String path = uri.substring(uri.lastIndexOf("/")+1);
		log.info("path는 >>> "+ path);
		
		switch(path) {
		case "join":
			log.info("case \"join\" :  진입  (회원가입 페이지 열기직전)");
			destPage = "/member/join.jsp";
			break; //"case join 끝"
			
		case "register":
			try {
				String id = request.getParameter("id"); //인풋된것은 set된거고 여기서 get한거임
				String pwd = request.getParameter("pwd");
				String email = request.getParameter("email");
				int age = Integer.parseInt(request.getParameter("age"));
				
				MemberVO mvo = new MemberVO(id, pwd, email, age);
				log.info(">>>join>check1"+mvo);
				isOk = msv.register(mvo);
				log.info("check4"+(isOk>0?"OK":"Fail"));
				destPage = "/index.jsp";	
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;//"case register: 끝" 
		
		case "login":
			try {
				log.info("<form action=\"/mem/login 동작");
				log.info("컨트롤러 case login 진입");
				
				//인풋된것은 set된거고 여기서 get한거임 한번 보낼때만 데이터가 살아있는듯...
				String id = request.getParameter("id"); 
				String pwd = request.getParameter("pwd");
				MemberVO mvo = new MemberVO(id,pwd);
				
				//로그인관련 매퍼는 *로 모든정보를 가져옴. 나중에 수정할때 등 뽑아 쓸수 있음
				MemberVO loginmvo = msv.login(mvo);
				log.info("login check 1 >>>" + loginmvo);
				
				//가져온데이터를 세션에 저장, 세션 가지오기
				if(loginmvo  != null) {
					//연결된 세션이 있다면 기존의 (getSession() 함수자체가...)세션 가져오기, 없으면 새로 생성까지 됌...
					HttpSession hses = request.getSession();//세션에 넣으면 다른케이스에서도 잡아냄
					//다른케이스문의 jsp라도 이후?에서 ses하면 내가 로그인 한 세션의 정보(모든정보)이라 생각하면 됨 (키,밸류)
					hses.setAttribute("ses", loginmvo);
					hses.setMaxInactiveInterval(10*60);//로그인유지시간 10분	
				}else {//loginmvo가 null이면...
					request.setAttribute("msg_login", 0); //키msg_login 밸류0으로 리퀘스트에 셋해버림
				}
				destPage = "/index.jsp";
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;// 로그인 끝
			
		case "logout":
			try {
				//1234getSession()는 연결된 세션이 있다면 해당 세션을 가져오기 (+없으면 새로만드는 함수)
				HttpSession ses = request.getSession();//로그인한 세션
				//lastlogin 시간 기록, id가 필요
				//session에서 id가져오기
				log.info("키값이 ses인것 "+(MemberVO)ses.getAttribute("ses"));
				MemberVO mvo = (MemberVO)ses.getAttribute("ses");
				String id = mvo.getId();
				log.info("ses에서 id추출 >>>" + id);
				isOk = msv.lastLogin(id);//마지막 로그인 시간 업데이트
				ses.invalidate();//세션 끝음
				log.info("logout >> " + (isOk>0?"OK":"Fail") );
				destPage = "/index.jsp";
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			break; //logout 끝
			
		case "list":
			try {
				List<MemberVO> list = msv.getList();
				request.setAttribute("list", list);
				destPage="/member/list.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;//list끝
	
		case "modify":
			try {
				destPage="/member/modify.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;//modify끝
		
		case "update":
			//jsp파라미터 가져와서 mvo 객체 생성
			try {
				log.info("컨트롤러의 update케이스 진입");
				String id = request.getParameter("id"); //인풋태그에서 액션한것을 가져옴
				String pwd = request.getParameter("pwd");
				String email = request.getParameter("email");
				int age = Integer.parseInt(request.getParameter("age"));
				
				MemberVO mvo = new MemberVO(id, pwd, email, age);
				
				//msv에게 수정요청 -> mdao 수정요청 -> mapper수정요청
				log.info("mvo는 "+mvo);
				isOk=msv.update(mvo);
				log.info(isOk>0?"OK":"Fail");
				
				log.info("update check 4 " + (isOk>0? "OK":"FAIL") );
				
				//세션 끊고, index로 이동하려고 logout목적지 페이지개념
				destPage="logout";
			
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;//update 끝
			
		case "remove":
			try {
				//세션에 저장된 id만 가져오기
				//getSession()은 reqeust에 세션 있는지 확인하고 있으면 가져오고 없으면 생성할 것임
				HttpSession hses = request.getSession(); 
				//현재 로그인된 사용자정보를 가져와서 mvo에 넣어좀
				MemberVO mvo = (MemberVO)hses.getAttribute("ses");//키ses 인 벨류값 가져옴 로그인할 때 넣어둠
				String id = mvo.getId();
				log.info("삭제할 id추출 >> "+ id );
				isOk = msv.remove(id); //아직 세션 안끝음
				
				//세션끊고 index로 이동
				hses.invalidate(); //세션 끊음
				log.info("logout>> " + (isOk>0?"OK":"FAIL"));
				destPage = "/index.jsp";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
			
			
		}//switch문 끝
		log.info("목적지페이지(데스티네이션페이지) >> " +destPage);
    	rdp = request.getRequestDispatcher(destPage); //목적지 주소 태워서 
    	rdp.forward(request, response);               // 뭐 포워딩? 함
	}//service문끝


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("doGet 로 들어옴");
		service(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("doPost 로 들어옴");
		service(request, response);
	}

}
