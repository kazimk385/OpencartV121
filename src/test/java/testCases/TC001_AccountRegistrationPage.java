package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationPage extends BaseClass {
		
	@Test(groups={"sanity","master"})
	public void verify_accountRegPage() {
		try {
		logger.info("****  TC001_AccountRegistrationPage   ***");
		
		HomePage hp = new HomePage(driver);
		
		logger.info("****  Clicked on MyAccount   ***");
		hp.clickMyAccount();
		
		logger.info("****  Clicked on Register   ***");
		hp.clickRegister();
		
		logger.info("****  Providing Customer Details   ***");
		
		AccountRegistrationPage rp = new AccountRegistrationPage(driver);
		rp.setFirstName(genRandomString().toUpperCase());
		rp.setLastName(genRandomString().toUpperCase());
		rp.setEmail(genRandomString()+"@gmail.com");
		rp.setTelephoneNum(genRandomNum());
		String password = genRandomAlphaNum();
		rp.setPassword(password);
		rp.setConfirmPassword(password);
		rp.checkPolicy();
		rp.clickContinue();
		
		logger.info("****  Verifying Success Message   ***");
		String confirmMSG = rp.getMSGConfirmation();
		if(confirmMSG.equals("Your Account Has Been Created!")){
			Assert.assertTrue(true);
		}
		else {
			logger.error("****  Test Failed   ***");
			logger.debug("****  Debug Logs   ***");
			Assert.assertTrue(false);
		}
		//Assert.assertEquals(confirmMSG, "Your Account Has Been Created!!!");
		}
		catch(Exception e){
			Assert.fail();
		}
	}
	
}
