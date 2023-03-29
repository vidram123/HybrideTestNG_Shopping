package com.shopping.qa.testcase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shopping.qa.base.Baseclass;
import com.shopping.qa.pageobject.AccountSuccessPage;
import com.shopping.qa.pageobject.HomePage;
import com.shopping.qa.pageobject.RegisterPage;
import com.shopping.qa.utils.Utilities;

public class Register extends Baseclass {
	
	public WebDriver driver;
	HomePage homePage;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	public Register() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		driver = initializeAndOpeningUrl(prop.getProperty("browser"));
		homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		registerPage = homePage.clickONRegister();
	}
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority = 1)
	public void verifyRegisterWithMandatoryFields() {
		
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateStringName());
		registerPage.enterTelephone(dataprop.getProperty("telephone"));
		registerPage.enterPassword(dataprop.getProperty("invalidPassword"));
		registerPage.enterConfirmPassword(dataprop.getProperty("invalidPassword"));
		registerPage.clickPrivacyPolicy();
		accountSuccessPage = registerPage.clickContinueButton();
		
		String actualText = accountSuccessPage.accountSuccessWarningMessage();
		Assert.assertEquals(actualText,(dataprop.getProperty("expectedText_Warning")));
	}
	@Test(priority = 2)
	public void verifyRegisterWithAllFields() {
		
		
		registerPage.enterFirstName(dataprop.getProperty("firstName"));
		registerPage.enterLastName(dataprop.getProperty("lastName"));
		registerPage.enterEmail(Utilities.generateStringName());
		registerPage.enterTelephone(dataprop.getProperty("telephone"));
		registerPage.enterPassword(dataprop.getProperty("invalidPassword"));
		registerPage.enterConfirmPassword(dataprop.getProperty("invalidPassword"));
		registerPage.clickOnYesOption();
		registerPage.clickPrivacyPolicy();
		accountSuccessPage =  registerPage.clickContinueButton();
		
		String actualText = accountSuccessPage.accountSuccessWarningMessage();
		Assert.assertEquals(actualText,(dataprop.getProperty("expectedText_Warning")));
	}
	
	@Test(priority = 3)
	public void verifyRegisterationWithExistingEmail() {
		
		registerPage.enterFirstName(prop.getProperty("firstname"));
		registerPage.enterLastName(prop.getProperty("lastname"));
		registerPage.enterEmail(prop.getProperty("validEmail"));
		registerPage.enterTelephone(prop.getProperty("telephone"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterConfirmPassword(prop.getProperty("validPassword"));
		registerPage.clickPrivacyPolicy();
		registerPage.clickContinueButton();
		String actualText = registerPage.duplicateRecordWarning();
		Assert.assertTrue(actualText.contains(dataprop.getProperty("exist_record_warning")));
	}

	@Test(priority = 4)
	public void verifyRegisterationWithoutAnyDetails() {
		
		registerPage.clickContinueButton();
		
		String actual_FWarning = registerPage.firstNameWarning();
		Assert.assertEquals(actual_FWarning,dataprop.getProperty("expected_FWarning"));
		
		String actual_LWarning = registerPage.lastNameWarning();
		Assert.assertEquals(actual_LWarning,dataprop.getProperty("expected_LWarning"));
		
		String actual_EWarning = registerPage.emailWarning();
		Assert.assertEquals(actual_EWarning,dataprop.getProperty("expected_EWarning"));
		
		String actual_TWarning = registerPage.telephoneWarning();
		Assert.assertEquals(actual_TWarning,dataprop.getProperty("expected_TWarning"));
		
		String actual_PWarning = registerPage.passwordWarning();
		Assert.assertEquals(actual_PWarning,dataprop.getProperty("expected_PWarning"));
		
		String actualWarning = registerPage.privacyPolicyWarning();
		Assert.assertEquals(actualWarning,dataprop.getProperty("expected_Privacy_Policy"));		
		
	}
}
