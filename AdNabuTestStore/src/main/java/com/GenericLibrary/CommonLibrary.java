package com.GenericLibrary;

import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.aventstack.extentreports.Status;

public class CommonLibrary extends Browser {

	public static WebDriverWait wait;
	public static String value;

	// Wait Statement to wait still Element to be loaded
		public static void waitForElementToBePresent(WebElement element) {
			try {
				wait = new WebDriverWait(driver, Duration.ofSeconds(100));
				wait.until(ExpectedConditions.visibilityOf(element));
				
			} catch (Exception e) {
				logger.error("Unable to wait for Element To BePresent " + e.getMessage());
				Assert.assertTrue(false, "Unable to wait for Element To BePresent " + e.getMessage());
				test.log(Status.FAIL, "Unable to wait for Element To BePresent");
			}

		}

		// For Entering Value
		public static String entervalue(String value, WebElement element) {
			try {
				waitForElementToBePresent(element);
				moveToElement(element);
				
				//test.log(Status.INFO, "waiting to enter value");
				element.sendKeys(value);
				logger.info("Entered value is " + value);
				//test.log(Status.PASS, "Entered value is " + value);
			} catch (Exception e) {
				Assert.assertTrue(false, "Unable to enter Value "+ e.getMessage());
				logger.error("Unable to enter Value " + e.getMessage());
				test.log(Status.FAIL, "Unable to enter Value " + e.getMessage());
			}
			return value;

		}
		
		public static void moveToElement(WebElement element) {
			try {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].scrollIntoView();", element);
			} catch (Exception e) {
				Assert.assertTrue(false, "Unable to move to element" + element);
				e.printStackTrace();
			}
		}

		// For Button Click
		public static void buttonClick(WebElement element) {
			try {
				waitForElementToBePresent(element);
				
				String elval = element.getText();
				test.log(Status.INFO, "waiting to click on  " + elval);
				element.click();
				test.log(Status.PASS, "Clicked on  " +elval);
			} catch (Exception e) {
				Assert.assertTrue(false, "Unable Click on element " + e.getMessage());
				test.log(Status.FAIL, "Unable Click on element " + e.getMessage());
				logger.error("Unable to Click on element " + e.getMessage());
			}
		}
	
		// For Get Text
		public static String getText(WebElement element) {
			try {
				waitForElementToBePresent(element);
//			moveToElement(element);
				value = element.getText().trim();
				logger.info("Value of element is " + value);
			} catch (Exception e) {
				Assert.assertTrue(false, "Element not found" + element);
				e.printStackTrace();
				test.log(Status.FAIL, "Element not found " + e.getMessage());
				logger.error("Element not found " + e.getMessage());
			}
			return value;
		}
		
		// For Get Text
		public static String getvisibleText(WebElement element) {
			try {
				waitForElementToBePresent(element);
				moveToElement(element);
				
				logger.info("Value of element is " + element.getText().trim());
				value = element.getText().trim();
				
			} catch (Exception e) {
				Assert.assertTrue(false, "Element not found" + element);
				e.printStackTrace();
			}
			return value;
		}

}
