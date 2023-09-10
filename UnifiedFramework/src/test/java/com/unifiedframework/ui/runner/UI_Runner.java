package com.unifiedframework.ui.runner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.presentation.PresentationMode;
import net.masterthought.cucumber.sorting.SortingMethod;


@CucumberOptions(
		//tags = "@Web",
		tags = 	"@Login",
		features = "src/test/java/com/unifiedframework/ui/features",
		glue = {"com.unifiedframework.ui.stepdefinitions",
				"com.unifiedframework.ui.hooks",
				},
		plugin = {"json:target/UI_cucumber.json", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
		)


public class UI_Runner extends AbstractTestNGCucumberTests {
	@DataProvider(parallel = false) //For Non parallel execution set parallel = false
	@Override
	public Object[][] scenarios() { //Override method in the abstract class
		return super.scenarios();    //Returns a 2D array of all scenarios features
	}
   
	@AfterSuite
	public static void teardown() {
		try {			
			File reportOutputDirectory = new File("Report");
			List<String> jsonFiles = new ArrayList<String>();
			jsonFiles.add("target/UI_cucumber.json");
			Configuration configuration = new Configuration(reportOutputDirectory, "Test");
			configuration.setBuildNumber("1");
			configuration.addClassifications("Environment", "QA");
			configuration.addClassifications("Platform", "Windows");
			configuration.addClassifications("Browser", "Chrome");
			configuration.setSortingMethod(SortingMethod.NATURAL);
			configuration.addPresentationModes(PresentationMode.EXPAND_ALL_STEPS);
			configuration.setTrendsStatsFile(new File("target/UI_demo-trends.json"));
			ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
			reportBuilder.generateReports();
		}catch(Exception e) {
			e.printStackTrace();}
	}
}