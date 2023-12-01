package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	WebDriver driver;
	
	//Objects
	@FindBy(xpath="//a[@title=\"My Account\"]") //From Selenium FindBy
	private WebElement myAccountDropMenu;

	@FindBy(linkText="Login")
	private WebElement loginOption;
	
	@FindBy(linkText="Register")
	private WebElement registerOption;
	
	@FindBy(xpath="//input[@name='search']")
	private WebElement searchBoxField;
	
	@FindBy(xpath="//span[contains(@class,'input-group-btn')]")
    private WebElement searchButton;
    
	public HomePage(WebDriver driver)
	{
    this.driver=driver;
    PageFactory.initElements(driver, this);
    }

	
	//actions
	public SearchPage clickOnSearchButton()
	{
		searchButton.click();
		return new SearchPage(driver);
	}
	public SearchPage searchForProduct(String productText)
	{
		searchBoxField.sendKeys(productText);
		searchButton.click();
		return new SearchPage(driver);
	}
	public void enterProductDetails(String productText)
	{
		searchBoxField.sendKeys(productText);
	}
	
	public void clickOnMyAccount()
	{
		myAccountDropMenu.click();
	}
	
	public LoginPage selectLoginOption()
	{
		loginOption.click();
		return new LoginPage(driver);
	}
	public LoginPage navigateToLoginPage()
	{
		myAccountDropMenu.click();
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage selectRegisterOption()
	{
		registerOption.click();
		return new RegisterPage(driver);
	}
	public RegisterPage navigateToRegisterPage()
	{
		myAccountDropMenu.click();
		registerOption.click();
		return new RegisterPage(driver);
	}
}
