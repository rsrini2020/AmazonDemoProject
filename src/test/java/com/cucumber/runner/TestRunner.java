package com.cucumber.runner;

import java.io.File;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.aalam.baseclass.FunctionalLibrary;
import com.cucumber.listener.Reporter;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {
		"com.cucumber.listener.ExtentCucumberFormatter:src\\test\\resource\\com\\cucumber\\extentreport\\report.html",
		"pretty",
		"json:src\\test\\resource\\com\\cucumber\\extentreport\\cucumber.json" }, features = "src\\test\\java\\com\\cucumber\\feature", glue = "com.cucumber.stepdefinition", dryRun = false, strict = false, monochrome = true)

public class TestRunner {

	public static WebDriver driver;

	@BeforeClass
	public static void driverInitialization() throws Exception {
		driver = FunctionalLibrary.getBrowser("chrome");
	}

	@AfterClass
	public static void driverQuit() {
		driver.quit();
		System.out.println("Browser Quit Successful");
		Reporter.loadXMLConfig(new File(
				System.getProperty("user.dir") + "\\src\\test\\resource\\com\\cucumber\\config\\extent-config.xml"));
		System.out.println("Report generated");
		System.out.println(
				System.getProperty("user.dir") + "\\src\\test\\resource\\com\\cucumber\\config\\extent-config.xml");
	}

}
