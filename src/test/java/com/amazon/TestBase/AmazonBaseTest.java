package com.amazon.TestBase;


import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.amazon.keywords.ApplicationKeywords;
import com.amazon.reports.ExtentManager;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

	public class AmazonBaseTest {

		public ApplicationKeywords app;
		public ExtentReports rep;
		public ExtentTest test;

		@BeforeTest(alwaysRun = true)
		public void beforeTest(ITestContext context) {
			System.out.println("----------Before Test--------");

			// Initialize the ApplicationKeywords and set it as an attribute
			app = new ApplicationKeywords();
			//app.OpenBrowser("mozilla");
			

			// Initialize ExtentReports
			rep = ExtentManager.getreports();
			String name = context.getCurrentXmlTest().getName();
			test = rep.createTest(name);

			// Set the test object and report in the context
			context.setAttribute("report", rep);
			context.setAttribute("test", test);


			// Log test start
			test.log(Status.INFO, "Starting Test: " + context.getCurrentXmlTest().getName());
			app.setReport(test);

			context.setAttribute("app", app);

		}

		@BeforeMethod(alwaysRun = true)
		public void beforeMethod(ITestContext context) {
			System.out.println("*********Before Method*********");

			// Retrieve the existing objects from the context
			test = (ExtentTest) context.getAttribute("test");
			
			
			app = (ApplicationKeywords) context.getAttribute("app");
			rep = (ExtentReports) context.getAttribute("report");

		}

		@AfterTest
		public void quit() {
			if (rep != null) {
				rep.flush();
			}
		}
	}

	
