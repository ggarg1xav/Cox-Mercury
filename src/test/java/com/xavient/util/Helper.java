package com.xavient.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

	public void waitForBrowserToLoadCompletely(WebDriver driver) {
		String state = null;
		String oldstate = null;
		try {
			System.out.print("Waiting for browser loading to complete");
			int i = 0;
			while (i < 5) {
				Thread.sleep(1000);
				state = ((JavascriptExecutor) driver).executeScript("return document.readyState;").toString();
				System.out.print("." + Character.toUpperCase(state.charAt(0)) + ".");
				if (state.equals("interactive") || state.equals("loading"))
					break;
				/*
				 * If browser in 'complete' state since last X seconds. Return.
				 */

				if (i == 1 && state.equals("complete")) {
					System.out.println();
					return;
				}
				i++;
			}
			i = 0;
			oldstate = null;
			Thread.sleep(2000);

			/*
			 * Now wait for state to become complete
			 */
			while (true) {
				state = ((JavascriptExecutor) driver).executeScript("return document.readyState;").toString();
				System.out.print("." + state.charAt(0) + ".");
				if (state.equals("complete"))
					break;

				if (state.equals(oldstate))
					i++;
				else
					i = 0;
				/*
				 * If browser state is same (loading/interactive) since last 60
				 * secs. Refresh the page.
				 */
				if (i == 15 && state.equals("loading")) {
					System.out.println("\nBrowser in " + state + " state since last 60 secs. So refreshing browser.");
					driver.navigate().refresh();
					System.out.print("Waiting for browser loading to complete");
					i = 0;
				} else if (i == 6 && state.equals("interactive")) {
					System.out.println(
							"\nBrowser in " + state + " state since last 30 secs. So starting with execution.");
					return;
				}

				Thread.sleep(4000);
				oldstate = state;

			}
			System.out.println();

		} catch (InterruptedException ie) {
			ie.printStackTrace();
		}
	}

	/*
	 * Retrieve Webelement List Size
	 * @author: guneet
	 */
	public int getWebelentSize(By loc, WebDriver driver){
		return driver.findElements(loc).size();
	}
	
	
	/*
	 * Validate List is sorted
	 * @author: guneet
	 */
	public void validateListIsSorted(LinkedList<String> tableData2, String order) {
		LinkedList<String> sortedData = new LinkedList<>(tableData2);
		LinkedList<String> sortedData1 = new LinkedList<>(tableData2);
		if (order.equalsIgnoreCase("asc"))
			Collections.sort(sortedData);
		else if (order.equalsIgnoreCase("desc")) {
			Collections.sort(sortedData1);
			sortedData.clear();

			for (int yy = sortedData1.size() - 1; yy >= 0; yy--) {
				sortedData.add(sortedData1.get(yy).toString());
			}
		}
		for (int i = 0; i < tableData2.size(); i++) {
			System.out.println(sortedData.get(i)+"             "+tableData2.get(i));
			Assert.assertEquals(sortedData.get(i), tableData2.get(i));
		}

	}

}