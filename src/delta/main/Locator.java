package delta.main;

import org.openqa.selenium.By;

public class Locator {
	
	public static By getLocator(String input1)
	{
		By b = null;
		String[] str = input1.split("_");
		String locator = str[0];
		String value = str[1];
		if(locator.equalsIgnoreCase("ID"))
		{
			b = By.id(value);
		}
		else if(locator.equalsIgnoreCase("NAME"))
		{
			b = By.name(value);
		}
		else if (locator.equalsIgnoreCase("XPATH"))
		{
			b = By.xpath(value);
		}
		else if (locator.equalsIgnoreCase("CLASSNAME"))
		{
			b = By.className(value);
		}
		else if (locator.equalsIgnoreCase("Linktext"))
		{
			b = By.linkText(value);
		}
		else if (locator.equalsIgnoreCase("partialLinktext"))
		{
			b = By.partialLinkText(value);
		}
		else if (locator.equalsIgnoreCase("cssSelector"))
		{
			b = By.cssSelector(value);
		}
		else if (locator.equalsIgnoreCase("tagname"))
		{
			b = By.tagName(value);
		}
		else
		{
			System.out.println("Invalid selector");
		}
		return b;
		
	}

}
