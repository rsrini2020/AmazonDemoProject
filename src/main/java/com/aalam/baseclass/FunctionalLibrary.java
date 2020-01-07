package com.aalam.baseclass;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FunctionalLibrary {

	public static WebDriver driver;

	public static WebDriver getBrowser(String browserName) throws Exception {

		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "//Drivers//chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "//src//test//resource//Drivers//geckodriver.exe");
				driver = new FirefoxDriver();
			} else if (browserName.equalsIgnoreCase("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "//src//test//resource//Drivers//IEDriverServer.exe");
				driver = new InternetExplorerDriver();
			} else {
				throw new Exception("Invalid browser name");
			}

			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
			return driver;
		} catch (Exception e) {

			e.printStackTrace();
			throw new Exception("Issue is browser launch");
		}

	}

	public static void getUrl(String Url) throws Throwable {
		try {
			driver.get(Url);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("URL Is not valid");
		}
	}

	public static boolean elementIsSelected(WebElement element) throws Exception {
		try {
			boolean selected = element.isSelected();
			return selected;
		} catch (Exception e) {

			e.printStackTrace();
			throw new Exception();

		}
	}

	public static void waitForElementVisiblity(WebElement element) throws Exception {
		try {
			WebDriverWait wb = new WebDriverWait(driver, 50);
			wb.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	public static boolean elementIsDisplayed(WebElement element) throws Exception {
		try {
			boolean displayed = element.isDisplayed();
			return displayed;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	public static boolean elementIsEnabled(WebElement element) throws Exception {
		try {
			boolean enabled = element.isEnabled();
			return enabled;
		} catch (Exception e) {
			throw new Exception();

		}
	}

	public static void clearValuesinElement(WebElement element) throws Exception {
		try {
			element.clear();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();

		}
	}

	public static void inputValestoElement(WebElement element, String values) throws Exception {
		try {
			waitForElementVisiblity(element);
			if (elementIsDisplayed(element) && elementIsEnabled(element)) {
				clearValuesinElement(element);
				element.sendKeys(values);

			}
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public static void clickOntheElement(WebElement element) throws Exception {
		try {
			waitForElementVisiblity(element);
			element.click();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	public static void moveTOtheElement(WebElement element) throws Exception {
		try {
			// waitForElementVisiblity(element);
			Actions ac = new Actions(driver);
			ac.moveToElement(element).build().perform();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}

	}

	public static void driverNavigate(String options) throws Exception {
		if (options.equalsIgnoreCase("forward")) {
			driver.navigate().forward();
		} else if (options.equalsIgnoreCase("backward")) {
			driver.navigate().back();
		} else if (options.equalsIgnoreCase("refresh")) {
			driver.navigate().refresh();
		} else {
			throw new Exception("Not a valid option");
		}

	}

	public static void driverNavigateToUrl(String url) {
		driver.navigate().to(url);
	}

	public static String getPageTitle() throws Exception {
		try {
			String title = driver.getTitle();
			return title;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new Exception();
		}
	}

	public static String getCurrentURL() {
		String currentUrl = driver.getCurrentUrl();
		return currentUrl;
	}

	public static void selectOptionInDD(WebElement element, String options, String values) throws Exception {
		waitForElementVisiblity(element);
		Select sc = new Select(element);
		if (options.equalsIgnoreCase("index")) {
			sc.selectByIndex(Integer.parseInt(values));
		} else if (options.equalsIgnoreCase("value")) {
			sc.selectByValue(values);
		} else if (options.equalsIgnoreCase("visibletext")) {
			sc.selectByVisibleText(values);
		} else {
			throw new Exception("Not a valid options");

		}

	}

	public static void scrollToElement(WebElement element) throws Exception {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	public static void scrollUsingCoordinates(int width, int height) throws Exception {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(" + width + "," + height + ")");
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}
	}

	public static void scrollToPageHeight(String options) throws Exception {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		if (options.equalsIgnoreCase("up")) {
			js.executeScript("window.scrollTo(0, -document.body.scrollHeight)");
		} else if (options.equalsIgnoreCase("down")) {
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		} else {
			throw new Exception("Not a valid option");
		}
	}

	public static void dragAndDroptoElement(WebElement source, WebElement target) throws Exception {
		waitForElementVisiblity(source);
		Actions ac = new Actions(driver);
		ac.dragAndDrop(source, target).build().perform();
	}

	public static void rightClickOntheElement(WebElement element) {
		Actions ac = new Actions(driver);
		ac.contextClick(element).build().perform();

	}

	public static void doubleClickOntheElement(WebElement element) {
		Actions ac = new Actions(driver);
		ac.doubleClick(element).build().perform();
	}

	public static void actionsforSendkeys(WebElement element, String values) {
		Actions ac = new Actions(driver);
		ac.sendKeys(element, values).build().perform();
	}

	public static void actionsForClick(WebElement element) {
		Actions ac = new Actions(driver);
		ac.click(element).build().perform();
	}

	public static File takescreenShot(String filename) throws Throwable {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File screenshotAs = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")
				+ "\\src\\test\\resource\\com\\cucumber\\extentreport\\\\screenshot\\" + filename + ".png");
		FileUtils.copyFile(screenshotAs, dest);
		return dest;

	}

	public static void switchToWindows(String pageTitle) {
		Set<String> windowHandles = driver.getWindowHandles();
		for (String x : windowHandles) {
			driver.switchTo().window(x);
			String title = driver.getTitle();
			if (title.equals(pageTitle)) {
				Assert.assertTrue(true);
			} else {
				Assert.fail("Title is mismatched");
			}
		}

	}

	public static void switchToWindows(int index) throws Exception {
		try {
			Set<String> windowHandles = driver.getWindowHandles();
			List<String> li = new ArrayList<String>();
			li.addAll(windowHandles);
			String winID = li.get(index);
			driver.switchTo().window(winID);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception();
		}

	}

}
