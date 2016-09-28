package com.epam.vyacheslav_utenkov.java.lesson7.utils;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenShot {
	private static final String PATH_TO_REPORT = "test-output/html/";
	private static final String SCREENSHOT_FOLDER = "screenshots";
	private static Logger LOG = Logger.getLogger(ScreenShot.class);
	private static final String DEFAULT_MESSAGE = "See screenshot";
	
	public static void make(WebDriver driver, String message){
		if(driver == null){
			return;
		}
		try{
			File screenShot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFileToDirectory(screenShot, new File(PATH_TO_REPORT + SCREENSHOT_FOLDER));
			String href = "<a href='" + SCREENSHOT_FOLDER + "/" + screenShot.getName() +"'>" + message + "</a>";
			LOG.info(href);
		}catch(Exception e){
			System.out.println("Screen shot exception");
		}
	}
	
	public static void make(WebDriver driver){
		make(driver, DEFAULT_MESSAGE);
	}
	
	public static void deleteAll(){
		File screenShotFolder = new File(PATH_TO_REPORT + SCREENSHOT_FOLDER);
		File[] files = screenShotFolder.listFiles();
		for(File file: files){
			file.delete();
		}
	}
}
