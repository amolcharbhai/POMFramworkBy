package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.hubspot.util.Constants;
import com.qa.hubspot.util.DriverUtil;
import com.qa.hubspot.util.ElementActions;

public class HomePage extends BasePage {

	WebDriver driver;
	ElementActions elementActions;
	DriverUtil driverUtil;

	By contactMainLink = By.id("nav-primary-contacts-branch");
	By contactSubLink = By.id("nav-secondary-contacts");
	By homePageHeader = By.xpath("//h1[@class='private-page__title']");
	By accountName = By.xpath("//span[normalize-space(@class)='account-name']");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		elementActions = new ElementActions(driver);
		driverUtil = new DriverUtil(driver);
	}

	public String getHomePageTitle() {
		elementActions.waitForPageTitle(Constants.HOME_PAGE_TITLE);
		return driverUtil.getPageTitle();
	}

	public String getHomePageHeader() {
		elementActions.waitForVisibilityOfElement(homePageHeader);
		return elementActions.getElementText(homePageHeader);
	}

	public String getLoggedAccountValue() {
		elementActions.waitForVisibilityOfElement(accountName);
		return elementActions.getElementText(accountName);
	}

	public ContactsPage goToContactsPage() {
		clickOnContacts();
		return new ContactsPage(driver);
	}

	private void clickOnContacts() {
		elementActions.doClickElement(contactMainLink);
		elementActions.waitForVisibilityOfElement(contactSubLink);
		elementActions.doClickElement(contactSubLink);
	}

	public void quitBrowser() {
		driverUtil.quitAssociateBrowser();
	}

}
