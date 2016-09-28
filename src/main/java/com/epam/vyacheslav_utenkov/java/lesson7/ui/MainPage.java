package com.epam.vyacheslav_utenkov.java.lesson7.ui;

import org.openqa.selenium.WebElement; 
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 * Class describes main page
 * @author Vyacheslav
 *
 */
public class MainPage extends AbstractPage{
	
	@FindBy(css = ".ns-view-toolbar-button-compose-go")
	private WebElement writeMessageButton;
	
	@FindBy(xpath = "//div[contains(@class, 'b-folders__nesting_closed')]")
	private WebElement draftButton;
	
	@FindBy(xpath = "//div[contains(@class, 'mail-User-Name')]")
	private WebElement headerUserName;
	
	@FindBy(xpath = "//a[contains(@href, 'action=logout')]")
	private WebElement exitButton;

	public MainPage() {
		PageFactory.initElements(this.driver, this);
	}
	
	/**
	 * Function for writing message page
	 * @return MessagePage
	 */
	public MessagePage goToMessagePage(){
		new Actions(driver).click(writeMessageButton).build().perform();
		return new MessagePage(driver);
	}
	
	/**
	 * Function for moving on draft page
	 * @return DraftPage
	 */
	public DraftPage goToDraftPage(){
		new Actions(driver).click(draftButton).build().perform();
		return new DraftPage();
	}
	
	/**
	 * Function for opening header.
	 */
	public void clickHeaderUserName(){
		new Actions(driver).click(headerUserName).build().perform();
	}
	
	/**
	 * Function for exit from email
	 */
	public void Exit(){
		new Actions(driver).click(exitButton).build().perform();
	}


}
