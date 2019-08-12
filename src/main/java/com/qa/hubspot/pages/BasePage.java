package com.qa.hubspot.pages;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BasePage {

	// public WebDriver driver;
	public Properties prop;

	public static ThreadLocal<WebDriver> tldriver = new ThreadLocal<WebDriver>();

	/**
	 * 
	 * @param browser
	 * @return This Method returns driver based on selected browser
	 */
	public WebDriver init_driver(Properties prop) {
		String browser = prop.getProperty("browser");

		if (browser.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", "D:\\SeleniumJava\\Jars\\chromedriver.exe");
			// driver = new ChromeDriver();
			tldriver.set(new ChromeDriver());
		} else if (browser.equals("FF")) {
			System.setProperty("webdriver.gecko.driver", "D:\\SeleniumJava\\Jars\\geckodriver.exe");
			// driver = new FirefoxDriver();
			tldriver.set(new FirefoxDriver());
		} else {
			System.out.println(browser + " Browser is not defined");
		}
		getDriver().manage().window().fullscreen();
		getDriver().manage().deleteAllCookies();
		getDriver().manage().timeouts().pageLoadTimeout(Integer.parseInt(prop.getProperty("pageloadtimeout")),
				TimeUnit.SECONDS);

		return getDriver();

	}

	public static synchronized WebDriver getDriver() {
		return tldriver.get();
	}

	/**
	 * 
	 * @return This method initialize the config Properties and returns properties
	 *         object.
	 */

	public Properties initialize_prop() {
		prop = new Properties();
		try {
			FileInputStream ip = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\hubspot\\config\\config.properties");
			prop.load(ip);
		} catch (FileNotFoundException e) {
			System.out.println("Properties file is not defined.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}

	public String getScreenshot() {
		File src = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshots/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		try {
			FileUtils.copyFile(src, destination);
		} catch (IOException e) {
			System.out.println("Capture Failed " + e.getMessage());
		}
		return path;
	}
}
