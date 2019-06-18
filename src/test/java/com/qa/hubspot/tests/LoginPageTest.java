package com.qa.hubspot.tests;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qa.hubspot.listeners.TestAllureListener;
import com.qa.hubspot.pages.BasePage;
import com.qa.hubspot.pages.HomePage;
import com.qa.hubspot.pages.LoginPage;
import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.TimeUtil;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;

@Listeners({ TestAllureListener.class })
public class LoginPageTest {

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
	}

	@Test(priority = 1, description = "verifying Login page title.")
	@Severity(SeverityLevel.NORMAL)
	@Description("Check login page title is corrct or not")
	public void verifyLoginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2, description = "verify sign up link on Login page.")
	@Severity(SeverityLevel.MINOR)
	@Description("check sign up link on Login page")
	public void verifySignupLinkTest() {
		Assert.assertTrue(loginPage.isSignUpLinkDisplayed(), "Link is not found");
	}

	@Test(priority = 3, description = "verify Login functionality")
	@Severity(SeverityLevel.CRITICAL)
	@Description("verify login functionality with correct credentials")
	public void hubspotLoginTest() {
		loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password")).toString();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
