package com.testCases;

import org.testng.annotations.Test;

import com.GenericLibrary.BaseClass;
import com.aventstack.extentreports.Status;

public class adnabu_Test extends BaseClass {
	
	@Test(priority = 1)
	public void verification_of_productSearchNfilter_Test() throws InterruptedException {
		test = extent.createTest("verification_of_productSearchNfilter_Test");
		
		test.info("Test case started to verifiy product search and filter");
		logger.info("Test case started to verifiy product search and filter");
		
		pr.clickOnPurductSearchButton();
		pr.searchProduct("Shirt");
		pr.clickOnSearchedPurductButton();
		pr.clickOnAddToCart();
		
		
				
		test.log(Status.PASS, "Test case ended to verifiy product search and filter");
		logger.info("Test case ended to verifiy product search and filter");
	}

	@Test(priority = 2)
	public void verification_of_productRemoveFromCart_Test() {
		test = extent.createTest("verification_of_productRemoveFromCart_Test");
		test.info("Test case started to verifiy product remove from cart");
		logger.info("Test case started to verifiy product remove from cart");
		
		pr.viewNRemoveproductFromCart();
		
		test.log(Status.PASS, "Test case ended to verifiy product remove from cart");
		logger.info("Test case ended to verifiy product remove from cart");
	}
	
}
