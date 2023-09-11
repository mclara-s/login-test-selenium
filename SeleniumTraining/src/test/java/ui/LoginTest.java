/*
 * References: 
 * Selenium webdriver tutorial at https://www.youtube.com/playlist?list=PLL34mf651faPB-LyEP0-a7Avp_RHO0Lsm
 * 
 * */

package ui;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

@Test
public class LoginTest {

	public static String[] browserList = {"chrome", "firefox", "edge"};
	public static WebDriver driver;
	
	
	static void TestBrowser(String browser) {
		if (browser.equals("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			
		} else if (browser.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			
		} else if (browser.equals("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			
		}
	}
	
		public static void runTests() {

		for (String browser: browserList) {
			
			/* Will run the test in chrome, then firefox, then edge */
			TestBrowser(browser);
			
			/* Maximizing window and accessing url */
			driver.manage().window().maximize();
			driver.get("https://www.saucedemo.com/");
			
			/* Accessing and filling form fields */ 	
			WebElement	username = driver.findElement(By.id("user-name"));
			WebElement	password = driver.findElement(By.id("password"));
			username.sendKeys("standard_user");
			password.sendKeys("secret_sauce");
			
			//WRONG PASSWORD TEST:
			//driver.findElement(By.id("password")).sendKeys("wrong_pass_test");
			
			/* Clicking login button*/
			driver.findElement(By.id("login-button")).click();
			
			/* Verify and validate test result */
			String actualUrl="https://www.saucedemo.com/inventory.html";
			String expectedUrl= driver.getCurrentUrl();		
			Assert.assertEquals(actualUrl, expectedUrl);
			
			
			driver.close();
			
		}
		
		
	}

}
