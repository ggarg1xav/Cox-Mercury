package com.xavient.test.script;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.xavient.pages.DashBoardView;
import com.xavient.util.BaseClass;
import com.xavient.util.Helper;
import com.xavient.util.Properties_Reader;


/**
 * @author csingh5
 *
 */
public class Test_View15 extends BaseClass implements DashBoardView {

	WebDriver driver;
	Helper helper;
	WebDriverWait wait;
	private static Logger logger = Logger.getLogger(Test_View15.class.getName());

	
	/**
	 * Calling Before Test for navigating to particular view15.
	 * @param browser
	 * @author csingh5
	 */
	
	@BeforeClass
	@Parameters({ "browser" })
	public void Before_Test(@Optional("Chrome") String browser) {
		
			driver = Browser_Selection(browser);
			logger.info(browser + " is opened successfully");
			// Initialize
			helper = new Helper();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			wait = new WebDriverWait(driver, 5);
			// Navigating to URL.
			driver.get(Properties_Reader.readProperty("URL"));

			// Handling PopUP with AutoIT , Need to have this screen as active
			// when this method is being executed.
			helper.handle_popup();

			// Login and Navigating to View
			driver.findElement(user_name).sendKeys(Properties_Reader.readProperty("Username"));
			driver.findElement(pword).sendKeys(Properties_Reader.readProperty("Password"));
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(submit_login)));
			driver.findElement(submit_login).click();
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(View)));
			driver.findElement(View).click();
			driver.findElement(Queue_And_Agent_Overview).click();
			driver.findElement(View15).click();
			System.out.println("------Before Test------");

	}

	/**
	 * Validating Table and Column data.
	 * 
	 * @author csingh5
	 */
	@Test(enabled = true)
	public void view3_validate_table_data() {
		
			helper.validate_table_names(driver.findElement(view15_today_data), "Test_View15", "view15_todays_data_details");
			helper.validate_table_columns(view15_today_data_table, driver, "", "Test_View15", "view15_today_data_table");

			helper.validate_table_names(driver.findElement(view3_today_data), "Test_View3", "view3_today_data");
			helper.validate_table_columns(view3_today_data_table, driver, "", "Test_View3", "view3_today_data_table");

			helper.validate_table_names(driver.findElement(view3_curr_agent_stats_tbl), "Test_View3",
					"view3_curr_agent_stats_tbl");
			helper.validate_table_columns(view3_curr_agent_stats_col, driver, "", "Test_View3",
					"view3_curr_agent_stats_col");

			helper.validate_table_names(driver.findElement(view3_agent_details), "Test_View3", "view3_agent_details");
			helper.validate_table_columns(view3_Agent_table_data_start, driver, view3_Agent_table_data_end,
					"Test_View3", "view3_Agent_table_data");

	}
	
	/**
	 * Closing Browser After Test.
	 */
		@AfterClass
		public void After_Test() {
			driver.close();
			System.out.println("------End Test------");
		}
}
