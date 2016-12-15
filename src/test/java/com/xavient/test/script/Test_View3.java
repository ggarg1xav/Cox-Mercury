package com.xavient.test.script;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.formula.CollaboratingWorkbooksEnvironment;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.xavient.pages.DashBoardView;
import com.xavient.util.Helper;
import com.xavient.util.Properties_Reader;

public class Test_View3 implements  DashBoardView {

	WebDriver driver;
 Helper helper;
 

String curr_data_table  = ".//*[@id='VIEW_3_table-first1']/thead/tr/th"; 
String Agent_table_data_start = "//div[@class='ui-grid-header-cell-row']/descendant::div[@class='ui-grid-cell-contents']" ;
String Agent_table_data_end = "/span[1]";
String Agent_table_data_sort_arrow = "/span[2]";

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

		helper.waitForBrowserToLoadCompletely(driver);
		
		Thread.sleep(10000);
		view3Sorting();
		view3Pagination();
		
	}
	
	/*
	 * View 3 sorting
	 * @author: guneet
	 */
	public void view3Sorting() {
		LinkedList<String>  tableData = new LinkedList<String>();
		int tableComtentCount = helper.getWebelentSize(view3_agent_details_data, driver);
		int totalTableCount = helper.getWebelentSize(By.xpath(Agent_table_data_start), driver);
		if (tableComtentCount > 0) {
			for (int i = 1; i <= totalTableCount; i++) {
				for (int j = 0; j < 2; j++) {
					driver.findElement(By.xpath(Agent_table_data_start + "[" + i + "]" + Agent_table_data_sort_arrow)).click();
					for (int i1 = 1; i1 <= tableComtentCount; i1++) {
						List<WebElement> el = driver.findElements(By.xpath("//div[@class='ui-grid-canvas']/div[" + i1 + "]//div[@role='gridcell']/div"));
						tableData.add(el.get(i - 1).getText().toString());
					}
					if(j==0)
						helper.validateListIsSorted(tableData,"asc");
					else if(j==1)
						helper.validateListIsSorted(tableData,"desc");
					tableData.clear();
				}
			}
		}

	}

	/*
	 * View 3 Pagination
	 * @author: guneet
	 */
	public void  view3Pagination() {
		Select select = new Select(driver.findElement(pagerPageDrop));

		String count=driver.findElement(pagerGridCount).getText();
		Object[] pagerGridCountList = count.split(" "); 
		
		//Validating text 
		Assert.assertEquals("of", pagerGridCountList[1]);
		Assert.assertEquals("items", pagerGridCountList[3]);		
		Assert.assertEquals(" items per page", driver.findElement(pagerPageDropText).getText());
		
		count = count.split(" ")[2];
		if(Integer.parseInt(count)>5){

			//Checking pagination first and previous is diabled
			Assert.assertEquals("true", driver.findElement(pagerFirst).getAttribute("disabled"),"Pagination first must be disabled");
			Assert.assertEquals("true", driver.findElement(pagerPrevious).getAttribute("disabled"),"Pagination Previous must be disabled");
			
			//clicking pagination last button
			driver.findElement(pagerLast).click();
			
			Assert.assertEquals("true", driver.findElement(pagerLast).getAttribute("disabled"),"Pagination Last must be disabled");
			Assert.assertEquals("true", driver.findElement(pagerNext).getAttribute("disabled"),"Pagination Next must be disabled");
			
			driver.findElement(pagerPrevious).click();

			//Validating pagination dropdown value
			Select select2 = new Select(driver.findElement(pagerPageDrop));
			List<WebElement> dCount = select2.getOptions();
			int dropList[] = {5,10,25,50,100};
			for (int i = 0; i < dCount.size(); i++) {
				select.selectByIndex(i);
				Assert.assertEquals(dropList[i], Integer.parseInt(select2.getFirstSelectedOption().getText()));
			}
		}
	}
	
	
	
}