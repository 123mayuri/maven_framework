package delta.main;
//import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Keyword {
public static void executeKeyword(WebDriver driver, String action, String input1, String input2)
{
    
    if(action.equalsIgnoreCase("enter"))
    {
    	//driver.findElement(By.xpath(input1)).sendKeys(input2);
    	//after adding locator.java
    	driver.findElement(Locator.getLocator(input1)).sendKeys(input2);
    }
    else if(action.equalsIgnoreCase("click"))
    {
    	driver.findElement(Locator.getLocator(input1)).click();
    }
    else
    {
    	System.out.println("Invalid actions:"+action);
    }
    
    }
    
 

}
