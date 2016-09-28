package com.epam.vyacheslav_utenkov.java.lesson7.bo.message;

public class Message {
	private String addresse; 
	private String subject;
	private String text;
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public void setAddresse(String addresse) {
		this.addresse = addresse;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public String getText() {
		return text;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public String getAddresse() {
		return addresse;
	}
	
}	
