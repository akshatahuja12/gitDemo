package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class L194_LoginPage {
	
	public WebDriver driver;
	
	
	public L194_LoginPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	
	@FindBy(id = "email")
	private WebElement email;
	
	@FindBy(xpath = "//input[@type='password']")
	private WebElement password;

	
	@FindBy(linkText = "Forgot Password")
	private WebElement forgotPass;
	
	@FindBy(name = "commit")
	private WebElement submit;



	public WebElement getEmail() {
		return email;
	}
	
	
	public WebElement getpassword() {
		return password;
	}
	
	
	public WebElement Submit() {
		return submit;
	}
	
	public L207_ForgotPwdPage ForgotPwd() {
		forgotPass.click();
		L207_ForgotPwdPage fp = new L207_ForgotPwdPage(driver);
		return fp;
		
		//return new L207_ForgotPwdPage(driver); //to make code even less
	}


}
