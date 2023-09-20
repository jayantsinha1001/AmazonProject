package com.amazon.keywords;

import java.io.FileInputStream;
import java.util.Properties;

import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

public class ApplicationKeywords extends ValidationKeywords {


	public ApplicationKeywords() {
		String path = System.getProperty("user.dir") + "//resources//amazon.properties";
		prop = new Properties();

		try {
			FileInputStream fs = new FileInputStream(path);
			prop.load(fs);
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Initialize soft assert
		softAssert = new SoftAssert();
	}

	public void login() {
		
	}

	public void setReport(ExtentTest test) {
		this.test = test;
	}

}
