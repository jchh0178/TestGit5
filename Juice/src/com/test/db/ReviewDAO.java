package com.test.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ReviewDAO {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	//디비연결 - 커넥션풀 
	private Connection getCon() throws Exception {
		Context initCTX = new InitialContext();
		DataSource ds = (DataSource) initCTX.lookup("java:comp/env/jdbc/JuiceDB");
		con = ds.getConnection();

		System.out.println("DAO : DB 디비 연결 성공! -> " + con);

		return con;
	}

	//자원해제 
	public void closeDB() {
		try {
			if (rs != null)	rs.close();
			if (pstmt != null) pstmt.close();
			if (con != null) con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("DAO : DB 자원해제 !");
	}
	
	// insertReview(rb)
	public void insertReview(ReviewBean rb){
		int num = 0;
		
		try {
			// DB연결
			con = getCon();
			
			// sql & pstmt
			sql = "select max(re_num) from review";
			pstmt = con.prepareStatement(sql);
			
			// sql 실행
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				num = rs.getInt(1)+1;
			}
			System.out.println("DAO : 리뷰번호 생성 - "+num);
			
			// 상품등록
			sql ="insert into review(re_num,nick,file,re_content,re_date,re_star) "
					+ "values(?,?,?,?,now(),?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			pstmt.setString(2, rb.getNick());
			pstmt.setString(3, rb.getFile());
			pstmt.setString(4, rb.getRe_content());
			pstmt.setInt(5, 3);
			
			// sql실행
			pstmt.executeUpdate();
			
			System.out.println("DAO : 리뷰저장 완료");
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}
	// insertReview(rb)
	
	// getReview(num)
	public ReviewBean getReview(String nick){
		ReviewBean rb = null;
		System.out.println(nick);
		
		try {
			// DB연결
			con = getCon();
			
			// sql & pstmt
			sql = "select * from review where nick=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, nick);
			
			// sql 실행
			rs = pstmt.executeQuery();
			
			// 데이터 처리
			if(rs.next()){
				rb = new ReviewBean();
				
				rb.setFile(rs.getString("file"));
				rb.setNick(rs.getString("nick"));
				rb.setRe_content(rs.getString("re_content"));
				rb.setRe_date(rs.getDate("re_date"));
				rb.setRe_num(rs.getInt("re_num"));
				rb.setRe_star(rs.getInt("re_star"));
			}
			
			System.out.println("DAO : 리뷰정보 저장 완료"+rb);
			
		} catch (Exception e) {
			System.out.println("DAO : 리뷰정보 저장 실패!!!!");
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return rb;
	}
	// getReview(num)
	
}
