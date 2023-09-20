package com.amazon.testcases;

import java.io.IOException;

import org.testng.annotations.Test;

import com.amazon.TestBase.AmazonBaseTest;

public class LoginTest extends AmazonBaseTest  {

	@Test
	public void login() throws InterruptedException, IOException {

	app.OpenBrowser("chrome");
	app.navigate("url");
	
	
	//clicks on sign in present in Home page
	app.click("sign_in_button_xpath");
	
	
	//will type in username filed
	app.type("username_xpath", "8210866966");
	
	//cick on continue button present in under username
	app.click("continuebutton_xpath");
	
		
	//type password
	app.type("password_xpath", "123456");
	
	
	//click on signin
	app.click("signbutton_xpath");
	
	
	Thread.sleep(7000);
	app.type("search_xpath", "headphones");
	app.click("searchicon_xpath");
	

	
	}
	
	
}
