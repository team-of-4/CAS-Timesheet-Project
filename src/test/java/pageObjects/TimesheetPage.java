package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class TimesheetPage extends BasePage{

	public TimesheetPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(id="PT_PAGETITLElbl")
	WebElement timesheetTitle;
	
	@FindBy(xpath="//a[contains(@id,'CTS_TS_LAND_PER_DESCR30$')]")
	List<WebElement> dates;
	
	@FindBy(id="CTS_TS_LAND_WRK_CTS_TS_SEARCH")
	WebElement fieldSearchby;
	
	@FindBy(id="CTS_TS_LAND_WRK_DATE$prompt")
	WebElement dateIcon;
	
	@FindBy(id="curdate")
	WebElement currDate;
	
	@FindBy(id="CTS_TS_LAND_WRK_SEARCH")
	WebElement searchButton;
	
	@FindBy(id="CTS_TS_LAND_WRK_DATE")
	WebElement date;
	
	@FindBy(xpath="//a[contains(@id,'CTS_TS_LAND_PER_DESCR30$')]")
	WebElement timesheetDate;
	
	@FindBy(id="CTS_TS_LAND_WRK_CTS_TS_LAND_STATUS")
	WebElement drpdwnStatus;
	
	@FindBy(xpath="//span[contains(@id, 'CTS_TS_LAND_PER_CTS_TS_STATUS_LAND$')]")
	List<WebElement> timesheetList;
	
	@FindBy(id="#ICOK")
	WebElement btnOk;
	
	public String getTitle() {
		
		return timesheetTitle.getText();
	}
	
	public List<String> getTimesheets() {
		
		List<String> timesheetDatesList  = new ArrayList<String>();
		
		for(int i=0;i<dates.size();i++) {
			timesheetDatesList.add(i,dates.get(i).getText());
		}
		return timesheetDatesList;
		
		
	}
	
	public boolean searchbyStatus() {
		
		return fieldSearchby.isDisplayed();
	}
	
	public String getCurrentDate() throws InterruptedException {
		
		Select select = new Select(fieldSearchby);
		select.selectByVisibleText("Date");
		dateIcon.click();
		currDate.click();
		searchButton.click();
		Thread.sleep(3000);
		String currentDate = date.getAttribute("value");
		
		return currentDate;
	}
	
	public String[] getTimesheetDate() {
		
		String timesheetDateTxt = timesheetDate.getText();
		String[] timesheetDates_parts = timesheetDateTxt.split(" To ");
		
		return timesheetDates_parts;
		
	}
	
//	public List<String> selectApproved() throws InterruptedException {
//		
//		Thread.sleep(2000);
//		Select select = new Select(fieldSearchby);
//		select.selectByVisibleText("Status");
//		
//		Select status = new Select(drpdwnStatus);
//		status.selectByVisibleText("Approved");
//		
//		searchButton.click();
//		Thread.sleep(2000);
//		List<String> approvedOptionTxt = new ArrayList<String>();
//		
//		
//		
//		
//		for (WebElement approvedOption: timesheetList) {
//			approvedOptionTxt.add(approvedOption.getText());
//		}
//		
//		return approvedOptionTxt;
//		
//	}
	
//	public void selectOverdue() {
//		
//		Select status = new Select(drpdwnStatus);
//		status.selectByVisibleText("Overdue");
//		searchButton.click();
//		btnOk.click();
//	}
	
	public List<String> selectStatus(String statusOption) throws InterruptedException {
		
		Thread.sleep(2000);
		Select select = new Select(fieldSearchby);
		select.selectByVisibleText("Status");
	
		Select status = new Select(drpdwnStatus);
		status.selectByVisibleText(statusOption);
		searchButton.click();
		Thread.sleep(2000);
		List<String> OptionTxt = new ArrayList<String>();
		
		try {
			
			if(btnOk.isDisplayed()) {
				btnOk.click();
			}
			
		}
		catch(Exception e) {
				
				for(WebElement option: timesheetList) {
					OptionTxt.add(option.getText());
				}
			
		}
		
		return OptionTxt;
	}
	
	
}
