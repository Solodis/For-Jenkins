package com.epam.vyacheslav_utenkov.java.lesson7.bo.message;

public class SimpleMessageBuilder extends MessageBuilder{

	@Override
	public void buildAddressee(String addressee) {
		message.setAddresse(addressee);
	}

	@Override
	public void buildSubject(String subject) {
		message.setSubject(subject);
	}

	@Override
	public void buildMessage(String text) {
		message.setText(text);
		
	}
	
}
