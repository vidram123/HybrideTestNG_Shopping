package com.shopping.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountSuccessPage {

	WebDriver driver;
	
	public AccountSuccessPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(xpath = "//div[@id='content']//h1")
	WebElement successWarning;
	
	public String accountSuccessWarningMessage() {
		String succ_Warning = successWarning.getText();
		return succ_Warning;
	}
}
