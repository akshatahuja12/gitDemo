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
public class L198_validateNavBar extends L191_base {
//Inheritance used, all methods of base class will now be available for this class.
	
	public static Logger log = LogManager.getLogger(L191_base.class.getName());
	public WebDriver driver;
	
	@BeforeTest
	public void initialize() throws IOException {
		
		driver = initializeDriver();
		log.info("Driver is initialized.");
		
		driver.get(prop.getProperty("url")); //Since this class extends base class all the properties can be accessed here too.
		//don't give " " to url in .prop file
		log.info("Navigated to Home page.");

	}
	
	
	@Test
	public void navBarValidation() throws IOException, InterruptedException {
		
		L193_LandingPage l = new L193_LandingPage(driver);
		Thread.sleep(2000);
		//To compare the text from browser with actual text:
		
		AssertJUnit.assertTrue(l.getNavbar().isDisplayed());
		log.info("Navbar Verified.");

		
	}
	
	@AfterTest 
	public void teardown() {
		driver.close();
	}
	
}

