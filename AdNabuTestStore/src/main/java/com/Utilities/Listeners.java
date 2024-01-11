package com.Utilities;

import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.GenericLibrary.BaseClass;
import com.GenericLibrary.Browser;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class Listeners extends BaseClass implements ITestListener {

	public void onTestStart(ITestResult result) {
		test = extent.createTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.INFO, "Test Success");
	}

	public void onTestFailure(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " - Test Case Failed", ExtentColor.RED));
            test.fail(result.getThrowable());
            Browser.captureScreenshotAndAttachToReport(driver, test, "failed_step");
        }
	}

	public void onTestSkipped(ITestResult result) {
		if (result.getStatus()==ITestResult.SKIP) {
		test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + " SKIPPED ", ExtentColor.ORANGE));
		}
	}

	public void onStart(ITestContext context) {
		test.log(Status.INFO, "Test Started");
	}

	public void onFinish(ITestContext context) {
		test.log(Status.INFO, "Test Finished");
		
	}

}

