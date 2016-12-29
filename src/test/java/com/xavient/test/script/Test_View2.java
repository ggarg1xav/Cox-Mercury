package com.xavient.test.script;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.xavient.pages.DashBoardView;
import com.xavient.util.BaseClass;
import com.xavient.util.ExcelCache;
import com.xavient.util.Helper;
import com.xavient.util.Properties_Reader;

public class Test_View2 extends BaseClass implements DashBoardView {
	public static Logger logger = Logger.getLogger(Test_View2.class.getName());
	static WebDriver driver;
	static Helper helper;
	static WebDriverWait wait;

	/**
	 * Calling Before Test for navigating to particular view3.
	 * 
	 * @param browser
	 * @author nkumar9
	 */
	@BeforeMethod
	@Parameters({ "browser" })
	public void Setup(@Optional("Chrome") String browser) {
		logger.info("-----Start test case execution for :Setup method------");
		parent = extent.startTest("Test View2","Verify Different scenarios in View 2");
		driver = Browser_Selection(browser);
		parent.log(LogStatus.PASS, browser + " is opened successfully");
		logger.info(browser + " is opened successfully");
		// Initialize
		helper = new Helper();
		wait = new WebDriverWait(driver, 100);
		
		// Navigating to URL.
		logger.info("Navigating to application URL : "+Properties_Reader.readProperty("URL"));
		

		// Handling PopUP with AutoIT , Need to have this screen as active when
		// this method is being executed.
		helper.handle_popup();
		parent.log(LogStatus.PASS, "Alert pop is closed");

		// Login and Navigating to View
		helper.login(driver);
		parent.log(LogStatus.PASS, "User is logged into application");
		By[] element = { View, Queue_Summary_EMC, View2 };
		helper.navigate_view(element, wait, driver);
		helper.explicitWait(driver, 5);
		helper.waitForBrowserToLoadCompletely(driver);
		helper.explicitWait(driver, 5);
		logger.info("Waiting for driver to load the page");
		parent.log(LogStatus.PASS, "Waiting for driver to load the page");
		driver.findElement(pauseToolTip).click();
		logger.info("User Clicked on to the pause button");
		parent.log(LogStatus.PASS, "User Clicked on to the pause button");
		logger.info("***** Successfully clicked on to the View2 link on left pannel");
		parent.log(LogStatus.PASS, "Successfully clicked on to the View2 link on left pannel");
		logger.info("-----End of test case execution for :Setup method------");
	}

	/**
	 * @author nkumar9 
	 * Method is validating static data set of Agent Count graph under line graph
	 */
	@Test(enabled = true, priority =1)
	public void view2_linegraph_validate_AgentCount_data() {
		child = extent.startTest("view2_linegraph_validate_AgentCount_data","Verifying the line graph data for Agent count");
		logger.info("-----Start test case execution for :view2_linegraph_validate_AgentCount_data------");	
		navigateToLinegraphPage();
		// Validating all static data.
		logger.info("Validating the Y-axis labels of Agent Count graph");
		helper.validate_table_names(driver.findElement(view2_AgentCount_y_axis_label), "Test_View2","view2_AgentCount_y_axis_label");
		logger.info("Validating the X-axis labels of Agent Count graph");
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(view2_AgentCount_x_axis)));
		helper.validate_list_data_axis(view2_AgentCount_x_axis, driver,"Test_View2", "view2_AgentCount_x_axis");
		/*Values for Y-axis are changing frequently so commenting it out
		 * wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(view2_AgentCount_y_axis)));
		helper.validate_graph_data_yaxis(view2_AgentCount_y_axis, driver,"Test_View2", "view2_AgentCount_y_axis");*/
		logger.info("-----End of test case execution for :view2_linegraph_validate_AgentCount_data------");
	}

	/**
	 * @author nkumar9 
	 * Method is validating static data set of percentage graph under line graph
	 */
	@Test(enabled = true, priority =2)
	public void view2_linegraph_validate_PencetageGraph_data() {
		logger.info("-----Start test case execution for :view2_linegraph_validate_PencetageGraph_data------");

		navigateToLinegraphPage();
		// Validating all static data of percentage graph.
		logger.info("Validating the Y-axis labels of Percentage graph");
		helper.validate_table_names(
				driver.findElement(view2_Percentage_y_axis_label), "Test_View2",
				"view2_Percentage_y_axis_label");
		logger.info("Validating the X-axis labels of Percentage graph");
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(view2_Percentage_x_axis)));
		helper.validate_list_data_axis(view2_Percentage_x_axis,
				driver, "Test_View2", "view2_Percentage_x_axis");
		logger.info("Validating the Y-axis labels of Percentage graph");
		/*wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(view2_Percentage_y_axis)));
		helper.validate_graph_data_yaxis(view2_Percentage_y_axis, driver,"Test_View2", "view2_Percentage_y_axis");*/
		logger.info("-----End of test case execution for :view2_linegraph_validate_PencetageGraph_data------");
	}

	/**
	 * @author nkumar9 
	 * Method is validating static data set of Time graph under line graph
	 */
	@Test(enabled = true , priority =3)
	public void view2_linegraph_validate_TimeGraph_data() {
		logger.info("-----Start test case execution for :view2_linegraph_validate_TimeGraph_data------");
		navigateToLinegraphPage();
		// Validating all static data of Time graph.
		logger.info("Validating the Y-axis labels of Time graph");
		helper.validate_table_names(
				driver.findElement(view2_Time_y_axis_label), "Test_View2",
				"view2_Time_y_axis_label");
		logger.info("Validating the X-axis labels of Time graph");
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(view2_Time_x_axis)));
		helper.validate_list_data_axis(view2_Time_x_axis, driver,
				"Test_View2", "view2_Time_x_axis");
		logger.info("Validating the Y-axis labels of Time graph");
		/*wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(view2_Time_y_axis)));
		helper.validate_graph_data_yaxis(view2_Time_y_axis, driver,"Test_View2", "view2_Time_y_axis");*/
		logger.info("-----End of test case execution for :view2_linegraph_validate_TimeGraph_data------");
	}
	
	/**
	 * @author nkumar9 
	 * Method is to validate the drop down list values in line chart
	 */
	@Test(enabled = true , priority =4)
	public void view2_line_graph_chart_validate_dropdown() {
		logger.info("-----Start test case execution for :view2_line_graph_chart_validate_dropdown------");
		navigateToLinegraphPage();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(icon_lst_chart_dd)));
		driver.findElement(icon_lst_chart_dd).click();
		logger.info("Successfully clicked on chart drop down list icon");
		helper.validate_DropDownListData(lst_chart_dd, driver, "Test_View2", "lst_chart_dd");
		driver.findElement(icon_lst_chart_dd).click();
		logger.info("-----End of test case execution for :view2_line_graph_chart_validate_dropdown------");
	
	}
	/**
	 * @author nkumar9 
	 * Method is validating static data set of Agent Count graph under bar graph
	 */
	@Test(enabled = true , priority =5)
	public void view2_bargraph_validate_AgentCount_data() {
		logger.info("-----Start test case execution for :view2_bargraph_validate_AgentCount_data------");
		navigateToBarGraph();
		// Validating all static data.
		logger.info("Validating the Y-axis labels of Agent Count graph");
		helper.validate_table_names(driver.findElement(view2_AgentCount_y_axis_label), "Test_View2","view2_AgentCount_y_axis_label");
		logger.info("Validating the X-axis labels of Agent Count graph");
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(view2_AgentCount_x_axis)));
		helper.validate_list_data_axis(view2_AgentCount_x_axis, driver,"Test_View2", "view2_AgentCount_x_axis");
		/*wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(view2_AgentCount_y_axis)));
		helper.validate_graph_data_yaxis(view2_AgentCount_y_axis, driver,"Test_View2", "view2_AgentCount_y_axis");*/
		logger.info("-----End of test case execution for :view2_bargraph_validate_AgentCount_data------");
	}

	/**
	 * @author nkumar9 
	 * Method is validating static data set of percentage graph under bar graph
	 */
	@Test(enabled = true , priority =6)
	public void view2_bargraph_validate_PencetageGraph_data() {
		logger.info("-----Start test case execution for :view2_bargraph_validate_PencetageGraph_data------");
		navigateToBarGraph();
		// Validating all static data of percentage graph.
		logger.info("Validating the Y-axis labels of Percentage graph");
		helper.validate_table_names(
				driver.findElement(view2_Percentage_y_axis_label), "Test_View2",
				"view2_Percentage_y_axis_label");
		logger.info("Validating the X-axis labels of Percentage graph");
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(view2_Percentage_x_axis)));
		helper.validate_list_data_axis(view2_Percentage_x_axis,
				driver, "Test_View2", "view2_Percentage_x_axis");
		/*logger.info("Validating the Y-axis labels of Percentage graph");
		helper.validate_graph_data_yaxis(view2_Percentage_y_axis, driver,"Test_View2", "view2_Percentage_y_axis");*/
		logger.info("-----End of test case execution for :view2_bargraph_validate_PencetageGraph_data------");
	}

	/**
	 * @author nkumar9 
	 * Method is validating static data set of Time graph under bar graph
	 */
	@Test(enabled = true , priority =7)
	public void view2_bargraph_validate_TimeGraph_data() {
		logger.info("-----Start test case execution for :view2_bargraph_validate_TimeGraph_data------");
		navigateToBarGraph();
		// Validating all static data of Time graph.
		logger.info("Validating the Y-axis labels of Time graph");
		helper.validate_table_names(
				driver.findElement(view2_Time_y_axis_label), "Test_View2",
				"view2_Time_y_axis_label");
		logger.info("Validating the X-axis labels of Time graph");
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(view2_Time_x_axis)));
		helper.validate_list_data_axis(view2_Time_x_axis, driver,
				"Test_View2", "view2_Time_x_axis");
		/*logger.info("Validating the Y-axis labels of Time graph");
		helper.validate_graph_data_yaxis(view2_Time_y_axis, driver,"Test_View2", "view2_Time_y_axis");*/
		logger.info("-----End of test case execution for :view2_bargraph_validate_TimeGraph_data------");
	}
	/**
	 * @author nkumar9 
	 * Method is to validate the drop down list values in bar chart
	 */
	@Test(enabled = false , priority =8)
	public void view2_bargraph_chart_validate_dropdown() {
		logger.info("-----Start test case execution for :view2_bargraph_chart_validate_dropdown------");
		navigateToBarGraph();
		driver.findElement(icon_lst_chart_dd).click();
		logger.info("Successfully clicked on chart drop down list icon");
		helper.validate_DropDownListData(lst_chart_dd, driver, "Test_View2", "lst_chart_dd");
		driver.findElement(icon_lst_chart_dd).click();
		logger.info("-----End of test case execution for :view2_bargraph_chart_validate_dropdown------");
	
	}
	/**
	 * @author nkumar9 
	 * Method is to validate the drop down list values in line chart
	 * @throws InterruptedException 
	 */
	@Test(enabled = false , priority =9)
	public void view2_table_graph_chart_validate_ColumnCustomization(){
		WebDriverWait wait = new WebDriverWait(driver, 100);
		logger.info("-----Start test case execution for :view2_table_graph_chart_validate_ColumnCustomization------");
		driver.findElement(tabularViewToolTip).click();	
		ArrayList<String> tableColumns=(ArrayList<String>) helper.getTableColumns(tbl_View2, driver);
		logger.info("Table column before customization:"+tableColumns);
		driver.findElement(img_column_cust).click();		
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(lnk_cc_more)));
		driver.findElement(lnk_cc_more).click();
		helper.deSelectCheckboxFromDD(lst_column_customization, driver, "Test_View2", "ds_lst_column_customization");
		helper.selectCheckboxFromDD(lst_column_customization, driver, "Test_View2", "lst_column_customization");
		driver.findElement(img_column_cust).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ArrayList<String> tableColumns_CC_UI=(ArrayList<String>) helper.getTableColumns(tbl_View2, driver);
		tableColumns.addAll(ExcelCache.getExpectedListData("Test_View2" , "lst_column_customization" ));
		tableColumns.removeAll(ExcelCache.getExpectedListData("Test_View2" , "ds_lst_column_customization" ));
		boolean actulValue=Helper.compareTwoList(tableColumns, tableColumns_CC_UI);
		Assert.assertEquals(actulValue, true);		
		logger.info("-----End of test case execution for :view2_table_graph_chart_validate_ColumnCustomization------");
	
	}
	/* @author nkumar9
	 * Method is validating filter value  for list Organization.
	 */
		
	@Test(enabled=false, priority = 10)
	public void view2_validate_organizationfilter_dropdown()
	{
		logger.info("-----Start test case execution for :view2_validate_filter_dropdown------");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterBtn)));
		helper.clickByJavascript(driver, driver.findElement(filterBtn));
		// Validate Organization FilterList
		helper.validate_filter_dropdown_data(organizationFilterList, driver, "Test_View2", "view2_FilterOrganizationValue_data");
	}
	/* @author nkumar9
	 * Method is validating filter value for list lob.
	 */
		
	@Test(enabled=false, priority = 11)
	public void view2_validate_lobfilter_dropdown()
	{
		logger.info("-----Start test case execution for :view2_validate_filter_dropdown------");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterBtn)));
		helper.clickByJavascript(driver, driver.findElement(filterBtn));
		helper.explicitWait(driver,2);
		// Validate LOB FilterList
		helper.validate_filter_dropdown_data(lobFilterList, driver, "Test_View2", "view2_Filter_lobFilterList_data");
	}
	/* @author nkumar9
	 * Method is validating filter value  for list subLob.
	 */
		
	@Test(enabled=false, priority = 12)
	public void view2_validate_sublobfilter_dropdown()
	{
		logger.info("-----Start test case execution for :view2_validate_filter_dropdown------");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterBtn)));
		helper.clickByJavascript(driver, driver.findElement(filterBtn));
		helper.explicitWait(driver,2);
		// Validate SUB LOB FilterList
		helper.validate_filter_dropdown_data(subLobFilterList, driver, "Test_View2", "view2_Filter_subLobFilterList_data");
	}
	/* @author nkumar9
	 * Method is validating filter value for list groups.
	 */
		
	@Test(enabled=false, priority = 13)
	public void view2_validate_groupsfilter_dropdown()
	{
		logger.info("-----Start test case execution for :view2_validate_filter_dropdown------");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterBtn)));
		helper.clickByJavascript(driver, driver.findElement(filterBtn));
		helper.explicitWait(driver,2);
		// Validate Functional Groups FilterList
		helper.validate_filter_dropdown_data(functionalGroupsFilterList, driver, "Test_View2", "view2_Filter_functionalGroupsFilterList_data");
	}
	/* @author nkumar9
	 * Method is validating filter value for list Sub Functional groups.
	 */
		
	@Test(enabled=false, priority = 14)
	public void view2_validate_subFunctionalGroupsfilter_dropdown()
	{
		logger.info("-----Start test case execution for :view2_validate_filter_dropdown------");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterBtn)));
		helper.clickByJavascript(driver, driver.findElement(filterBtn));
		helper.explicitWait(driver,2);
		// Validate Sub Functional Groups FilterList
		helper.validate_filter_dropdown_data(subFunctionalGroupsFilterList, driver, "Test_View2", "view2_Filter_subFunctionalGroupsFilterList_data");
	}
	/* @author nkumar9
	 * Method is validating filter value for list Functions.
	 */
		
	@Test(enabled=false, priority = 15)
	public void view2_validate_functionsfilter_dropdown()
	{
		logger.info("-----Start test case execution for :view2_validate_filter_dropdown------");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterBtn)));
		helper.clickByJavascript(driver, driver.findElement(filterBtn));
		helper.explicitWait(driver,2);
		// Validate Functions FilterList
		helper.validate_filter_dropdown_data(functionFilterList, driver, "Test_View2", "view2_functionsFilterList_data");
	}
	/* @author nkumar9
	 * Method is validating filter value for list language.
	 */
		
	@Test(enabled=false, priority = 16)
	public void view2_validate_languagefilter_dropdown()
	{
		logger.info("-----Start test case execution for :view2_validate_filter_dropdown------");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterBtn)));
		helper.clickByJavascript(driver, driver.findElement(filterBtn));
		helper.explicitWait(driver,2);
		// Validate Language FilterList
		helper.validate_filter_dropdown_data(languageFilterList, driver, "Test_View2", "view2_Filter_languageFilterList_data");
	}
	/* @author nkumar9
	 * Method is validating filter value for list Time Zone.
	 */
		
	@Test(enabled=false, priority = 17)
	public void view2_validate_timezonefilter_dropdown()
	{
		logger.info("-----Start test case execution for :view2_validate_filter_dropdown------");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterBtn)));
		helper.clickByJavascript(driver, driver.findElement(filterBtn));
		helper.explicitWait(driver,2);
		// Validate Time Zone FilterList
		helper.validate_filter_dropdown_data(timeZoneFilterList, driver, "Test_View2", "view2_Filter_timeZoneFilterList_data");
	}
	// drill down validations
	@Test(enabled = true , priority =18)
	public void view2_drilldown_validation() {
		helper.validate_drilldown(view2_table_name, view2DrillStart, view2DrillEnd, driver);
		logger.info("----drill down successfull---------");

	}
	
public static void navigateToLinegraphPage()
{
	// Navigating to line chart page.	
	wait.until(ExpectedConditions.visibilityOf((driver.findElement(lineChartToolTip))));
	driver.findElement(lineChartToolTip).click();
	helper.explicitWait(driver,2);
	logger.info("Successfully clicked on Line Chart image");
	}
public static void navigateToBarGraph()
{// Navigating to line chart page.
	wait.until(ExpectedConditions.visibilityOf(driver
			.findElement(barGraphToolTip)));
	driver.findElement(barGraphToolTip).click();
	helper.explicitWait(driver,5);
	logger.info("Successfully clicked on Bar Chart image");
	
}
@AfterClass
public static void killDriver()
{
	  String  strCmdLinechr = String.format("TaskKill /F /IM " + "chromedriver.exe");
	  String  strCmdLineie = String.format("TaskKill /F /IM " + "iedriverserver.exe");
		try {
			System.out.println("Total memory :"+Runtime.getRuntime().totalMemory());
			System.out.println("Free memory :"+Runtime.getRuntime().freeMemory());
			System.out.println("Available processors :"+Runtime.getRuntime().availableProcessors());
			
			
			Runtime.getRuntime().exec(strCmdLinechr);
			Runtime.getRuntime().exec(strCmdLineie);
			System.out.println("Total memory after prosess run :"+Runtime.getRuntime().totalMemory());
			System.out.println("Free memory after prosess run :"+Runtime.getRuntime().freeMemory());
			System.out.println("Available processors after prosess run :"+Runtime.getRuntime().availableProcessors());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

}

}
