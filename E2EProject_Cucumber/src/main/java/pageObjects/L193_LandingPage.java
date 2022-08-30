package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class L193_LandingPage {

	public WebDriver driver;

	public L193_LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "a[href*='sign_in']")
	private WebElement signIn; // Give variable/locators and methods as public as private so they can only be
								// accessed by the method of class and not directly.
	// private -> can't be access by any class that extends it. (inheritance) i.e.
	// Parent's private vars/methods are not accessible to child

	public WebElement getLogin() {
		return signIn; // create login page handling here only.
	}

	@FindBy(xpath = "//div[@class='pull-left']/h2")
	private WebElement title;

	public WebElement getTitle() {
		return title;
	}

	@FindBy(css = "div[class='nav-outer clearfix'] ul[class='navigation clearfix']")
	private WebElement navbar;

	public WebElement getNavbar() {
		return navbar;
	}

	@FindBy(xpath = "//div[@class='col-md-6 text-left']/h2")
	private WebElement header;

	public WebElement getHeader() {
		return header;

	}

}
