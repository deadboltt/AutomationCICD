package SeleniumTest.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumTest.TestComponents.BaseTest;
import SeleniumTest.TestComponents.Retry;
import SeleniumTest.pageobjects.ProductCatalog;
import SeleniumTest.pageobjects.cartPage;


public class ErrorValidationsTest extends BaseTest{
	

	@Test(groups = {"ErrorHandling"},retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException{
		
		LP.loginApp("rajathpai078@gmail.com", "Test@1233"); // Log-In and creating object for with wrong password
		//Assert.assertEquals("Incorrect email or password.", LP.getErrorMsg());
		Assert.assertEquals("Incorrect email or password.", LP.getErrorMsg());

	}
	
	@Test(groups = {"ErrorHandling"})
	public void productErrorValidation() throws IOException, InterruptedException{
		
		ProductCatalog PC = LP.loginApp("rajathpai078@gmail.com", "Test@1234"); // Log-In and creating object for
		List<WebElement> products = PC.getProductList(); // To get List of elements
		PC.addProductToCart("ZARA COAT 3"); // To add product to the cart
		cartPage CP = PC.clickOnCartBtn(); // To click on the Cart icon and creating object for cartPage

		Boolean match = CP.checkCartItem("ZARA COAT 33"); // To verify if the added item is in cart
		//System.out.println(match);
		Assert.assertFalse(match); //Expected match var to be false as match is not found in orders page

	}
	
		
}
