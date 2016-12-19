package com.xavient.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

public interface DashBoardView {
/**
 * Locators for All Pages
 * 
 */
	By pword = By.id("pword");
	By user_name = 	By.id("username");
	By submit_login = By.name("go");
	
	By alertBtn = By.xpath("//button[contains(@ng-click,'getNotification')]");
	By loginUserName = By.className("");
	By signOut = By.xpath("//i[@tooltip='LogOut']");

	By headerTitle = By.xpath("//div[@class=\"panel-heading\"]/h4/span");
	
	/*
	 Home Page 
	 */
	
	By View = By.cssSelector(".fa.fa-file-text-o");
	By View3 = By.xpath(".//*[contains(text(),'LOB Queue & Agent Overview With COE Agent Drilldown View')]");
	By Queue_And_Agent_Overview = By.xpath(".//*[contains(text(),'Queue And Agent Overview')]");
	By View15 = By.xpath("//*[@class='nav nav-pills nav-stacked']//*[contains(text(),'COE With Agent View')]");
	
	
	/*
	 view 3 - Table Names
	 */
	By view3_curr_data = By.xpath(".//*[contains(text(),'Current Data')]");
	By view3_today_data = By.xpath(".//*[contains(text(),\"Today's Data\")]");
	By view3_curr_agent_stats_tbl = By.xpath(".//*[contains(text(),'Current Agents Statistics for all COEs')]");
	By view3_agent_details = By.xpath(".//*[@id='tableCtrl']/div/div[3]/div/div[1]/div/h4");
	
	String view3_curr_data_table  = ".//*[@id='VIEW_3_table-first1']/thead/tr/th"; 
	String view3_Agent_table_data_start = "//div[@class='ui-grid-header-cell-row']/descendant::Div[@class='ui-grid-cell-contents']" ;
	String view3_Agent_table_data_end = "/span[1]";
	String view3_today_data_table = ".//*[@id='VIEW_3_table-second1']/thead/tr/th"; 
	String view3_curr_agent_stats_col = ".//*[@id='VIEW_3_table-third1']/thead/tr/th";	
	String view3_Agent_table_data_sort_arrow = "/span[2]";

	/*
	 view 3 - Line Chart.
	 */
	By view3_line_graph_title = By.xpath(".//*[contains(text(),'Current Agent Statistics For all COEs')]");
	By view3_line_graph_header = By.xpath(".//*[contains(text(),'Agent AUX State Detail')]");
	By view3_line_graph_y_axis = By.xpath(".//*[contains(text(),'Agent Count')]");
	//By view3_line_graph_x_axis = By.xpath(".//*[contains(text(),'Agents Break ')]/parent::*/parent::*/child::*/child::*");
	 By view3_graph_x_axis = By.xpath("//*[contains(text(),'Agents Break ')]/parent::*/parent::*/child::*");
	
	
	/*
	 view 3 - Bar Chart.
	 */
	By view3_bar_graph_title = By.xpath(".//*[contains(text(),'Current Agent Statistics For all COEs')]");
	By view3_bar_graph_header = By.xpath(".//*[contains(text(),'Agent AUX State Detail')]");
	By view3_bar_graph_y_axis = By.xpath(".//*[contains(text(),'Agent Count')]");
	By view3_bar_graph_x_axis = By.xpath(".//*[contains(text(),'Agents Break ')]/parent::*/parent::*/child::*/child::*");
	/*
	 * View 3 - Pie Chart
	 */
	By view3_piechart_graph_header = By.xpath("//h4[text()='Agent Status']");
	By view3_piechart_labels = By.xpath("//*[text()='Agents Available']/parent::*/parent::*/parent::*/child::*/child::*[3]");
	By view3_piechart_Total_Agents_label =  By.xpath("//label[text()='Total Agents']");
	By view3_piechart_Agents_Staffed_label =  By.xpath("//label[text()='Agents Staffed']");
	/*
	 * ToolTip locator
	 */
	By popOutToolTip = By.id("fullScrExpand");
	By pauseToolTip = By.id("clickPause");
	By tabularViewToolTip = By.xpath(".//*[@id='fullscrnhide']/i[@tooltip=\"Tabular View\"]");
	By lineChartToolTip = By.xpath(".//*[@id='ChartCombineLine']");
	By barGraphToolTip = By.xpath(".//*[@id='ChartCombineBar']");
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
	
	/*
	 * View 15
	 * 
	 */
	 By view15_today_data = By.xpath("//*[@id='tableCtrl']//h4");
	 String view15_today_data_table  = "//table[@id='VIEW_15_table-first1']//th"; 
	 By view15_current_data = By.xpath("//h4[text()='Current Data']");
	 String view15_current_data_table  = "//*[@id='VIEW_15_table-second1']//th"; 
	 By view15_Half_Hour_data = By.xpath("//h4[text()='Half Hour Data']");
	 String view15_Half_Hour_data_table  = "//*[@id='VIEW_15_table-third1']//th";
	 
	/*
	 * View 3 table data
	 */
	By view3_agent_details_data = By.xpath("//div[@class='ui-grid-canvas']/div");
}
