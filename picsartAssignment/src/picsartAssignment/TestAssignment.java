package picsartAssignment;

import static org.testng.Assert.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

import java.util.concurrent.TimeUnit;
import java.util.logging.*;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Utility.RetryAnalyzer;

//import org.testng.log4testng.Logger;

public class TestAssignment {

	private static final Logger LOGGER = Logger.getLogger("TestAssignment.class");
	public WebDriver driver;

	TestAssignment() {
		System.setProperty("webdriver.chrome.driver",
				"D://selenium-java-3.141.59//chromedriver_win32_95//chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();

	}

	@BeforeTest
	public void beforeClass() {

		driver.get("https://picsart.com/");
	}

	@Test(priority=0,retryAnalyzer = Utility.RetryAnalyzer.class)
	public void firstAssignment() throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 60);
		driver.navigate().refresh();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[text()='Log in']"))));
		
		driver.findElement(By.xpath("//button[text()='Log in']")).click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='username']"))));
		driver.findElement(By.xpath("//input[@name='username']")).sendKeys("rajanisram@gmail.com");
		driver.findElement(By.xpath("//input[@name='password']")).sendKeys("Test1234");
		driver.findElement(By.xpath("//button[@data-test='login']")).click();

		Thread.sleep(3000);

		assertEquals(driver.getCurrentUrl(), "https://picsart.com/create");

		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//img[@title='User avatar']"))).build().perform();

		// img[@title='User avatar']

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@href='/logout']"))));

		driver.findElement(By.xpath("//a[@href='/logout']")).click();

		Thread.sleep(1000);

		assertEquals(driver.getCurrentUrl(), "https://picsart.com/explore");

		act.moveToElement(driver.findElement(By.xpath("//div/strong[text()='Learn']"))).build().perform();
		// a[@href='/blog']
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[@href='/blog']"))));

		driver.findElement(By.xpath("//a[@href='/blog']")).click();

		assertEquals(driver.getCurrentUrl(), "https://picsart.com/blog");

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[text()='Design School']"))));

		driver.findElement(By.xpath("//a[text()='Design School']")).click();
		Thread.sleep(2000);
		assertEquals(driver.getCurrentUrl(), "https://picsart.com/blog/category/design-school");

		driver.navigate().back();
		Thread.sleep(2000);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//a[text()='Trends']"))));

		driver.findElement(By.xpath("//a[text()='Trends']")).click();
		Thread.sleep(2000);
		assertEquals(driver.getCurrentUrl(), "https://picsart.com/blog/category/trends");

		driver.navigate().back();
		driver.navigate().refresh();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//a[text()='Picsart Pro'])[1]"))));

		driver.findElement(By.xpath("(//a[text()='Picsart Pro'])[1]")).click();

		Thread.sleep(2000);
		assertEquals(driver.getCurrentUrl(), "https://picsart.com/blog/category/picsart-pro");

		driver.navigate().back();
		Thread.sleep(1000);

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("(//a[text()='News'])[1]"))));

		driver.findElement(By.xpath("(//a[text()='News'])[1]")).click();
		Thread.sleep(1000);

		assertEquals(driver.getCurrentUrl(), "https://picsart.com/blog/category/news");

		act.moveToElement(driver.findElement(By.xpath("//button[@aria-label='search']"))).build().perform();

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@name='search']"))));

		driver.findElement(By.xpath("//input[@name='search']")).sendKeys("Sample Text");
		act.sendKeys(Keys.ENTER).build().perform();

		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@data-watermark='search']/h2"))));

		assertEquals(driver.findElement(By.xpath("//div[@data-watermark='search']/h2")).getText(), "Sample Text");

	}
	
	
	/**
	 * Second Script
	 * @throws Exception 
	 * 
	 */
	@Test(priority=1,retryAnalyzer = Utility.RetryAnalyzer.class)
	public void secondAssigment() throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver, 60);
		driver.navigate().to("https://picsart.com/blog");
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@class,'mainContent')]"))));
		Actions act = new Actions(driver);
		act.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'mainContent')]"))).build().perform();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@class,'main-carousel-view')]/div/div[contains(@class,'active')]"))));
		String imgName1=driver.findElement(By.xpath("//div[contains(@class,'main-carousel-view')]/div/div[contains(@class,'active')]/a")).getAttribute("href");
		
		System.out.println("imgLink="+imgName1);
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[contains(@class,'right')]"))));
		driver.findElement(By.xpath("//button[contains(@class,'right')]")).click();
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[contains(@class,'main-carousel-view')]/div/div[contains(@class,'active')]"))));
		String imgName2=driver.findElement(By.xpath("//div[contains(@class,'main-carousel-view')]/div/div[contains(@class,'active')]/a")).getAttribute("href");
			
			System.out.println("imageLink="+imgName2);
			assertFalse(imgName1.equalsIgnoreCase(imgName2),"image after clicking right arrow should not be the same  ");
			
		//   act.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'main-carousel-view')]//div[contains(@class,'active')]//div/h1/a"))).click().build().perform();
			wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[contains(@class,'main-carousel-view')]//div[contains(@class,'active')]//div/h1/a"))));
			
			driver.findElement(By.xpath("//div[contains(@class,'main-carousel-view')]//div[contains(@class,'active')]//div/h1/a")).click();
		
			Thread.sleep(2000);
			assertEquals(driver.getCurrentUrl(),imgName2);
			
			act.moveToElement(driver.findElement(By.xpath("//div[contains(@class,'image-with-overlay-overlay')]"))).click().build().perform();
	}

	@AfterTest
	public void afterSuit() {
		LOGGER.info("Closing browser");
		driver.quit();

	}
}
