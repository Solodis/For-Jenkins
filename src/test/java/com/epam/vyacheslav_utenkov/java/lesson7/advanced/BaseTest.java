package com.epam.vyacheslav_utenkov.java.lesson7.advanced;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

import com.epam.vyacheslav_utenkov.java.lesson7.driver.Driver;
import com.epam.vyacheslav_utenkov.java.lesson7.ui.LoginPage;


public class BaseTest{
	public WebDriver driver;
	
	public BaseTest() {
		try {
			driver = initDriver();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public WebDriver getDriver() {
		return driver;
	}
	
	public void startBrowser() throws IOException {
		driver = initDriver();
	}

	public WebDriver initDriver() throws IOException {
		return Driver.getDriver();
	}
	
	

	public LoginPage navigate(String url) {
		driver.get(url);
		return new LoginPage();
	}

	public void closeBrowser() throws IOException {
		driver.quit();
	}
}
