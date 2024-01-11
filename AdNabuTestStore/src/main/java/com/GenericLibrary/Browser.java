package com.GenericLibrary;

import java.net.InetAddress;
import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Browser {

	public static final String url = "https://adnabu-arjun.myshopify.com";
	public static WebDriver driver;
	public ExtentHtmlReporter htmlReporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	public static Logger logger = Logger.getLogger("LoggerCreation");
	
	@BeforeSuite
	public void setLog() throws Exception {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/ExtentReport/testReport.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		PropertyConfigurator.configure(System.getProperty("user.dir") + "/Log/log4j.properties");
		
		// To add system or environment info by using the setSystemInfo method.
				extent.setSystemInfo("System IP Address", InetAddress.getLocalHost().getHostAddress());
				extent.setSystemInfo("System Host Name", InetAddress.getLocalHost().getHostName());
				extent.setSystemInfo("OS", "Windows 10");
				
				// configuration items to change the look and feel
				htmlReporter.config().setDocumentTitle("adnabu application Automation Test Result");
				htmlReporter.config().setReportName("adnabu application Automation Test Result");
				htmlReporter.config().setTheme(Theme.DARK);
				htmlReporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	}
	
	@Parameters("Browser")
	@BeforeTest
	public void setBrowser(String browser) {
		if (browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			driver = new ChromeDriver(options);
		} else if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		logger.info("Browser opened");
		driver.manage().window().maximize();
		driver.navigate().refresh();
		driver.navigate().to(url);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		logger.info("Navigated to application");
	}
	
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
		logger.info("Browser closed");
	}
	
	@AfterSuite
	public void flushExtentReport() {
		extent.flush();
	}
	
	public static void captureScreenshotAndAttachToReport(WebDriver driver, ExtentTest test, String screenshotName) {
        try {
            // Take screenshot and convert it to base64 for Extent Report
            TakesScreenshot ts = (TakesScreenshot) driver;
            String Screenshot = ts.getScreenshotAs(OutputType.BASE64);

            // Embed the screenshot into the report
            test.log(Status.FAIL, MarkupHelper.createLabel("Test Case Failed. See the screenshot below:", com.aventstack.extentreports.markuputils.ExtentColor.RED));
            test.fail("Screenshot: " + test.addScreenCaptureFromBase64String(Screenshot));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
