package SeleniumTest.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumTest.AbstractComponents.AbstractComponent;

public class orderPage extends AbstractComponent {
	WebDriver driver;

	public orderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	} 
	@FindBy(css = ".hero-primary")
	WebElement cnfrmText;
	
	By text = By.cssSelector(".hero-primary");
	
	public String cnfrmOrder() {
		waitForElementToAppear(text);
		System.out.println(cnfrmText.getText());
		return cnfrmText.getText();
	}
	
}

