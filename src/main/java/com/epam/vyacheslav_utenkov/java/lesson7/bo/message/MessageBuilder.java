package com.epam.vyacheslav_utenkov.java.lesson7.bo.message;

public abstract class MessageBuilder {
	Message message;
	
	public abstract void buildAddressee(String addressee);
	public abstract void buildSubject(String subject);
	public abstract void buildMessage(String text);
	public void createMessage(){
		message = new Message();
	}
	
	public Message getMessage(){
		return message;
	}
}
