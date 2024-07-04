package api.utilities;

import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcelFile {

	public static InputStream fis;
	public static Workbook wbook;
	public static Sheet sheet;
	public static Row row;
	public static Cell cell;

	public static String getCellValue(String fileName,String sheetName, int rowNo, int cellNo) {
		String cellValue = null;
		try {
		 fis = new FileInputStream(fileName);
		 wbook = new XSSFWorkbook(fis);
		 sheet = wbook.getSheet(sheetName);
		 row =  sheet.getRow(rowNo);
		 cell = row.getCell(cellNo);
		 cellValue = cell.getStringCellValue();
		wbook.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return cellValue;
	}

	public static int getRowCount(String fileName, String sheetName) {
		int ttlRows = -1;
		try {
			fis = new FileInputStream(fileName);
			wbook = new XSSFWorkbook(fis);
			sheet = wbook.getSheet(sheetName);
			ttlRows = sheet.getLastRowNum() + 1;
			wbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ttlRows;
	}

	public static int getColumCount(String fileName, String sheetName) {
		int ttlCell = -1;
		try {
			fis = new FileInputStream(fileName);
			wbook = new XSSFWorkbook(fis);
			sheet = wbook.getSheet(sheetName);
			ttlCell = sheet.getRow(0).getLastCellNum();
			wbook.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return ttlCell;
	}

}
