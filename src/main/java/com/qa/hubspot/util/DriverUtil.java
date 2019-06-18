package com.qa.hubspot.util;

import org.openqa.selenium.WebDriver;

import com.qa.hubspot.pages.BasePage;

public class DriverUtil extends BasePage {

	WebDriver driver;

	public DriverUtil(WebDriver driver) {
		this.driver = driver;
	}

	public String getPageTitle() {
		String title = null;
		try {
			title = driver.getTitle();
		} catch (Exception e) {
			System.out.println("Exception occured while getting the title" + e.getMessage());
		}
		return title;
	}

	public void quitAssociateBrowser() {
		try {
			driver.quit();
		} catch (Exception e) {
			System.out.println("Exception occured while quitting browser" + e.getMessage());
		}
	}
}
