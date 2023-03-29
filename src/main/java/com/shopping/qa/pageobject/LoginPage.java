package com.shopping.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shopping.qa.utils.Utilities;

public class LoginPage {
	
	WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	
	//OBJECTS
	@FindBy(name = "email")
	WebElement emailAddresField;
	@FindBy(name = "password")
	WebElement passwordAddressField;
	@FindBy(xpath = "//input[@value='Login']")
	WebElement loginButton;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement error_Warning_Msg;
	
	
	
	//ACTIONS
	
//	public void enterEmailAddress(String email) {
//		emailAddresField.sendKeys(email);
//	}
//	public void enterPasswordAddress(String password) {
//		passwordAddressField.sendKeys(password);
//	}
//	public AccountPage clickOnLoginButtonclick() {
//		loginButton.click();
//		return new AccountPage(driver);
//	}
	
	public AccountPage loginCredential(String email, String password) {
		emailAddresField.sendKeys(email);
		passwordAddressField.sendKeys(password);
		//loginButton.click();
		Utilities.onClick(driver, loginButton);
		return new AccountPage(driver);
	}
	public String error_On_Login_Msg() {
		String warning = error_Warning_Msg.getText();
		return warning;
	}
	
}
