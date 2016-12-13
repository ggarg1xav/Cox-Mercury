package com.xavient.pages;

import org.openqa.selenium.By;

public interface DashBoardView {

	By alertBtn = By.xpath("//button[contains(@ng-click,'getNotification')]");
	By loginUserName = By.className("");
	By signOut = By.xpath("//i[@tooltip='LogOut']");

	By headerTitle = By.xpath("//div[@class=\"panel-heading\"]/h4/span");

	/*
	 * ToolTip locator
	 */
	By popOutToolTip = By.id("fullScrExpand");
	By pauseToolTip = By.id("clickPause");
	By tabularViewToolTip = By.xpath(".//*[@id='fullscrnhide']/i[@tooltip=\"Tabular View\"]");
	By lineChartToolTip = By.id("ChartCombineLine");
	By barGraphToolTip = By.id("ChartCombineBar");
	By saveMyViewToolTip = By.id("saveMyView");

	/*
	 * Pagination
	 */
	By pagerFirst = By.className("ui-grid-pager-first");
	By pagerPrevious = By.className("ui-grid-pager-previous");
	By pagerCurrentPage= By.xpath("//input[contains(@class,'ui-grid-pager-control-input')]"); 
	By pagerMaxPages = By.xpath("//span[contains(@class,'ui-grid-pager-max-pages-number')]");
	By pagerNext = By.className("ui-grid-pager-next");
	By pagerLast = By.className("ui-grid-pager-last");
	By pagerPageDrop = By.xpath("//div[contains(@class,'ui-grid-pager-row-count-picker')]/select");
	By pagerPageDropText = By.xpath("//div[contains(@class,'ui-grid-pager-row-count-picker')]/span");
	By pagerGridCount = By.xpath("//div[@class=\"ui-grid-pager-count\"]/span");
	
	
	By searchTextBox = By.xpath("//input[contains(@placeholder,'Search')]");
	
	/*
	 * Filter
	 */
	By filterBtn = By.xpath("//i[@tooltip=\"Filter\"]");
	
	
	
}
