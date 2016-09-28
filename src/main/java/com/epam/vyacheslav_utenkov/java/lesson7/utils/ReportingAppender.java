package com.epam.vyacheslav_utenkov.java.lesson7.utils;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;

public class ReportingAppender extends AppenderSkeleton{

	public void close() {
		
	}

	public boolean requiresLayout() {
		return true;
	}

	@Override
	protected void append(LoggingEvent arg0) {
		String message = this.layout.format(arg0);
		message = message.replaceAll("\n", "<br>");
		Reporter.log(message);
	}

}
