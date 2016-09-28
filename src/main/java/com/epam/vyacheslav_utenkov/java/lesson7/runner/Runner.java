package com.epam.vyacheslav_utenkov.java.lesson7.runner;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.testng.TestNG;
import org.testng.xml.Parser;
import org.testng.xml.XmlSuite;

import com.epam.vyacheslav_utenkov.java.lesson7.config.Settings;
import com.epam.vyacheslav_utenkov.java.lesson7.exceptions.TestNGRunException;

public class Runner {
	protected TestNG testng = new TestNG();
	private static String testNG_Path;
	private static String testNG_MAilURL;
	private Settings settings = new Settings();
	
	public static void main(String[] args) {
		Runner runner = new Runner(args);
		runner.run();
	}
	
	private void run() {
		try{
			XmlSuite xmlSuite = new Parser(testNG_Path).parseToList().get(0);
			this.testng.setCommandLineSuite(xmlSuite);
			this.testng.run();
		}catch(Exception e){
			throw new TestNGRunException("Error running TestNG suit " + e.getMessage());
		}
		
	}
	
	public static String getMailURL(){
		return testNG_MAilURL;
	}

	public Runner(String[] args) {
		
		CmdLineParser parser = new CmdLineParser(settings);
		
		try{
			parser.parseArgument(args);
			testNG_Path = settings.pathTestNG;
			testNG_MAilURL = settings.pathMailURL;
		}catch(CmdLineException e){
			System.out.println("error: " + e.toString());
			parser.printUsage(System.out);
		}
	}

}
