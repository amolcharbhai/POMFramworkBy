package com.qa.hubspot.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")
			+ "\\src\\main\\java\\com\\qa\\hubspot\\testdata\\HubSpot_TestData.xlsx";
	public static Workbook book;
	public static Sheet sheet;

	public static Object[][] getTestData(String sheetName) {

		FileInputStream File = null;
		try {
			File = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			book = WorkbookFactory.create(File);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);

		Object data[][] = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

		for (int rownum = 0; rownum < sheet.getLastRowNum(); rownum++) {
			for (int colnum = 0; colnum < sheet.getRow(0).getLastCellNum(); colnum++) {
				data[rownum][colnum] = sheet.getRow(rownum + 1).getCell(colnum).toString();
			}

		}
		return data;
	}

}
