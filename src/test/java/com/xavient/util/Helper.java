package com.xavient.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.xavient.pages.DashBoardView;

public class Helper implements DashBoardView{
	/**
	 * Handling Browser pop-up with AutoIT.
	 * @author NMakkar
	 */
	
	Logger logger = Logger.getLogger(Helper.class);
	
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
		logger.info("Alert pop is closed");
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
		logger.info("-----Comparing the table columns size----- ");
		logger.info("Actual table columns size from UI: "+rows);
		logger.info("Expected table columns size from Excel: "+xls_col_names.size());
		Assert.assertEquals(rows, xls_col_names.size() , "No of columns are not same");
		

		//Iterating for fetching elements from UI.
		for (int i = 1 ;  i <= rows ; i ++)
		{
			String locator = element_start +"[" + i + "]" + element_end;
			ui_col_names.add(driver.findElement(By.xpath(locator)).getText().trim());
		}
		//Comparing List (Column Names)
		logger.info("Actual table columns names from UI: "+ui_col_names);
		logger.info("Expected table columns names from Excel: "+xls_col_names);
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
		logger.info("Actual table columns from UI: "+ui_col_names);
		//UI Element data.
		String xls_table_name = ExcelCache.getExpectedData(class_name , table_element);
		logger.info("Expected table columns from excel: "+xls_table_name);
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
	
	public void validate_list_data_axis( By element  , WebDriver driver  , String class_name , String table_element)
	{
		List<String> xls_col_names  = ExcelCache.getExpectedListData(class_name , table_element );
		ArrayList<String> ui_col_names = new ArrayList<String>();	
		String ui_innerlist="";
		List<WebElement> listelement = driver.findElements(element);
		for (WebElement webelement : listelement)
		{
			List<WebElement> listText=webelement.findElements(By.tagName("tspan"));
			if (listText.size()>1)
			{
			for(WebElement textobject:listText)
			{
				ui_innerlist=ui_innerlist+textobject.getText()+" ";
				
			}
			String ui_innerlistTrim = ui_innerlist.trim();
			ui_col_names.add(ui_innerlistTrim);
			}
			else
			{
			String ele = webelement.findElement(By.tagName("tspan")).getText();
			ui_col_names.add(ele);
			}
		}
		System.out.println("Ui Values:"+ui_col_names);
		Assert.assertEquals(xls_col_names.containsAll(ui_col_names) , true , "All values does not match");				
	}
	
	/*
	 * Waiting for browser loading to complete
	 * @author: guneet
	 * @param driver
	 */
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
	 * @param loc
	 * @param driver
	 */
	public int getWebelentSize(By loc, WebDriver driver){
		return driver.findElements(loc).size();
	}
	
	
	/*
	 * Validate List is sorted
	 * @author: guneet
	 * @param tableData2
	 * @param order
	 */
	public void validateListIsSorted(LinkedList<String> tableData2, String order) {
		
		LinkedList<String> sortedData = new LinkedList<>(tableData2);
		LinkedList<String> sortedData1 = new LinkedList<>(tableData2);
		if (order.equalsIgnoreCase("asc"))
		{
			Collections.sort(sortedData);
		logger.info("List data in ascending order :-  "+sortedData);
		}
		else if (order.equalsIgnoreCase("desc")) {
			Collections.sort(sortedData1);
			sortedData.clear();
			for (int yy = sortedData1.size() - 1; yy >= 0; yy--) {
				sortedData.add(sortedData1.get(yy).toString());
			}
			logger.info("List data in descending order :-  "+sortedData);
		}
		for (int i = 0; i < tableData2.size(); i++) {
			Assert.assertEquals(sortedData.get(i), tableData2.get(i));
		}

	}
	
	public void login(WebDriver driver)
	{
		WebDriverWait wait = new WebDriverWait(driver, 5);
		driver.findElement(user_name).sendKeys(Properties_Reader.readProperty("Username"));
		driver.findElement(pword).sendKeys(Properties_Reader.readProperty("Password"));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(submit_login)));
		//driver.findElement(submit_login).click();
		clickByJavascript(driver, driver.findElement(submit_login));
		Boolean viewPresent = isElementPresent(driver, View);
		if(!viewPresent)
		{
			clickByJavascript(driver, driver.findElement(submit_login));
			logger.info("Clicked login button at second attempt");
		}
	}
	
	public void clickByJavascript(WebDriver driver, WebElement ele)
	{
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", ele);
	}
	
	public boolean isElementPresent(WebDriver driver,By ele)
	{
		boolean flag = false;
		try {
			driver.findElement(ele);
			flag = true;
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * Handling table drill-down operation.
	 * @author guneet
	 */	
	public void validate_drilldown(String view2Table, String view2DrillStart, String view2DrillEnd, WebDriver driver) {
		waitForBrowserToLoadCompletely(driver);
		int count = driver.findElements(By.xpath(view2Table)).size();
		int i = 1;
		if (count > 0) {
			List<WebElement> dr = driver.findElements(By.xpath(view2DrillStart + i + view2DrillEnd));
			while (dr.get(dr.size() - 1).getAttribute("class").toString()
					.equalsIgnoreCase("treegrid-expander treegrid-expander-collapsed drilling")) {
				dr.get(dr.size() - 1).click();
				i++;
				dr = driver.findElements(By.xpath(view2DrillStart + i + view2DrillEnd));
			}
		}
	}	
}