package com.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.GenericLibrary.CommonLibrary;

public class ProductSearchNfilter extends CommonLibrary {
	WebDriver driver;
	JavascriptExecutor js;
	
	public ProductSearchNfilter(WebDriver driver) {
		this.driver=driver;
	}
	
	@FindBy(how = How.XPATH, using="(//summary[@role='button']/span)[2]")
	private WebElement productSearchButton;
	
	@FindBy(how = How.XPATH, using="//input[@id='Search-In-Modal-1']")
	private WebElement productSearchTextField;
	
	@FindBy(how = How.XPATH, using="(//button[@class='search__button field__button'])[1]")
	private WebElement enteredTextSearchButton;
	
	@FindBy(how = How.XPATH, using="//button[@name='add']")
	private WebElement addToCart;
	
	@FindBy(how = How.XPATH, using="//span[text()='Cart']")
	private WebElement viewCartButton;
	
	@FindBy(how = How.XPATH, using="//a[@id='HeaderMenu-home']")
	private WebElement homeButton;
	
	
	
	//clicking on search button
	public void clickOnPurductSearchButton() {
		buttonClick(productSearchButton);
	}
	
	// searching product
	public void searchProduct(String value) {
		entervalue(value, productSearchTextField);
	}
	
	// Navigating to the searched product
	public void clickOnSearchedPurductButton() {
		buttonClick(enteredTextSearchButton);
		driver.findElement(By.xpath("//a[@id='CardLink--2151063355489']")).click();
		logger.info("Clicked on selected item");
	}
	
	//Add to cart
		public void clickOnAddToCart() throws InterruptedException {
			buttonClick(addToCart);
			Thread.sleep(2000);
			logger.info("Clicked on add to cart button");
			
		}

		//View and remove item from cart
				public void viewNRemoveproductFromCart() throws InterruptedException {
					js = (JavascriptExecutor) driver;
					js.executeScript("arguments[0].scrollIntoView(true);", viewCartButton);
					js.executeScript("arguments[0].click();", viewCartButton);
					driver.findElement(By.xpath("//a[contains(@aria-label,'Remove')]")).click();
					logger.info("Item removed from the cart");
					buttonClick(homeButton);
				}
		
}
