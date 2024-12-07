package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;

public class TC02_LoginTest extends BaseClass {
	
	@Test(groups={"sanity","regression","majid"})
	public void test_login() {
		try {
		logger.info("***   Test Execution is started   ***");
		
		HomePage hp = new HomePage(driver);
		
		logger.info("***   Clicked on MyAccount   ***");
		hp.clickMyAccount();
		
		logger.info("***   Clicked on Login   ***");
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		
		logger.info("***   Providing Login Details   ***");
		
		lp.login_email(p.getProperty("email"));
		lp.login_password(p.getProperty("pwd"));
		lp.click_Login();
		logger.info("***   Verifying login is successfull or not   ***");
		
		MyAccountPage ap = new MyAccountPage(driver);
		
		boolean account_display_msg=ap.login_MyAccount();
		Assert.assertEquals(account_display_msg, true, "Login failed");
		}
		catch(Exception e) {
			Assert.fail();
		}
		
		logger.info("***   Test Execution is finished   ***");
	}
	
}
