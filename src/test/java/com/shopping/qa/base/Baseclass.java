package com.shopping.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.shopping.qa.utils.Utilities;

public class Baseclass {
	
	WebDriver driver=null;
	public Properties prop;
	public Properties dataprop;
	
	public Baseclass() {
		prop = new Properties();
		File propFile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\shopping\\qa\\config\\config.properties");
		FileInputStream fis;
		try {
			fis = new FileInputStream(propFile);
			prop.load(fis);
		    } catch (Throwable e) {
			e.printStackTrace();
		}
		
	    dataprop = new Properties();
	    File data_prop = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\shopping\\qa\\testdata\\testdata.properties");
	    FileInputStream data_fis;
		try {
			data_fis = new FileInputStream(data_prop);
			dataprop.load(data_fis);
		} catch (Throwable e) {
			e.printStackTrace();
		}    
	}
	
	public WebDriver initializeAndOpeningUrl(String browserName) {
				
		switch(browserName.toLowerCase()){
			case "chrome" : ChromeOptions options = new ChromeOptions();
							options.addArguments("--remote-allow-origins=*");
							driver = new ChromeDriver(options);	
							break;
			
			case "firefox":	driver = new FirefoxDriver();
							break;
			
			case "edge" : driver = new EdgeDriver();
						   break;
						   
			default : System.out.println("Enter the valid browser name");
					  break;
		}
			
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Utilities.IMP_WAIT_TIME));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(Utilities.PAGE_LOAD_TIME));
		driver.get(prop.getProperty("url"));
		
		return driver;
	}

}
