package SeleniumTest.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumTest.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver;

	//Constructor this will be common for all the child classes 
	public LandingPage(WebDriver driver) {
		super (driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

//	WebElement userEmail = driver.findElement(By.id("userEmail"));

	// Page Factory
	@FindBy(id = "userEmail")
	WebElement userEmail;

	@FindBy(id = "userPassword")
	WebElement userPassword;

	@FindBy(id = "login")
	WebElement loginBtn;
	
	@FindBy(css ="[class*= 'flyInOut']")
	WebElement errorMsg;
	
	
	
	
	public String getErrorMsg() {
		waitForWebElementToAppear(errorMsg);
		//System.out.println(errorMsg.getText());
		return errorMsg.getText();
		}

	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}

	public ProductCatalog loginApp(String email, String password) {
		userEmail.sendKeys(email);
		userPassword.sendKeys(password);
		loginBtn.click();
		
		ProductCatalog PC = new ProductCatalog(driver); // creating object for ProductCatalog class
		return PC;

	}

}
