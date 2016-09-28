package com.epam.vyacheslav_utenkov.java.lesson7.bo.message;

public class Director {
	private MessageBuilder messageBuilder;
	
	public Director (MessageBuilder messageBuilder){
		this.messageBuilder = messageBuilder;
	}
	
	public Message makeMessage(String addressee, String subject, String text){
		messageBuilder.createMessage();
		messageBuilder.buildAddressee(addressee);
		messageBuilder.buildSubject(subject);
		messageBuilder.buildMessage(text);
		return messageBuilder.getMessage();
	}
}
