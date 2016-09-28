package com.epam.vyacheslav_utenkov.java.lesson7.bo.message;

public class MessageOfFarewell extends MessageBuilder {
	private final String FAREWELL = "Farewell my friend";
	@Override
	public void buildAddressee(String addressee) {
		message.setAddresse(addressee);
	}

	@Override
	public void buildSubject(String subject) {
		message.setSubject(FAREWELL);
	}

	@Override
	public void buildMessage(String text) {
		message.setText(text + " " + FAREWELL);
	}

}
