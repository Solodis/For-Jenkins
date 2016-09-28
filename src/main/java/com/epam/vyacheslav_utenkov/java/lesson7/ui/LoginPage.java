package com.epam.vyacheslav_utenkov.java.lesson7.ui;

import org.openqa.selenium.WebElement; 
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
/**
 *
 * class describes page Login. 
 * 
 * @author Vyacheslav
 */
public class LoginPage extends AbstractPage{

	@FindBy(name = "login")
	private WebElement loginInsert;

	@FindBy(name = "passwd")
	private WebElement passwordInsert;

	@FindBy(xpath = "//button[contains(@class, 'button auth__button')]")
	private WebElement sighInButton;

	public LoginPage() {
		PageFactory.initElements(this.driver, this);
	}
	/**Function for insert string in "Login" field
	 * 
	 * @param login
	 */
	public void setLogin(String login){
		new Actions(driver).sendKeys(loginInsert, login).build().perform();
	}
	
	/**
	 * Function for insert string in "Password" field
	 * 
	 * @param password
	 */
	public void setPassword(String password){
		new Actions(driver).sendKeys(passwordInsert, password).build().perform();
	}
	
	/**
	 * Function for pressing "sign in" button 
	 * 
	 * @return MainPage
	 */
	public MainPage clickSighIn(){
		new Actions(driver).click(sighInButton).build().perform();
		return new MainPage();
	}
	
	
}
