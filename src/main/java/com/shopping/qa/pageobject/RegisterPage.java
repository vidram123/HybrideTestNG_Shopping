package com.shopping.qa.pageobject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.shopping.qa.utils.Utilities;

public class RegisterPage {
	
	WebDriver driver;
	//JavascriptExecutor js;
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver,this);
	}

	
	@FindBy(name = "firstname")
	WebElement firstName;
	@FindBy(name = "lastname")
	WebElement lastName;
	@FindBy(name = "email")
	WebElement email;
	@FindBy(name = "telephone")
	WebElement telephone;
	@FindBy(name = "password")
	WebElement password;
	@FindBy(name = "confirm")
	WebElement confirmPassword;
	@FindBy(name = "agree")
	WebElement privacyPolicy;
	@FindBy(xpath = "//input[@value='Continue']")
	WebElement continueButton;
	@FindBy(xpath = "//input[@name='newsletter'][@value='1']")
	WebElement yesOption;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement existingRecordWarning;	
	
	@FindBy(xpath = "//input[@name='firstname']/following-sibling::div")
	WebElement actual_FWarning;
	@FindBy(xpath = "//input[@name='lastname']/following-sibling::div")
	WebElement actual_LWarning;
	@FindBy(xpath = "//input[@name='email']/following-sibling::div")
	WebElement actual_EWarning;
	@FindBy(xpath = "//input[@name='telephone']/following-sibling::div")
	WebElement actual_TWarning;
	@FindBy(xpath = "//input[@name='password']/following-sibling::div")
	WebElement actual_PWarning;
	@FindBy(xpath = "//div[@class='alert alert-danger alert-dismissible']")
	WebElement privacyPolicy_Warning;
	
	
	public String firstNameWarning() {
		String fWarning = actual_FWarning.getText();
		return fWarning;
	}
	public String lastNameWarning() {
		String lWarning = actual_LWarning.getText();
		return lWarning;
	}
	public String emailWarning() {
		String eWarning = actual_EWarning.getText();
		return eWarning;
	}
	public String telephoneWarning() {
		String tWarning = actual_TWarning.getText();
		return tWarning;
	}
	public String passwordWarning() {
		String pWarning = actual_PWarning.getText();
		return pWarning;
	}
	public String privacyPolicyWarning() {
		String ppWarning = privacyPolicy_Warning.getText();
		return ppWarning;
	}
	public void enterFirstName(String fName) {
		firstName.sendKeys(fName);
	}
	public void enterLastName(String lName) {
		lastName.sendKeys(lName);
	}
	public void enterEmail(String email1) {
		email.sendKeys(email1);
	}
	public void enterTelephone(String tNumber) {
		telephone.sendKeys(tNumber);
	}
	public void enterPassword(String pWord) {
		password.sendKeys( pWord);
	}
	public void enterConfirmPassword(String cpWord) {
		confirmPassword.sendKeys( cpWord);
	}
	public void clickPrivacyPolicy() {
		
		Utilities.onClick(driver, privacyPolicy);
		//privacyPolicy.click();
		//((JavascriptExecutor)driver).executeScript("arguments[0].click();", privacyPolicy);
		//js = (JavascriptExecutor)driver;
		//js.executeScript("argument[0].click();",privacyPolicy);
	}
	public AccountSuccessPage clickContinueButton() {
		//continueButton.click();
		//((JavascriptExecutor)driver).executeScript("arguments[0].click();", continueButton);
		//js = (JavascriptExecutor)driver;
		//js.executeScript("argument[0].click()",continueButton);
		Utilities.onClick(driver, continueButton);
		return new AccountSuccessPage(driver);
	}
	public void clickOnYesOption() {
		
		Utilities.onClick(driver, yesOption);
		//yesOption.click();
		//((JavascriptExecutor)driver).executeScript("arguments[0].click();", yesOption);
		//js = (JavascriptExecutor)driver;
		//js.executeScript("argument[0].click()",yesOption);
	}
	public String duplicateRecordWarning() {
		String existing_Record = existingRecordWarning.getText();
		return existing_Record;
	}
}
