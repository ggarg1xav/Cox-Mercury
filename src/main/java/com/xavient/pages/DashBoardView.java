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
	By pagerCurrentPage = By.xpath("//input[contains(@class,'ui-grid-pager-control-input')]");
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

	By organizationFilterTxt = By.xpath("//div[@class='filter-panel']/child::*/div[@class='row']/div/label[text()='Organization']");
	By coeFilterTxt = By.xpath("//div[@class='filter-panel']/child::*/div[@class='row']/div/label[text()='COE']");
	By lobFilterTxt = By.xpath("//div[@class='filter-panel']/child::*/div[@class='row']/div/label[text()='LOB']");
	By subLobFilterTxt = By.xpath("//div[@class='filter-panel']/child::*/div[@class='row']/div/label[text()='Sub LOB']");
	By functionalGroupsFilterTxt = By.xpath("//div[@class='filter-panel']/child::*/div[@class='row']/div/label[text()='Functional Groups']");
	By subFunctionalGroupsFilterTxt = By.xpath("//div[@class='filter-panel']/child::*/div[@class='row']/div/label[text()='Sub Functional Groups']");
	By languageFilterTxt = By.xpath("//div[@class='filter-panel']/child::*/div[@class='row']/div/label[text()='Language']");
	By timeZoneFilterTxt = By.xpath("//div[@class='filter-panel']/child::*/div[@class='row']/div/label[text()='Time Zone']");

	//Retrieve value using attribute-label
	By organizationFilterList = By.xpath(".//*[@id='orgHandller']/option");
	By coeFilterList = By.xpath("//select[@id='coeHandller']/option");
	By lobFilterList = By.xpath("//select[@id='lobHandller']/option");
	By subLobFilterList = By.xpath("//select[@id='subLobListHandller']/option");
	By functionalGroupsFilterList = By.xpath(".//*[@id='funGroupHandller']/option");
	By subFunctionalGroupsFilterList = By.xpath(".//*[@id='subFunGroupHandller']/option");
	By languageFilterList = By.xpath(".//*[@id='langHandller']/option");
	By timeZoneFilterList = By.xpath(".//*[@id='tzHandller']/option");
}
