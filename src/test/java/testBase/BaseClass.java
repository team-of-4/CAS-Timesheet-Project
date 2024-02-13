package testBase;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.*;

import testCases.Testcases;
import utilities.ExcelUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	@BeforeClass
	@Parameters({"browser"})
	public void setup(String browser) {
		
		logger = LogManager.getLogger(this.getClass());
		
		if(browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else {
			driver = new EdgeDriver();
		}
		
		driver.get("https://be.cognizant.com");
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		
	}
	
	@AfterClass
	public void tearDown() throws IOException {
		ExcelUtils.statusDetails(Testcases.optionStatus);
		driver.quit();
	}

}
