package com.epam.vyacheslav_utenkov.java.lesson7.utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.epam.vyacheslav_utenkov.java.lesson7.driver.Driver;

public class EventListener implements ITestListener {

	private static Logger LOG = Logger.getLogger(EventListener.class);
	private static final String FAILED_MESSAGE = "Failed screen shot";
	private static final String SUCCESS_MESSAGE = "Success screen shot";
	private static final String DEFAULT_MESSAGE = "Avto screen shot";

	public void makeScreenShot(ITestResult arg0, String message) {
		Object obj = arg0;
		if (obj == null) {
			return;
			
		}
		WebDriver driver = Driver.getDriver();
		if (driver == null) {
			return;
		}
		ScreenShot.make(driver, message);

	}

	public void onFinish(ITestContext arg0) {
		LOG.info("EventListener - onFinish");
	}

	public void onStart(ITestContext arg0) {
		LOG.info("EventListener - onStart");
		ScreenShot.deleteAll();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		LOG.info("EventListener - onTestFailedButWithinSuccessPercentage");
		makeScreenShot(arg0, DEFAULT_MESSAGE);
	}

	public void onTestFailure(ITestResult arg0) {
		LOG.info("EventListener - onTestFailure");
		makeScreenShot(arg0, FAILED_MESSAGE);
	}

	public void onTestSkipped(ITestResult arg0) {
		LOG.info("EventListener - onTestSkipped");
		makeScreenShot(arg0, DEFAULT_MESSAGE);
	}

	public void onTestStart(ITestResult arg0) {
		LOG.info("EventListener - onTestStart");
		makeScreenShot(arg0, DEFAULT_MESSAGE);
	}

	public void onTestSuccess(ITestResult arg0) {
		LOG.info("EventListener - onTestSuccess");
		makeScreenShot(arg0, SUCCESS_MESSAGE);
	}

}
