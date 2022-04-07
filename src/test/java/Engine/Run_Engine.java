package Engine;

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

import Common.Exceldata_retiever;

public class Run_Engine {

	public static void main(String[] args) {
		try {
		// Create object of TestNG Class
			
			
			
		Exceldata_retiever getExceldata = new Exceldata_retiever();
		getExceldata.getTestcontrolData(".//Test_app.xlsx", "Config", "Type");
			
			
		TestNG runner=new TestNG();
		 
		// Create a list of String 
		List<String> suitefiles=new ArrayList<String>();
		 
		// Add xml file which you have to execute
		suitefiles.add(".\\testng.xml");
		 
		// now set xml file for execution
		runner.setTestSuites(suitefiles);
		 
		// finally execute the runner using run method
		runner.run();
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Unable to Run tetsNG XML");
		}
	}

}
