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
import com.xavient.pages.DashBoardView;
import com.xavient.util.Helper;
import com.xavient.util.Properties_Reader;

public class Test_Login implements  DashBoardView {

	WebDriver driver;
 Helper helper;
 

String curr_data_table  = ".//*[@id='VIEW_3_table-first1']/thead/tr/th"; 
String Agent_table_data_start = "//div[@class='ui-grid-header-cell-row']/descendant::Div[@class='ui-grid-cell-contents']" ;
String Agent_table_data_end = "/span[1]";
	@BeforeTest
	@Parameters({ "browser" })
	public void Before_Test(@Optional("Chrome") String browser) throws Exception {
		helper = new Helper();

		String path = System.getProperty("user.dir");
		if (browser.equalsIgnoreCase("firefox")) {
			// create firefox instance
			driver = new FirefoxDriver();
			driver.manage().window().maximize();

		}

		else if (browser.equalsIgnoreCase("Chrome")) {
			// set path to chromedriver.exe
			System.out.println(path + "\\main\\resources\\Drivers_executable\\chromedriver.exe");
			System.setProperty("webdriver.chrome.driver", path + "\\src\\main\\resources\\Drivers_executable\\chromedriver.exe");

			driver = new ChromeDriver();
			driver.manage().window().maximize();

		}

		else if (browser.equalsIgnoreCase("ie")) {
			System.setProperty("webdriver.ie.driver", path + "\\src\\main\\resources\\Drivers_executable\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			driver.manage().window().maximize();

		}

		else {
			// If no browser passed throw exception
			throw new Exception("Browser is not correct");
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("------Before Test------");
	}

	@Test
	public void view3_current_table_test() throws Exception {

		driver.get(Properties_Reader.readProperty("URL"));
		helper.handle_popup();
		driver.findElement(user_name).sendKeys(Properties_Reader.readProperty("Username"));
		driver.findElement(pword).sendKeys(Properties_Reader.readProperty("Password"));
		driver.findElement(submit_login).click();
		driver.findElement(View).click();
		driver.findElement(Queue_And_Agent_Overview).click();
		driver.findElement(View3).click();
		helper.validate_table_columns( curr_data_table , driver , "" , "Test_Login" , "view3_curr_data" );	
		
	}
	
	


	@AfterTest
	public void After_Test() {
		System.out.println("------End Test------");
		driver.close();
	}


}
