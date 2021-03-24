package com.test.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.test.db.ReviewBean;
import com.test.db.ReviewDAO;

public class ReviewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("M : ReviewAction_excute 실행");
		
		// 파일 업로드
		// upload 가상폴더 생성
				
		// 파일이 저장되는 위치
		ServletContext ctx = request.getServletContext();
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
				
		System.out.println("M : 파일업로드 완료!");
		
		// 파라미터 저장
		ReviewBean rb = new ReviewBean();
		
		rb.setNick(multi.getParameter("nick"));
		rb.setRe_content(multi.getParameter("re_content"));
		
		String img = multi.getFilesystemName("file");
		rb.setFile(img);
		System.out.println("M : img "+ img);
		
		System.out.println("M : 전달된 파라미터값 저장완료");
		
		// DAO 객체 생성
		ReviewDAO rdao = new ReviewDAO();
		rdao.insertReview(rb);
		
		/***new****/
		rb = rdao.getReview(rb.getNick());
		/***new****/
		
		request.setAttribute("rb", rb);
		
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!"+rb);
		
		// 페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./review/reviewContent.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
