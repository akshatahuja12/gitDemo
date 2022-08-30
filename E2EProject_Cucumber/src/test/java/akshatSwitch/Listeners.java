package akshatSwitch;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import resources.L191_base;
import resources.L205_ExtentReporterNG;

public class Listeners extends L191_base implements ITestListener {

	//TO get access to all these methods, RC Listeners.java -> Source -> Override/Implement Methods -> Check all required.
	
	ExtentReports extent = L205_ExtentReporterNG.config(); //Extent variable (to catch extent object) = className.methodName (as static method given)
	ExtentTest test; //test is an object now, it will take care of all subsequent reports. hence declare it globally
	//The initial code works only for sequential execution as test object when created for parallel execution will be overridden by next method it is called at. Hence error might be reported but with a wrong method name.
	//To solve this -> Use threadlocal class, assign test object to it. Java will make test object thread safe and each parallel execution will be handled separately.
	ThreadLocal<ExtentTest> paralleTestObject = new ThreadLocal<ExtentTest>();
	
	
	@Override
	public void onTestStart(ITestResult result) {

		//give the initial extent reports code here so it works for all tests on starting
		

		test = extent.createTest(result.getMethod().getMethodName()); //create report base on method name
		paralleTestObject.set(test); //assign test object to threadlocal object
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		
		paralleTestObject.get().log(Status.PASS, "Test Passed");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		
		
		paralleTestObject.get().fail(result.getThrowable()); //GetThrowable() will get the failure logs from onTestFailure method using result object.
		//Hence on test failure, test object is extracted from paralleTestObject pool and contains correct method associated to it. 
		
		WebDriver driver = null;
		
		
		String testCaseName = result.getMethod().getMethodName(); // Get this method name and pass it to base class so that this method name will be used as the screenshot file name on failure.
		
		try {
			
			//below long step is to get access to the fields of the class where your test got failed.
			driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
			
			//here the drive has no life. so you give above code so that the driver instance of the test which failed can be called and used here. 

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		//hence you get the driver with life from test case and pass it to screenshot method below along with the test case name where it failed.
		try {
			//capture screenshot in extent reports:
			paralleTestObject.get().addScreenCaptureFromPath(getScreenShotPath(testCaseName, driver), result.getMethod().getMethodName());
			//arguments passed -> getScreenshotPath method which returns screenshot's destination path and -> name of the test case failed using result object.
			
			
			//getScreenShotPath(testCaseName, driver); -> this method is now called in extent reports parallel object above
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Screenshot - create method in base class
	}

	@Override
	public void onTestSkipped(ITestResult result) {}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {}

	@Override
	public void onStart(ITestContext context) {}

	@Override
	public void onFinish(ITestContext context) {
		
		 extent.flush(); //executes at end of each tc
	}

}
