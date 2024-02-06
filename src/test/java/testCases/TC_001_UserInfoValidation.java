package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.BeCognizantPage;
import testBase.BaseClass;

public class TC_001_UserInfoValidation extends BaseClass{
	
	@Test
	public void verify_userInfo()  {
		BeCognizantPage be = new BeCognizantPage(driver);
		be.clickUserInfo();
		String userName = be.getUserName();
		String userId = be.getUserId();
		
		String Name = "Chakote, Shubham (Cognizant)";
		String Id = "2303652@cognizant.com";
		
		Assert.assertEquals(true, userName.equals(Name)&& userId.equals(Id),"The username and userid is not valid!");
		
		be.clickOneCognizant();
	}
	

}
