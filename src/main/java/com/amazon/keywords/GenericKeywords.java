package com.amazon.keywords;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import com.amazon.reports.ExtentManager;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class GenericKeywords {

	public WebDriver driver;
	public Properties prop;
	public Properties envprop;
	public ExtentTest test;
	public SoftAssert softAssert;
	
	
	
	
	public void OpenBrowser(String browser) throws IOException{
		log("Opening the browser" + browser);
		
		
		
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", "G:\\Automation\\Drivers\\chromedriver-win64\\chromedriver.exe");

			driver = new ChromeDriver();
		}

		if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver", "G:\\Automation\\Drivers\\Firefoxdriver-geckodriver-v0.32.1-win-aarch64\\geckodriver.exe");
			driver = new FirefoxDriver();
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
	
	}
	
	
	public void navigate(String urlkey) {
		log("Navigating to " + urlkey);
		driver.get(prop.getProperty(urlkey));

	}

	public void click(String locatorkey) {
		log("clicking on  " + locatorkey);
		getElement(locatorkey).click();
	}

	public void type(String locatorkey, String data) {
		log("typing on  " + locatorkey);
		 getElement(locatorkey).sendKeys(data);

	}

	public void select(String locatorkey) {

	}

	public void getText(String locatorkey) {
		log("Selecting the text of   " + locatorkey);
		 getElement(locatorkey).getText();

	}

	// central function to extract elements
	public WebElement getElement(String locatorkey) {
		// check the presence
		if (!isElementPresent(locatorkey)) {
			// report failure
			System.out.println("Element not present : " + locatorkey);
		}
		
		if (!isElementVisible(locatorkey)) {
			// report failure
			System.out.println("Element not visible  : " + locatorkey);
		}
		
		WebElement e = driver.findElement(getLocator(locatorkey));
		return e;
		
		
		}

		

	// true - present
	// false -not present

	public boolean isElementPresent(String locatorkey) {
		System.out.println("checking presence of element " + locatorkey);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		try {

			wait.until(ExpectedConditions.presenceOfElementLocated(getLocator(locatorkey)));

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	// true - visible
	// false -not visible
	public boolean isElementVisible(String locatorkey) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locatorkey)));
		} catch (Exception e) {
			return false;
		}
		return true;

	}

	public By getLocator(String locatorkey) {
		By by = null;

		if (locatorkey.endsWith("_id"))
			by = By.id(prop.getProperty(locatorkey));

		else if (locatorkey.endsWith("_xpath"))
			by = By.xpath(prop.getProperty(locatorkey));

		else if (locatorkey.endsWith("_css"))
			by = By.cssSelector(prop.getProperty(locatorkey));

		else if (locatorkey.endsWith("_name"))
			by = By.name(prop.getProperty(locatorkey));

		return by;

	}
	
	public void log(String msg) {
		System.out.println(msg);
		test.log(Status.INFO,msg);
		
	}
	
		
		

	
	public void assertAll() {
		//SoftAssert.assertAll();
	}
	
	public void takeScreenshot() {
	    Date d = new Date();
	    String screenshotFile = d.toString().replace(":", "-") + ".png";

	    // Set the path to the "reports" folder
	    String screenshotFolderpath = System.getProperty("user.dir") + File.separator + "reports";

	    // Take a screenshot
	    File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

	    try {
	        // Save the screenshot in the "reports" folder
	        FileUtils.copyFile(srcFile, new File(screenshotFolderpath + File.separator + screenshotFile));
	        test.log(Status.INFO, "Screenshot" + test.addScreenCaptureFromPath(screenshotFolderpath + File.separator + screenshotFile));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}


	
/*	
	public void waitForPageToLoad() throws InterruptedException {
		
		JavascriptExecutor js  = (JavascriptExecutor)driver;
		
		int i=0;
		//ajax status
		
		while(i!=10) {
			String state = (String) js.executeScript("return document.readystate;");
			System.out.println(state);
			
			if(state.equals("complete")) {
				break;
			}
			else
				wait(2);
			i++;
			
		}
		
		//check for jquery status
		
		i=0;
		while(i!=0) {
			Long d = (Long) js.executeScript("return jQuery.active;");
			System.out.println(d);
			if(d.longValue()==0)
				break;
			else
				wait(2);
			i++;		
		}
		
	}*/
}
