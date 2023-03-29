package com.shopping.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;

public class Utilities {
	
	public static int IMP_WAIT_TIME = 30;
	public static int PAGE_LOAD_TIME = 40;
	
	public static void onClick(WebDriver driver,WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", element);
	}
	
	///////////////GENERATING RANDOM USERNAME////////////////
	public static String generateStringName() {
		Date dateText = new Date();
		String dateText1 =  dateText.toString().replace(" ","_").replace(":","_");
		return "kasivasu"+dateText1+"@gmail.com";
	}
	
	///////////////READING DATA FROM EXCEL SHEET/////////////
	public static Object[][] getTestDataFromExcel(String sheetName) {
		
		File excelFile = new File(System.getProperty("user.dir")+("/src/main/java/com/shopping/qa/testdata/ShoppingTestData.xlsx"));
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fisExcel = new FileInputStream(excelFile);
			workbook = new XSSFWorkbook(fisExcel);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		XSSFSheet sheet = workbook.getSheet(sheetName);
		
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object[][] data = new Object[rows][cols];
		
		for(int r=0; r<rows; r++) {
			for(int c=0; c<cols; c++) {
				DataFormatter df = new DataFormatter();
				data[r][c] = df.formatCellValue(sheet.getRow(r+1).getCell(c));
			}
		}
		return data;
	}
	
	/////////////////////////TO TAKE SCREENSHOT/////////////////////////
	public static String captureTheScreenShot(WebDriver driver,String testName) {
		
				TakesScreenshot screenShot = ((TakesScreenshot)driver);
				File srcFile = screenShot.getScreenshotAs(OutputType.FILE);
				String destinationScreenshotPath = System.getProperty("user.dir")+"\\Screenshots\\" +testName+ ".png";
				try {
					FileHandler.copy(srcFile, new File(destinationScreenshotPath));
				} catch (Throwable e) {
					e.printStackTrace();
				}
				
			return destinationScreenshotPath;
	}
}
