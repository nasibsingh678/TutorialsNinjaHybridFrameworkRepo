package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.SearchPage;

public class SearchTest extends Base{
	
	public SearchTest()
	{
		super();
	}
	
	public WebDriver driver;
	HomePage homePage;
	SearchPage searchPage;
	
	@BeforeMethod
	void setup()
	{
	driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));	
	homePage=new HomePage(driver);
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();	
	}
	
	@Test(priority=1)
	public void verifySearchWithValidProduct()
	{
	searchPage=homePage.searchForProduct(dataProp.getProperty("validProduct"));
	Assert.assertTrue(searchPage.displayStatusOfHPValidProduct(),"Valid Product HP is not displayed in the search results");
	}
	
	@Test(priority=2)
	public void verifySearchWithInValidProduct()
	{
		searchPage=homePage.searchForProduct(dataProp.getProperty("invalidProduct"));
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(),"abcd"/*dataProp.getProperty("noProductTextInSearchResults")*/,"No product message in search results is not displayed.");
	}
	
	@Test(priority=3,dependsOnMethods={"verifySearchWithValidProduct","verifySearchWithInValidProduct"})
	public void verifySearchWithoutAnyProduct()
	{
		searchPage=homePage.clickOnSearchButton();
		Assert.assertEquals(searchPage.retrieveNoProductMessageText(),dataProp.getProperty("noProductTextInSearchResults"),"No product message in search results is not displayed.");
	}

}
