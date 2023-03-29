package com.shopping.qa.testcase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.shopping.qa.base.Baseclass;
import com.shopping.qa.pageobject.AccountPage;
import com.shopping.qa.pageobject.HomePage;
import com.shopping.qa.pageobject.LoginPage;
import com.shopping.qa.utils.Utilities;



public class Login extends Baseclass {
	
	public WebDriver driver;
	HomePage homePage;
	LoginPage loginPage;
	AccountPage accountPage;
	
	public Login() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		driver = initializeAndOpeningUrl(prop.getProperty("browser"));
		homePage = new HomePage(driver);
		homePage.clickOnMyAccount();
		loginPage =  homePage.clickOnLogin();
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
	
	@Test(priority = 1,dataProvider = "loginTestData")
	public void verifyLoginWithValidCredentials(String email1,String password1) {
		
		accountPage = loginPage.loginCredential(email1,password1);
		String actual_Warning_msg = accountPage.actualWelcomeMessage();
		Assert.assertTrue(actual_Warning_msg.contains(dataprop.getProperty("expected_Login_Warning")));
	
	}
	
	@DataProvider(name = "loginTestData")
	public Object[][] validData() {
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority = 2)
	public void verifyWithInvalidCredentials() {
		
		loginPage.loginCredential(Utilities.generateStringName(),dataprop.getProperty("invalidPassword"));
		String loginErrorWarning = loginPage.error_On_Login_Msg();
		Assert.assertTrue(loginErrorWarning.contains(dataprop.getProperty("expected_warning")));

	}
	@Test(priority=3)
	public void verifyWithValidEmailAndInvalidPassword() {
		
		loginPage.loginCredential(prop.getProperty("validEmail"),dataprop.getProperty("invalidPassword"));
		String loginErrorWarning = loginPage.error_On_Login_Msg();
		Assert.assertTrue(loginErrorWarning.contains(dataprop.getProperty("expected_warning")));
	}
	@Test(priority=4)
	public void verifyWithInvalidEmailAndValidPassword() {
		
		loginPage.loginCredential(Utilities.generateStringName(),dataprop.getProperty("invalidPassword"));
		String loginErrorWarning = loginPage.error_On_Login_Msg();
		Assert.assertTrue(loginErrorWarning.contains(dataprop.getProperty("expected_warning")));
	}
	@Test(priority=5)
	public void verifyWithoutProvidingCredential() {

		loginPage.loginCredential("","");
		String loginErrorWarning = loginPage.error_On_Login_Msg();
		Assert.assertTrue(loginErrorWarning.contains(dataprop.getProperty("expected_warning")));
	}
	
	
}
