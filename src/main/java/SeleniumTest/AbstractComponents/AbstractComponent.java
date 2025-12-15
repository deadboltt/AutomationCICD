package SeleniumTest.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumTest.pageobjects.cartPage;
import SeleniumTest.pageobjects.cnfrmOrderPage;

public class AbstractComponent {

	WebDriver driver;

	// Constructor
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	// Page Factory for Cart Btn
	@FindBy(xpath = "//button[@routerlink='/dashboard/cart']")
	WebElement cartBtn;
	
	@FindBy(xpath = "//button[@routerlink='/dashboard/myorders']")
	WebElement orderHeader;
	
	public cnfrmOrderPage clickOnOrdersBtn() {
		orderHeader.click();
		cnfrmOrderPage  COP = new cnfrmOrderPage(driver);// creating object for cartPage class
		return COP;
	}
	
	

	public void waitForElementToAppear(By findBy) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10)); // explicit wait
		w.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void waitForWebElementToAppear(WebElement find1) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10)); // explicit wait
		w.until(ExpectedConditions.visibilityOf(find1));
	}

	public void waitForElementToDisappear(WebElement ele) {
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10)); // explicit wait
		w.until(ExpectedConditions.invisibilityOf(ele));
	}

	public cartPage clickOnCartBtn() {
		cartBtn.click();
		cartPage CP = new cartPage(driver);// creating object for cartPage class
		return CP;
	}

}
