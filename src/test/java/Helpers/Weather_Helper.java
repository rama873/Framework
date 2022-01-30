package Helpers;

import java.io.FileReader;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentTest;

import Common.CommonMethods;
import Common.UIObjectRetriever;

public class Weather_Helper {
	public WebDriver driver = null;
	CommonMethods CommonMethods = new CommonMethods();
	UIObjectRetriever landigPageObj = new UIObjectRetriever("weathershopper_home");
	
	public boolean VerifyNavigatingPage(ExtentTest reporter, WebDriver driver, String Expectedpagename) {
		boolean status =true;
		String Getpageheader = CommonMethods.loggettext(reporter, driver, landigPageObj.getPageObject("PAGE_HEADER", "XPATH"), Expectedpagename);
		if(Getpageheader.equalsIgnoreCase(Expectedpagename)) {
			status =true;
			reporter.pass("Successfully Navigated to <b>"+Expectedpagename+ "</b> Page with CurrentURL: "+ driver.getCurrentUrl());
		}else { 
			status =false;
			reporter.fail("Naviagation failed to "+Expectedpagename+ "Page");
		}
		return status;
	}

	public  int GetCurrentTemperature(ExtentTest reporter, WebDriver driver) {
		int tempature=0;
		// TODO Auto-generated method stub
		try {
			reporter.info("Checking the Tempature on the Site");
			String temp= CommonMethods.loggettext(reporter, driver, landigPageObj.getPageObject("CURRENTTEMPERATURE", "XPATH")
					, "WeatherShopperPage");
			tempature = Integer.parseInt( temp.split(" ")[0]);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return tempature;
	}

	public HashMap<WebElement, Integer> getMoisturizerTargetProducts(ExtentTest reporter,  WebDriver driver) {
		// TODO Auto-generated method stub
		HashMap<WebElement,Integer> finalproducts = new HashMap();
		try {
			List<WebElement> sValues = CommonMethods.logGetElemntsList(reporter, driver, landigPageObj.getPageObject("ALL_PRODUCTS", "XPATH"),
					"Weather");
			HashMap<WebElement,Integer> Aloe = new HashMap();
			HashMap<WebElement,Integer> almond = new HashMap();
			Iterator<WebElement> i = sValues.iterator();
			while(i.hasNext()) {
				WebElement row = i.next();
				int price = Integer.parseInt(row.getAttribute("onclick").split(",",2)[1].split("\\)")[0]);
				if(row.getAttribute("onclick").contains("Aloe")) 
					Aloe.put(row, price);
				else 
					almond.put(row, price);
				}
			
			int aloemin = Collections.min(Aloe.values());
			int almondmin = Collections.min(Aloe.values());
			
			for (Map.Entry<WebElement,Integer> entry : Aloe.entrySet()) {
				if(entry.getValue() == Collections.min(Aloe.values()))
					finalproducts.put(entry.getKey(), entry.getValue());
			}
			
			for (Map.Entry<WebElement,Integer> entry : almond.entrySet()) {
				if(entry.getValue() == Collections.min(almond.values()))
					finalproducts.put(entry.getKey(), entry.getValue());
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return finalproducts;
	}
	
	public boolean VerifyNumberofCartsItems(ExtentTest reporter,  WebDriver driver) {
		boolean status = true;
		
		if(CommonMethods.loggettext(reporter, driver, landigPageObj.getPageObject("CARTS_ITEMS", "XPATH"), "CartsItems").contains("2")) {
			reporter.pass("Successfully added two Items to the Cart");
			return true;
		}
		else
			reporter.fail("failed to added two Items to the Cart");
			return false;
		
		
	}

	public HashMap<String, Integer> getcheckoutpageItems(ExtentTest reporter, WebDriver driver) {
		// TODO Auto-generated method stub
		HashMap<String,Integer> finalproducts = new HashMap();
		try {
			List<WebElement> sValues = CommonMethods.logGetElemntsList(reporter, driver, landigPageObj.getPageObject("CHEOUT_CARTITEMS", "XPATH"),
					"Weather");
			
			for(int i=0;i<sValues.size();i++) {
				finalproducts.put(sValues.get(i).getText(), Integer.parseInt(sValues.get(++i).getText()));
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return finalproducts;
	}

	public boolean VerifyItemsinCheckout(ExtentTest reporter, WebDriver driver,
		 HashMap<String, Integer> ActualProducts, HashMap<String, Integer> targetProduts) {
		try {
			System.out.println("test");
			if(ActualProducts.equals(targetProduts)) {
				reporter.pass("items are matching with CART details");
				return true;
			}
			else {
				reporter.fail("items are NOT matching with CART details");
				return false;}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public void PayfortheCart(ExtentTest reporter, WebDriver driver) {
		try {
			
			
			String payload = CommonMethods.getPayload(System.getProperty("user.dir") + "/src/test/resources/TestData.json");
			JSONObject jsonOBj = new JSONObject(payload);
			JSONObject Credit_data = jsonOBj.getJSONObject("CreditCardDetails");
			
			driver.switchTo().frame(driver.findElement(landigPageObj.getPageObject("STRIPE_FRAMECHANGE", "XPATH")));
			
			CommonMethods.logSendKeys(reporter, driver, landigPageObj.getPageObject("STRIPE_EMAIL", "XPATH"), 
					Credit_data.getString("Email"), "Email");
			
//			CommonMethods.logSendKeys(reporter, driver, landigPageObj.getPageObject("STRIPE_CC", "XPATH"), 
//					Credit_data.getString("CardNumber"), "CardNumber");
			
			String[] cardnumber = Credit_data.getString("CardNumber").split("");
			for(int i=0;i<cardnumber.length;i++) {
				driver.findElement(landigPageObj.getPageObject("STRIPE_CC", "XPATH")).sendKeys(cardnumber[i]);
			}
			CommonMethods.logSendKeys(reporter, driver, landigPageObj.getPageObject("STRIPE_MMYY", "XPATH"), 
					Credit_data.getString("MM"), "MM");
			driver.findElement(landigPageObj.getPageObject("STRIPE_MMYY", "XPATH")).sendKeys(Credit_data.getString("YY"));
			CommonMethods.logSendKeys(reporter, driver, landigPageObj.getPageObject("STRIPE_CVC", "XPATH"), 
					Credit_data.getString("CVC"), "CVC");
			CommonMethods.logSendKeys(reporter, driver, landigPageObj.getPageObject("STRIPE_ZIP", "XPATH"), 
					Credit_data.getString("ZipCode"), "ZipCode");
			
			CommonMethods.logClick(reporter, driver, landigPageObj.getPageObject("PAY_BUTTON", "XPATH"), "Pay button");
			Thread.sleep(5000);
			driver.switchTo().defaultContent();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public HashMap<WebElement, Integer> getSunscreenTargetProducts(ExtentTest reporter, WebDriver driver) {
		// TODO Auto-generated method stub
		HashMap<WebElement,Integer> finalproducts = new HashMap();
		try {
			List<WebElement> sValues = CommonMethods.logGetElemntsList(reporter, driver, landigPageObj.getPageObject("ALL_PRODUCTS", "XPATH"),
					"Weather");
			HashMap<WebElement,Integer> SPF_30 = new HashMap();
			HashMap<WebElement,Integer> SPF_50 = new HashMap();
			Iterator<WebElement> i = sValues.iterator();
			while(i.hasNext()) {
				WebElement row = i.next();
				int price = Integer.parseInt(row.getAttribute("onclick").split(",",2)[1].split("\\)")[0]);
				if(row.getAttribute("onclick").contains("30")) 
					SPF_30.put(row, price);
				else 
					SPF_50.put(row, price);
				}
			
			for (Map.Entry<WebElement,Integer> entry : SPF_30.entrySet()) {
				if(entry.getValue() == Collections.min(SPF_30.values()))
					finalproducts.put(entry.getKey(), entry.getValue());
			}
			
			for (Map.Entry<WebElement,Integer> entry : SPF_50.entrySet()) {
				if(entry.getValue() == Collections.min(SPF_50.values()))
					finalproducts.put(entry.getKey(), entry.getValue());
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return finalproducts;
	}
}
