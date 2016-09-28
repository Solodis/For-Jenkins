package com.epam.vyacheslav_utenkov.java.lesson7.advanced;

import java.io.IOException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class AdvanceTest {
	
	public AdvanceTest() {
	}
	private WebDriver driver;
	
	public WebDriver getDriver() {
		return driver;
	}

	public WebDriver initDriver() throws IOException {
		DesiredCapabilities capabilities = null;
		capabilities = DesiredCapabilities.firefox();
		capabilities.setCapability(CapabilityType.TAKES_SCREENSHOT, true);
		RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		return driver;
	}

}
