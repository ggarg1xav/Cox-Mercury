package com.xavient.test.script;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseClass {
	 WebDriver driver;

	public  WebDriver Browser_Selection(String browser)  {

		String path = System.getProperty("user.dir");
		System.out.println("path is"+path);
		if (browser.equalsIgnoreCase("firefox")) {
			// create firefox instance
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}

		else if (browser.equalsIgnoreCase("Chrome")) {
			// set path to chromedriver.exe
			System.out
					.println(path
							+ "\\main\\resources\\Drivers_executable\\chromedriver.exe");
			System.setProperty(
					"webdriver.chrome.driver",
					path
							+ "\\src\\main\\resources\\Drivers_executable\\chromedriver.exe");

			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}

		else if (browser.equalsIgnoreCase("ie")) {

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
			
			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			System.setProperty(
					"webdriver.ie.driver",
					path
							+ "\\src\\main\\resources\\Drivers_executable\\IEDriverServer.exe");
			driver = new InternetExplorerDriver(capabilities);
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.manage().window().maximize();
		}

		else {
			// If no browser passed throw exception
			try {
				throw new Exception("Browser is not correct");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
return driver;
	}
}
