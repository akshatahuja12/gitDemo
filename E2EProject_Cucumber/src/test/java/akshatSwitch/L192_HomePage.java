package akshatSwitch;

import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.L193_LandingPage;
import pageObjects.L194_LoginPage;
import pageObjects.L207_ForgotPwdPage;
import resources.L191_base;

@SuppressWarnings("unused")
public class L192_HomePage extends L191_base {
//Inheritance used, all methods of base class will now be available for this class.
	
	public static Logger log = LogManager.getLogger(L191_base.class.getName());
	public WebDriver driver; // To run all the cases in parallel, create a local instance of WebDriver to which the instance from L191_base can be assigned to using *  
	//if you dive actual driver as static, still there wont be an issue as you are creating local copy in your test cases.-> see notes.
	
	
	
	@BeforeTest
	public void initialize() throws IOException {
		
		driver = initializeDriver(); // gets driver from initializeDriver() of L191_base and assigns it to local instance created above in *
		log.info("Driver is initialized.");
		//driver.get(prop.getProperty("url")); 
	}
	
	
	@Test(dataProvider = "getData")
	public void homePageLogin(String Username, String Password, String text) throws IOException, InterruptedException {
		
		
		driver.get(prop.getProperty("url")); //We are not initializing url in BeforeTest because dataProvider runs twice and BeforeTest runs only once, so for second set of data browser wont be invoked. 
		log.info("Navigated to Home page.");

		
		//2 ways of accessing methods of other classes:
		//1. Inheritance 2. Creating objects of that class and invoking methods of it. Using second one below:
		
		L193_LandingPage l = new L193_LandingPage(driver);//if you don't pass driver you get null pointer exception as driver has no life in LandingPage Object repo class
		Thread.sleep(3000);
		
		//WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(2));
		//w.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("//div[class='preloader']")));
		//w.until(ExpectedConditions.visibilityOf(l.getLogin()));
				
		l.getLogin().click();; //capture lp object sent from L193_LandingPage. Even tho there are 2 pages, we are initiaitng only once now
		log.info("Navigated to Login page.");

		//L194_LoginPage lp = new L194_LoginPage(driver); due to lp being initiated and called from L193_LandingPage, this is no longer needed.
		
		L194_LoginPage lp = new L194_LoginPage(driver);
		lp.getEmail().sendKeys(Username);
		lp.getpassword().sendKeys(Password);
		log.info("User is a " +text);
		
	
		
		lp.Submit().click();
		log.info("Submit button clicked");

		//navigate to forgot password page without creating its object here
		L207_ForgotPwdPage fp = lp.ForgotPwd();
		
		
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(2));
		w.until(ExpectedConditions.urlToBe("https://sso.teachable.com/secure/9521/identity/forgot_password"));
		fp.getEmail().sendKeys(Username);
		fp.Next().click();
	

	}
	
	@AfterTest 
	public void teardown() {
		driver.close();
	}
	
	@DataProvider
	public Object[][] getData() {
		
		//Row stands for different types of scenarios of data that needs to be run
		//Eg - Normal Account, Restricted Account etc.
		//Column stands for how many var/ values are being sent per scenario.
		
		Object[][] data = new Object[2][3]; //2 rows, 3 columns 
		//For Array of size 5 -> it starts from 0 -> 0,1,2,3,4.
		
		data[0][0] = "nonrestruser@email.com";
		data[0][1] = "12345";
		data[0][2] = "Non-Restricted User";
		
		data[1][0] = "restruser@email.com";
		data[1][1] = "12345";
		data[1][2] = "Restricted User";
		return data;
	}
	
	
}

