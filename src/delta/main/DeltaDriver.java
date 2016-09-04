package delta.main;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
//import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
//import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import generics.Excel;
import generics.Property;
import generics.utility;


public class DeltaDriver extends BaseDriver {
	public WebDriver driver;
	public static ExtentReports eReport;
	public ExtentTest testreport;
	public String imageFolderPath ="./screenShots";
	public String reportFilePath = "./report/results.html";	
	public  String configPath = "./config/config.properties";
	public String scenariosPath = "./scripts/Scenarios.xlsx";
	public String browser;
	public String hubURL;
	
	@BeforeTest
	public void initFramework()
	{
		eReport = new ExtentReports(reportFilePath);
	}
	
	
	@BeforeMethod
	public void launchApp(XmlTest xmltest) throws MalformedURLException
	{
				
	    
	    
         browser = xmltest.getParameter("browser");
         hubURL=xmltest.getParameter("hubURL");
         DesiredCapabilities dc= new DesiredCapabilities();
         dc.setBrowserName(browser);
         dc.setPlatform(Platform.ANY);         
         driver = new RemoteWebDriver(new URL(hubURL),dc);
//		if(browser.contains("chrome"))
//		{
//			System.setProperty("webdriver.chrome.driver", "C:\\Users\\CBT\\Giridhar\\Jar files\\chromedriver.exe");
//			driver = new ChromeDriver();
//		}
//		else if (browser.contains("firefox"))
//		{
//			driver = new FirefoxDriver();
//		} 
        String appURl =Property.getPropertyvalue(configPath,"URL");
 	    String timeOut = Property.getPropertyvalue(configPath, "TimeOut");
		driver.get(appURl);
		driver.manage().timeouts().implicitlyWait(Long.parseLong(timeOut),TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
	}
	
	@Test(dataProviderClass=BaseDriver.class, dataProvider="getScenarios")
    public void testScenario( String scenarioSheet, String executionStatus)
    {
		
       // String scenarioSheet = "scenario1";
		
		
        testreport = eReport.startTest(browser+"_"+scenarioSheet);
        
        if(executionStatus.equalsIgnoreCase("yes"))
        {
        	int stepCount = Excel.getRowCount(scenariosPath, scenarioSheet);
        for(int i =1; i <= stepCount; i++ )
        {
        String description  = Excel.getCellValue(scenariosPath, scenarioSheet, i,0);
        String action =  Excel.getCellValue(scenariosPath, scenarioSheet, i,1);
        String input1 =  Excel.getCellValue(scenariosPath, scenarioSheet, i,2);
        String input2 = Excel.getCellValue(scenariosPath, scenarioSheet, i,3);
        //System.out.println(description + action + input1 + input2);
        
        /*if(action.equalsIgnoreCase("enter"))
        {
        	driver.findElement(By.xpath(input1)).sendKeys(input2);
        }
        else if(action.equalsIgnoreCase("click"))
        {
        	driver.findElement(By.xpath(input1)).click();
        }
        else
        {
        	System.out.println("Invalid actions:"+action);
        }
        
        } */
        String msg = "description:"+description+ "action:" + action+ "input1:" +input1+ "input2:" +input2;
        testreport.log(LogStatus.INFO,msg);
        Keyword.executeKeyword(driver, action, input1, input2);
        //Assert.fail();
        } 
        }
        else
        {
        	testreport.log(LogStatus.SKIP, "Exescution Status in NO");
        	throw new SkipException ("Skipping Scenario as Exescution Status in NO");
        	
        }
              
         
    }
	@AfterMethod
	public void quitapp(ITestResult test)
	{
		if(test.getStatus() == ITestResult.FAILURE)// can be written as if(test.getStatus() == 2)
		{
		String pImage = utility.getPageScreenShot(driver, imageFolderPath);
	    String p = testreport.addScreenCapture("."+pImage );
	    testreport.log(LogStatus.FAIL,"page ScreenShot:"+ p);
		}
		eReport.endTest(testreport);;
		
		driver.close();
	}
	
	@AfterTest
	public void endFrameWork()
	{
		eReport.flush();
	}
	  
}
