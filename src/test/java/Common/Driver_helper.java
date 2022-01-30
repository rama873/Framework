package Common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Driver_helper {
	public static WebDriver driver = null;
	private static String browser =null;
	
	public static WebDriver Setup() {
		browser = CommonMethods.getSuiteConfiguration("BROWSER");
		if(browser.equalsIgnoreCase("CHROME")) {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/resources/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}else  {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/src/resources/geckodriver.exe");
			driver = new FirefoxDriver();
			driver.manage().window().maximize();
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
	}

	public static void tearDown(WebDriver driver) {
		// TODO Auto-generated method stub
		try {
			if (driver != null) {
				driver.quit();
			}
			if (driver == null) {
				System.out.println("Browser has been closed successfully");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
