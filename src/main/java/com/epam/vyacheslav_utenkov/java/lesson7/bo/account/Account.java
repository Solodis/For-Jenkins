package com.epam.vyacheslav_utenkov.java.lesson7.bo.account;

public class Account {
	private String login;
	private String password;
	
	public Account(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}
	
	public Account() {
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
