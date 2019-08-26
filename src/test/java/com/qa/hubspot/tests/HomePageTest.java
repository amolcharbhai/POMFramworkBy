package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.hubspot.listeners.ExtentReportListener;
import com.qa.hubspot.pages.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.TimeUtil;

@Listeners(ExtentReportListener.class)
public class HomePageTest {
	WebDriver driver;
	BasePage basePage;
	Properties prop;
	LoginPage loginPage;
	HomePage homePage;

	@BeforeMethod
	public void setUp() {
		basePage = new BasePage();
		prop = basePage.initialize_prop();
		driver = basePage.init_driver(prop);
		driver.get(prop.getProperty("url"));
		loginPage = new LoginPage(driver);
		TimeUtil.mediumWait();
		homePage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		}

	@Test(priority = 1)
	public void verifyHomePageTitle() {
		String homepagetitle = homePage.getHomePageTitle();
		System.out.println("Home page Title is : " + homepagetitle);
		Assert.assertEquals(homepagetitle, Constants.HOME_PAGE_TITLE);
	}
	
	@Test(priority = 2)
	public void verifyHomePageHeaderTest() {
		String actualheader = homePage.getHomePageHeader();
		System.out.println("Actual header is : " + actualheader);
		Assert.assertEquals(actualheader, Constants.DASHBOARD_PAGE_HEADER);
	}

	@Test(priority = 3)
	public void verifyLoggedAccountNameTest() {
		String actualccountname = homePage.getLoggedAccountValue();
		System.out.println("Account name is " + actualccountname);
		Assert.assertEquals(actualccountname, prop.getProperty("accountname"));
	}

	@AfterMethod
	public void tearDown() {
	homePage.quitBrowser();
	}
}
