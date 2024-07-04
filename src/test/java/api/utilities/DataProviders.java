package api.utilities;

import org.testng.annotations.DataProvider;

public class DataProviders {

	@DataProvider(name = "TestCaseData")
	public Object[][] allDataprovider() {
		
		String fileName = System.getProperty("user.dir")+"TestData\\ExcelTestData.xlsx";
		
		int ttlRowCont = ReadExcelFile.getRowCount(fileName, "Sheet1");
		
		int ttlColumCont = ReadExcelFile.getColumCount(fileName, "Sheet1");
		
		String [][] userData = new String[ttlRowCont-1][ttlColumCont];
		
		for (int rowNo = 1; rowNo < ttlRowCont; rowNo++) {
			
			for (int columNo = 0; columNo <= ttlColumCont; columNo++) {
				
				userData[rowNo-1][columNo] = ReadExcelFile.getCellValue(fileName, "Sheet1", rowNo, columNo);
			}
		}
		
		return userData;
		
	}
	
	@DataProvider(name = "userNameData")
	public Object[] userNameDataProvider() {
		
		String fileName = System.getProperty("user.dir")+"TestData\\ExcelTestData.xlsx";
		
		int ttlRowCont = ReadExcelFile.getRowCount(fileName, "Sheet1");
		
		//int ttlColumCont = ReadExcelFile.getColumCount(fileName, "Sheet1");
		
		String [] userNameData = new String[ttlRowCont-1];
		
		for (int rowNo = 1; rowNo < ttlRowCont; rowNo++) {
			
			//for (int columNo = 0; columNo <= ttlColumCont; columNo++) {
				userNameData[rowNo-1]= ReadExcelFile.getCellValue(fileName, "Sheet1", rowNo, 1);
			//}
		}
		
		return userNameData;
		
	}
	
}
