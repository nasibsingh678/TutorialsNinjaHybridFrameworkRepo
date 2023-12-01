package com.tutorialsninja.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.tutorialsNinjaPro.qa.utils.Utilites;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.LoginPage;
import org.openqa.selenium.WebDriver;

public class LoginTest extends Base{

	public LoginTest()
	{
		super();
	}

	public WebDriver driver;
	HomePage homePage;
	LoginPage loginPage;
	AccountPage accountPage;

	@BeforeMethod
	public void setUp()
	{
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		homePage=new HomePage(driver);
		loginPage=homePage.navigateToLoginPage();
	}

	//This is tearDown method.
	@AfterMethod
	public void tearDown()
	{
		driver.quit();	
	}



	@Test(priority=1,dataProvider="validCredentialsSupplier")
	public void verifyLoginWithValidCredentials(String email,String password)
	{
		accountPage=loginPage.login(email, password);
		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(),"Edit you account information option is not displayed");
	}
	
	@DataProvider(name="validCredentialsSupplier")
	public Object[][] supplyTestData()
	{
		Object[][] data=Utilites.getTestDataFromExcel("Login");
		return data;
	}


	@Test(priority=2)
	public void verifyLoginWithInValidCredentials()
	{   
		loginPage.login(Utilites.genrateEmailWithTimeStamp(), dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not displayed");
	}

	@Test(priority=3)
	public void verifyLoginWithInValidEmailAndValidPassword()
	{
		loginPage.login(Utilites.genrateEmailWithTimeStamp(),prop.getProperty("validPassword"));
		Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not displayed");
	}

	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInValidPassword()
	{
		loginPage.login(prop.getProperty("validEmail"),dataProp.getProperty("invalidPassword"));
		Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not displayed");
	}


	@Test(priority=5)
	public void verifyLoginWithoutProvidingCredentials()
	{
		loginPage.clickOnLoginButton();
		Assert.assertTrue(loginPage.retriveEmailPasswordNotMatchingWarningMessageText().contains(dataProp.getProperty("emailPasswordNoMatchWarning")), "Expected Warning message is not displayed");
	}



}

