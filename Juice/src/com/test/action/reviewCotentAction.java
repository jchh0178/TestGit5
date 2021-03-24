package com.test.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.test.db.ReviewBean;
import com.test.db.ReviewDAO;

public class reviewCotentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : reviewCotentAction_excute 호출");
		
		// 
		//ReviewBean rb = new ReviewBean();
		//int re_num = rb.getRe_num();
		
		// 파일이 저장되는 위치
				/*ServletContext ctx = request.getServletContext();
				String realpath = ctx.getRealPath("/upload");
						
				System.out.println("M : realpath -"+realpath);
						
				// 파일의 용량
				int maxSize = 10 * 1024 * 1024; //10MB
						
				MultipartRequest multi =
						new MultipartRequest(
								request,
								realpath,
								maxSize,
								"UTF-8",
								new DefaultFileRenamePolicy()
						);
						
				System.out.println("M : 파일업로드 완료!");*/
				

		ReviewBean rb = (ReviewBean)request.getAttribute("rb");
		System.out.println(rb.getNick());
		// DAO 객체 생성 - getReview(num);
		ReviewDAO rdao = new ReviewDAO();
		
		// request 영역에 저장
		request.setAttribute("rb", rdao.getReview(rb.getNick()));
		
		//System.out.println(rdao.getReview(nick));
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./review/reviewContent.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
