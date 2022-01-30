package Common;

import java.io.File;

import org.testng.Reporter;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReporterNG {
	static ExtentReports extent;
	public static ExtentReports extentReports;
	public ExtentTest extentTest;
	
	@BeforeSuite
	public static ExtentReports configExtentReport() {
		
		String path =System.getProperty("user.dir")+"\\Reports\\ExecutionReport.html";

		//ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		ExtentHtmlReporter reporter = new ExtentHtmlReporter(path);
		
		reporter.config().setReportName("weathershopper Results");

		reporter.config().setDocumentTitle("Test Results");

		extent =new ExtentReports();

		extent.attachReporter(reporter);
		
        return extent;
	}
	
}
