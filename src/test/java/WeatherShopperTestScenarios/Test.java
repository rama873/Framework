package WeatherShopperTestScenarios;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import Common.CommonMethods;
import Common.Driver_helper;

public class Test {
	
	public static WebDriver driver = null;
	private static String URL =null;
	private int Temperature=0;
	static ExtentReports extent;
	ExtentTest Reporter;


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		driver = Driver_helper.Setup();
		URL = CommonMethods.getSuiteConfiguration("URL");
		driver.get("https://www.naukri.com/");
		
		driver.findElement(By.xpath("//div[contains(text(),'Register')]")).click();
		
		Set<String> windows = driver.getWindowHandles();
		for(String s : windows) {
			if(driver.switchTo().window(s).getTitle().contains("Register")) {
				driver.findElement(By.xpath("//input[@placeholder='What is your name?']")).sendKeys("Ramakrishna");
				driver.findElement(By.xpath("//input[@placeholder='Tell us your Email ID']")).sendKeys("Ramakrishnachi22@gamil.com");
			}
		}
		
		
		
	}
}
