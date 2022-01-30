package Common;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;

public class UIObjectRetriever {
	
	private String sPage;
	
	public UIObjectRetriever(String sPageName) {
		this.sPage = sPageName;
	}
	public By getPageObject(String sObjectName, String sLocator) {
		By by = null;
		try {
			// Add Conditions
			if (sLocator.toUpperCase().equals("CSS")) {
				by = By.cssSelector(this.getObjectValue(sObjectName));
			} else if (sLocator.toUpperCase().equals("XPATH")) {
				by = By.xpath(this.getObjectValue(sObjectName));
			} else if (sLocator.toUpperCase().equals("ID")) {
				by = By.id(this.getObjectValue(sObjectName));
			} else if (sLocator.toUpperCase().equals("CLASSNAME")) {
				by = By.className(this.getObjectValue(sObjectName));
			} else {
				by = By.name(this.getObjectValue(sObjectName));
			}
		} catch (Exception e) {
			System.out.println("No Data Available for Object Called***" + sObjectName + " With locator****" + sLocator);
		}
		return by;
	}
	
	public String getObjectValue(String sObjectName) {
		String sValue = null;
		File file = null;
		FileInputStream fis = null;
		try {
			// get the Project Name
			file = new File(System.getProperty("user.dir") + "/src/test/java/PageProperties/" 
					+  this.sPage + ".properties");
			fis = new FileInputStream(file);
			Properties prop = new Properties();
			// load the file
			prop.load(fis);
			sValue = prop.getProperty(sObjectName.toUpperCase());
			System.out.println("Value retrieved+++" + sValue);
		} catch (Exception e) {
			System.out.println("No Object available in the Given property file*****" + this.sPage);
		}
		return sValue;
	}

}
