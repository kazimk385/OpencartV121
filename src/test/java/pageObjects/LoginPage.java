package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends HomePage {
	
	public LoginPage(WebDriver driver) {
		super(driver);
		
	}
	//Locators:
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_Email;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement txt_Password;
	
	@FindBy(xpath="//input[@value='Login']")
	WebElement btn_login;
	
	//Action methods:
	
	public void login_email(String email) {
		txt_Email.sendKeys(email);
	}

	public void login_password(String pwd) {
		txt_Password.sendKeys(pwd);
	}
	
	public void click_Login() {
		btn_login.click();
	}
	
}
