package delta.main;

import org.testng.annotations.DataProvider;

import generics.Excel;

public class BaseDriver {
	
	@DataProvider
	public  String[][] getScenarios()
	{
		String controllerpath  = "./scripts/Controller.xlsx";
		String suitesheet = "Suite";
		int rc1 = Excel.getRowCount(controllerpath, suitesheet);
		String[][] data = new String[rc1][2];
		//data[0][0] = "scenario1";
		//data[1][0]="scenario2";
		
		for(int i = 1; i<=rc1; i++)
		{
		String scenarioName= Excel.getCellValue(controllerpath, suitesheet, i, 0);
		String executionStatus = Excel.getCellValue(controllerpath, suitesheet, i, 1);
		data[i-1][0]= scenarioName;
		data[i-1][1]=executionStatus;
		}
		
		return data;
	}

	
	
}
    
