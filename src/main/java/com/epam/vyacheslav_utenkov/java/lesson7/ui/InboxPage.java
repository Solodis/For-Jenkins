package com.epam.vyacheslav_utenkov.java.lesson7.ui;

import org.openqa.selenium.WebElement; 
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Class describes a page Inbox.
 * @author Vyacheslav
 *
 */
public class InboxPage extends AbstractPage{

	@FindBy(css = ".b-ico.b-ico_compose")
	private WebElement writeMessageButton;

	public InboxPage() {
		PageFactory.initElements(this.driver, this);
	}
	
	/**
	 * Function for go to a message page
	 * 
	 * @return MessagePage
	 */
	public MessagePage goToMessagePage(){
		new Actions(driver).click(writeMessageButton).build().perform();
		return new MessagePage(driver);
	}

}
