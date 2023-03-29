package com.shopping.qa.testcase;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.shopping.qa.base.Baseclass;
import com.shopping.qa.pageobject.HomePage;
import com.shopping.qa.pageobject.SearchPage;

public class Search extends Baseclass{

	public WebDriver driver;
	HomePage homePage;
	SearchPage searchPage;
	
	public Search() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		driver = initializeAndOpeningUrl(prop.getProperty("browser"));
		homePage = new HomePage(driver);
	}
	
	@AfterMethod
	public void teatDown() {
		driver.quit();
	}
	@Test(priority = 1)
	public void verifySearchWithValidProducts() {
		
		homePage.searchBox(dataprop.getProperty("valid_product"));
		searchPage = homePage.clickSearchButton();
		
		Assert.assertTrue(searchPage.productFoundVerification());
		
	}
	@Test(priority = 2)
	public void verifySearchWithInvalidProduct() {
		
		homePage.searchBox(dataprop.getProperty("invalid_Product"));
		searchPage = homePage.clickSearchButton();	
		
		String actual_Text = searchPage.invalidProductVerification();
		Assert.assertEquals(actual_Text,"abc");	
		
	}
	
	@Test(priority = 3, dependsOnMethods = {"verifySearchWithValidProducts","verifySearchWithInvalidProduct"})
	public void verifySearchWithoutAnyProduct() {
		
		homePage.searchBox("");
		searchPage = homePage.clickSearchButton();
		
		String actual_Text = searchPage.emptySearchBoxVerification();
		Assert.assertTrue(actual_Text.contains(dataprop.getProperty("expected_Empty_Warning")));			
		
	}
	
	
}
