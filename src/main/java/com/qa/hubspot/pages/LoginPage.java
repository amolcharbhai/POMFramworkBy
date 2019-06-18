package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.util.DriverUtil;
import com.qa.hubspot.util.ElementActions;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {

	WebDriver driver;
	ElementActions elementActions;
	DriverUtil driverUtil;

	/**
	 * Define page object using By locator
	 */
	By userName = By.id("username");
	By passWord = By.id("password");
	By loginButton = By.id("loginBtn");
	By signUpLink = By.linkText("Sign up");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		elementActions = new ElementActions(driver);
		driverUtil = new DriverUtil(driver);
	}

	/**
	 * 
	 * @return: This method returns the page title.
	 */
	@Step("get login page title step...")
	public String getLoginPageTitle() {
		return driverUtil.getPageTitle();
	}

	@Step("checking Sign Up link is displayed on login page step..")
	public boolean isSignUpLinkDisplayed() {
		return elementActions.elementIsDisplayed(signUpLink);
	}

	@Step("Loggin HubSpot page with username:{0} and password : {1}")
	public HomePage doLogin(String user, String pwd) {
		elementActions.doSendKeys(userName, user);
		elementActions.doSendKeys(passWord, pwd);
		elementActions.doClickElement(loginButton);

		return new HomePage(driver);
	}

}
