package com.xavient.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class Helper {
	/**
	 * Handling Browser pop-up with AutoIT.
	 * @author NMakkar
	 */
	public void handle_popup()
	{
		try {
			//Initialize.
			Robot robot = new Robot();
			//Pressing Escape for Browser Alert.
			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyRelease(KeyEvent.VK_ESCAPE);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Method is fetching and comparing data from XLS and UI for multiple values. 
	 * @author NMakkar
	 * @param element_start
	 * @param driver
	 * @param element_end
	 * @param class_name
	 * @param table_element
	 */
	public void validate_table_columns(  String element_start  , WebDriver driver ,  String element_end , String class_name , String table_element) 
	{
		//Initialize.
		List<String> xls_col_names  = ExcelCache.getExpectedListData(class_name , table_element );
		ArrayList<String> ui_col_names = new ArrayList<String>();	
		int rows = driver.findElements(By.xpath(element_start+element_end)).size();

		//Comparing No of columns
		Assert.assertEquals(rows, xls_col_names.size() , "No of columns are not same");

		//Iterating for fetching elements from UI.
		for (int i = 1 ;  i <= rows ; i ++)
		{
			String locator = element_start +"[" + i + "]" + element_end;
			ui_col_names.add(driver.findElement(By.xpath(locator)).getText());
		}
		//Comparing List (Column Names)
		Assert.assertEquals(xls_col_names.containsAll(ui_col_names) , true , "All Column Names does not match");		
	}
	/**
	 * Method is fetching and comparing data from XLS and UI for single values.
	 * @author NMakkar
	 * @param element
	 * @param class_name
	 * @param table_element
	 */

	public void validate_table_names( WebElement element  , String class_name , String table_element)
	{
		//XLS data.
		String ui_col_names = element.getText();
		//UI Element data.
		String xls_table_name = ExcelCache.getExpectedData(class_name , table_element);
		//Comparing String.
		Assert.assertEquals(ui_col_names, xls_table_name , "Table Names are different");
	}
	public void validate_list_data( By element  , WebDriver driver  , String class_name , String table_element)
	{
		List<String> xls_col_names  = ExcelCache.getExpectedListData(class_name , table_element );
		ArrayList<String> ui_col_names = new ArrayList<String>();	
		List<WebElement> listelement = driver.findElements(element);
		for (WebElement webelement : listelement)
		{
			ui_col_names.add(webelement.getText());
		}
		Assert.assertEquals(xls_col_names.containsAll(ui_col_names) , true , "All values does not match");				
	}
}