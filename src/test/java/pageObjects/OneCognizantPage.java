package pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OneCognizantPage extends BasePage {

	public OneCognizantPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath="//a[contains(@class,'oneC_brandName')]")
	WebElement oneCognizantTitle;
	
	@FindBy(id="oneC_searchAutoComplete")
	WebElement btnSearch;
	
	@FindBy(xpath="//*[@id='newSearchQALST']/div[1]/div/div[2]")
	WebElement btnSubmitSheet;
	
	public String getTitle() {
		return oneCognizantTitle.getText();
	}
	
	public void clickSearch() {
		btnSearch.click();
		btnSearch.sendKeys("submit timesheet");
	}
	
	public void clickSubmitTimesheet() {
		btnSubmitSheet.click();
	}
	
	public void driverShift(WebDriver driver) {
		
		Set<String> Window=driver.getWindowHandles();
		List<String> Window1=new ArrayList<String>(Window);
//		System.out.println(Window.size());
//		System.out.println("1 = "+Window1.get(1));
//		System.out.println("2 = "+Window1.get(0));
		driver.switchTo().window(Window1.get(2));
		
	}
	
	
	
	
}
