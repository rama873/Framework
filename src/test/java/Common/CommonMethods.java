package Common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

public class CommonMethods extends Driver_helper {
	String sFile = null;
	public static String outputDir = "";
	public static String sExtentReport = "";
	
	public  boolean logSendKeys(ExtentTest Report, WebDriver driver, By by, String sText, String sName) {
		Boolean sReturn = null;
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
			driver.findElement(by).click();
			
			Report.pass("Text Entered<br/>" + sName + " :" + sText + " is typed");
			Thread.sleep(1500);
			driver.findElement(by).clear();
			driver.findElement(by).sendKeys(sText);
			Thread.sleep(1000);
			System.out.println("Text Entered: " + sText);
			sReturn = true;
		} catch (Exception e) {
			e.printStackTrace();
			sReturn = false;
		}
		return sReturn;
	}
	
	public boolean logClick(ExtentTest Report, WebDriver driver, By by, String sName) {
		
		Boolean sReturn = null;
		try {
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(by));
			jse.executeScript("arguments[0].click();", driver.findElement(by));
			Thread.sleep(2000);
			Report.pass("Click:" + sName + "<br/>" + sName + " is clicked");
			System.out.println("Clicked:" + sName);

			sReturn = true;
		} catch (Exception e) {
			Report.fail("Click:" + sName + "<br/>" + sName + " is not clicked ");
			e.printStackTrace();
			sReturn = false;
		}
		return sReturn;
	}
	
	public  String loggettext(ExtentTest Report, WebDriver driver, By by, String sName) {
		String sTmp = "";
		try {
			sTmp = driver.findElement(by).getText();
			System.out.println("Retrieved text from " + sName + ". Value:" + sTmp);
			Report.pass("Retrieved text from " + sName + ". Value:" + sTmp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sTmp;
	}
	
	public static String getSuiteConfiguration(String sKey) {
		String sValue = null;
		File file = null;
		FileInputStream fis = null;
		String sFilePath = System.getProperty("user.dir") + "/Configuration.properties";
		// Create Properties Object
		Properties prop = new Properties();
		try {
			file = new File(sFilePath);
			fis = new FileInputStream(file);
			prop.load(fis);
			// get the Value
			sValue = prop.getProperty(sKey.toUpperCase().trim());
		} catch (Exception e) {
			System.out.println("Not Bale to retrieve the value for Key:::" + sKey + " due to " + e.getMessage());
		}
		return sValue;
	}
	
	public List<WebElement> logGetElemntsList(ExtentTest Report, WebDriver driver, By by, String sName) {
		List<WebElement> sValues = new ArrayList<WebElement>();
		try {
			sValues = driver.findElements(by);

			System.out.println("Retrieved text from " + sName + ". Value:" + sValues);
//			Report.pass("Retrieved text from " + sName + ". Value:" +
//			sValues);
		} catch (Exception e) {
			Report.fail("Failed to retrieve text from " + sName + ". " + sFile);
			e.printStackTrace();
		}
		return sValues;
	}
	
	public String getPayload(String FILE_PATH) {
		String sUpdatedPayload = null;
		try {
			JSONParser parser = new JSONParser();
			FileReader fileread = new FileReader(FILE_PATH);
			// Get Object for the JSON file
			Object obj = parser.parse(fileread);
			// get JSON Object
			JSONObject JsonData = (JSONObject) obj;
			System.out.println(JsonData.toString());
			sUpdatedPayload = JsonData.toString();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return sUpdatedPayload;
	}

	

}
