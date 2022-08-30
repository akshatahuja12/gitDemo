package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class L322_portalHomePage {
	
	public WebDriver driver;
	
	
	public L322_portalHomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	
	@FindBy(xpath = "//input[@id='search-courses']")
	private WebElement searchBox;

	public WebElement getSearchBox() {
		return searchBox;
	}
	



}
