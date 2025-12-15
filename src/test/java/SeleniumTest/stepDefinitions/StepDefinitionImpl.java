package SeleniumTest.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumTest.TestComponents.BaseTest;
import SeleniumTest.pageobjects.LandingPage;
import SeleniumTest.pageobjects.ProductCatalog;
import SeleniumTest.pageobjects.cartPage;
import SeleniumTest.pageobjects.checkoutPage;
import SeleniumTest.pageobjects.orderPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionImpl extends BaseTest {

	public LandingPage LP;
	public ProductCatalog PC;
	public orderPage OP;

	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		LP = launchApplication();
	}

	@Given("^Loggedin with username (.+) and (.+)$")
	public void loggedin_with_username_and_password(String username, String password) {
		PC = LP.loginApp(username, password); // Log-In and creating object for PC
	}	

	@When("^I add product (.+) to cart$")
	public void I_add_product_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = PC.getProductList(); // To get List of elements
		PC.addProductToCart(productName); // To add product to the cart
	}

	@When("^checkout the (.+) and submit the order$")
	public void checkout_the_product_and_submit_the_orders(String productName) {
		cartPage CP = PC.clickOnCartBtn(); // To click on the Cart icon and creating object for cartPage
		Boolean match = CP.checkCartItem(productName); // To verify if the added item is in cart
		Assert.assertTrue(match);
		checkoutPage COP = CP.chkoutBtn(); // To click on the Checkout Button and creating object for checkoutPage
		COP.selectCountry("india"); // Selecting country in checkout page
		OP = COP.placeOrder(); // clicking on place order button
	}
	
    @Then("{string} message is displayed on confirmation screen")
    public void message_displayed_on_confirmation_page(String string) {
    	String text = OP.cnfrmOrder(); //Confirming Order is Placed or Not
		Assert.assertEquals(text,string);
		driver.close();
    }
    
    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {
   
    	Assert.assertEquals(strArg1,LP.getErrorMsg());
    	driver.close();
    }


}
