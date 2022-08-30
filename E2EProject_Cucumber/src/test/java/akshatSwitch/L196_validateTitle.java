package akshatSwitch;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pageObjects.L193_LandingPage;
import pageObjects.L194_LoginPage;
import resources.L191_base;

@SuppressWarnings("unused")
public class L196_validateTitle extends L191_base {
//Inheritance used, all methods of base class will now be available for this class.
	
	public static Logger log = LogManager.getLogger(L191_base.class.getName());
	public WebDriver driver;
	L193_LandingPage l; //declare globally so that it can be used by all test cases.
	
	@BeforeTest
	public void initialize() throws IOException {
		
		driver = initializeDriver();
		log.info("Driver is initialized.");

		driver.get(prop.getProperty("url")); 
		log.info("Navigated to Home page.");

	}
	
	
	@Test
	public void titleValidation() throws IOException, InterruptedException {
		
		l = new L193_LandingPage(driver);
		Thread.sleep(2000);
		//To compare the text from browser with actual text:
		AssertJUnit.assertEquals(l.getTitle().getText(), "Featured Courses123");
		log.info("Succesfully validated text message.");
		
	}
	
	@Test
	public void headerValidation() throws IOException, InterruptedException { //TC on same page/module should be grouped in same class -> POM for test cases
		
		l = new L193_LandingPage(driver); //declared globally but giving life here.
		Thread.sleep(2000);
		//To compare the text from browser with actual text:
		AssertJUnit.assertEquals(l.getHeader().getText(), "An Academy to Learn Earn & Shine  in your QA Career");
		log.info("Succesfully validated text message.");
		
	}
	
	@AfterTest 
	public void teardown() {
		driver.close();
	}
		
}

