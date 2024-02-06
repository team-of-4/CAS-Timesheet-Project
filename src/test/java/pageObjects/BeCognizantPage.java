package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BeCognizantPage extends BasePage {

	WebDriver driver;
	
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

	public void clickUserInfo() throws InterruptedException {
		
//		WebDriverWait wt = new WebDriverWait(driver,Duration.ofSeconds(20));
//		
//		wt.until(ExpectedConditions.elementToBeClickable(btnUserInfo));
		
		btnUserInfo.click();
	}
	
	public String getUserName() {
		String userName = userInfoName.getText();
		return userName;
	}
	
	public String getUserId() {
		String userId = userInfoId.getText();
		return userId;
	}
	
	public void clickOneCognizant() {
		btnOneCognizant.click();
	}
	
}
