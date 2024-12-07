package utilities;

import java.awt.Desktop;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter sparkreporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	String repName;
	public void onStart(ITestContext context) {
		// to way to write date format string
		 /*1)
		 SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		 Date dt = new Date();
		 String datetimestamp = df.format(dt);*/
		 
	//2)
	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
	
	repName = "Report-Name"+ timeStamp+ ".html";
	
	sparkreporter = new ExtentSparkReporter(".\\reports\\"+ repName);
		
	sparkreporter.config().setDocumentTitle("OpenCart Automation Report");
	sparkreporter.config().setReportName("OpenCart Functional Testing");
	sparkreporter.config().setTheme(Theme.STANDARD); 
	
	extent = new ExtentReports();
	extent.attachReporter(sparkreporter);
	extent.setSystemInfo("Application", "OpenCart");
	extent.setSystemInfo("Module", "Admin");
	extent.setSystemInfo("Sub Module", "Customers");
	extent.setSystemInfo("User Name", System.getProperty("user.name"));
	extent.setSystemInfo("Environment", "SIT");
	
	String os = context.getCurrentXmlTest().getName();
	extent.setSystemInfo("Operating System", os);
	
	String browser = context.getCurrentXmlTest().getName();
	extent.setSystemInfo("browser", browser);
	
	List<String> inculdedgroups = context.getCurrentXmlTest().getIncludedGroups();
	if(!inculdedgroups.isEmpty()) {
	extent.setSystemInfo("Groups", inculdedgroups.toString());
		}
	  }
	
	  public void onTestSuccess(ITestResult result) {
		  
		test = extent.createTest(result.getClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName()+"Test case is passed");
		 
	  }
	  //Invoked each time a test fails.
	  public void onTestFailure(ITestResult result) {
		  test = extent.createTest(result.getClass().getName());
		  test.assignCategory(result.getMethod().getGroups());
		  test.log(Status.FAIL, result.getName()+"test failed");
		  test.log(Status.INFO, result.getThrowable().getMessage());
		  
		  try {
			  String imgpath = new BaseClass().captureScreenshot(result.getName());
			  test.addScreenCaptureFromPath(imgpath);
		  }
		  catch(Exception e) {
			  e.printStackTrace();
		  }
	  }

	  public void onTestSkipped(ITestResult result) {
	 test = extent.createTest(result.getClass().getName());
	 test.assignCategory(result.getMethod().getGroups());
	 test.log(Status.SKIP, result.getName()+"test skipped");
	 test.log(Status.INFO, result.getThrowable().getMessage());
	  }

	  public void onFinish(ITestContext context) {
	   extent.flush();
	   
	   // to open extent report automatically
	   String pathOfExtentReport = System.getProperty("user.dir"+"\\reports"+repName);
	  File extentReport = new File(pathOfExtentReport);
	  try {
		  Desktop.getDesktop().browse(extentReport.toURI());  
	  }
	  catch(Exception e) {
		  e.printStackTrace();
	  }
	  }

}

