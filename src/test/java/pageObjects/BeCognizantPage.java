package pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BeCognizantPage extends BasePage {

	public WebDriver driver;
	
	public BeCognizantPage(WebDriver driver) {
		super(driver);
	}
	
	
	
	@FindBy(xpath="//button[contains(@title,'Account manager')]")
	WebElement btnUserInfo;
	
	//@FindBy(xpath="//div[@class='mectrl_accountDetails']/div[1]")
	@FindBy(id="mectrl_currentAccount_primary")
	WebElement userInfoName;
	
	//@FindBy(xpath="//div[@class='mectrl_accountDetails']/div[2]")
	@FindBy(id="mectrl_currentAccount_secondary")
	WebElement userInfoId;
	
	@FindBy(xpath="//div[@title='OneCognizant']")
	WebElement btnOneCognizant;
	
	@FindBy(xpath="(//span[@id='CaptionElementView'])[2]")
	WebElement btnAnnouncements;
	
	@FindBy(xpath="(//span[@id='CaptionElementView'])[3]")
	WebElement btnAppsAndTools;
	

	public void clickUserInfo() throws InterruptedException {
				
		btnUserInfo.click();
//		Thread.sleep(1000);
//		btnUserInfo.click();
//		Thread.sleep(1000);
//		btnUserInfo.click();
	}
	
	public String getUserName() {
		String userName = userInfoName.getText();
		return userName;
	}
	
	public String getUserId() {
		String userId = userInfoId.getText();
		return userId;
	}
	
	public void clickOneCognizant(WebDriver driver) {
		
		
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",btnAnnouncements);
	//	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",btnAppsAndTools);
		//btnUserInfo.click();
		btnOneCognizant.click();
	}
	
	public void driverShift(WebDriver driver) {
		
//		Set<String> pages = driver.getWindowHandles();
//		String beCognizant = driver.getWindowHandle();
//		for (String page: pages) {
//			if (!page.equals(beCognizant)) {
//				driver.switchTo().window(page);
//			}
//		}
//		return beCognizant;
		
		Set<String> WindowIDSet=driver.getWindowHandles();
		List<String>WindowIDList = new ArrayList<String>(WindowIDSet);
//		System.out.println("One beCognizant Window Handle = "+WindowIDList.get(0));
//		System.out.println("One Cognizant Window Handle = "+WindowIDList.get(1));
		driver.switchTo().window(WindowIDList.get(1));
		
	}
	
}
