package SeleniumTest.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumTest.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {

	WebDriver driver;

	// Constructor this will be common for all the child classes
	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Page Factory
	@FindBy(css = ".mb-3")
	List<WebElement> products;

	By productsBy = By.cssSelector(".mb-3");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMsg = By.cssSelector("#toast-container");
	
	//Page Factory for waitForElementToDisappear
	@FindBy(css = ".ng-animating")
	WebElement dsprMsg;
	

	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productsBy);
		return products;
	}

	public WebElement getProductByName(String productName) throws InterruptedException {
		// Using stream to traverse through the list and find the product ZARA COAT 3
		//Thread.sleep(3000);
		WebElement prod = getProductList().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst()
				.orElse(null);
		System.out.println(prod);
		return prod;
	}

	public void addProductToCart(String productName) throws InterruptedException {
		// Getting the product from getProductList and clicking on the the Add to Cart
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		// code to check for the toast message of added to cart appear and disappear
		waitForElementToAppear(toastMsg);
		waitForElementToDisappear(dsprMsg);
	}
	


}
