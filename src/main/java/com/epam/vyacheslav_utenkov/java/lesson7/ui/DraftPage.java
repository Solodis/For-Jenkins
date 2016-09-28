package com.epam.vyacheslav_utenkov.java.lesson7.ui;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Class describes draft page
 * @author Vyacheslav
 *
 */

public class DraftPage extends AbstractPage{

	private String xpathToIndexAddressee = "//div[@class='b-messages']/div[%d]//span[@class='b-messages__from__text']";
	private String xpathToIndexSubject = "//div[@class='b-messages']/div[%d]//span[@class ='b-messages__subject']";
	private String xpathToIndexBody = "//div[@class='b-messages']/div[%d]//span[@class='b-messages__firstline']";
	private String xpathToIndexLabel = "//div[@class='b-messages']/div[%d]/label";

	@FindBy(xpath = "//a[@class='b-link b-link_header b-link_header_mail b-link_current daria-action']")
	private WebElement mainPageButton;

	@FindBy(xpath = "//div[contains(@class, 'b-folders__nesting_closed')]")
	private WebElement draftButton;

	@FindAll(@FindBy(xpath = "//div[@class = 'b-messages']/div[contains(@class, 'b-messages__message_without-folder')]"))
	private List<WebElement> allDraft;

	@FindBy(xpath = "//span[contains(@class, 'js-toolbar-item-title-delete')]")
	private WebElement deleteButton;

	public DraftPage() {
		PageFactory.initElements(this.driver, this);
	}
	/**Function for return to main page
	 * 
	 * @return MainPage class
	 */
	public MainPage goToMainPage() {
		new Actions(driver).click(mainPageButton).build().perform();
		return new MainPage();
	}
	
	/**
	 * Function is checking out, whether there is a letter with same parameters  
	 * 
	 * @param addressee - parameter addressee
	 * @param subject - parameter subject
	 * @param message - parameter letter text
	 */

	public void checkDraft(String addressee, String subject, String message) {
		int allDraftCount = allDraft.size();
		for (int i = allDraftCount; i > 0; i--) {
			WebElement draftAddressee = driver.findElement(By.xpath(String.format(xpathToIndexAddressee, i)));
			WebElement draftSubject = driver.findElement(By.xpath(String.format(xpathToIndexSubject, i)));
			WebElement draftBody = driver.findElement(By.xpath(String.format(xpathToIndexBody, i)));
			if (draftAddressee.getText().equals(addressee) && draftSubject.getText().equals(subject)
					&& draftBody.getText().equals(message)) {
				clickLabel(i);
			}
		}
		deleteDraft();
	}
	/**Function for marker letter by index
	 * 
	 * @param index
	 */
	private void clickLabel(int index) {
		new Actions(driver).click(driver.findElement(By.xpath(String.format(xpathToIndexLabel, index)))).build()
				.perform();
	}
	/**
	 * Function is deleting all checked letter.
	 */
	public void deleteDraft() {
		new Actions(driver).click(deleteButton).build().perform();
	}

}
