package SeleniumTest.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import SeleniumTest.AbstractComponents.AbstractComponent;

public class checkoutPage extends AbstractComponent {
	WebDriver driver;

	public checkoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

//	// Place Order Button Code
//	driver.findElement(By.cssSelector()).click();
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement countryToSelect;
	
	@FindBy(xpath = "(//button[@type='button'])[2]")
	WebElement selectedCountry;
	
	By countriesVisibility = By.className("ta-results");
	
	@FindBy(css=".action__submit")
	WebElement plcOrder;
	
	public void selectCountry(String country) {
		countryToSelect.sendKeys(country);
		Actions a = new Actions(driver);
		waitForElementToAppear(countriesVisibility);
		selectedCountry.click();
		
	}
	
	public orderPage placeOrder() {
		plcOrder.click();
		orderPage OP = new orderPage(driver);
		return OP;
		
	}

}
