package com.test.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class TestDAO {
	
	/* DB 테이블의 모든 처리
	 * 변수 : con(DB 커넥션) | sql(DB sql 구문)
	 * 	  | pstmt(sql구문을 연결하기위한 객체) | rs(sql 구문의 리턴 값)
	 * */
	
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
	
	/************** DB 처리에 대한 Method들 **************/
	//DB에서 정보를 가져오는 test method
	public TestBean getInfo(String nick){
		System.out.println("DAO : DB info ! nick >>> "+nick);
		
		TestBean tb = null;
		
		try {
			con = getCon();
			sql = "select * from users where nick=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nick);
			rs = pstmt.executeQuery();
			if(rs.next()){
				tb = new TestBean();
				
				tb.setNum(rs.getInt("user_num"));
				tb.setEmail(rs.getString("email"));
				tb.setNick(rs.getString("nick"));
			}
			
			System.out.println("DAO : DB info 저장 완료 ! tb >>> "+tb);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return tb;
	}
}
