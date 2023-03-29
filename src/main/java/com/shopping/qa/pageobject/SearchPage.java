package com.shopping.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	
	 WebDriver driver;
	 
	 public SearchPage(WebDriver driver) {
		 this.driver = driver;
		 PageFactory.initElements(driver,this);
	 }
	 
	 @FindBy(linkText = "HP LP3065")
	 WebElement productName;
	 @FindBy(xpath = "//div[@id='content']//h2//following-sibling::p")
	 WebElement invalidProductName;
	 @FindBy(xpath = "//div[@id='content']//h2//following-sibling::p")
	 WebElement emptySearchBox;
	 
	 
	 public Boolean productFoundVerification() {
		 Boolean actualProductName = productName.isDisplayed();
		 return actualProductName;
	 }
	 public String invalidProductVerification() {
		 String invalidProduct = invalidProductName.getText();
		 return invalidProduct;
	 }
	 public String emptySearchBoxVerification() {
		 String emptySearchProduct = emptySearchBox.getText();
		 return emptySearchProduct;
	 }

}
