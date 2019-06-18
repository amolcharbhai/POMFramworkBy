package com.qa.hubspot.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.hubspot.pages.BasePage;

/**
 * This is wrapper class of all the actions perform on web element.
 * 
 * @author Amol
 *
 */
public class ElementActions extends BasePage {

	WebDriver driver;

	public ElementActions(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * 
	 * @param selector
	 * @return This method find the web element and return web element
	 */
	public WebElement getElement(By selector) {
		waitForElement(selector);
		WebElement element = driver.findElement(selector);
		return element;
	}

	/**
	 * This method wait till element is found on page.
	 * 
	 * @param selector
	 */
	public void waitForElement(By selector) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.presenceOfElementLocated(selector));
	}

	public void waitForPageTitle(String selector) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.titleContains(selector));
	}
	
	public void waitElementToBeClickable(By selector) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.elementToBeClickable(selector));
	}

	/**
	 * This method wait and check the visibility of web element
	 * 
	 * @param selector
	 */
	public void waitForVisibilityOfElement(By selector) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_EXPLICIT_WAIT_TIMEOUT);
		wait.until(ExpectedConditions.visibilityOfElementLocated(selector));
	}

	/**
	 * Click on provided locator
	 * 
	 * @param selector
	 */
	public void doClickElement(By selector) {
		try {
			getElement(selector).click();
		} catch (Exception e) {
			System.out.println("exception occured with locator" + selector);
		}
	}

	/**
	 * This method set value in text field.
	 * 
	 * @param selector
	 * @param String
	 */

	public void doSendKeys(By selector, String value) {
		try {
			getElement(selector).sendKeys(value);

		} catch (Exception e) {
			System.out.println("exception occured with locator" + selector);
		}
	}

	/**
	 * This method check whether element is displayed on page
	 * 
	 * @param selector
	 * @return boolean
	 */
	public boolean elementIsDisplayed(By selector) {
		return getElement(selector).isDisplayed();

	}

	/**
	 * This method get the element text.
	 * 
	 * @param selector
	 * @return string
	 */
	public String getElementText(By selector) {
		String header = null;
		try {
			header = getElement(selector).getText();
		} catch (Exception e) {
			System.out.println("exception occured with locator" + selector);
		}
		return header;

	}

}
