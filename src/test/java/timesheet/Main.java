package timesheet;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Main {

	public static void main(String[] args) throws InterruptedException, ParseException 
	{
		//driver setup
		WebDriver driver = new ChromeDriver();
		
		//going to be cognizant
		driver.get("https://be.cognizant.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		//validating user info 
		driver.findElement(By.xpath("//button[contains(@title,'Account manager')]")).click();
		
		String name = driver.findElement(By.xpath("//div[@class='mectrl_accountDetails']/div[1]")).getText();
		String id = driver.findElement(By.xpath("//div[@class='mectrl_accountDetails']/div[2]")).getText();
		
		System.out.println(name);
		System.out.println(id);
		
		// validating we are on oneCogizant page 
		driver.findElement(By.xpath("//div[@title='OneCognizant']")).click();
		Set<String> pages = driver.getWindowHandles();
		String beCognizant = driver.getWindowHandle();
		for (String page: pages) {
			if (!page.equals(beCognizant)) {
				driver.switchTo().window(page);
			}
		}
		
		
		WebElement oneCognizantTitle = driver.findElement(By.xpath("//a[contains(@class,'oneC_brandName')]"));
		String oneCognizantTitle_ = oneCognizantTitle.getText();
		
		System.out.println(oneCognizantTitle_);
		
		if (oneCognizantTitle_.equals("OneCognizant")) {
			System.out.println("Verified that the driver has moved to OneCognizant application");
		}
		else {
			System.out.println("Driver has not moved to OneCognizant application");
		}
		
		
		String oneCognizant = driver.getWindowHandle();
		
		WebElement search = driver.findElement(By.id("oneC_searchAutoComplete"));
		search.click();
		
		search.sendKeys("timesheet");
		driver.findElement(By.xpath("//*[@id=\"newSearchQALST\"]/div[1]/div/div[2]")).click();
		
		Thread.sleep(10000);
		
		// validating we are on timesheet page
		pages = driver.getWindowHandles();
		for(String page : pages) {
			if(!page.equals(beCognizant)&&!page.equals(oneCognizant)) {
				driver.switchTo().window(page);
			}
		}
		
		WebElement timesheetTitle = driver.findElement(By.id("PT_PAGETITLElbl"));
		String timesheetTitle_ = timesheetTitle.getText();
		System.out.println(timesheetTitle_);
		if(timesheetTitle_.equals("Timesheets")) {
			System.out.println("Verified that the driver has moved to Timesheet application");
		}
		else {
			System.out.println("Driver has not moved to Timesheet application");
		}
		
		
		//validating first 3 timesheets 
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		
		List<WebElement> dates = driver.findElements(By.xpath("//a[contains(@id,'CTS_TS_LAND_PER_DESCR30$')]"));
		for(int i=0;i<3;i++) {
			
			String date = dates.get(i).getText();
			 // Split the date range string
            String[] dateParts = date.split(" To ");
 
            // Parse the start date
            Date startDate =  sdf.parse(dateParts[0]);
            System.out.println("Start Date: " + sdf.format(startDate));
 
            // Parse the end date
            Date endDate = sdf.parse(dateParts[1]);
            System.out.println("End Date: " + sdf.format(endDate));
            
            Calendar s = Calendar.getInstance();
            s.setTime(startDate);
            
            Calendar e = Calendar.getInstance();
            e.setTime(endDate);
            
            if(s.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            	if(e.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            		System.out.println("Timesheet "+(i+1)+" is valid.");
            	}
            }
            else {
            	System.out.println("Timesheet "+(i+1)+" is not valid.");
            }
            
		}
		
		//verify search field is displayed or not 
		WebElement searchBy = driver.findElement(By.id("CTS_TS_LAND_WRK_CTS_TS_SEARCH"));
		if(searchBy.isDisplayed()) {
			System.out.println("Search by filed is displayed");
		}
		
		//selecting current date
		Select select = new Select(searchBy);
		select.selectByVisibleText("Date");
		
		WebElement dateIcon = driver.findElement(By.id("CTS_TS_LAND_WRK_DATE$prompt"));
		dateIcon.click();
		
		WebElement currDate = driver.findElement(By.id("curdate"));
		currDate.click();
		
		WebElement searchButton = driver.findElement(By.id("CTS_TS_LAND_WRK_SEARCH"));
		searchButton.click();
		
		
		
		
		// validating Displayed timesheet is of current date
		SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
		
		WebElement date = driver.findElement(By.id("CTS_TS_LAND_WRK_DATE"));
		String date_ = date.getAttribute("value");
		
		
		System.out.println(date_);
		
		Date d_ = sd.parse(date_);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d_);
		
		int week = cal.get(Calendar.WEEK_OF_YEAR);
	
		
		String dates_ = driver.findElement(By.xpath("//a[contains(@id,'CTS_TS_LAND_PER_DESCR30$')]")).getText();
		
		String [] dates_parts = dates_.split(" To ");
		
		String date1 = dates_parts[0];
		
		Date date1_ = sdf.parse(date1);
		
		String date2 = dates_parts[1];
		
		Date date2_ = sdf.parse(date2);
		
		cal.setTime(date1_);
		
		int weekOfdate1_ = cal.get(Calendar.WEEK_OF_YEAR);
		cal.setTime(date2_);
		int weekOfdate2_ = cal.get(Calendar.WEEK_OF_YEAR);
		

		if(week==weekOfdate1_||week==weekOfdate2_){
		System.out.println("Displayed timesheet is of current date:validated");
	}
	
		
		//validating displayed timesheets are of selected status

		Select search_ = new Select(driver.findElement(By.id("CTS_TS_LAND_WRK_CTS_TS_SEARCH")));
		search_.selectByVisibleText("Status");
		
		
		
	}

}
