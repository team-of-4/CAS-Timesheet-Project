package testCases;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BeCognizantPage;
import pageObjects.OneCognizantPage;
import pageObjects.TimesheetPage;
import testBase.BaseClass;
import utilities.Screenshot;

public class Testcases extends BaseClass{
	
	BeCognizantPage be;
	OneCognizantPage op;
	TimesheetPage tp;
	public static List<Boolean> optionStatus = new ArrayList<Boolean>(); 
	
	@Test(priority=1)
	public void verify_userInfo() throws InterruptedException, IOException  {
		
		 be = new BeCognizantPage(driver);
		be.clickUserInfo();
		String userName = be.getUserName();
		String userId = be.getUserId();
		
		String Name = "Chakote, Shubham (Cognizant)";
		String Id = "2303652@cognizant.com";

		Screenshot.takeScreenShot(driver, "BeCognizantUserInfo");
		Assert.assertEquals(true, userName.equals(Name)&& userId.equals(Id),"The username and userid is not valid!");
		
		System.out.println("Verified that user details has been verified ☑️");
		logger.info("Verified that user details has been verified ☑️");
		
		be.clickOneCognizant(driver);
		be.driverShift(driver);
	}
	
	@Test(priority=2)
	public void TC_002_OneCognizant() throws InterruptedException {
		
		 op = new OneCognizantPage(driver);
		String title = op.getTitle();
		
		Screenshot.takeScreenShot(driver, "OneCognizant");
		Assert.assertEquals("OneCognizant", title,"the onecognizant title not matched");
		System.out.println("Verified that driver has been moved to onecognizant page ☑️");
		logger.info("Verified that driver has been moved to onecognizant page ☑️");
		op.clickSearch();
		op.clickSubmitTimesheet();
		Thread.sleep(5000);
		op.driverShift(driver);
	} 
	

	
	
	@Test(priority=3)
	public void TC_003_verifyNavigatedToTimesheet() {
		
		TimesheetPage tp = new TimesheetPage(driver);
		String title = tp.getTitle();
		
		Screenshot.takeScreenShot(driver, "Timesheet");
		Assert.assertEquals("Timesheets",title);
		System.out.println("Verified that driver has been moved to timesheet page ☑️");
		logger.info("Verified that driver has been moved to timesheet page ☑️");
	}
	
	@Test(priority=4)
	public void TC_004_validateTimesheets() throws ParseException, IOException {
		
		tp = new TimesheetPage(driver);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		
		List<String> timesheetDates = tp.getTimesheets();
//		ExcelUtils.write("First 3 timesheets", 0, 0, "Timesheet Dates");
		for(int i=0;i<3;i++) {
			
			String date = timesheetDates.get(i);
		//	ExcelUtils.write("First 3 timesheets", 0, (i+1), "Timesheet Dates");
			 // Split the date range string
            String[] dateParts = date.split(" To ");
 
            // Parse the start date
            Date startDate =  sdf.parse(dateParts[0]);
           // System.out.println("Start Date: " + sdf.format(startDate));
 
            // Parse the end date
            Date endDate = sdf.parse(dateParts[1]);
          //  System.out.println("End Date: " + sdf.format(endDate));
            
            Calendar s = Calendar.getInstance();
            s.setTime(startDate);
            
            Calendar e = Calendar.getInstance();
            e.setTime(endDate);
            
            Assert.assertEquals((s.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)&&(e.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY),true,"Timesheet"+(i+1)+"is not valid");
            
            System.out.println("Verified that Timesheet"+(i+1)+"is valid ☑️");
            logger.info("Verified that Timesheet"+(i+1)+"is valid ☑️");
		}
		
	}
	
	@Test(priority=5)
	public void TC_005_verifySearchField() {
		
		tp = new TimesheetPage(driver);
			
		Screenshot.takeScreenShot(driver, "SearchBy");
		Assert.assertEquals(tp.searchbyStatus(), true,"search filed is not displayed ❌ ");
		System.out.println("Verified that search filed is displayed ☑️");
		logger.info("Verified that search filed is displayed ☑️");
	}
	
	@Test(priority=6)
	public void TC_006_verifyCurrentDateTimesheet() throws ParseException, InterruptedException, IOException {
		
		TimesheetPage tp = new TimesheetPage(driver);
		
		SimpleDateFormat sd = new SimpleDateFormat("MM/dd/yyyy");
		//Thread.sleep(3000);
		String date = tp.getCurrentDate();

		Date d_ = sd.parse(date);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(d_);
		
		int week = cal.get(Calendar.WEEK_OF_YEAR);
		
		String[] dates_parts = tp.getTimesheetDate();
		
		String date1 = dates_parts[0];
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		
		Date date1_ = sdf.parse(date1);
		
		String date2 = dates_parts[1];
		
		Date date2_ = sdf.parse(date2);
		
		cal.setTime(date1_);
		int weekOfdate1_ = cal.get(Calendar.WEEK_OF_YEAR);
		
		cal.setTime(date2_);
		int weekOfdate2_ = cal.get(Calendar.WEEK_OF_YEAR);
		
//		System.out.println(week+" "+weekOfdate1_+" "+weekOfdate2_);
		Screenshot.takeScreenShot(driver, "CurrentDateTimesheet");
		Assert.assertEquals(week==weekOfdate1_||week==weekOfdate2_, true,"Displayed timesheet is not of current date");
		System.out.println("Verified that displayed timesheet is of current date ☑️ ");
		logger.info("Verified that displayed timesheet is of current date ☑️ ");
	}
	
	@Test(priority=7)
	public void TC_007_verifyApprovedStatus() throws InterruptedException, IOException {
		
		tp = new TimesheetPage(driver);
	
		List<String>approvedOptions = tp.selectStatus("Approved");

		Screenshot.takeScreenShot(driver, "ApprovedStatus");
		boolean verified = true;
		if(approvedOptions.size()==0) {
			System.out.println("Approved status has no records");

		}
		else {
			for (String approvedOption: approvedOptions) {

				if(!approvedOption.equals("Approved")) verified = false;
				
				Assert.assertEquals(approvedOption, "Approved","wrong status timesheet is displayed in approved status");
			}
			
			System.out.println("Verified that all displayed timesheets are of approved status ☑️");
			logger.info("Verified that all displayed timesheets are of approved status ☑️");
		}
		
		optionStatus.add(verified);
		
		
	}
	
	@Test(priority=8)
	public void TC_008_verifyOverdueStatus() throws InterruptedException {
		
		
		tp = new TimesheetPage(driver);
		
		List<String> overdueOptions = tp.selectStatus("Overdue");
	
		Screenshot.takeScreenShot(driver, "OverdueStatus");
		boolean verified = true;
		if(overdueOptions.size()==0) {
			System.out.println("Overdue status has no records");
		}
		else {
			
			for (String Option: overdueOptions) {
				if(!Option.equals("Overdue")) verified = false;
				Assert.assertEquals(Option, "Overdue","wrong status timesheet is displayed in overdue status");
				
			}
			System.out.println("Verified that all displayed timesheets are of overdue status ☑️");
			logger.info("Verified that all displayed timesheets are of overdue status ☑️");
		}
		
		
		optionStatus.add(verified);
		
	}
	
	@Test(priority=9)
	public void TC_009_verifyPartiallyApprovedStatus() throws InterruptedException {
			
			
			tp = new TimesheetPage(driver);
			
			List<String> partiallyApprovedOptions = tp.selectStatus("Partially Approved");
			
			Screenshot.takeScreenShot(driver, "PartiallyApprovedStatus");
			boolean verified = true;
			if(partiallyApprovedOptions.size()==0) {
				System.out.println("Partially Approved status has no records");
			}
			else {
				
				for (String Option: partiallyApprovedOptions) {
					if(!Option.equals("Partially Approved")) verified = false;
					Assert.assertEquals(Option, "Partially Approved","wrong status timesheet is displayed in partially approved status");
					
				}
				System.out.println("Verified that all displayed timesheets are of partially approved status ☑️");
				logger.info("Verified that all displayed timesheets are of partially approved status ☑️");
			}
			
			
			optionStatus.add(verified);
			
		}
	
	
	@Test(priority=10)
	public void TC_010_verifyPendingStatus() throws InterruptedException {
			
			
			tp = new TimesheetPage(driver);
			
			List<String> pendingOptions = tp.selectStatus("Pending");
			
			Screenshot.takeScreenShot(driver, "PendingStatus");
			boolean verified = true;
			if(pendingOptions.size()==0) {
				System.out.println("Pending status has no records");
			}
			else {
				
				for (String Option: pendingOptions) {
					if(!Option.equals("Pending")) verified = false;
					Assert.assertEquals(Option, "Pending","wrong status timesheet is displayed in Pending status");
				}
				System.out.println("Verified that all displayed timesheets are of pending status ☑️");
				logger.info("Verified that all displayed timesheets are of pending status ☑️");
			}
			
			
			optionStatus.add(verified);
			
		}
	
	@Test(priority=11)
	public void TC_011_verifySavedStatus() throws InterruptedException {
			
			
			tp = new TimesheetPage(driver);
			
			List<String> savedOptions = tp.selectStatus("Saved");
			
			Screenshot.takeScreenShot(driver, "SavedStatus");
			boolean verified = true;
			if(savedOptions.size()==0) {
				System.out.println("Saved status has no records");
			}
			else {
				
				for (String Option: savedOptions) {
					if(!Option.equals("Saved")) verified = false;
					Assert.assertEquals(Option, "Saved","wrong status timesheet is displayed in partially saved status");
				}
				System.out.println("Verified that all displayed timesheets are of saved status ☑️");
				logger.info("Verified that all displayed timesheets are of saved status ☑️");
			}
			
			
			optionStatus.add(verified);
			
		}
	
	@Test(priority=12)
	public void TC_012_verifySentBackforRevisionStatus() throws InterruptedException {
			
			
			tp = new TimesheetPage(driver);
			
			List<String> sentBackforRevisionOptions = tp.selectStatus("Sent Back for Revision");
			
			Screenshot.takeScreenShot(driver, "SentBackforRevisionStatus");
			boolean verified = true;
			if(sentBackforRevisionOptions.size()==0) {
				System.out.println("Sent Back for Revision status has no records");
			}
			else {
				
				for (String Option: sentBackforRevisionOptions) {
					if(!Option.equals("Sent Back for Revision")) verified = false;
					Assert.assertEquals(Option, "Sent Back for Revision","wrong status timesheet is displayed in partially Sent Back for Revision status");
				}
				System.out.println("Verified that all displayed timesheets are of sent back for revision status ☑️");
				logger.info("Verified that all displayed timesheets are of sent back for revision status ☑️");
			}
			
			optionStatus.add(verified);
			
			
		}
	
	
	
	@Test(priority=13)
	public void TC_013_verifySubmittedforApprovalStatus() throws InterruptedException {
		
		tp = new TimesheetPage(driver);
		
		List<String> subforAppOptions = tp.selectStatus("Submitted for Approval");
		Screenshot.takeScreenShot(driver, "SubmittedforApprovalStatus");
		boolean verified = true;
		if(subforAppOptions.size()==0) {
			System.out.println("Submitted for Approval status has no records");
		}
		else {
			
			for (String Option: subforAppOptions) {
				if(!Option.equals("Submitted for Approval")) verified = false;
				Assert.assertEquals(Option, "Submitted for Approval","wrong status timesheet is displayed in Submitted for Approval status");
			}
			System.out.println("Verified that all displayed timesheets are of submitted for approval status ☑️");
			logger.info("Verified that all displayed timesheets are of submitted for approval status ☑️");
		}
		
		optionStatus.add(verified);
		
	}
	
//	@Test(priority=14)
//	public void writeIntoExcel() throws IOException {
//		ExcelUtils.statusDetails(optionStatus);
//		System.out.println("written sucessfully");
//	}
	
}
