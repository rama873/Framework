package Common;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Exceldata_retiever {
	
	
  public void getTestcontrolData(String filename, String Sheet, String ColumnName) {
	  
	  getxlsxColumnValues( filename, Sheet, ColumnName);
	  
  }

private void getxlsxColumnValues(String filename, String sheet, String columnName) {
	// TODO Auto-generated method stub
	try {
	FileInputStream fis=new FileInputStream(new File(filename));  
	XSSFWorkbook wb = new XSSFWorkbook(fis);   
	Sheet getsheet=wb.getSheet(sheet);
	int rows = getsheet.getPhysicalNumberOfRows();

	
	
	}catch(Exception e) {
		e.printStackTrace();
	}
}
  
}
