package resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class L205_ExtentReporterNG {
	
	static ExtentReports extent; //since declared method is static, all variables inside it should be static too
	
	public static ExtentReports config() { //if you declare this method as static, you can directly call its object "extent" anywhere using classname.methodname
		//if you don't declare this method as static, then ou will have to declare an object of this class and then call using Object.method
		
		String path = System.getProperty("user.dir")+"\\Reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Web Automation Results");
		reporter.config().setDocumentTitle("Test Resuts");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Akshat");
		return extent; // return the extent report object created globally so that all tcs can access it.
		
		//reports will be created base on method names, just like screenshots
	}
}
