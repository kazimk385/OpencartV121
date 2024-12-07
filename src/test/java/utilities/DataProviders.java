package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;


public class DataProviders {
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException {
		String path=".\\testData\\OpenCart_LoginData.xlsx"; // taking excel file from testData
		
		ExcelUtility xlutil = new ExcelUtility(path); // creating an object for XLUtility 
	
		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1", 1);
		
		String logindata[][]= new String[totalrows][totalcols]; //created two dimensional array to get rows & columns
	
		for(int r=1; r<=totalrows; r++) {
			for(int c=0; c<totalcols; c++) {
				logindata[r-1][c]=xlutil.getCellData("Sheet1",r, c);
			}
		}
	return logindata; // returning two dimensional array in string format
	}

	// DataProvder2  we can multiple in future as per need
	
	//DataProvder3
}
