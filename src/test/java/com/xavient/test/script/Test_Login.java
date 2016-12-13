package com.xavient.test.script;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.xavient.pages.Login;
import com.xavient.util.Properties_Reader;

public class Test_Login {

	WebDriver driver;

	@BeforeTest
	@Parameters({ "browser" })
	public void Before_Test(@Optional("Chrome") String browser) throws Exception {
		String path = System.getProperty("user.dir");
		System.out.println(path);
		if (browser.equalsIgnoreCase("firefox")) {
			// create firefox instance
			driver = new FirefoxDriver();
		}

		else if (browser.equalsIgnoreCase("Chrome")) {
			// set path to chromedriver.exe
			System.out.println(path + "\\main\\resources\\Drivers_executable\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", path + "\\src\\main\\resources\\Drivers_executable\\chromedriver.exe");

			driver = new ChromeDriver();
		}

		else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", path + "\\rc\\main\\resources\\Drivers_executable\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}

		else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("------Before Test------");
	}

	@Test
	public void login() throws Exception {
		driver.get(Properties_Reader.readProperty("URL"));
		Login.userName(driver).sendKeys(Properties_Reader.readProperty("Username"));
		Login.Password(driver).sendKeys(Properties_Reader.readProperty("Password"));
		Login.login_button(driver).click();
	}

	@AfterTest
	public void After_Test() {
		System.out.println("------End Test------");
		driver.close();
	}

}
