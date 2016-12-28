package com.xavient.test.script;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import com.xavient.pages.DashBoardView;
import com.xavient.util.BaseClass;
import com.xavient.util.ExcelCache;
import com.xavient.util.Helper;


/**
 * @author csingh5
 *
 */
public class Test_View15 extends BaseClass implements DashBoardView {

	WebDriver driver;
	Helper helper;
	WebDriverWait wait;
	Logger logger = Logger.getLogger(Test_View15.class);

	/**
	 * Calling Before Test for navigating to particular view15.
	 * @param browser
	 * @author csingh5
	 */


	@BeforeMethod
	@Parameters({ "browser" })
	public void Before_Test(@Optional("Chrome") String browser) {
		parent = extent.startTest("Test View15","Verify Different scenarios in View 15");
		driver = Browser_Selection(browser);
		parent.log(LogStatus.PASS, browser + " browser is opened successfully");
		logger.info(browser + " is opened successfully");
		// Initialize
		helper = new Helper();
		wait = new WebDriverWait(driver, 30);

		// Handling PopUP with AutoIT , Need to have this screen as active
		// when this method is being executed.
		helper.handle_popup();
		parent.log(LogStatus.PASS, "Alert pop is closed");
		// Login and Navigating to View
		helper.login(driver);
		parent.log(LogStatus.PASS, "User is logged into application");
		helper.waitForBrowserToLoadCompletely(driver);
		helper.clickByJavascript(driver, driver.findElement(View));
		parent.log(LogStatus.PASS, "Clicked on the Views");
		helper.clickByJavascript(driver, driver.findElement(Queue_And_Agent_Overview));
		parent.log(LogStatus.PASS, "Clicked on the Queue And Agent Overview");
		helper.clickByJavascript(driver, driver.findElement(View15));
		parent.log(LogStatus.PASS, "Clicked on the View 15");
		helper.clickByJavascript(driver, driver.findElement(pauseToolTip));
		logger.info("------Before Test------");
	}

	/**
	 * Validating Table and Column data.
	 * 
	 * @author csingh5
	 */
	@Test
	public void view15_validate_table_data() {

		child = extent.startTest("view15_validate_table_data","Verify Table Data");
		helper.waitForLoad(2);
		logger.info("-----Start test case execution for :view15_validate_table_data------");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(view15_today_data)));
		//Validating today data table
		helper.validate_table_names(driver.findElement(view15_today_data), "Test_View15", "view15_todays_data_details");
		child.log(LogStatus.PASS, "Verified the today data table headline");
		helper.validate_table_columns(view15_today_data_table, driver, "", "Test_View15", "view15_today_data_table");
		child.log(LogStatus.PASS, "Verified the today data table columns");
		//Validating current data table
		helper.validate_table_names(driver.findElement(view15_current_data), "Test_View15", "view15_current_data_details");
		child.log(LogStatus.PASS, "Verified the current data table headline");
		helper.validate_table_columns(view15_current_data_table, driver, "", "Test_View15", "view15_current_data_table");
		child.log(LogStatus.PASS, "Verified the current data table columns");
		//Validating Half hour data table
		helper.validate_table_names(driver.findElement(view15_Half_Hour_data), "Test_View15", "view15_view15_Half_Hour_data_details");
		child.log(LogStatus.PASS, "Verified the Half hour table headline");
		helper.validate_table_columns(view15_Half_Hour_data_table, driver, "", "Test_View15", "view15_view15_Half_Hour_data_table");
		child.log(LogStatus.PASS, "Verified the Half hour data table columns");
		//Validating Agents Statistics for all COEs data table
		helper.validate_table_names(driver.findElement(view15_Agents_Statistics_data), "Test_View15", "view15_Agents_Statistics_data");
		child.log(LogStatus.PASS, "Verified the Agents Statistics for all COEs table headline");
		helper.validate_table_columns(view15_Agents_Statistics_data_table, driver, "", "Test_View15", "view15_Agents_Statistics_data_table");
		child.log(LogStatus.PASS, "Verified the Agents Statistics for all COEs table columns");
		//Validating Site details data table
		helper.validate_table_names(driver.findElement(view15_Site_Detail_data), "Test_View15", "view15_Site_Detail_data");
		child.log(LogStatus.PASS, "Verified the Site details table headline");
		helper.validate_table_columns(view15_Site_Detail_data_table, driver, "", "Test_View15", "view15_Site_Detail_data_table");
		child.log(LogStatus.PASS, "Verified the Site details table columns");
		//Validating Agent details data tables
		helper.waitForLoad(2);
		helper.validate_table_names(driver.findElement(view15_Agent_Detail_data), "Test_View15", "view15_Agent_Detail_data");
		child.log(LogStatus.PASS, "Verified the Agent details table headline");
		helper.validate_table_columns(view15_Agent_Detail_data_table_start, driver, view15_Agent_Detail_data_table_end, "Test_View15", "view15_Agent_Detail_data_table");
		child.log(LogStatus.PASS, "Verified the Agent details table columns");
		logger.info("-----End test case execution for :view15_validate_table_data------");
	}

	/**
	 * @author csingh
	 * Method is validating table pagination
	 */
	@Test
	public void  view15_table_pagination() {
		child = extent.startTest("View15 Table Pagination","Verify Table Pagination");
		logger.info("-----Start test case execution for :view15_table_pagination------");
		//Validating pagination dropdown value
		Select select = new Select(driver.findElement(pagerPageDrop));
		Select select2 = new Select(driver.findElement(pagerPageDrop));
		child.log(LogStatus.PASS, "Select the grid page drop down value");
		List<WebElement> dCount = select2.getOptions();
		int dropList[] = {5,10,25,50,100};
		for (int i = 0; i < dCount.size()-1; i++) {
			select.selectByIndex(i);
			Assert.assertEquals(dropList[i], Integer.parseInt(select2.getFirstSelectedOption().getText()));
			child.log(LogStatus.PASS, "Value of dropdown "+select2.getFirstSelectedOption().getText()+" items per page");
		}
		if(!helper.isElementPresent(driver, noRecordData))
		{
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(pagerGridCount)));
			String count=driver.findElement(pagerGridCount).getText();
			Object[] pagerGridCountList = count.split(" "); 

			//Validating texts 
			Assert.assertEquals("of", pagerGridCountList[1]);
			Assert.assertEquals("items", pagerGridCountList[3]);		
			Assert.assertEquals(" items per page", driver.findElement(pagerPageDropText).getText());

			count = count.split(" ")[2];
			if(Integer.parseInt(count)>5){

				//Checking pagination first and previous is disabled
				Assert.assertEquals("true", driver.findElement(pagerFirst).getAttribute("disabled"),"Pagination first must be disabled");
				child.log(LogStatus.PASS, "Pagination first is disabled");
				Assert.assertEquals("true", driver.findElement(pagerPrevious).getAttribute("disabled"),"Pagination Previous must be disabled");
				child.log(LogStatus.PASS, "Pagination Previous is disabled");
				//clicking pagination last button
				driver.findElement(pagerLast).click();
				child.log(LogStatus.PASS, "Clicked on the Last Page");
				Assert.assertEquals("true", driver.findElement(pagerLast).getAttribute("disabled"),"Pagination Last must be disabled");
				child.log(LogStatus.PASS, "Pagination Last is disabled");
				Assert.assertEquals("true", driver.findElement(pagerNext).getAttribute("disabled"),"Pagination Next must be disabled");
				child.log(LogStatus.PASS, "Pagination Next is disabled");
				driver.findElement(pagerPrevious).click();
				child.log(LogStatus.PASS, "Clicked on the Previous Page");
			}
		}
		else
		{
			child.log(LogStatus.PASS, "Data do not present for Agents Details Table");
			logger.info("-----Data does not present for Agents Details Table------");
		}

		logger.info("-----End of test case execution for :view15_table_pagination------");
	}

	/**
	 * @author csingh
	 * Method is validating table sorting
	 */
	@Test
	public void view15_table_sorting() {
		logger.info("-----Start test case execution for :view15_table_sorting------");
		child = extent.startTest("View15 Table Sorting","Verify Table Sorting");
		if(!helper.isElementPresent(driver, noRecordData))
		{
			LinkedList<String>  tableData = new LinkedList<String>();
			int tableComtentCount = helper.getWebelentSize(view3_agent_details_data, driver);
			logger.info("Agent details table ");
			int totalTableCount = helper.getWebelentSize(By.xpath(view3_Agent_table_data_start), driver);
			int noofRecords = driver.findElements(By.xpath("//div[@class='ui-grid-canvas']/div")).size();
			child.log(LogStatus.INFO, "Number of Records on the page "+noofRecords);
			if(noofRecords>1)
			{
				if (tableComtentCount > 0) {
					for (int i = 1; i <= totalTableCount; i++) {
						for (int j = 0; j < 2; j++) {
							driver.findElement(By.xpath(view3_Agent_table_data_start + "[" + i + "]" + view3_Agent_table_data_sort_arrow)).click();
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
				else
				{
					child.log(LogStatus.INFO, "Column of the Table is not present");
					Assert.fail("----Column of the Table is not present------");
					logger.info("----Column of the Table is not present------");
				}
			} else
			{
				child.log(LogStatus.PASS, "Only one record is present so no need to sorting");
				logger.info("----Only one record is present so no need to sorting------");
			}

		} else
		{
			child.log(LogStatus.INFO, "Data does not present for Agents Details Table");
			logger.info("-----Data does not present for Agents Details Table------");
		}

		logger.info("-----End of test case execution for :view15_table_sorting------");
	}

	// Graph test cases are not completed as we are facing the java script error on the graphs

	/**
	 * @author csingh
	 * Method is validating static data set of line graph 
	 */
	@Test(enabled=false)
	public void view15_validate_line_graph_data()  {
		logger.info("-----Start test case execution for :view15_validate_line_graph_data------");
		child = extent.startTest("View15 Line Graph Data","Verify Line Graph Data");
		//Navigating to line chart page. 	
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(lineChartToolTip)));
		driver.findElement(lineChartToolTip).click();
		child.log(LogStatus.PASS, "Clicked on the Line Chart icon");
		logger.info("-----End of test case execution for :view_validate_line_graph_data------");
	}

	/**
	 * @author csingh
	 * Method is validating filter name
	 */
	@Test
	public void view15_validate_filter_name()
	{
		logger.info("-----Start test case execution for :view15_validate_filter_name------");
		child = extent.startTest("View15 Verify Filters Name","Verify Filter Name");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterBtn)));
		helper.clickByJavascript(driver, driver.findElement(filterBtn));
		child.log(LogStatus.PASS, "Clicked on the Filter Icon");
		helper.validate_filter_data(filterTxt, driver, "Test_View15", "view15_FilterName_data");
		child.log(LogStatus.PASS, "Verify all filters name successfully");
		logger.info("-----End test case execution for :view15_validate_filter_name------");
	}

	/**
	 * @author csingh
	 * Method is validating filter value.
	 */

	@Test
	public void view15_validate_filter_dropdown()
	{
		logger.info("-----Start test case execution for :view15_validate_filter_dropdown------");
		child = extent.startTest("View15 Verify Filters Drop down Value","Verify Filters Drop down Value");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterBtn)));
		helper.clickByJavascript(driver, driver.findElement(filterBtn));
		child.log(LogStatus.PASS, "Clicked on the filter icon");
		// Validate Organization FilterList
		helper.validate_filter_dropdown_data(organizationFilterList, driver, "Test_View15", "view15_FilterOrganizationValue_data");
		child.log(LogStatus.PASS, "Verified the Organization Filter");
		// Validate Customer Region FilterList
		helper.validate_filter_dropdown_data(customerRegionList, driver, "Test_View15", "view15_Filter_customerRegionList_data");
		child.log(LogStatus.PASS, "Verified the Customer Region Filter");
		// Validate COE FilterList
		helper.validate_filter_dropdown_data(coeFilterList, driver, "Test_View15", "view15_Filter_coeFilterList_data");		
		child.log(LogStatus.PASS, "Verified the COE Filter");
		// Validate LOB FilterList
		helper.validate_filter_dropdown_data(lobFilterList, driver, "Test_View15", "view15_Filter_lobFilterList_data");
		child.log(LogStatus.PASS, "Verified the LOB Filter");
		// Validate SUB LOB FilterList
		helper.validate_filter_dropdown_data(subLobFilterList, driver, "Test_View15", "view15_Filter_subLobFilterList_data");		
		child.log(LogStatus.PASS, "Verified the SUB LOB Filter");
		// Validate Functional Groups FilterList
		helper.validate_filter_dropdown_data(functionalGroupsFilterList, driver, "Test_View15", "view15_Filter_functionalGroupsFilterList_data");
		child.log(LogStatus.PASS, "Verified the Functional Groups Filter");
		// Validate Sub Functional Groups FilterList
		helper.validate_filter_dropdown_data(subFunctionalGroupsFilterList, driver, "Test_View15", "view15_Filter_subFunctionalGroupsFilterList_data");
		child.log(LogStatus.PASS, "Verified the Sub Functional Groups Filter");
		// Validate Language FilterList
		helper.validate_filter_dropdown_data(languageFilterList, driver, "Test_View15", "view15_Filter_languageFilterList_data");
		child.log(LogStatus.PASS, "Verified the Language Filter");
		// Validate Time Zone FilterList
		helper.validate_filter_dropdown_data(timeZoneFilterList, driver, "Test_View15", "view15_Filter_timeZoneFilterList_data");
		child.log(LogStatus.PASS, "Verified the Time Zone Filter");
		logger.info("-----End test case execution for :view15_validate_filter_dropdown------");
	}

	/**
	 * @author csingh 
	 * Method is to validate the ColumnCustomization for Today's Data
	 * 
	 */
	@Test
	public void view15_validate_ColumnCustomization_Today_Data(){
		WebDriverWait wait = new WebDriverWait(driver, 100);
		logger.info("-----Start test case execution for :view15_validate_ColumnCustomization_Today_Data------");	
		child = extent.startTest("View15 Verify Column Customization Today Data","Verify Today Data Column Customization");
		ArrayList<String> tableColumns=(ArrayList<String>) helper.getTableColumns(view15_today_data_col, driver);
		logger.info("Table column before customization:"+tableColumns);
		driver.findElement(todaydata_columnCustomization).click();	
		child.log(LogStatus.PASS, "Clicked on the Today Data Column Customization icon");
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(lnk_cc_more)));
		driver.findElement(lnk_cc_more).click();
		child.log(LogStatus.PASS, "Clicked on the more link");
		helper.deSelectCheckboxFromDD(lst_column_customization, driver, "Test_View15", "view15_today_data_ds_lst_column_customization");
		child.log(LogStatus.PASS, "Un-selected the different Column Names");
		helper.selectCheckboxFromDD(lst_column_customization, driver, "Test_View15", "view15_today_data_lst_column_customization");
		child.log(LogStatus.PASS, "Selected the different Column Names");
		driver.findElement(img_column_cust).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		helper.waitForLoad(5);
		ArrayList<String> tableColumns_CC_UI=(ArrayList<String>) helper.getTableColumns(view15_today_data_col, driver);
		tableColumns.addAll(ExcelCache.getExpectedListData("Test_View15" , "view15_today_data_lst_column_customization" ));
		tableColumns.removeAll(ExcelCache.getExpectedListData("Test_View15" , "view15_today_data_ds_lst_column_customization" ));
		boolean actulValue=Helper.compareTwoList(tableColumns, tableColumns_CC_UI);
		Assert.assertEquals(actulValue, true);		
		child.log(LogStatus.PASS, "Verified the Today Data Column Customization successfully");
		logger.info("-----End of test case execution for :view15_validate_ColumnCustomization_Today_Data------");
	}

	/**
	 * @author csingh 
	 * Method is to validate the ColumnCustomization for Current Data
	 * 
	 */
	@Test
	public void view15_validate_ColumnCustomization_Current_Data(){
		logger.info("-----Start test case execution for :view15_validate_ColumnCustomization_Current_Data------");	
		child = extent.startTest("View15 Verify Column Customization Current Data","Verify Current Data Column Customization");
		ArrayList<String> tableColumns=(ArrayList<String>) helper.getTableColumns(view15_current_data_col, driver);
		logger.info("Table column before customization:"+tableColumns);
		driver.findElement(currentdata_columnCustomization).click();
		child.log(LogStatus.PASS, "Clicked on the Current Data Column Customization icon");
		helper.deSelectCheckboxFromDD(lst_column_customization, driver, "Test_View15", "view15_current_data_ds_lst_column_customization");
		child.log(LogStatus.PASS, "Un-selected the different Column Names");
		driver.findElement(currentdata_columnCustomization).click();
		child.log(LogStatus.PASS, "Clicked on the Current Data Column Customization icon");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		helper.waitForLoad(5);
		ArrayList<String> tableColumns_CC_UI=(ArrayList<String>) helper.getTableColumns(view15_current_data_col, driver);
		tableColumns.removeAll(ExcelCache.getExpectedListData("Test_View15" , "view15_current_data_ds_lst_column_customization" ));
		boolean actulValue=Helper.compareTwoList(tableColumns, tableColumns_CC_UI);
		Assert.assertEquals(actulValue, true);		
		child.log(LogStatus.PASS, "Verified the Current Data Column Customization successfully");
		logger.info("-----End of test case execution for :view15_validate_ColumnCustomization_Current_Data------");
	}

	/**
	 * @author csingh 
	 * Method is to validate the ColumnCustomization for Half Hour Data
	 * 
	 */
	@Test
	public void view15_validate_ColumnCustomization_HalfHour_Data(){
		WebDriverWait wait = new WebDriverWait(driver, 100);
		logger.info("-----Start test case execution for :view15_validate_ColumnCustomization_HalfHour_Data------");	
		child = extent.startTest("View15 Verify Column Customization Half Hour Data","Verify HalfHour Data Column Customization");
		ArrayList<String> tableColumns=(ArrayList<String>) helper.getTableColumns(view15_halfhour_data_col, driver);
		logger.info("Table column before customization:"+tableColumns);
		driver.findElement(halfhourtdata_columnCustomization).click();	
		child.log(LogStatus.PASS, "Clicked on the HalfHour Data Column Customization icon");
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(view15_halfhour_lnk_cc_more)));
		driver.findElement(view15_halfhour_lnk_cc_more).click();
		child.log(LogStatus.PASS, "Clicked on the more link");
		helper.deSelectCheckboxFromDD(lst_column_customization, driver, "Test_View15", "view15_halfhour_data_ds_lst_column_customization");
		child.log(LogStatus.PASS, "Un-selected the different Column Names");
		helper.selectCheckboxFromDD(lst_column_customization, driver, "Test_View15", "view15_halfhour_data_lst_column_customization");
		child.log(LogStatus.PASS, "Selected the different Column Names");
		driver.findElement(halfhourtdata_columnCustomization).click();	
		child.log(LogStatus.PASS, "Clicked on the HalfHour Data Column Customization icon");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		helper.waitForLoad(5);
		ArrayList<String> tableColumns_CC_UI=(ArrayList<String>) helper.getTableColumns(view15_halfhour_data_col, driver);
		tableColumns.addAll(ExcelCache.getExpectedListData("Test_View15" , "view15_halfhour_data_lst_column_customization" ));
		tableColumns.removeAll(ExcelCache.getExpectedListData("Test_View15" , "view15_halfhour_data_ds_lst_column_customization" ));
		boolean actulValue=Helper.compareTwoList(tableColumns, tableColumns_CC_UI);
		Assert.assertEquals(actulValue, true);		
		child.log(LogStatus.PASS, "Verified the Half Hour Data Column Customization successfully");
		logger.info("-----End of test case execution for :view15_validate_ColumnCustomization_HalfHour_Data------");
	}

	/**
	 * @author csingh 
	 * Method is to validate the ColumnCustomization for Agents Statistics Data
	 * 
	 */
	@Test
	public void view15_validate_ColumnCustomization_Agents_Statistics_Data(){
		WebDriverWait wait = new WebDriverWait(driver, 100);
		logger.info("-----Start test case execution for :view15_validate_ColumnCustomization_Agents_Statistics_Data------");		
		child = extent.startTest("View15 Verify Column Customization Agents Statistics Data","Verify Agents Statistics Data Column Customization");
		ArrayList<String> tableColumns=(ArrayList<String>) helper.getTableColumns(view15_Agent_data_col, driver);
		logger.info("Table column before customization:"+tableColumns);
		driver.findElement(Agent_data_columnCustomization).click();	
		child.log(LogStatus.PASS, "Clicked on the Agents Statistics Data Column Customization icon");
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(view15_Agent_lnk_cc_more)));
		driver.findElement(view15_Agent_lnk_cc_more).click();
		child.log(LogStatus.PASS, "Clicked on the more link");
		helper.deSelectCheckboxFromDD(lst_column_customization, driver, "Test_View15", "view15_Agent_data_ds_lst_column_customization");
		child.log(LogStatus.PASS, "Un-selected the different Column Names");
		helper.selectCheckboxFromDD(lst_column_customization, driver, "Test_View15", "view15_Agent_data_lst_column_customization");
		child.log(LogStatus.PASS, "Selected the different Column Names");
		driver.findElement(Agent_data_columnCustomization).click();	
		child.log(LogStatus.PASS, "Clicked on the Agents Statistics Data Column Customization icon");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		helper.waitForLoad(5);
		ArrayList<String> tableColumns_CC_UI=(ArrayList<String>) helper.getTableColumns(view15_Agent_data_col, driver);
		tableColumns.add("Agents On Other ACD Calls");
		tableColumns.removeAll(ExcelCache.getExpectedListData("Test_View15" , "view15_Agent_data_ds_lst_column_customization" ));
		boolean actulValue=Helper.compareTwoList(tableColumns, tableColumns_CC_UI);
		logger.info("Table column before customization: "+tableColumns_CC_UI);
		Assert.assertEquals(actulValue, true);		
		child.log(LogStatus.PASS, "Verified the Agents Statistics Data Column Customization successfully");
		logger.info("-----End of test case execution for :view15_validate_ColumnCustomization_Agents_Statistics_Data------");
	}

	/**
	 * @author csingh
	 * Method validating creation of Custom View.
	 */
	@Test
	public void view15_Custom_Views() {
		child = extent.startTest("view15_Custom_Views","Verify Custom View");
		logger.info("-----Start test case execution for :view15_Custom_Views------");
		//Getting View3 Title to validate text in My view.
		String string_to_validate = "COE With Agent View";
		child.log(LogStatus.INFO, "Adding the Custom View");
		//Calling Method for creation and validation for custom views.
		helper.create_validate_Custom_View(driver, wait, string_to_validate);
		child.log(LogStatus.PASS, "Deleted the Created Custom View");
		child.log(LogStatus.PASS, "Verified the Custom View successfully");
		logger.info("-----End of test case execution for :view15_Custom_Views------------");
	}
}
