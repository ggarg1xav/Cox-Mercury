package com.xavient.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class Properties_Reader {

	public static String readProperty(String key) {

		File file = new File(System.getProperty("user.dir")
				+ "\\src\\test\\resources\\testData\\dataFile.properties");
		System.out.println(file);
		Properties prop = new Properties();
		FileInputStream inputFile = null;
		{
			try {
				inputFile = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}

			
			try {
				prop.load(inputFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return prop.getProperty(key);
		}
	}
	
}
