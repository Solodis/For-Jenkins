package com.epam.vyacheslav_utenkov.java.lesson7.ui;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Class describes "sent box messages" page
 * 
 * @author Vyacheslav
 *
 */
public class SentboxPage extends AbstractPage{

	@FindBy(xpath = "//a[@class='b-link b-link_header b-link_header_mail b-link_current daria-action']")
	private WebElement mainMenuButton;

	private String xpathToIndexAddressee = "//div[contains(@class, 'ns-view-messages-item-wrap')][%d]//span[@class='b-messages__from__text']";
    private String cssToIndexSubject = ".mail-MessageSnippet-Item.mail-MessageSnippet-Item_subject>span";
    private String xpathToIndexLabel = "//div[contains(@class, 'ns-view-messages-item-wrap')][%d]//label";

	@FindAll(@FindBy(xpath = "//div[contains(@class, 'ns-view-messages-item-wrap')]"))
	private List<WebElement> allMessage;
	
	@FindBy(css = ".ns-view-toolbar-button-delete.js-toolbar-item")
	private WebElement deleteButton;
	
	private static final long WAIT_MILISECONDS = 1000;

	public SentboxPage() {
		PageFactory.initElements(this.driver, this);
	}

	/**
	 * Function for moving to a main page
	 * 
	 * @return MainPage
	 */
	public MainPage clickMainMenuButton() {
		new Actions(driver).click(mainMenuButton).build().perform();
		return new  MainPage();
	}
	
	/** 
	 * Function will be marking message if parameter and message data are equals
	 * 
	 * @param addressee
	 * @param subject
	 */
	public void markLabel(String addressee, String subject) {
		for (int i = allMessage.size(); i > 0; i--) {
			try {
				Thread.sleep(WAIT_MILISECONDS);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			WebElement addresseeElement = driver.findElement(By.xpath(String.format(xpathToIndexAddressee, i)));
			WebElement subjectElement = driver.findElement(By.cssSelector(String.format(cssToIndexSubject, i)));
			if (addresseeElement.getText().contains(addressee) && subjectElement.getText().contains(subject)) {
				clickLabel(i);
			}
		}
	}
	/**
	 * Function for marking message by index
	 * 
	 * @param index
	 */
	private void clickLabel(int index) {
		new Actions(driver).click(driver.findElement(By.xpath(String.format(xpathToIndexLabel, index)))).build()
				.perform();
	}
	
	/**
	 * Function for deleting marked messages 
	 */
	public void deleteMessages() {
		new Actions(driver).click(deleteButton).build().perform();
	}
}
