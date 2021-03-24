package com.test.db;

public class TestBean {
	//DTO
	
	private int num;
	private String email;
	private String nick;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	@Override
	public String toString() {
		return "TestBean [num=" + num + ", email=" + email + ", nick=" + nick + "]";
	}
	
}
