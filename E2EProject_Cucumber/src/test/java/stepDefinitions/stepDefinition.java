package stepDefinitions;


import org.junit.Assert;
import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import pageObjects.L193_LandingPage;
import pageObjects.L194_LoginPage;
import pageObjects.L322_portalHomePage;
import resources.L191_base;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.And;


@SuppressWarnings("unused")
@RunWith(Cucumber.class)
public class stepDefinition extends L191_base {


    @Given("^Initialize the browser with chrome$")
    public void initialize_the_browser_with_chrome() throws Throwable {
     
    	driver = initializeDriver();
    	
    }
    
    @Given("^Navigate to \"([^\"]*)\" Site$") //you can either give here @And or @Given
    public void navigate_to_something_site(String strArg1) throws Throwable {
    	driver.get(strArg1); //pass url directl from feature file
    }

    @Given("^Click on login link in homepage to land on secure sign in page$")
    public void click_on_login_link_in_homepage_to_land_on_secure_sign_in_page() throws Throwable {
    	
    	L193_LandingPage l = new L193_LandingPage(driver);
		Thread.sleep(3000);				
		l.getLogin().click();

    }
    
    @When("^User enters (.+) and (.+) and clicks on login button$")
    public void user_enters_and_and_clicks_on_login_button(String username, String password) throws Throwable {
    	L194_LoginPage lp = new L194_LoginPage(driver);
    	Thread.sleep(2000);
       	lp.getEmail().sendKeys(username);
		lp.getpassword().sendKeys(password);
		lp.Submit().click();
    }

    
    @When("^User enters \"([^\"]*)\" and \"([^\"]*)\" and clicks on login button$")
    public void user_enters_something_and_something_and_clicks_on_login_button(String strArg1, String strArg2) throws Throwable {
    	
		
    }

    @Then("^Verify user is succesfully logged in$")
    public void verify_user_is_succesfully_logged_in() throws Throwable {
    
    	L322_portalHomePage p = new L322_portalHomePage(driver);
    	Assert.assertTrue(p.getSearchBox().isDisplayed());
    	
    }
    

    @And("^Close the browser$")
    public void close_the_browser() throws Throwable {
    	
    	driver.close();
    	
    }

  


}