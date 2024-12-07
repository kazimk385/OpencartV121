package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 * Data is valid -> login success -> test pass  -> Hence need to logout
 * Data is valid -> login failed  -> test fails -> Hence no need to logout
 * 
 * Data is invalid -> login success -> test fails -> Hence no need to logout
 * Data is invalid -> login failed  -> test pass  -> Hence need to logout
 */
public class TC03_DataDrivenTest extends BaseClass {
	
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class) // getting dataprovider from generic dataprovider class
	public void loginDDT(String email, String pwd, String dataType) {
	try {
		logger.info("***   Test Execution is started   ***");
		
		HomePage hp = new HomePage(driver);
		
		logger.info("***   Clicked on MyAccount   ***");
		hp.clickMyAccount();
		
		logger.info("***   Clicked on Login   ***");
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		
		logger.info("***   Providing Login Details   ***");
		
		lp.login_email(email);
		lp.login_password(pwd);
		lp.click_Login();
		logger.info("***   Verifying login is successfull or not   ***");
		
		MyAccountPage ap = new MyAccountPage(driver);
		
		boolean account_display_msg=ap.login_MyAccount();
		
		if(dataType.equalsIgnoreCase("valid")) {
			if(account_display_msg==true) {
				ap.clickLogout();
				Assert.assertTrue(true);
			}
			else {
				Assert.assertTrue(false);
			}
		}
		if(dataType.equalsIgnoreCase("invalid")) {
			if(account_display_msg==true) {
				ap.clickLogout();
				Assert.assertTrue(false);
			}
			else {
				Assert.assertTrue(true);
			}
		}
	}
	catch(Exception e) {
		Assert.fail();
	}
	
	logger.info("***   Test Execution is finished   ***");
}
}
