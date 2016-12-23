package com.xavient.test.script;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.xavient.pages.DashBoardView;
import com.xavient.util.BaseClass;
import com.xavient.util.Helper;
import com.xavient.util.Properties_Reader;

/**
 * @author ajameel Test_View1
 *
 */
public class Test_View1 extends BaseClass implements DashBoardView {

	WebDriver driver;
	Helper helper;
	WebDriverWait wait;

	Logger logger = LogManager.getLogger(Test_View1.class);

	@BeforeTest
	@Parameters({ "browser" })
	public void Before_Test(@Optional("Chrome") String browser) {
		driver = Browser_Selection(browser);
		logger.info(browser + " is opened successfully");
		// Initialize
		wait = new WebDriverWait(driver, 50);
		helper = new Helper();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Navigating to URL.

		// Handling PopUP with AutoIT , Need to have this screen as active when
		// this method is being executed.
		helper.handle_popup();

		// Login and Navigating to View
		helper.login(driver);
		driver.findElement(View).click();
		driver.findElement(Queue_Summary).click();
		driver.findElement(View1).click();
		logger.info("----------Nagigated to View1----------");

	}

	// Validating table and column names
	@Test(enabled = true, priority = 1)
	public void view1_validate_table_data() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(view1_residential_summary)));
		helper.validate_table_names(driver.findElement(view1_residential_summary), "Test_View1",
				"view1_residential_summary");
		helper.validate_table_columns(view1_residential_summary_col, driver, "", "Test_View1",
				"view1_residential_summary_col");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pauseToolTip)));
		driver.findElement(pauseToolTip).click();

		logger.info("-----table and column name validated-------------");
	}
	// drill down validation

	@Test(enabled = true, priority = 2)
	public void drilldown_validation() {
		helper.validate_drilldown(view1_table_name, view1DrillStart, view1DrillEnd, driver);

		logger.info("----drill down successfull---------");

	}

	// line chart validation
	@Test(enabled = true, priority = 3)
	public void view1_validate_line_chart() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(lineChartToolTip)).click();

		helper.validate_list_data_axis(view1_line_agentCount_x_axis, driver, "Test_View1",
				"view1_line_agentCount_x_axis");
		helper.validate_list_data_axis(view1_line_percentage_x_axis, driver, "Test_View1",
				"view1_line_percentage_x_axis");
		helper.validate_list_data_axis(view1_line_time_x_axis, driver, "Test_View1", "view1_line_time_x_axis");

		logger.info("------line chart validated----");
	}

	// bar chart validation
	@Test(enabled = true, priority = 4)
	public void view1_validate_bar_graph() {

		wait.until(ExpectedConditions.visibilityOfElementLocated(barGraphToolTip)).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(view1_bar_agentCount_x_axis));

		helper.validate_list_data_axis(view1_bar_agentCount_x_axis, driver, "Test_View1",
				"view1_bar_agentCount_x_axis");

		helper.validate_list_data_axis(view1_bar_percentage_x_axis, driver, "Test_View1",
				"view1_bar_percentage_x_axis");
		helper.validate_list_data_axis(view1_bar_time_x_axis, driver, "Test_View1", "view1_bar_time_x_axis");
		logger.info("------bar chart validated-------");

	}

}
