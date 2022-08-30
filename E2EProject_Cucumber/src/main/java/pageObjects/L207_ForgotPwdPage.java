package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class L207_ForgotPwdPage {
	
	public WebDriver driver;

	
	public L207_ForgotPwdPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id = "email")
	private WebElement email;
	
	@FindBy(name = "commit")
	private WebElement Nextbtn;
		
	
	public WebElement getEmail() {
		return email;
	}
	

	public WebElement Next() {
		return Nextbtn;
	}
	
}
