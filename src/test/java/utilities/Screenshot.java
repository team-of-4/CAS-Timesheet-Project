package utilities;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {
	public static void takeScreenShot(WebDriver driver,String imgName) {
		try {
		TakesScreenshot ss = ((TakesScreenshot) driver);
		File srcFile = ss.getScreenshotAs(OutputType.FILE);
		File destFile = new File(System.getProperty("user.dir") + "\\screenshots\\"  + imgName+".png");
		FileUtils.copyFile(srcFile, destFile);
		} catch(Exception e) {}
	}
}
