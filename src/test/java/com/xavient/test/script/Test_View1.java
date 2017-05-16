package com.xavient.test.script;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.xavient.pages.DashBoardView;
import com.xavient.util.BaseClass;
import com.xavient.util.ExcelCache;
import com.xavient.util.Helper;

/**
 * @author ajameel Test_View1
 *
 */
public class Test_View1 extends BaseClass implements DashBoardView {

	WebDriver driver;
	Helper helper;
	WebDriverWait wait;

	Logger logger = Logger.getLogger(Test_View1.class);

	@BeforeMethod
	@Parameters({ "browser" })
	public void Before_Test(@Optional("ie") String browser) {
		driver = Browser_Selection(browser);
		logger.info(browser + " is opened successfully");

		// Initialize
		wait = new WebDriverWait(driver, 50);
		helper = new Helper();
		
		// Handling PopUP with AutoIT , Need to have this screen as active when this method is being executed.
		helper.handle_popup();

		// Login and Navigating to Views
		helper.login(driver);
		By[] element = { View, Queue_Summary, View1 };
		helper.navigate_view(element, wait, driver);
		helper.waitloader(driver);
		logger.info("------Before Test------");
	}

	// Validating table and column names
	@Test
	public void view1_validate_table_data() {
		helper.waitForBrowserToLoadCompletely(driver);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pauseToolTip)));
		driver.findElement(pauseToolTip).click();
		helper.validate_table_names(driver.findElement(view1_residential_summary), "Test_View1","view1_residential_summary");
		helper.validate_table_columns(view1_residential_summary_col, driver, "", "Test_View1","view1_residential_summary_col");
		logger.info("-----table and column name validated-------------");
	}
	
	// drill down validations
	@Test
	public void drilldown_validation() {
		helper.validate_drilldown(view1_table_name, view1DrillStart, view1DrillEnd, driver);
		logger.info("----drill down successfull---------");

	}

	// line chart validation
	@Test
	public void view1_validate_line_chart() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pauseToolTip)));
		driver.findElement(pauseToolTip).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(lineChartToolTip)).click();
		helper.validate_list_data_axis(view1_line_agentCount_x_axis, driver, "Test_View1","view1_line_agentCount_x_axis");
		helper.validate_list_data_axis(view1_line_percentage_x_axis, driver, "Test_View1","view1_line_percentage_x_axis");
		helper.validate_list_data_axis(view1_line_time_x_axis, driver, "Test_View1", "view1_line_time_x_axis");

		logger.info("------line chart validated----");
	}

	// bar chart validation
	@Test
	public void view1_validate_bar_graph() {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(pauseToolTip)));
		driver.findElement(pauseToolTip).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(barGraphToolTip)).click();
		helper.waitForBrowserToLoadCompletely(driver);
		wait.until(ExpectedConditions.visibilityOfElementLocated(view1_bar_agentCount_x_axis));

		helper.validate_list_data_axis(view1_bar_agentCount_x_axis, driver, "Test_View1","view1_bar_agentCount_x_axis");

		helper.validate_list_data_axis(view1_bar_percentage_x_axis, driver, "Test_View1","view1_bar_percentage_x_axis");
		helper.validate_list_data_axis(view1_bar_time_x_axis, driver, "Test_View1", "view1_bar_time_x_axis");
		logger.info("------bar chart validated-------");

	}

	
	/**
	 * @author guneet
	 * Method is validating filter value.
	 */
	@Test
	public void view1_validate_filter_dropdown()
	{
		logger.info("-----Start test case execution for :view5_validate_filter_dropdown------");
		helper.waitloader(driver);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterBtn)));
		helper.clickByJavascript(driver, driver.findElement(filterBtn));
		
		// Validate Organization FilterList
		helper.validate_filter_dropdown_data(organizationFilterList, driver, "Test_View1", "view1_FilterOrganizationValue_data");
		
		// Validate LOB FilterList
		helper.validate_filter_dropdown_data(lobFilterList, driver, "Test_View1", "view1_Filter_lobFilterList_data");
		
		// Validate SUB LOB FilterList
		helper.validate_filter_dropdown_data(subLobFilterList, driver, "Test_View1", "view1_Filter_subLobFilterList_data");		
		
		// Validate Functional Groups FilterList
		helper.validate_filter_dropdown_data(functionalGroupsFilterList, driver, "Test_View1", "view1_Filter_functionalGroupsFilterList_data");
		
		// Validate Sub Functional Groups FilterList
		helper.validate_filter_dropdown_data(subFunctionalGroupsFilterList, driver, "Test_View1", "view1_Filter_subFunctionalGroupsFilterList_data");
		
		// Validate  Function FilterList
		helper.validate_filter_dropdown_data(functionHandllerFilterList, driver, "Test_View1", "view1_Filter_functionFilterList_data");
	
		// Validate Language FilterList
		helper.validate_filter_dropdown_data(languageFilterList, driver, "Test_View1", "view1_Filter_languageFilterList_data");

		// Validate Time Zone FilterList
		helper.validate_filter_dropdown_data(timeZoneFilterList, driver, "Test_View1", "view1_Filter_timeZoneFilterList_data");
		logger.info("-----End test case execution for :view5_validate_filter_dropdown------");
	}
	
	/**
	 * @author NMakkar
	 * Method validating creation of Custom Views.
	 */
	@Test
	public void view1_Custom_Views() {
	//Getting View3 Title to validate text in My view.
		String string_to_validate = driver.findElement(View1).getText();
		
		//Calling Method for creation and validation for custom views.
		helper.create_validate_Custom_View(driver, wait, string_to_validate);

	}
	/**
	 * @author nkumar9 
	 * Method is to validate the drop down list values in line chart
	 * @throws InterruptedException 
	 */
	@Test
	public void view1_table_graph_chart_validate_ColumnCustomization(){
		WebDriverWait wait = new WebDriverWait(driver, 100);
		logger.info("-----Start test case execution for :view1_table_graph_chart_validate_ColumnCustomization------");
		driver.findElement(tabularViewToolTip).click();		
		ArrayList<String> tableColumns=(ArrayList<String>) helper.getTableColumns(tbl_View1, driver);
		logger.info("Table column before customization:"+tableColumns);
		driver.findElement(img_column_cust).click();		
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(lnk_cc_more)));
		driver.findElement(lnk_cc_more).click();
		helper.explicitWait(driver, 5);
		helper.deSelectCheckboxFromDD(lst_column_customization, driver, "Test_View1", "ds_lst_column_customization");
		helper.explicitWait(driver, 5);
		helper.selectCheckboxFromDD(lst_column_customization, driver, "Test_View1", "lst_column_customization");
		helper.explicitWait(driver, 5);
		driver.findElement(img_column_cust).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ArrayList<String> tableColumns_CC_UI=(ArrayList<String>) helper.getTableColumns(tbl_View1, driver);
		tableColumns.addAll(ExcelCache.getExpectedListData("Test_View1" , "lst_column_customization" ));
		tableColumns.removeAll(ExcelCache.getExpectedListData("Test_View1" , "ds_lst_column_customization" ));
		boolean actulValue=Helper.compareTwoList(tableColumns, tableColumns_CC_UI);
		Assert.assertEquals(actulValue, true);		
		logger.info("-----End of test case execution for :view1_table_graph_chart_validate_ColumnCustomization------");
	
	}
}
