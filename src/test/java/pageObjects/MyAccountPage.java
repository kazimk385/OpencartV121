package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {
	
	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath="//h2[text()='My Account']")
	WebElement txt_MyAccount;
	
	@FindBy(xpath="//a[@class='list-group-item'][normalize-space()='Logout']")
	WebElement lnk_logout;
	
	public boolean login_MyAccount() {
		try{
			return (txt_MyAccount.isDisplayed()); 
		}
		catch(Exception e) {
			return false;
		}
	}
	
	public void clickLogout() {
		lnk_logout.click();
	}

}
