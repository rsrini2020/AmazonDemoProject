package com.cucumber.stepdefinition;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aalam.baseclass.FunctionalLibrary;
import com.cucumber.listener.Reporter;
import com.cucumber.runner.TestRunner;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class StepDefinition extends FunctionalLibrary {

	public static WebDriver driver = TestRunner.driver;

	@When("^User Launch the amazon application$")
	public void user_Launch_the_amazon_application() throws Throwable {
		driver.get("https://www.amazon.in/");

	}

	@Then("^User verify the amazon logo present in the page$")
	public void user_verify_the_amazon_logo_present_in_the_page() throws Throwable {
		WebElement amazonLogo = driver.findElement(By.xpath("//div[@id='nav-logo']"));
		boolean elementIsDisplayed = elementIsDisplayed(amazonLogo);
		Assert.assertTrue(elementIsDisplayed);
		Reporter.addScreenCaptureFromPath(takescreenShot("Amazon Logo").getAbsolutePath());
	}

	@Then("^User verify the amazon page title$")
	public void user_verify_the_amazon_page_title() throws Throwable {
		String title = driver.getTitle();
		Assert.assertTrue(title.contains("Online Shopping site in India: "));

	}

	@Then("^User should see search feild present in the page$")
	public void user_should_see_search_feild_present_in_the_page() throws Throwable {
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		boolean enabled = searchBox.isEnabled();
		Assert.assertTrue(enabled);

	}

	@When("^User enter \"([^\"]*)\" in the search feild$")
	public void user_enter_in_the_search_feild(String arg1) throws Throwable {
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		inputValestoElement(searchBox, arg1);

	}

	@When("^User click on search button$")
	public void user_click_on_search_button() throws Throwable {
		WebElement searchBtn = driver.findElement(By.xpath("//input[@type='submit' and @class='nav-input']"));
		clickOntheElement(searchBtn);

	}

	@Then("^User should see the \"([^\"]*)\" in the search result page header$")
	public void user_should_see_the_in_the_search_result_page_header(String arg1) throws Throwable {
		String actual = driver.findElement(By.xpath("//span[@class='a-color-state a-text-bold']")).getText();
		Assert.assertEquals(arg1, actual);
		Reporter.addScreenCaptureFromPath(takescreenShot(actual).getAbsolutePath());


	}

	@When("^User click on Amazon Hamburger menu bar$")
	public void user_click_on_Amazon_Hamburger_menu_bar() throws Throwable {
		driver.findElement(By.id("nav-hamburger-menu")).click();

	}

	@When("^User click on main menu \"([^\"]*)\"$")
	public void user_click_on_main_menu(String arg1) throws Throwable {

		driver.findElement(
				By.xpath("//a[@class='hmenu-item' and @data-menu-id]//div[contains(text(),\"" + arg1 + "\")]")).click();
	}

	@When("^User click on sub menu \"([^\"]*)\"$")
	public void user_click_on_sub_menu(String arg1) throws Throwable {

		driver.findElement(
				By.xpath("//ul[@class='hmenu hmenu-visible hmenu-translateX']//div[contains(text(),\"" + arg1 + "\")]"))
				.click();
	}

	@Then("^User should see the \"([^\"]*)\" in the landing page$")
	public void user_should_see_the_in_the_landing_page(String arg1) throws Throwable {
		String text = driver.findElement(By.xpath("//div[@class='bxw-pageheader__title__text']//h1")).getText();
		Assert.assertTrue(text.contains(arg1));
		Reporter.addScreenCaptureFromPath(takescreenShot(text).getAbsolutePath());

	}

}
