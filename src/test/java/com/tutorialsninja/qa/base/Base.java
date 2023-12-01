package com.tutorialsninja.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.tutorialsNinjaPro.qa.utils.Utilites;

public class Base {
	WebDriver driver = null;
	public Properties prop;
	public Properties dataProp;

	public Base()//constructor don't have return type
	{
		prop=new Properties();//This class is from java.
		File propFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\Config.properties");//This class is from java.
		dataProp=new Properties();
		File dataPropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\testdata\\testdata.properties");
		try {
			FileInputStream dataFis=new FileInputStream(dataPropFile);//This class is from java.
			dataProp.load(dataFis);
		}
		catch (Throwable e) {
			e.printStackTrace();
		}
		try {
			FileInputStream fis=new FileInputStream(propFile);//This class is from java.
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}    
	}

	public WebDriver initializeBrowserAndOpenApplicationURL(String browserName)
	{
		if(browserName.equalsIgnoreCase("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilites.IMPLICT_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilites.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		return driver;
	}

}
