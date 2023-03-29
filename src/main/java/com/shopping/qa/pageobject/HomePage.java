package com.shopping.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shopping.qa.utils.Utilities;



public class HomePage {
	
	WebDriver driver;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//OBJECTS
	@FindBy(xpath = "//span[text()='My Account']")
	WebElement myAccount;
	@FindBy(xpath = "//a[text()='Login']")
	WebElement loginOption;	
	@FindBy(xpath = "//a[text()='Register']")
	WebElement registerOption;	
	@FindBy(name = "search")
	WebElement searchOption;
	@FindBy(xpath = "//button[@class='btn btn-default btn-lg']")
	WebElement searchButton;
	
	//ACTIONS
	public void clickOnMyAccount() {
		//myAccount.click();
		//((JavascriptExecutor)driver).executeScript("arguments[0].click();", myAccount);
		Utilities.onClick(driver, myAccount);
	}
	public LoginPage clickOnLogin() {
		//loginOption.click();
		//((JavascriptExecutor)driver).executeScript("arguments[0].click();", loginOption);
		Utilities.onClick(driver, loginOption);
		return new LoginPage(driver);
	}
	public RegisterPage clickONRegister() {
		//registerOption.click();
		//((JavascriptExecutor)driver).executeScript("arguments[0].click();", registerOption);
		Utilities.onClick(driver, registerOption);
		return new RegisterPage(driver);
	}
	public void searchBox(String product) {
		searchOption.sendKeys(product);
	 }
	public SearchPage clickSearchButton() {
		 searchButton.click();
		 return new SearchPage(driver);
	 }
}
