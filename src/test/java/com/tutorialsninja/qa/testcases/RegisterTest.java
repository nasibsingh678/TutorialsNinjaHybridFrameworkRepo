package com.tutorialsninja.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.tutorialsNinjaPro.qa.utils.Utilites;
import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pages.AccountSuccessPage;
import com.tutorialsninja.qa.pages.HomePage;
import com.tutorialsninja.qa.pages.RegisterPage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
// I have made the change to RegisterTest Also
public class RegisterTest extends Base{
	
	public RegisterTest()
	{
		super();
	}
	
	public WebDriver driver;
	HomePage homePage;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	@BeforeMethod
	public void setUp()
	{
		driver=initializeBrowserAndOpenApplicationURL(prop.getProperty("browser"));
		homePage=new HomePage(driver);
		registerPage=homePage.navigateToRegisterPage();
	}
	@AfterMethod
	public void tearDown()
	{
		driver.quit();	
	}
	
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields()
	{
		accountSuccessPage=registerPage.registerWithMandatoryField(dataProp.getProperty("firstName"),
		dataProp.getProperty("lastName"),Utilites.genrateEmailWithTimeStamp(), dataProp.getProperty("telephoneNumber"), prop.getProperty("validPassword"));
		Assert.assertEquals(accountSuccessPage.retriveAccountPageHeading(), dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success Page is not displayed.");
	}
	
	@Test(priority=2)
	public void verifyRegisteringByProvidingAllFields()
	{
		accountSuccessPage=registerPage.registerWithAllField(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),Utilites.genrateEmailWithTimeStamp()
				,dataProp.getProperty("telephoneNumber"),prop.getProperty("validPassword"));
		Assert.assertEquals(accountSuccessPage.retriveAccountPageHeading(), dataProp.getProperty("accountSuccessfullyCreatedHeading"),"Account Success Page is not displayed.");	
	}
	
	@Test(priority=3)
	public void verifyRegisteringAnAccountWithExistingEmailAddress()
	{
		registerPage.registerWithAllField(dataProp.getProperty("firstName"),dataProp.getProperty("lastName"),prop.getProperty("validEmail")
		,dataProp.getProperty("telephoneNumber"),prop.getProperty("validPassword"));
		Assert.assertTrue(registerPage.retrieveDuplicateEmailAddressWarning().contains(dataProp.getProperty("duplicateEmailWarning")),"Warning Message regrading duplicate email address not displayed");
	}
	
	@Test(priority=4)
	public void verifyRegisteringAccountWithoutFillingAnyDetails()
	{
		registerPage.continueButton();
		
		Assert.assertTrue(registerPage.retrievePrivacyPolicyWarning().contains(dataProp.getProperty("privacyPolicyWarning")),"Privacy Policy Warning message is not displayed");
		Assert.assertTrue(registerPage.retrieveFirstNameWarning().contains(dataProp.getProperty("firstNameWarning")),"First Name Warning message is not displayed");
		Assert.assertTrue(registerPage.retrieveLastNameWarning().contains(dataProp.getProperty("lastNameWarning")),"Last Name Warning message is not displayed");	
		Assert.assertTrue(registerPage.retrieveEmailWarning().contains(dataProp.getProperty("emailWarning")),"Email Warning message is not displayed");	
		Assert.assertTrue(registerPage.retrieveTelephoneWarning().contains(dataProp.getProperty("telephoneWarning")),"Telephone Warning message is not displayed");
		Assert.assertTrue(registerPage.retrievePasswordWarning().contains(dataProp.getProperty("passwordWarning")),"Password Warning message is not displayed");
	}
}
