package com.test.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.db.TestBean;
import com.test.db.TestDAO;

public class TestAction implements Action {
	/* controller test Action */

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : TestAction_execute() ȣ�� !");
		
		String nick = request.getParameter("nick");
		
		//DAO 객체 생성
		TestDAO tdao = new TestDAO();
		request.setAttribute("info", tdao.getInfo(nick));
		
		//페이지 이동(forward로 이동 - 주소 변경 X , 화면 변경 O)
		ActionForward forward = new ActionForward();
		forward.setPath("./test.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
