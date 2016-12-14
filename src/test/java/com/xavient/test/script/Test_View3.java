package com.xavient.test.script;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
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
 
/**
 * Calling Before Test for navigating to particular view3.
 * @param browser
 * @author NMakkar
 */
	@BeforeTest
	@Parameters({ "browser" })
	public void Before_Test(@Optional("Chrome") String browser) {
		driver = Browser_Selection(browser);
		//Initialize
		helper = new Helper();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
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
		
		
		System.out.println("------Before Test------");
	}
/**
 * Validating Table and Column data.
 * @author NMakkar
 */
	@Test
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
	
	public void view3_validate_graph_data()  {
		
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
