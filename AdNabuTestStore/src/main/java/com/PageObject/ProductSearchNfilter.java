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
	JavascriptExecutor jsExecutor;
	
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
	private WebElement viewCart;
	
	
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
	}
	
	//Add to cart
		public void clickOnAddToCart() throws InterruptedException {
			buttonClick(addToCart);
			Thread.sleep(2000);
			logger.info("Clicked on add to cart button");
			
//			String cartText = driver.findElement(By.xpath("//h1[text()='Your cart']")).getText();
		}

		//View and remove item from cart
				public void viewNRemoveproductFromCart() {
					jsExecutor = (JavascriptExecutor) driver;

			        // Execute JavaScript to scroll up
			        jsExecutor.executeScript("window.scrollTo(0, 0);");
			        
					buttonClick(viewCart);
					driver.findElement(By.xpath("//a[contains(@aria-label,'Remove')]")).click();
					String emptyCartText = driver.findElement(By.xpath("//h1[text()='Your cart is empty']")).getText();
					driver.findElement(By.xpath("//a[@id='HeaderMenu-home']")).click();
					
				}
		
}
