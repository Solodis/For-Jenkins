package com.epam.vyacheslav_utenkov.java.lesson7.driver;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.epam.vyacheslav_utenkov.java.lesson7.exceptions.UnknowDriverException;

public class Driver {
	private static final String DEFAULT_WEB_DRIVER = "DEFAULT_WEB_DRIVER";
	private static DriverType defaultWebDriver = DriverType.FIREFOX;
	private static Map<String, WebDriver> driversInstances = new HashMap<String, WebDriver>();
	private static WebDriver driver;
	
	private Driver() {
		
	}
	
	public static WebDriver getDriver(String driverName, DriverType driverType){
		if(!driversInstances.containsKey(driverName)){
			if(driverType.equals(DriverType.FIREFOX)){
				driversInstances.put(driverName, new FirefoxDriver());
			} else if(driverType.equals(DriverType.IE)){
				driversInstances.put(driverName, new InternetExplorerDriver());
			} else {
				throw new UnknowDriverException();
			}
		} 
		driver = driversInstances.get(driverName);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		return driver;
	}
	
	public static WebDriver getDriver(String driverName){
		return getDriver(driverName, defaultWebDriver);
	}
	
	public static WebDriver getDriver(){
		return getDriver(DEFAULT_WEB_DRIVER);
	}
	
	public static void addWebDriver(String name, WebDriver driver){
		driversInstances.put(name, driver);
	}
	
	public static WebDriver getCurrentDriver(){
		return driver;
	}
	
	
}
