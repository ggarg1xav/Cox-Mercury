package com.xavient.test.script;

import java.util.concurrent.TimeUnit;

import javax.servlet.jsp.tagext.TryCatchFinally;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.xavient.pages.DashBoardView;
import com.xavient.util.BaseClass;
import com.xavient.util.Helper;
import com.xavient.util.Properties_Reader;

public class Test_View3 extends BaseClass implements  DashBoardView {

 WebDriver driver;
 Helper helper;
 WebDriverWait wait;
 private static Logger logger = Logger.getLogger(Test_View3.class.getName());
 
/**
 * Calling Before Test for navigating to particular view3.
 * @param browser
 * @author NMakkar
 */
	@BeforeTest
	@Parameters({ "browser" })
	public void Before_Test(@Optional("Chrome") String browser) {
		driver = Browser_Selection(browser);
		logger.info(browser+ " is opened successfully");
		//Initialize
		helper = new Helper();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		//Navigating to URL.
		driver.get(Properties_Reader.readProperty("URL"));
		
		//Handling PopUP with AutoIT , Need to have this screen as active when this method is being executed.
		helper.handle_popup();
		
		//Login and Navigating to View
		driver.findElement(user_name).sendKeys(Properties_Reader.readProperty("Username"));
		driver.findElement(pword).sendKeys(Properties_Reader.readProperty("Password"));
		driver.findElement(submit_login).click();
		driver.findElement(View).click();
		driver.findElement(Queue_And_Agent_Overview).click();
		driver.findElement(View3).click();
		
		 wait = new WebDriverWait(driver , 5);
		System.out.println("------Before Test------");
	}
/**
 * Validating Table and Column data.
 * @author NMakkar
 */
	@Test(enabled= false)
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
	 * 
	 */
	@Test(enabled=false)
	public void view3_validate_graph_data()  {
		
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(lineChartToolTip)));
	driver.findElement(lineChartToolTip).click();
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(view3_line_graph_title)));
	helper.validate_table_names(driver.findElement(view3_line_graph_title), "Test_View3", "view3_line_graph_title");
	helper.validate_table_names(driver.findElement(view3_line_graph_y_axis), "Test_View3", "view3_line_graph_y_axis");
	helper.validate_table_names(driver.findElement(view3_line_graph_header), "Test_View3", "view3_line_graph_header");
	helper.validate_list_data(view3_line_graph_x_axis, driver , "Test_View3" , "view3_line_graph_x_axis" );
		
	}
	
	/**
	 * Validate pie chart data 
	 * 
	 */
	
	@Test(enabled=true)
	public void view3_validate_piechart_data()  {
		
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(lineChartToolTip)));
	driver.findElement(lineChartToolTip).click();
	logger.info("Click on the Line Chart Tool tip");
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(view3_line_graph_title)));
	helper.validate_table_names(driver.findElement(view3_piechart_graph_header), "Test_View3", "view3_pie_chart_header");
	wait.until(ExpectedConditions.visibilityOf(driver.findElement(view3_piechart_labels)));
	helper.validate_list_data(view3_piechart_labels, driver, "Test_View3", "view3_piechart_labels");	
	helper.validate_table_names(driver.findElement(view3_piechart_Total_Agents_label), "Test_View3", "view3_piechart_Total_Agents_label");
	helper.validate_table_names(driver.findElement(view3_piechart_Agents_Staffed_label), "Test_View3", "view3_piechart_Agents_Staffed_label");
	}
	
/**
 * Closing Browser After Test.
 */
	@AfterTest
	public void After_Test() {
		driver.close();
		System.out.println("------End Test------");
	}


}
