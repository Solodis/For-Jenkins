package com.epam.vyacheslav_utenkov.java.lesson7.advanced;

import java.io.IOException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.epam.vyacheslav_utenkov.java.lesson7.bo.account.Account;
import com.epam.vyacheslav_utenkov.java.lesson7.bo.message.Director;
import com.epam.vyacheslav_utenkov.java.lesson7.bo.message.Message;
import com.epam.vyacheslav_utenkov.java.lesson7.bo.message.SimpleMessageBuilder;
import com.epam.vyacheslav_utenkov.java.lesson7.driver.Driver;
import com.epam.vyacheslav_utenkov.java.lesson7.runner.Runner;
import com.epam.vyacheslav_utenkov.java.lesson7.ui.LoginPage;
import com.epam.vyacheslav_utenkov.java.lesson7.ui.MainPage;
import com.epam.vyacheslav_utenkov.java.lesson7.ui.MessagePage;
import com.epam.vyacheslav_utenkov.java.lesson7.ui.SentboxPage;
import com.epam.vyacheslav_utenkov.java.lesson7.utils.ScreenShot;

public class YandexMailTest extends BaseTest {
	private static Logger LOG = Logger.getLogger(YandexMailTest.class);
	private String mainURL = Runner.getMailURL();
	private WebDriver driver = Driver.getCurrentDriver();
	private static final String DEFALT_URL = "https://www.yandex.ru/";
	public static final String LOGIN_KEY = "login";
	public static final String MESSAGE1_KEY = "message1";
	public static final String MESSAGE2_KEY = "message2";
	public static final String MESSAGE3_KEY = "message3";
	private static ResourceBundle bundle;
	private static String filePath = "data";
	private static String loginPath = "login";
	private LoginPage loginPage;
	private MainPage mainPage;
	private MessagePage messagePage;
	private SentboxPage sentboxPage;

	static {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
	}

	@BeforeTest
	public void startBrowser() throws IOException {
		LOG.info("Testing started");
		super.startBrowser();
		LOG.info("Browser was started");
	}

	public static String getData(String key) {
		LOG.info("Take data from file 'data.properties'");
		bundle = ResourceBundle.getBundle(filePath);
		return bundle.getString(key);
	}

	public static String getLoginData(String key) {
		LOG.info("Take data from file 'data.properties'");
		bundle = ResourceBundle.getBundle(loginPath);
		return bundle.getString(key);
	}

	@DataProvider
	public static Object[][] getLoginData() {
		Object[][] obj = { { getLoginData(LOGIN_KEY) } };
		return obj;
	}

	@DataProvider
	public static Object[][] getMessageData() {
		Object[][] obj = { { getData(MESSAGE1_KEY) }, { getData(MESSAGE2_KEY) }, { getData(MESSAGE3_KEY) } };
		return obj;
	}

	@Test(dataProvider = "getLoginData")
	public void loginTest(String login) {
		LOG.info("Start login");
		if (loginPage == null) {
			String[] loginValues = login.split(":");
			if (mainURL != null) {
				LOG.info("Set URL from getMailURL");
				loginPage = navigate(mainURL);
			} else {
				LOG.info("Set default URL");
				loginPage = navigate(DEFALT_URL);
			}
			String accountLogin = loginValues[0];
			String accountPassword = loginValues[1];
			Account account = new Account(accountLogin, accountPassword);
			LOG.info("account was initialized");
			loginPage.setLogin(account.getLogin());
			loginPage.setPassword(account.getPassword());
			LOG.info("return login page");
			mainPage = loginPage.clickSighIn();
		}
	}

	@Test(dataProvider = "getMessageData", dependsOnMethods = "loginTest")
	public void sendMessagesTest(String data) {
		LOG.info("sendMessagesTest started");
		String[] values = data.split(":");
		messagePage = mainPage.goToMessagePage();
		LOG.info("main page is chanched to message page");
		String addressee = values[0];
		String subject = values[1];
		String text = values[2];
		Message message = new Director(new SimpleMessageBuilder()).makeMessage(addressee, subject, text);
		messagePage.setAddressee(message.getAddresse());
		LOG.info("set addressee");
		messagePage.setSubject(message.getSubject());
		LOG.info("set subject");
		messagePage.setMessage(message.getText());
		LOG.info("set message");
		ScreenShot.make(driver);
		sentboxPage = messagePage.sendMessage();
		//some change
		LOG.info("message page  is chanched to sentbox page");
	}

	@Test(dataProvider = "getMessageData", dependsOnMethods = "sendMessagesTest")
	public void deleteMessage(String data) {
		String[] values = data.split(":");
		LOG.info("Start check label");
		sentboxPage.checkLabel(values[0], values[1]);
	}

	public static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Test(dependsOnMethods = "deleteMessage")
	public void getException() {
		throw new RuntimeException();
	}

	@AfterTest
	public void closeBrowser() throws IOException {
		sentboxPage.deleteMessages();
		mainPage.clickHeaderUserName();
		mainPage.Exit();
		ScreenShot.make(driver);
		super.closeBrowser();
		LOG.info("Browser was closed");
		LOG.info("Test was end ");
	}

}
