package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {
	
	public AccountRegistrationPage(WebDriver driver) {
		super(driver);

	}
	
	@FindBy(xpath="//input[@name='firstname']")
	WebElement txt_firstName;
	
	@FindBy(xpath="//input[@name='lastname']")
	WebElement txt_lastName;
	
	@FindBy(xpath="//input[@name='email']")
	WebElement txt_email;
	
	@FindBy(xpath="//input[@name='telephone']")
	WebElement txt_telephone;
	
	@FindBy(xpath="//input[@name='password']")
	WebElement txt_password;
	
	@FindBy(xpath="//input[@name='confirm']")
	WebElement txt_conpassword;
	@FindBy(xpath="//input[@name='agree']")
	WebElement checkbox_policy;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement btn_continue;
	
	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement msgConfirmation;
	
	public void setFirstName(String fname) {
		txt_firstName.sendKeys(fname);
	}
	
	public void setLastName(String lname) {
		txt_lastName.sendKeys(lname);
	}
	
	public void setEmail(String email) {
		txt_email.sendKeys(email);
	}
	
	public void setTelephoneNum(String telNum) {
		txt_telephone.sendKeys(telNum);
	}
	
	public void setPassword(String pwd) {
		txt_password.sendKeys(pwd);
	}
	
	public void setConfirmPassword(String pwd) {
		txt_conpassword.sendKeys(pwd);
	}
	
	public void checkPolicy( ) {
		checkbox_policy.click();
	}
	
	public void clickContinue( ) {
		btn_continue.click();
	}

	public String getMSGConfirmation() {
		try {
			return (msgConfirmation.getText());
		}
		catch(Exception e){
			return e.getMessage();
		}
	}
}
