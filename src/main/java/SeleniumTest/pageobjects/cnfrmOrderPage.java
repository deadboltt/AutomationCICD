package SeleniumTest.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import SeleniumTest.AbstractComponents.AbstractComponent;

public class cnfrmOrderPage extends AbstractComponent{
	
	WebDriver driver;

	public cnfrmOrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	//Page Factory 
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutButton;
	
	By cartItems = By.cssSelector("td:nth-child(3)");
	
	public Boolean verifyOrderDisplay(String productName) {
		// Code to check if the added item is present in the cart
		waitForElementToAppear(cartItems);
		Boolean match = productNames.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		return match;
	}
		

	
	

}
