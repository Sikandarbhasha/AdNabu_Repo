package com.GenericLibrary;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeClass;

import com.PageObject.ProductSearchNfilter;

public class BaseClass extends Browser {

	public ProductSearchNfilter pr;
	
	@BeforeClass
	public void setClass() {
		pr = PageFactory.initElements(driver, ProductSearchNfilter.class);
	}
}
