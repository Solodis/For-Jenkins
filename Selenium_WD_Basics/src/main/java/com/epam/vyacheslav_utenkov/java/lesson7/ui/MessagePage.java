package com.epam.vyacheslav_utenkov.java.lesson7.ui;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.FluentWait;

import com.epam.vyacheslav_utenkov.java.lesson7.advanced.YandexMailTest;
/**
 * Class describes messages page
 * 
 * @author Vyacheslav
 *
 */
public class MessagePage extends AbstractPage{

	@FindBy(id = "compose-send_tbl")
	private WebElement messageArea;

	@FindBy(id = "compose-subj")
	private WebElement subjectArea;

	@FindBy(xpath = "//input[@name='to']/..")
	private WebElement addresseeArea;

	@FindBy(xpath = "//div[contains(@class, 'b-folders__nesting_closed')]")
	private WebElement draftButton;

	@FindBy(xpath = "//a[@class='b-link b-link_header b-link_header_mail b-link_current daria-action']")
	private WebElement inbox;
	
	@FindBy(xpath = "//div[@class='b-statusline']/span[@class='b-statusline__content']")
	private WebElement messageSended;
	
	@FindBy(id = "nb-6")
	private WebElement sendMessage;

	@FindBy(xpath = "//span[@class='b-compose-message__actions__helper__date']")
	private WebElement saveInDraft;

	@FindBy(xpath = "//a[@href ='#sent']")
	private WebElement sendedMessages;
	
	@FindBy(xpath = "//label[@class='b-messages-head__title']")
	private WebElement messageHeadTitle;
	
	private static final long WAIT_MILISECOND = 1000;
	private static final int WAIT_PERIOD = 15;

	public MessagePage(WebDriver driver) {
		PageFactory.initElements(this.driver, this);
	}

	/**
	 * Function for insert addressee in "addressee" field 
	 * 
	 * @param addresse
	 */
	public void setAddressee(String addresse) {
		new Actions(driver).click(addresseeArea).build().perform();
		new Actions(driver).sendKeys(addresseeArea, addresse).build().perform();

	}
	
	/**
	 * Function for insert subject in "subject" field  
	 * 
	 * @param subject
	 */
	public void setSubject(String subject) {
		new Actions(driver).sendKeys(subjectArea, subject).build().perform();
	}

	public void setMessage(String message) {
		new Actions(driver).sendKeys(messageArea, message).build().perform();
	}

	/**
	 * 
	 * Function for insert text in "text" field  
	 * 
	 * @return SentBoxPAge
	 */
	public SentboxPage sendMessage() {
		
		fluentWaitMethod(WAIT_PERIOD, sendMessage.isDisplayed());
		new Actions(driver).click(sendMessage).build().perform();
		fluentWaitMethod(WAIT_PERIOD, messageSended.isDisplayed());
		new Actions(driver).click(sendedMessages).build().perform();
		YandexMailTest.sleep(WAIT_MILISECOND);
		
		return new SentboxPage();
	}
	
	/**
	 * Function for saving messages into draft, also moving to draft page
	 * 
	 * @return DraftPage
	 */
	public DraftPage saveDraft() {
		fluentWaitMethod(WAIT_PERIOD, saveInDraft.isDisplayed());
		new Actions(driver).click(draftButton).build().perform();
		return new DraftPage();
	}
	
	/**
	 * Function for waiting a certain result, specified period of time
	 * 
	 * @param time
	 * @param b
	 */
	private void fluentWaitMethod(int time, final boolean b){
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(time, TimeUnit.SECONDS)
				.pollingEvery(2, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);

		wait.until(new ExpectedCondition<Boolean>() {

			public Boolean apply(WebDriver arg0) {
				return  b;
			}
		});
	}
}
