package WeatherShopperTestScenarios;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import Common.CommonMethods;
import Common.Driver_helper;


public class Base extends CommonMethods  {

	
	@BeforeSuite
	public void beforesuite() {
		
		//start DB connection if any
	}
	
	@AfterSuite
	public void Aftersuite() {
		//close DB connection if any
	}
}
