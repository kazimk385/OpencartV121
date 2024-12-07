package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseClass {
	
	public static WebDriver driver;
	public Logger logger;
	public Properties p;
	
	@SuppressWarnings("deprecation")
	@BeforeClass(groups= {"sanity", "regression","master"})
	@Parameters({"os","browser"})
	public void setup(String os, String br) throws IOException {
		
		FileReader file= new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		
		logger = LogManager.getLogger(getClass());
		if(p.getProperty("execution_env").equalsIgnoreCase("remote")) {
			
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		if(os.equalsIgnoreCase("windows")) {
			capabilities.setPlatform(Platform.WIN11);
		}
		else if(os.equalsIgnoreCase("mac")) {
			capabilities.setPlatform(Platform.MAC);
		}
		else {
			System.out.println("No matching os");
			return;
		}
			
		//browser also need to get from xml	as os we take above
		switch(br.toLowerCase()) {
		case "chrome": capabilities.setBrowserName("chrome");break;
		case "edge": capabilities.setBrowserName("MicrosoftEdge");break;
		default:System.out.println("No Matching browser found");return;
		}
		driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);	
		}
		
		
		if(p.getProperty("execution_env").equalsIgnoreCase("local")) {
		switch(br.toLowerCase()) {
		case "chrome": driver = new ChromeDriver();break;
		case "edge": driver = new EdgeDriver();break;
		default: System.out.println("Invalid browser value");return;
		}
		}
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(p.getProperty("appURL")); // reading URL from properties file
		driver.manage().window().maximize();
		 
	}
	@AfterClass(groups= {"sanity", "regression","master"})
	public void tearDown() {
		driver.quit();
	}
		
	public String genRandomString() {
		String randomString = RandomStringUtils.randomAlphanumeric(6);
		return randomString;
	}
	
	public String genRandomNum() {
		String randomNum = RandomStringUtils.randomNumeric(10);
		return randomNum;
	}
	
	public String genRandomAlphaNum() {
		String randomAphaNum = RandomStringUtils.randomAlphanumeric(8);
		return randomAphaNum;
	}
	
	public String captureScreenshot(String tname) {
		
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		
		TakesScreenshot takescreenshot = (TakesScreenshot)driver;
		File sourcefile = takescreenshot.getScreenshotAs(OutputType.FILE);
		
		String targetfilepath = System.getProperty("user.dir"+"\\screenshots\\"+tname+"_"+timeStamp);
		File targetfile = new File(targetfilepath);
		
		sourcefile.renameTo(targetfile);
	
		return targetfilepath;	
	}

}