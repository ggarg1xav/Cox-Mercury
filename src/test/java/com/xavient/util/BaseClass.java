package com.xavient.util;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.xavient.pages.DashBoardView;

public class BaseClass implements DashBoardView{
	WebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	/**
	 * Method is initalizing driver with defined browser properties.
	 * @author AJameel
	 * @param browser
	 * @return driver Object
	 */
	public  WebDriver Browser_Selection(String browser)  {

		String path = System.getProperty("user.dir");
		System.out.println("driver path is"+path);
		if (browser.equalsIgnoreCase("firefox")) {	
			// create firefox instance
			driver = new FirefoxDriver();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(Properties_Reader.readProperty("URL"));

		}

		else if (browser.equalsIgnoreCase("Chrome")) {
			// set path to chromedriver.exe
		
			System.setProperty(
					"webdriver.chrome.driver",
					path
					+ Properties_Reader.readProperty("chrome_driver"));

			driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(Properties_Reader.readProperty("URL"));

		}

		else if (browser.equalsIgnoreCase("ie")) {

			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);

			capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
			System.setProperty("webdriver.ie.driver", path + Properties_Reader.readProperty("ie_driver"));
			driver = new InternetExplorerDriver(capabilities);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			driver.get(Properties_Reader.readProperty("URL"));
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			driver.findElement(IECertificate).click();

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
	
	@BeforeTest
	public void startExecute()
	{
		//Reporting.deleteReport();
		extent = Reporting.Instance();
	}
	
	@AfterTest
	public void stopExecution()
	{
		extent.endTest(test);
		extent.flush();
		//extent.close();
	}
	
	@AfterMethod
	public void getStatus(ITestResult result1)
	{

		String result = " ";
		int newResult = 0;
		newResult = result1.getStatus();
		result =test.getRunStatus().toString();
		System.out.println("Result : "+result);
		if(result.equalsIgnoreCase("error")||result.equalsIgnoreCase("fail")||newResult==result1.FAILURE)
		{
			String img = test.addScreenCapture(Reporting.CaptureScreen(result1.getMethod().getMethodName(),driver));
			System.out.println(img); 
			test.log(LogStatus.INFO, "Image", "Screenshot below : " + img);
		}
		driver.close();
	}
}
