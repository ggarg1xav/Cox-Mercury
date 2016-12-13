package com.xavient.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;



public class Helper {
	/**
	 * 
	 */

	public void handle_popup()
	{

		try {
			Robot robot = new Robot();

			robot.keyPress(KeyEvent.VK_ESCAPE);
			robot.keyRelease(KeyEvent.VK_ESCAPE);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void validate_table_columns(  String element_start  , WebDriver driver ,  String element_end , String class_name , String table_element) 
	{
		try {
			List<String> xls_col_names  = ExcelCache.getExpectedListData(class_name , table_element );
			ArrayList<String> ui_col_names = new ArrayList<String>();	
			int rows = driver.findElements(By.xpath(element_start+element_end)).size();
			for (int i = 1 ;  i <= rows ; i ++)
			{
				String locator = element_start +"[" + i + "]" + element_end;
				ui_col_names.add(driver.findElement(By.xpath(locator)).getText());

			}


			Assert.assertEquals(xls_col_names.containsAll(ui_col_names) , true);		

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}








}