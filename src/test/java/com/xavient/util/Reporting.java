package com.xavient.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.relevantcodes.extentreports.ExtentReports;

public class Reporting {

	/**
	 * @author csingh
	 * Method for creating Extent Report Instance
	 */
	public static ExtentReports Instance()
	{
		ExtentReports extent;
		String Path = getPath()+"\\Reports\\ExtentReport.html";
		extent = new ExtentReports(Path,true);
		return extent;
	}
	
	/**
	 * @author csingh
	 * Method for taking the screenshot
	 */
	public static String CaptureScreen(WebDriver driver,String name)
	{
		String ImagesPath = getPath()+"\\Reports\\Screenshot\\";
		TakesScreenshot oScn = (TakesScreenshot)driver;
		File oScnShot = oScn.getScreenshotAs(OutputType.FILE);
		File oDest = new File(ImagesPath+name+".jpg");
		try {
			FileUtils.copyFile(oScnShot, oDest);
		} catch (IOException e) {
		e.printStackTrace();
			}
		return ImagesPath+name+".jpg";
	}
	
	/**
	 * @author csingh
	 * Method for getting path of the project
	 */
	public static String getPath()
	{
		File file =  new File("");
		String path = file.getAbsolutePath();
		return path;
	}
	
	/**
	 * @author csingh
	 * Method for finding the current time
	 */
	public static String currentTime()
	{
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		Date date = new Date();
		return sdf.format(date);
	}
	
	/**
	 * @author csingh
	 * Method for deleting the existing report
	 */
	public static void deleteReport()
	{
		try {
			File f =  new File(getPath()+"\\Reports\\ExtentReport.html");
			if(f.exists())
			{
				f.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	} 
}
