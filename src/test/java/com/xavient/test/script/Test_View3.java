package com.xavient.test.script;

import java.util.ArrayList;
import java.util.List;
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

import com.xavient.pages.DashBoardView;
import com.xavient.util.BaseClass;
import com.xavient.util.ExcelCache;
import com.xavient.util.Helper;
public class Test_View3 extends BaseClass implements  DashBoardView {

	//Object Declaration.
	WebDriver driver;
	Helper helper;
	WebDriverWait wait;
	Logger logger = Logger.getLogger(Test_View3.class);

	/**
	 * Calling Before Test for navigating to particular view3. 
	 * @param browser
	 * @author NMakkar
	 */
	@BeforeMethod
	@Parameters({ "browser"})
	public void Before_Test(@Optional("Chrome") String browser) {
		driver = Browser_Selection(browser);
		//Initialize
		helper = new Helper();
		wait = new WebDriverWait(driver , 5);

		//Handling PopUP with AutoIT , Need to have this screen as active when this method is being executed.
		helper.handle_popup();
		
		//Login and Navigating to View
		helper.login(driver);
		By[] element = { View, Queue_And_Agent_Overview, View3 };
		helper.navigate_view(element, wait, driver);
		logger.info("------Before Test------");
	}

	/**
	 * Validating Table and Column names.
	 * @author NMakkar
	 */
	@Test(enabled=true, priority =1)
	public void view3_validate_table_data()  {

		helper.validate_table_names( driver.findElement(view3_curr_data) ,  "Test_View3" , "view3_curr_data" );	
		helper.validate_table_columns( view3_curr_data_table , driver , "" , "Test_View3" , "view3_curr_data_table" );

		helper.validate_table_names( driver.findElement(view3_today_data) ,  "Test_View3" , "view3_today_data" );
		helper.validate_table_columns( view3_today_data_table , driver , "" , "Test_View3" , "view3_today_data_table" );

		helper.validate_table_names( driver.findElement(view3_curr_agent_stats_tbl) ,  "Test_View3" , "view3_curr_agent_stats_tbl" );
		helper.validate_table_columns( view3_curr_agent_stats_col , driver , "" , "Test_View3" , "view3_curr_agent_stats_col" );

		helper.validate_table_names( driver.findElement(view3_agent_details) ,  "Test_View3" , "view3_agent_details" );	
		helper.validate_table_columns( view3_Agent_table_data_start , driver , view3_Agent_table_data_end , "Test_View3" , "view3_Agent_table_data" );
	}

	/**
	 * @author NMakkar
	 * Method is validating static data set of line graph 
	 */
	@Test(enabled=true,priority=4)
	public void view3_validate_line_graph_data()  {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(lineChartToolTip)));
		driver.findElement(lineChartToolTip).click();

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(view3_line_graph_title)));

		helper.validate_table_names(driver.findElement(view3_line_graph_title), "Test_View3", "view3_line_graph_title");
		helper.validate_table_names(driver.findElement(view3_line_graph_y_axis), "Test_View3", "view3_line_graph_y_axis");
		helper.validate_table_names(driver.findElement(view3_line_graph_header), "Test_View3", "view3_line_graph_header");
		helper.validate_list_data_axis(view3_graph_x_axis, driver , "Test_View3" , "view3_line_graph_x_axis" );	
	}

	/**
	 * Method is validating static data set of bar graph 
	 * @author NMakkar
	 */
	@Test(enabled=true,priority =5)
	public void view3_validate_bar_graph_data()  {
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(barGraphToolTip)));
		driver.findElement(barGraphToolTip).click();

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(view3_bar_graph_title)));

		helper.validate_table_names(driver.findElement(view3_bar_graph_title), "Test_View3", "view3_bar_graph_title");
		helper.validate_table_names(driver.findElement(view3_bar_graph_y_axis), "Test_View3", "view3_bar_graph_y_axis");
		helper.validate_table_names(driver.findElement(view3_bar_graph_header), "Test_View3", "view3_bar_graph_header");
		helper.validate_list_data_axis(view3_graph_x_axis, driver , "Test_View3" , "view3_bar_graph_x_axis" );

	}

	/**
	 * @author guneet
	 * Method is validating table sorting
	 */
	@Test(enabled=true,priority =2)
	public void view3_table_sorting() {
		
		helper.validate_table_sorting(driver,view3_agent_details_data,view3_Agent_table_data_start,view3_Agent_table_data_sort_arrow,tableRowStart,tableRowLast);

	}

	/**
	 * @author guneet
	 * Method is validating table pagination
	 */
	@Test(enabled=true, priority = 3)
	public void  view3_table_pagination() {
		
		int dropList[] = {5,10,25,50,100};

		helper.validate_pagination_navigation_arrow(driver, pagerFirst, pagerPrevious, pagerLast, pagerNext,pagerGridCount);

		helper.navigation_pagination_dropdown(driver, pagerPageDrop, dropList);

		helper.validate_pagination_text(driver, pagerGridCount, pagerPageDropText);

	}

	/**
	 * Validate pie chart data 
	 * 
	 */

	@Test(enabled=true,priority = 6)
	public void view3_validate_piechart_data()  {
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(lineChartToolTip)));
		driver.findElement(lineChartToolTip).click();

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(view3_line_graph_title)));
		helper.validate_table_names(driver.findElement(view3_piechart_graph_header), "Test_View3", "view3_pie_chart_header");

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(view3_piechart_labels)));
		helper.validate_list_data(view3_piechart_labels, driver, "Test_View3", "view3_piechart_labels");	
		helper.validate_table_names(driver.findElement(view3_piechart_Total_Agents_label), "Test_View3", "view3_piechart_Total_Agents_label");
		helper.validate_table_names(driver.findElement(view3_piechart_Agents_Staffed_label), "Test_View3", "view3_piechart_Agents_Staffed_label");
	}

	/**
	 * Validating data of COEs table
	 * @author NMakkar
	 */
	@Test
	public void view3_COEs_table_data_validation()
	{	
		//Key on which we have to validate other values.
		String key = "Calls In Queue";
		//Text on which we will fetch other tables columns
		String check_text = "Agents";

		//Initialize Elements  of tables and their columns data.
		//Table 3 - Data .
		List<WebElement> data_of_table3  = driver.findElements(view3_COEs_table_data_val);
		//Table 3 - Columns
		List<WebElement> col_of_table3 = driver.findElements(By.xpath(view3_curr_agent_stats_col));
		//Table 1 - Data
		List<WebElement> updated_col_table1 = driver.findElements(view3_Current_table_data_val);
		//Table 1 - Colums
		List<WebElement> col_of_table1 = driver.findElements(By.xpath(view3_curr_data_table));
		
		//Adding Column data from another table.
		data_of_table3 = helper.modify_cols_data_of_table(col_of_table1, data_of_table3, updated_col_table1 , check_text  );
		//Validating N/A and integer for columns
		helper.data_validate_Down(driver, key , col_of_table3, data_of_table3  );
	}

	/**
	 * Validating data of todays table
	 * @author NMakkar
	 */
	@Test
	public void view3_todays_table_data_validation()
	{	
		//Key on which we have to validate other values.
		String key = "CML Service Level";

		//Initialize Elements  of tables and their columns data.

		//Table 1 - Data
		List<WebElement> data_of_table2 = driver.findElements(view3_todays_table_data_val);
		//Table 1 - Colums
		List<WebElement> col_of_table2 = driver.findElements(By.xpath(view3_today_data_table));
		//Validating N/A and integer for columns
		helper.data_validate_Down(driver, key , col_of_table2, data_of_table2  );
	}

	/**
	 * Validating data of Current table
	 * @author NMakkar
	 */
	@Test
	public void view3_current_table_data_validation()
	{	
		//Key on which we have to validate other values.
		String key = "Calls In Queue";
		//Text on which we will fetch other tables columns
		String check_text = "Agents";

		//Initialize Elements  of tables and their columns data.

		//Table 1 - Data
		List<WebElement> data_of_table1 = driver.findElements(view3_Current_table_data_val);
		//Table 1 - Columns
		List<WebElement> col_of_table1 = driver.findElements(By.xpath(view3_curr_data_table));
		//Removing Column data from another table.
		data_of_table1 = helper.modify_cols_data_of_table(col_of_table1, data_of_table1  , check_text );
		//Validating N/A and integer for columns
		helper.data_validate_Down(driver, key , col_of_table1, data_of_table1  );
	}

	/**
	 * @author NMakkar
	 * Method validating creation of Custom Views.
	 */
	@Test
	public void view3_Custom_Views() {
		
		//Getting View3 Title to validate text in My view.
		String string_to_validate = driver.findElement(View3).getText();
		//Calling Method for creation and validation for custom views.
		helper.create_validate_Custom_View(driver, wait, string_to_validate);

	}
	/**
	 * @author nkumar9 
	 * Method is to validate the drop down list values in line chart
	 */
	@Test
	public void view3_table_CurrentData_validate_ColumnCustomization(){
		logger.info("-----Start test case execution for :view3_table_CurrentData_validate_ColumnCustomization------");
		helper.explicitWait(driver, 5);
		helper.waitloader(driver);
		helper.waitForBrowserToLoadCompletely(driver);
		driver.findElement(pauseToolTip).click();
		driver.findElement(tabularViewToolTip).click();	
		helper.explicitWait(driver, 2);
		ArrayList<String> tableColumns=(ArrayList<String>) helper.getTableColumns(tbl_View3_CurrentData, driver);
		logger.info("Table column before customization:"+tableColumns);
		driver.findElement(img_column_cust_currentData).click();		
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(currentData_columnCustomization)));
		driver.findElement(currentData_columnCustomization).click();
		helper.explicitWait(driver, 5);
		helper.deSelectCheckboxFromDD(lst_column_customization, driver, "Test_View3", "ds_lst_Currentdata_column_customization");
		helper.explicitWait(driver, 5);
		helper.selectCheckboxFromDD(lst_column_customization, driver, "Test_View3", "lst_CurrentData_column_customization");
		helper.explicitWait(driver, 5);
		driver.findElement(img_column_cust_currentData).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ArrayList<String> tableColumns_CC_UI=(ArrayList<String>) helper.getTableColumns(tbl_View3_CurrentData, driver);
		tableColumns.addAll(ExcelCache.getExpectedListData("Test_View3" , "lst_CurrentData_column_customization" ));
		tableColumns.removeAll(ExcelCache.getExpectedListData("Test_View3" , "ds_lst_Currentdata_column_customization" ));
		boolean actulValue=Helper.compareTwoList(tableColumns, tableColumns_CC_UI);
		Assert.assertEquals(actulValue, true);		
		logger.info("-----End of test case execution for :view3_table_CurrentData_validate_ColumnCustomization------");

	}
	/**
	 * @author nkumar9 
	 * Method is to validate the drop down list values in line chart
	 * @throws InterruptedException 
	 */
	@Test
	public void view3_table_TodayData_validate_ColumnCustomization(){
		WebDriverWait wait = new WebDriverWait(driver, 100);
		logger.info("-----Start test case execution for :view3_table_TodayData_validate_ColumnCustomization------");
		helper.explicitWait(driver, 5);
		helper.waitloader(driver);
		helper.waitForBrowserToLoadCompletely(driver);
		driver.findElement(pauseToolTip).click();
		driver.findElement(tabularViewToolTip).click();
		helper.explicitWait(driver, 2);
		ArrayList<String> tableColumns=(ArrayList<String>) helper.getTableColumns(tbl_View3_TodayData, driver);
		logger.info("Table column before customization:"+tableColumns);
		driver.findElement(img_column_cust_todayData).click();		
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(todayData_columnCustomization)));
		driver.findElement(todayData_columnCustomization).click();
		helper.explicitWait(driver, 5);
		helper.deSelectCheckboxFromDD(lst_column_customization, driver, "Test_View3", "ds_lst_TodayData_column_customization");
		helper.explicitWait(driver, 5);
		helper.selectCheckboxFromDD(lst_column_customization, driver, "Test_View3", "lst_TodayData_column_customization");
		helper.explicitWait(driver, 5);
		driver.findElement(img_column_cust_todayData).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ArrayList<String> tableColumns_CC_UI=(ArrayList<String>) helper.getTableColumns(tbl_View3_TodayData, driver);
		tableColumns.addAll(ExcelCache.getExpectedListData("Test_View3" , "lst_TodayData_column_customization" ));
		tableColumns.removeAll(ExcelCache.getExpectedListData("Test_View3" , "ds_lst_TodayData_column_customization" ));
		boolean actulValue=Helper.compareTwoList(tableColumns, tableColumns_CC_UI);
		Assert.assertEquals(actulValue, true);		
		logger.info("-----End of test case execution for :view3_table_TodayData_validate_ColumnCustomization------");

	}
	/**
	 * @author nkumar9 
	 * Method is to validate the drop down list values in line chart
	 * @throws InterruptedException 
	 */
	@Test
	public void view3_table_CurrentAgent_validate_ColumnCustomization(){
		WebDriverWait wait = new WebDriverWait(driver, 100);
		logger.info("-----Start test case execution for :view3_table_CurrentAgent_validate_ColumnCustomization------");
		helper.explicitWait(driver, 5);
		helper.waitloader(driver);
		helper.waitForBrowserToLoadCompletely(driver);
		driver.findElement(pauseToolTip).click();
		helper.explicitWait(driver, 2);
		driver.findElement(tabularViewToolTip).click();
		helper.explicitWait(driver, 2);
		ArrayList<String> tableColumns=(ArrayList<String>) helper.getTableColumns(tbl_View3_CurrentAgent, driver);
		logger.info("Table column before customization:"+tableColumns);
		driver.findElement(img_column_cust_currentAgent).click();		
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(currentAgent_columnCustomization)));
		driver.findElement(currentAgent_columnCustomization).click();
		helper.explicitWait(driver, 5);
		helper.deSelectCheckboxFromDD(lst_column_customization, driver, "Test_View3", "ds_lst_CurrentAgent_column_customization");
		helper.explicitWait(driver, 5);
		helper.selectCheckboxFromDD(lst_column_customization, driver, "Test_View3", "lst_CurrentAgent_column_customization");
		helper.explicitWait(driver, 5);
		driver.findElement(img_column_cust_currentAgent).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ArrayList<String> tableColumns_CC_UI=(ArrayList<String>) helper.getTableColumns(tbl_View3_CurrentAgent, driver);
		tableColumns.addAll(ExcelCache.getExpectedListData("Test_View3" , "lst_CurrentAgent_column_customization" ));
		tableColumns.removeAll(ExcelCache.getExpectedListData("Test_View3" , "ds_lst_CurrentAgent_column_customization" ));
		boolean actulValue=Helper.compareTwoList(tableColumns, tableColumns_CC_UI);
		Assert.assertEquals(actulValue, true);		
		logger.info("-----End of test case execution for :view3_table_CurrentAgent_validate_ColumnCustomization------");
	}
}