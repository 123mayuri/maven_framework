package generics;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class utility {
	public static String getForematedDateTime()
	{
		SimpleDateFormat sd = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		return sd.format(new Date());
	}
	public static String getPageScreenShot(WebDriver driver, String imageFolderPath)
	{
		String imagePath = imageFolderPath+"/"+getForematedDateTime()+".png";
		EventFiringWebDriver eDriver = new EventFiringWebDriver(driver);
		try
		{
		   org.apache.commons.io.FileUtils.copyFile(eDriver.getScreenshotAs(OutputType.FILE), new File(imagePath));
		}
		catch(Exception e)
		{
			
		}
	 return imagePath;
	}
	

}
