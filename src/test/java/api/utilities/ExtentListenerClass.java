package api.utilities;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentListenerClass implements ITestListener{

	ExtentSparkReporter extentSpark;
	ExtentReports reports;
	ExtentTest extTest;
	
	public void configureReport() {
		
		String timeStamp = new SimpleDateFormat("yyyy.mm.dd.hh.mm.ss").format(new Date());
	
		String reportName = "PetStoreAutomationTestReport-"+timeStamp+".html";
	
		
		reports = new ExtentReports();
		extentSpark = new ExtentSparkReporter("Reports/"+ reportName );
		reports.attachReporter(extentSpark);
		
		// add system information/ environment info to report
		reports.setSystemInfo("Machin", "TestPc1");
		reports.setSystemInfo("OS", "Window 11");
		reports.setSystemInfo("user name", " Arjun ");
		
		// configuration to change look and feel of report
		extentSpark.config().setDocumentTitle("Extent Listener Report Demo");
		extentSpark.config().setReportName("This is Sample RestAssured Automation Report");
		extentSpark.config().setTheme(Theme.DARK);
	}
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestStart(result);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println("Name of Successfully executed test method : " + result.getName());
		extTest = reports.createTest(result.getName());
		extTest.log(Status.PASS, MarkupHelper.createLabel("Name of the pass test case is : "+ result.getName(), ExtentColor.GREEN));
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println("Name of failed test method : " + result.getName());
		extTest = reports.createTest(result.getName());
		extTest.log(Status.FAIL, MarkupHelper.createLabel("Name of the failed test case is : "+ result.getName(), ExtentColor.RED));
		String screenShotPath = System.getProperty("user.dir") + "\\ScreenShots\\" + result.getName() + ".png";
		
		File screenShotFile = new File(screenShotPath);
		
		if(screenShotFile.exists())
		{
			extTest.fail("Captured Screenshot is below:" + extTest.addScreenCaptureFromPath(screenShotPath));
			
		}
		
		//	test.addScreenCaptureFromPath(null)
				
	
	}

	@Override
	public void onTestSkipped(ITestResult result) {
	
		System.out.println("Name of Skipped test method : "+ result.getName());
		extTest = reports.createTest(result.getName());
		extTest.log(Status.SKIP, MarkupHelper.createLabel("Name of the skipped test case is : "+ result.getName(), ExtentColor.YELLOW));
	}

	@Override
	public void onStart(ITestContext context) {
		configureReport();
		System.out.println(" On Start Method is Invoked ....... ");
	}

	@Override
	public void onFinish(ITestContext context) {
		
		System.out.println(" On Finish Method is Invoked ....... ");
		reports.flush();
		
	}
	
}
