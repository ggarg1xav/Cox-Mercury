package com.xavient.framework.COX_Test_Suite;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login {
	 private static WebElement element = null;
	 
	 public static WebElement userName(WebDriver driver){
		 
         element = driver.findElement(By.id("username"));
         return element;
 
         }
	 
public static WebElement Password(WebDriver driver){
		 
         element = driver.findElement(By.id("pword"));
         return element;
 
         }

public static WebElement login_button(WebDriver driver){
	 
    element = driver.findElement(By.name("go"));
    return element;

    }
}	
