package com.shopping.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReporters {
	
	public static ExtentReports generateExtentReport() {
		
		ExtentReports extentReport = new ExtentReports();
		
		File extentReprtFile = new File(System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReports.html");
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(extentReprtFile);
		
		sparkReporter.config().setTheme(Theme.DARK);
	    sparkReporter.config().setReportName("TutorialsNinja Test Automation");
	    sparkReporter.config().setDocumentTitle("TN Automation Report");
	    sparkReporter.config().setTimeStampFormat("dd/MM/YYYY  hh:mm:ss");
	    
	    extentReport.attachReporter(sparkReporter);
	    
	    Properties prop = new Properties();
	    File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\shopping\\qa\\config\\config.properties");
	    FileInputStream fis;
		try {
			fis = new FileInputStream(propFile);
			prop.load(fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	    
	    extentReport.setSystemInfo("Application URL", prop.getProperty("url"));
	    extentReport.setSystemInfo("Browser Name",prop.getProperty("browser"));
	    extentReport.setSystemInfo("OperatingSystem",System.getProperty("os.name"));
	    extentReport.setSystemInfo("UserName",System.getProperty("user.name"));
	    extentReport.setSystemInfo("Java Version",System.getProperty("java.version"));
	    
	    return extentReport;
		
	}

}
