package com.tutorialsNinjaPro.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporter {
	public static ExtentReports genrateExtentReport()
	{
		ExtentReports extentReport=new ExtentReports();//we need extentReport library to import like java library
		//-----https://www.extentreports.com/
		File extentReportFile=new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReport.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReportFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
		sparkReporter.config().setReportName("TutorialsNinja Test Automation Results Report"); 
		sparkReporter.config().setDocumentTitle("TN Automation Report");
		sparkReporter.config().setTimeStampFormat("dd/MM/YYYY hh:mm:ss");
		extentReport.attachReporter(sparkReporter);
		
	    //properties file loading.
		Properties configProp=new Properties();
		File configPropFile=new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsninja\\qa\\config\\Config.properties");
        try {
		FileInputStream fisConfigProp=new FileInputStream(configPropFile);
        configProp.load(fisConfigProp);
        }
        catch(Throwable e)
        {
        	e.printStackTrace();
        }
		extentReport.setSystemInfo("Application URL",configProp.getProperty("url"));
		extentReport.setSystemInfo("Browser Name", configProp.getProperty("browser"));
		extentReport.setSystemInfo("Email",configProp.getProperty("validEmail"));
		extentReport.setSystemInfo("Password",configProp.getProperty("12345"));
		extentReport.setSystemInfo("Operting System",System.getProperty("os.name"));
		extentReport.setSystemInfo("Username",System.getProperty("user.name"));
		extentReport.setSystemInfo("Java Version",System.getProperty("java.version"));
		return extentReport;
	}
}
