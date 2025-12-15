package SeleniumTest.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import SeleniumTest.AbstractComponents.AbstractComponent;

public class cartPage extends AbstractComponent{
	
	WebDriver driver;

	public cartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		
	//Page Factory 
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement checkOutButton;
	
	By cartItems = By.cssSelector(".cartSection h3");
	
	public Boolean checkCartItem(String productName) {
		// Code to check if the added item is present in the cart
		waitForElementToAppear(cartItems);
		Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
		//System.out.println(match);
		return match;
	}
		
	public checkoutPage chkoutBtn() {
		// Checkout Button Code
		checkOutButton.click();
		checkoutPage COP = new checkoutPage(driver);
		return COP;
	}
	
	

}
