package com.xavient.test.script;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.xavient.pages.DashBoardView;
import com.xavient.util.BaseClass;
import com.xavient.util.ExcelCache;
import com.xavient.util.Helper;
import com.xavient.util.Properties_Reader;

public class Test_View2 extends BaseClass implements DashBoardView {
	public static Logger logger = Logger.getLogger(Test_View2.class.getName());
	static WebDriver driver;
	Helper helper;
	

	/**
	 * Calling Before Test for navigating to particular view3.
	 * 
	 * @param browser
	 * @author nkumar9
	 */
	@BeforeClass
	@Parameters({ "browser" })
	public void Setup(@Optional("Chrome") String browser) {
		logger.info("-----Start test case execution for :Setup method------");		
		driver = Browser_Selection(browser);
		// Initialize
		WebDriverWait wait = new WebDriverWait(driver, 60);
		helper = new Helper();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Navigating to URL.
		logger.info("Navigating to application URL : "+Properties_Reader.readProperty("URL"));
		driver.get(Properties_Reader.readProperty("URL"));

		// Handling PopUP with AutoIT , Need to have this screen as active when
		// this method is being executed.
		helper.handle_popup();

		// Login and Navigating to View
		driver.findElement(user_name).sendKeys(
				Properties_Reader.readProperty("Username"));
		driver.findElement(pword).sendKeys(
				Properties_Reader.readProperty("Password"));
		driver.findElement(submit_login).click();
		logger.info("Successfully loged on to the application");
		driver.findElement(View).click();
		logger.info("Successfully clicked on to the View link on left pannel");
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(Queue_Summary_EMC)));
		driver.findElement(Queue_Summary_EMC).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(View2)));
		driver.findElement(View2).click();
		logger.info("***** Successfully clicked on to the View2 link on left pannel");

		wait = new WebDriverWait(driver, 5);
		logger.info("-----End of test case execution for :Setup method------");
	}

	/**
	 * @author nkumar9 
	 * Method is validating static data set of Agent Count graph under line graph
	 */
	@Test(enabled = true, priority =1)
	public void view2_linegraph_validate_AgentCount_data() {
		logger.info("-----Start test case execution for :view2_linegraph_validate_AgentCount_data------");
		
		// Navigating to line chart page.
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(lineChartToolTip)));
		driver.findElement(lineChartToolTip).click();
		logger.info("Successfully clicked on Line Chart image");
		driver.findElement(pauseToolTip).click();
		logger.info("Successfully clicked on pause button");
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

		
		// Navigating to line chart page.
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(view2_Percentage_y_axis_label)));

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
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(view2_Time_y_axis_label)));

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
		WebDriverWait wait = new WebDriverWait(driver, 100);
		// Navigating to line chart page.
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(barGraphToolTip)));
		driver.findElement(barGraphToolTip).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		logger.info("Successfully clicked on Line Chart image");
		wait.until(ExpectedConditions.visibilityOf((driver
				.findElement(view2_AgentCount_y_axis_label))));
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
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Navigating to line chart page.
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(view2_Percentage_y_axis_label)));

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
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(driver
				.findElement(view2_Time_y_axis_label)));

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
	@Test(enabled = true , priority =8)
	public void view2_bargraph_chart_validate_dropdown() {
		logger.info("-----Start test case execution for :view2_bargraph_chart_validate_dropdown------");
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
	@Test(enabled = true , priority =9)
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
		
	@Test(enabled=true, priority = 10)
	public void view2_validate_organizationfilter_dropdown()
	{
		logger.info("-----Start test case execution for :view2_validate_filter_dropdown------");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterBtn)));
		helper.clickByJavascript(driver, driver.findElement(filterBtn));
		// Validate Organization FilterList
		helper.validate_filter_dropdown_data(organizationFilterList, driver, "Test_View2", "view2_FilterOrganizationValue_data");
	}
	/* @author nkumar9
	 * Method is validating filter value for list lob.
	 */
		
	@Test(enabled=true, priority = 11)
	public void view2_validate_lobfilter_dropdown()
	{
		logger.info("-----Start test case execution for :view2_validate_filter_dropdown------");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterBtn)));
		helper.clickByJavascript(driver, driver.findElement(filterBtn));
		helper.explicitWait(driver,2);
		// Validate LOB FilterList
		helper.validate_filter_dropdown_data(lobFilterList, driver, "Test_View2", "view2_Filter_lobFilterList_data");
	}
	/* @author nkumar9
	 * Method is validating filter value  for list subLob.
	 */
		
	@Test(enabled=true, priority = 12)
	public void view2_validate_sublobfilter_dropdown()
	{
		logger.info("-----Start test case execution for :view2_validate_filter_dropdown------");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterBtn)));
		helper.clickByJavascript(driver, driver.findElement(filterBtn));
		helper.explicitWait(driver,2);
		// Validate SUB LOB FilterList
		helper.validate_filter_dropdown_data(subLobFilterList, driver, "Test_View2", "view2_Filter_subLobFilterList_data");
	}
	/* @author nkumar9
	 * Method is validating filter value for list groups.
	 */
		
	@Test(enabled=true, priority = 13)
	public void view2_validate_groupsfilter_dropdown()
	{
		logger.info("-----Start test case execution for :view2_validate_filter_dropdown------");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterBtn)));
		helper.clickByJavascript(driver, driver.findElement(filterBtn));
		helper.explicitWait(driver,2);
		// Validate Functional Groups FilterList
		helper.validate_filter_dropdown_data(functionalGroupsFilterList, driver, "Test_View2", "view2_Filter_functionalGroupsFilterList_data");
	}
	/* @author nkumar9
	 * Method is validating filter value for list Sub Functional groups.
	 */
		
	@Test(enabled=true, priority = 14)
	public void view2_validate_subFunctionalGroupsfilter_dropdown()
	{
		logger.info("-----Start test case execution for :view2_validate_filter_dropdown------");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterBtn)));
		helper.clickByJavascript(driver, driver.findElement(filterBtn));
		helper.explicitWait(driver,2);
		// Validate Sub Functional Groups FilterList
		helper.validate_filter_dropdown_data(subFunctionalGroupsFilterList, driver, "Test_View2", "view2_Filter_subFunctionalGroupsFilterList_data");
	}
	/* @author nkumar9
	 * Method is validating filter value for list Functions.
	 */
		
	@Test(enabled=true, priority = 15)
	public void view2_validate_functionsfilter_dropdown()
	{
		logger.info("-----Start test case execution for :view2_validate_filter_dropdown------");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterBtn)));
		helper.clickByJavascript(driver, driver.findElement(filterBtn));
		helper.explicitWait(driver,2);
		// Validate Functions FilterList
		helper.validate_filter_dropdown_data(functionFilterList, driver, "Test_View2", "view2_functionsFilterList_data");
	}
	/* @author nkumar9
	 * Method is validating filter value for list language.
	 */
		
	@Test(enabled=true, priority = 16)
	public void view2_validate_languagefilter_dropdown()
	{
		logger.info("-----Start test case execution for :view2_validate_filter_dropdown------");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterBtn)));
		helper.clickByJavascript(driver, driver.findElement(filterBtn));
		helper.explicitWait(driver,2);
		// Validate Language FilterList
		helper.validate_filter_dropdown_data(languageFilterList, driver, "Test_View2", "view2_Filter_languageFilterList_data");
	}
	/* @author nkumar9
	 * Method is validating filter value for list Time Zone.
	 */
		
	@Test(enabled=true, priority = 17)
	public void view2_validate_timezonefilter_dropdown()
	{
		logger.info("-----Start test case execution for :view2_validate_filter_dropdown------");
		WebDriverWait wait = new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(filterBtn)));
		helper.clickByJavascript(driver, driver.findElement(filterBtn));
		helper.explicitWait(driver,2);
		// Validate Time Zone FilterList
		helper.validate_filter_dropdown_data(timeZoneFilterList, driver, "Test_View2", "view2_Filter_timeZoneFilterList_data");
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
