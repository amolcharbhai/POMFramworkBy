package com.qa.hubspot.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import com.qa.hubspot.util.ElementActions;

public class ContactsPage extends BasePage {
	WebDriver driver;
	ElementActions elementActions;

	By contactPageHeader = By.xpath("//h1/i18n-string[text()='Contacts']");
	By createContactBtn = By.xpath("//span[text()='Create contact']");
	By createContactSecondBtn = By.xpath("//li//span[text()='Create contact']");
	By emailId = By.xpath("//input[@data-field='email']");
	By firstName = By.xpath("//input[@data-field='firstname']");
	By lastName = By.xpath("//input[@data-field='lastname']");
	By jobTitle = By.xpath("//input[@data-field='jobtitle']");

	public ContactsPage(WebDriver driver) {
		this.driver = driver;
		elementActions = new ElementActions(driver);

	}

	public boolean verifyContactPageHeader() {
		elementActions.waitForVisibilityOfElement(contactPageHeader);
		return elementActions.elementIsDisplayed(contactPageHeader);
	}

	public void createNewConatact(String emailVal, String firstname, String lastname, String jobtitle) {

		elementActions.waitElementToBeClickable(createContactBtn);
		elementActions.doClickElement(createContactBtn);

		elementActions.waitElementToBeClickable(emailId);
		elementActions.doSendKeys(emailId, emailVal);

		elementActions.waitElementToBeClickable(firstName);
		elementActions.doSendKeys(firstName, firstname);

		elementActions.waitElementToBeClickable(lastName);
		elementActions.doSendKeys(lastName, lastname);

		elementActions.waitElementToBeClickable(jobTitle);
		elementActions.doSendKeys(jobTitle, jobtitle);

		elementActions.waitElementToBeClickable(createContactSecondBtn);
		elementActions.doClickElement(createContactSecondBtn);

	}

}
