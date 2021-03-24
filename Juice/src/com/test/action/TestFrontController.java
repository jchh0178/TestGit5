package com.test.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("*.test") //url 패턴(가상주소의 형태)
public class TestFrontController extends HttpServlet {
	
	/* 컨트롤러 : 모델과 뷰의 연결지점 => 서블릿 */

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TestFrontController-doProcess()");
		
		System.out.println("\n\n 1. 가상주소 계산");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();

		String command = requestURI.substring(contextPath.length());
		System.out.println("command : " + command);
		System.out.println("\n 1. 가상주소 계산");

		
		System.out.println("\n 2. 가상주소 매핑");
		Action action = null;
		ActionForward forward = null;
		
		//("/이동 할 가상주소")
		if(command.equals("/reviewWrite.test")){
			forward = new ActionForward();
			forward.setPath("./review/reviewWriteForm.jsp");
			forward.setRedirect(false);
			
		}else if(command.equals("/reviewInsert.test")){
			System.out.println("/reviewInsert.test 호출");
			action = new ReviewAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/reviewContent.test")){
			System.out.println("./reviewContent.test 호출");
			action = new reviewCotentAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		System.out.println("\n 2. 가상주소 매핑");
		

		System.out.println("\n 3. 페이지 이동");
		if (forward != null) {
			if (forward.isRedirect()) {		//이동정보가 있을경우 페이지 이동
				//true
				response.sendRedirect(forward.getPath());
			} else {
				//false
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
		System.out.println("\n 3. �페이지 이동");
	}
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	    System.out.println("TestFrontController-doGet()");
	    doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("TestFrontController-doPost()");
		doProcess(request, response);
		
	}
}
