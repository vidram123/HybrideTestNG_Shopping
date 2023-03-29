package com.shopping.qa.listeners;

import java.awt.Desktop;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.shopping.qa.utils.ExtentReporters;
import com.shopping.qa.utils.Utilities;

public class MyListeners implements ITestListener{

	
	ExtentReports extentReport;
	ExtentTest extentTest;
	String testName;
	
	@Override
	public void onStart(ITestContext context) {
		System.out.println("Start of the Execution of the project....");////PRINTING ON CONSOLE
		extentReport = ExtentReporters.generateExtentReport();//ON EXTENT REPORT
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		
		testName = result.getName();
		
		System.out.println(testName+"start executed");//PRINTING ON CONSOLE
		
		extentTest = extentReport.createTest(testName);//ON EXTENT REPORT
		extentTest.log(Status.INFO, testName + "Started");//ON EXTENT REPORT
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		System.out.println(testName+"passed");//PRINTING ON CONSOLE
		
		extentTest.log(Status.PASS, testName + "Passed");//ON EXTENT REPORT
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		System.out.println(testName+"failed");//PRINTING ON CONSOLE
		System.out.println(result.getThrowable());//TO DISPLAY THE ERROR MESSAGE
		
		//TO GET THE DRIVER
		WebDriver driver = null;
		try {
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		String destinationScreenshotPath = Utilities.captureTheScreenShot(driver,result.getName() );
		extentTest.addScreenCaptureFromPath(destinationScreenshotPath);//ON EXTENT REPORT
		extentTest.log(Status.INFO,result.getThrowable());//ON EXTENT REPORT
		extentTest.log(Status.FAIL,testName+"failed");//ON EXTENT REPORT
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		
		System.out.println(testName+"skipped");//PRINTING ON CONSOLE
		System.out.println(result.getThrowable());//PRINTING ON CONSOLE
		
		extentTest.log(Status.SKIP,testName+ "Skipped");//ON EXTENT REPORT
		extentTest.log(Status.INFO,result.getThrowable());//ON EXTENT REPORT
	}

	
	@Override
	public void onFinish(ITestContext context) {
		
		System.out.println("Execution completed");
		extentReport.flush();
		
		String extentReportDestination = System.getProperty("user.dir")+"\\test-output\\ExtentReports\\extentReports.html";
		File pathFile = new File(extentReportDestination);
		try {
			Desktop.getDesktop().browse(pathFile.toURI());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
