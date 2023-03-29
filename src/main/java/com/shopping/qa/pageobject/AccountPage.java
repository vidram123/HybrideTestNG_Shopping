package com.shopping.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountPage {

	WebDriver driver;
	
	public AccountPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//div[@id='content']//h2[text()='My Account']")
	WebElement myAcc_ActualText;
	
	
	public String actualWelcomeMessage() {
		String warning = myAcc_ActualText.getText();
		return warning;
	}
	
}
