package com.xavient.test.script;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.xavient.pages.DashBoardView;
import com.xavient.util.BaseClass;
import com.xavient.util.ExcelCache;
import com.xavient.util.Helper;

public class Test_View5 extends BaseClass implements  DashBoardView{
	

WebDriver driver;
 Helper helper;
 WebDriverWait wait;
 Logger logger = Logger.getLogger(Test_View3.class);
 
	/**
	 * Calling Before Test for navigating to particular view5.
	 * @param browser
	 * @author guneet
	 */
	@BeforeMethod
	@Parameters({ "browser" })
	public void Before_Test(@Optional("Chrome") String browser) {
		parent = extent.startTest("View 5","Verifing scenarios of View 5");

		driver = Browser_Selection(browser);
		parent.log(LogStatus.INFO, "Browser oppened successfully  :"+browser);

		// Initialize
		helper = new Helper();
		wait = new WebDriverWait(driver, 10);

		// Handling PopUP with AutoIT , Need to have this screen as active when this method is being executed.
		helper.handle_popup();
		parent.log(LogStatus.INFO, "Alert Closed");
		// Login and Navigating to View
		helper.login(driver);
		helper.waitForBrowserToLoadCompletely(driver);
		By[] element = { View, CumulativePerformance, View5 };
		helper.navigate_view(element, wait, driver);
		parent.log(LogStatus.INFO, "Navigated to View 5");
		logger.info("------Before Test------");	}

	/**
	 * @author guneet
	 * Method is validating stack chart
	 */
	@Test
	public void view5_stack_chart()  {
	child = extent.startTest("view5_stack_chart","Verify Stack Chart");

	helper.waitloader(driver);
	driver.findElement(chartCombineStackToolTip).click();
	child.log(LogStatus.INFO, "Clicked Stacked chart tool tip");

	helper.validate_list_data(view5_AgentCount_sub_label, driver, "Test_View5", "view5_AgentCount_sub_label");
	child.log(LogStatus.PASS, "Verified Agent Count label");
	helper.validate_list_data(view5_Percentage_sub_label, driver, "Test_View5", "view5_Percentage_sub_label");
	child.log(LogStatus.PASS, "Verified Percentage label");
	helper.validate_list_data(view5_Time_sub_label, driver, "Test_View5", "view5_Time_sub_label");
	child.log(LogStatus.PASS, "Verified Time label");
	
	helper.validate_table_names((WebElement)driver.findElement(view5_AgentCount_y_axis_label), "Test_View5", "view5_AgentCount_y_axis_label");
	child.log(LogStatus.PASS, "Verified Agent Count y axis label");
	helper.validate_table_names((WebElement)driver.findElement(view5_Percentage_y_axis_label), "Test_View5", "view5_Percentage_y_axis_label");
	child.log(LogStatus.PASS, "Verified Percentage y axis label");
	helper.validate_table_names((WebElement)driver.findElement(view5_Time_y_axis_label), "Test_View5", "view5_Time_y_axis_label");
	child.log(LogStatus.PASS, "Verified Time y axis label");
	
	helper.validate_list_data_axis(view5_AgentCount_x_axis_label, driver,"Test_View5", "view5_AgentCount_x_axis_label");
	child.log(LogStatus.PASS, "Verified AgentCount x axis label");
	helper.validate_list_data_axis(view5_Percentage_x_axis_label, driver, "Test_View5", "view5_Percentage_x_axis_label");
	child.log(LogStatus.PASS, "Verified Percentage x axis label");
	helper.validate_list_data_axis(view5_Time_x_axis_label, driver, "Test_View5", "view5_Time_x_axis_label");
	child.log(LogStatus.PASS, "Verified Time x axis label");
	
	}
	
	/**
	 * @author guneet
	 * Method is validating filter name
	 */
	@Test
	public void view5_validate_filter_name()
	{		
		child = extent.startTest("view5_validate_filter_name","Verify filter name");
		logger.info("-----Start test case execution for :view5_validate_filter_name------");
		helper.waitloader(driver);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterBtn)));
		helper.clickByJavascript(driver, driver.findElement(filterBtn));
		child.log(LogStatus.INFO, "Clicked Filter Button");

		helper.validate_filter_data(filterTxt, driver, "Test_View5", "view5_FilterName_data");
		logger.info("-----End test case execution for :view5_validate_filter_name------");
		child.log(LogStatus.PASS, "Verified Filter Name");

	}
	
	/**
	 * @author guneet
	 * Method is validating filter value.
	 */
	@Test
	public void view5_validate_filter_dropdown()
	{
		child = extent.startTest("view5_validate_filter_dropdown","Verify filter dropdown value");
		logger.info("-----Start test case execution for :view5_validate_filter_dropdown------");
		helper.waitloader(driver);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterBtn)));
		helper.clickByJavascript(driver, driver.findElement(filterBtn));
		child.log(LogStatus.INFO, "Clicked Filter Button");

		// Validate Start Time FilterList
		helper.validate_filter_dropdown_data(startTimeHandllerFilterList, driver, "Test_View5", "view5_FilteIntervalRangeStart_data");
		child.log(LogStatus.PASS, "Verified Start Time Handller Filter List");

		// Validate End Time FilterList
		helper.validate_filter_dropdown_data(endTimeHandllerFilterList, driver, "Test_View5", "view5_FilteIntervalRangeEnd_data");
		child.log(LogStatus.PASS, "Verified End Time Handller Filter List");
		
		// Validate Organization FilterList
		helper.validate_filter_dropdown_data(organizationFilterList, driver, "Test_View5", "view5_FilterOrganizationValue_data");
		child.log(LogStatus.PASS, "Verified Organization Filter List");
		
		// Validate COE FilterList
		helper.validate_filter_dropdown_data(coeFilterList, driver, "Test_View5", "view5_Filter_coeFilterList_data");		
		child.log(LogStatus.PASS, "Verified");
		
		// Validate LOB FilterList
		helper.validate_filter_dropdown_data(lobFilterList, driver, "Test_View5", "view5_Filter_lobFilterList_data");
		child.log(LogStatus.PASS, "Verified COE Filter List");
		
		// Validate SUB LOB FilterList
		helper.validate_filter_dropdown_data(subLobFilterList, driver, "Test_View5", "view5_Filter_subLobFilterList_data");		
		child.log(LogStatus.PASS, "Verified Sub Lob Filter List");
		
		// Validate Functional Groups FilterList
		helper.validate_filter_dropdown_data(functionalGroupsFilterList, driver, "Test_View5", "view5_Filter_functionalGroupsFilterList_data");
		child.log(LogStatus.PASS, "Verified Functional Groups Filter List");
		
		// Validate Sub Functional Groups FilterList
		helper.validate_filter_dropdown_data(subFunctionalGroupsFilterList, driver, "Test_View5", "view5_Filter_subFunctionalGroupsFilterList_data");
		child.log(LogStatus.PASS, "Verified Sub Functional Groups Filter List");
		
		// Validate  Function FilterList
		helper.validate_filter_dropdown_data(functionHandllerFilterList, driver, "Test_View5", "view5_Filter_functionFilterList_data");
		child.log(LogStatus.PASS, "Verified Function Handller Filter List");
		
		// Validate Language FilterList
		helper.validate_filter_dropdown_data(languageFilterList, driver, "Test_View5", "view5_Filter_languageFilterList_data");
		child.log(LogStatus.PASS, "Verified Language Filter List");
		
		// Validate Time Zone FilterList
		helper.validate_filter_dropdown_data(timeZoneFilterList, driver, "Test_View5", "view5_Filter_timeZoneFilterList_data");
		child.log(LogStatus.PASS, "Verified Time Zone Filter List");
		logger.info("-----End test case execution for :view5_validate_filter_dropdown------");
	}
	/**
	 * Validating Table and Column data.
	 * @author guneet
	 */
		@Test
		public void view5_validate_table_data()  {
			child = extent.startTest("view5_validate_table_data","Verify Forecast Staffing Table Data");
			logger.info("-----Start test case execution for :view3_validate_table_data------");
			helper.waitloader(driver);
			helper.waitForBrowserToLoadCompletely(driver);
			driver.findElement(pauseToolTip).click();
			child.log(LogStatus.INFO, "Clicked pause tooltip");
			helper.validate_list_data_using_attribute(view5_ForecastStaffing_table , driver , "Test_View5" , "view5_ForecastStaffing_table" );	
			logger.info("-----End of test case execution for :view5_validate_table_data------");
			child.log(LogStatus.PASS, "Verified Forecast Staffing Table Data");
		}
		
		/**
		 * @author NMakkar
		 * Method validating creation of Custom Views.
		 */
		@Test
		public void view5_Custom_Views() {
			child = extent.startTest("view5_Custom_Views","validating creation of Custom Views");

		//Getting View3 Title to validate text in My view.
			String string_to_validate = driver.findElement(View5).getText();
			
			//Calling Method for creation and validation for custom views.
			helper.create_validate_Custom_View(driver, wait, string_to_validate);
			child.log(LogStatus.PASS, "Verified creation of Custom Views");
		}
		/**
		 * @author nkumar9 
		 * Method is to validate the drop down list values in line chart
		 * @throws InterruptedException 
		 */
		@Test
		public void view5_table_graph_chart_validate_ColumnCustomization(){
			WebDriverWait wait = new WebDriverWait(driver, 100);
			helper.explicitWait(driver, 5);
			helper.waitloader(driver);
			helper.waitForBrowserToLoadCompletely(driver);
			driver.findElement(pauseToolTip).click();
			logger.info("-----Start test case execution for :view5_table_graph_chart_validate_ColumnCustomization------");
			driver.findElement(tabularViewToolTip).click();		
			ArrayList<String> tableColumns=(ArrayList<String>) helper.getTableColumns(tbl_View5, driver,"view5");
			logger.info("Table column before customization:"+tableColumns);
			driver.findElement(img_column_cust).click();		
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(lnk_cc_more)));
			driver.findElement(lnk_cc_more).click();
			helper.explicitWait(driver, 5);
			helper.deSelectCheckboxFromDD(lst_column_customization, driver, "Test_View5", "ds_lst_column_customization");
			helper.explicitWait(driver, 5);
			helper.selectCheckboxFromDD(lst_column_customization, driver, "Test_View5", "lst_column_customization");
			helper.explicitWait(driver, 5);
			driver.findElement(img_column_cust).click();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			ArrayList<String> tableColumns_CC_UI=(ArrayList<String>) helper.getTableColumns(tbl_View5, driver,"view5");
			tableColumns.addAll(ExcelCache.getExpectedListData("Test_View5" , "lst_column_customization" ));
			tableColumns.removeAll(ExcelCache.getExpectedListData("Test_View5" , "ds_lst_column_customization" ));
			boolean actulValue=Helper.compareTwoList(tableColumns, tableColumns_CC_UI);
			Assert.assertEquals(actulValue, true);		
			logger.info("-----End of test case execution for :view5_table_graph_chart_validate_ColumnCustomization------");
		
		}
}