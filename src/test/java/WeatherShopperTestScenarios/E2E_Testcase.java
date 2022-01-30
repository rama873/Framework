package WeatherShopperTestScenarios;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.google.gson.JsonParser;
import Common.CommonMethods;
import Common.Driver_helper;
import Common.ExtentReporterNG;
import Common.UIObjectRetriever;
import Helpers.Weather_Helper;

public class E2E_Testcase extends Base {
	
	public WebDriver driver = null;
	private String URL =null;
	private int Temperature=0;
	static ExtentReports extent;
	ExtentTest Reporter;
	//ExtentReports extent = ExtentReporterNG.configExtentReport(); 
	UIObjectRetriever landigPageObj = new UIObjectRetriever("weathershopper_home");
	 Weather_Helper weatherHelper = new Weather_Helper();
	CommonMethods CommonMethods = new CommonMethods();
	
	@BeforeClass
	public void beforeClass() {
		driver = Driver_helper.Setup();
		URL = CommonMethods.getSuiteConfiguration("URL");
		driver.get(URL);
		
		
		String path =System.getProperty("user.dir")+"\\Reports\\ExecutionReport.html";
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(path);
		reporter.config().setReportName("weathershopper Results");
		reporter.config().setDocumentTitle("Test Results");
		extent =new ExtentReports();
		extent.attachReporter(reporter);
	}
	
	@Test
	public void ExecuteWeathershopper() throws ClassNotFoundException {
		Reporter = extent.createTest("Validation End to End Test case for weathershopper");
		Reporter.info("validation begins here - Naviagted to the Landing page: "+driver.getCurrentUrl() );

	
		Temperature = weatherHelper.GetCurrentTemperature(Reporter, driver);
		Reporter.info("Current temperature of the Weather is :<b>"+Temperature+"</b>");
		
		if(Temperature < 19) {
			Reporter.pass("Shopping for Moisturizers as weather is less than 37 which is: "+Temperature);
			ShoppingForMoisturizers(Reporter, driver);
		}
		else if(Temperature > 34) {
			Reporter.pass("Shopping for Sunscreens as weather is greater than 37 which is: "+Temperature);
			ShoppingForSunscreens(Reporter, driver);
		}
		else
			Reporter.info("Terminating the executions as weather is :"+Temperature);
	}
	
	
	
	
	public void ShoppingForMoisturizers(ExtentTest reporter, WebDriver driver) {
		// TODO Auto-generated method stub
		HashMap<WebElement,Integer> targetwebElements = new HashMap();
		HashMap<String,Integer> Checkoutmap = new HashMap();
		try {
			Reporter.info("Starting the Validation for Moisturizers Shopping");
			CommonMethods.logClick(reporter, driver, landigPageObj.getPageObject("BUYMOISTURIZER", "XPATH"), "WeatherShopperPage");
			
			if(!weatherHelper.VerifyNavigatingPage(reporter, driver, "Moisturizers"))
				Assert.assertEquals(false, true, "Failed While naviagting to the page");
			
			targetwebElements = weatherHelper.getMoisturizerTargetProducts(reporter, driver);
			HashMap<String, Integer> targetProduts = new HashMap();
			
			for(Map.Entry<WebElement,Integer> map : targetwebElements.entrySet() ) {
				Thread.sleep(2000);
				targetProduts.put(map.getKey().getAttribute("onclick").split("'")[1],map.getValue());
				map.getKey().click();
				reporter.pass("Added <b>"+ map.getKey().getAttribute("onclick").split("'")[1] +"</b> to the Cart with amount"
						+ " <b>"+ map.getValue()+"</b>");
			}
			
			Weather_Helper weatherHelper1 = new Weather_Helper();
			if(weatherHelper1.VerifyNumberofCartsItems(reporter, driver))
				CommonMethods.logClick(reporter, driver, landigPageObj.getPageObject("CARTS_BUTTON", "XPATH"), "carts button");
			else
				Assert.assertEquals(false, true, "Failed to click on the carts button");
			
			
			//Checkout navigation and validation
			if(!weatherHelper.VerifyNavigatingPage(reporter, driver, "Checkout"))
				Assert.assertEquals(false, true, "Failed While naviagting to the page");
			
			Checkoutmap = weatherHelper1.getcheckoutpageItems(reporter, driver);
			
			if(!weatherHelper1.VerifyItemsinCheckout(reporter, driver, Checkoutmap, targetProduts))
				Assert.assertEquals(false, true, "Failed While comparing products in Checkout");
			
			
			//Payment
			CommonMethods.logClick(reporter, driver, landigPageObj.getPageObject("PAYWITHCARD", "XPATH"), "WeatherShopperPage");
			
			weatherHelper1.PayfortheCart(reporter, driver);
			
			
			//Success Page
			if(!weatherHelper.VerifyNavigatingPage(reporter, driver, "PAYMENT SUCCESS")) {
				reporter.fail("Payment Failed");
			}
			
			String successmessage = CommonMethods.loggettext(reporter, driver, landigPageObj.getPageObject("SUCCESSMESSAGE", "XPATH"), "Success mesaage");
			reporter.pass("Message Dispalyed on Success page <b>"+successmessage+"</b>");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private void ShoppingForSunscreens(ExtentTest reporter, WebDriver driver) {
		// TODO Auto-generated method stub
		
			HashMap<WebElement,Integer> targetwebElements = new HashMap();
			HashMap<String,Integer> Checkoutmap = new HashMap();
			try {
				Reporter.info("Starting the Validation for SunScreens Shopping");
				CommonMethods.logClick(reporter, driver, landigPageObj.getPageObject("BUYSUNSCREEN", "XPATH"), "WeatherShopperPage");
				
				if(!weatherHelper.VerifyNavigatingPage(reporter, driver, "Sunscreens"))
					Assert.assertEquals(false, true, "Failed While naviagting to the page");
				
				targetwebElements = weatherHelper.getSunscreenTargetProducts(reporter, driver);
				HashMap<String, Integer> targetProduts = new HashMap();
				
				for(Map.Entry<WebElement,Integer> map : targetwebElements.entrySet() ) {
					Thread.sleep(2000);
					targetProduts.put(map.getKey().getAttribute("onclick").split("'")[1],map.getValue());
					map.getKey().click();
					reporter.pass("Added <b>"+ map.getKey().getAttribute("onclick").split("'")[1] +"</b> to the Cart with amount"
							+ " <b>"+ map.getValue()+"</b>");
				}
				
				Weather_Helper weatherHelper1 = new Weather_Helper();
				if(weatherHelper1.VerifyNumberofCartsItems(reporter, driver))
					CommonMethods.logClick(reporter, driver, landigPageObj.getPageObject("CARTS_BUTTON", "XPATH"), "carts button");
				else
					Assert.assertEquals(false, true, "Failed to click on the carts button");
				
				
				//Checkout navigation and validation
				if(!weatherHelper.VerifyNavigatingPage(reporter, driver, "Checkout"))
					Assert.assertEquals(false, true, "Failed While naviagting to the page");
				
				Checkoutmap = weatherHelper1.getcheckoutpageItems(reporter, driver);
				
				if(!weatherHelper1.VerifyItemsinCheckout(reporter, driver, Checkoutmap, targetProduts))
					Assert.assertEquals(false, true, "Failed While comparing products in Checkout");
				
				
				//Payment
				CommonMethods.logClick(reporter, driver, landigPageObj.getPageObject("PAYWITHCARD", "XPATH"), "WeatherShopperPage");
				
				weatherHelper1.PayfortheCart(reporter, driver);
				
				
				//Success Page
				if(!weatherHelper.VerifyNavigatingPage(reporter, driver, "PAYMENT SUCCESS")) {
					reporter.fail("Payment Failed - Sunscreens");
				}
				
				String successmessage = CommonMethods.loggettext(reporter, driver, landigPageObj.getPageObject("SUCCESSMESSAGE", "XPATH"), "Success mesaage");
				reporter.pass("Message Dispalyed on Success page <b>"+successmessage+"</b>");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

	@AfterClass
	public void afterTest() {
		Driver_helper.tearDown(driver);
		// driver = null;
		extent.flush();
	}

}
