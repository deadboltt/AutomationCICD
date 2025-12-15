package SeleniumTest.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumTest.TestComponents.BaseTest;
import SeleniumTest.pageobjects.ProductCatalog;
import SeleniumTest.pageobjects.cartPage;
import SeleniumTest.pageobjects.checkoutPage;
import SeleniumTest.pageobjects.cnfrmOrderPage;
import SeleniumTest.pageobjects.orderPage;

public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";
	String country = "India";

	@Test(dataProvider ="getData",groups= {"purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException{
		
		ProductCatalog PC = LP.loginApp(input.get("email"), input.get("password")); // Log-In and creating object for PC
		List<WebElement> products = PC.getProductList(); // To get List of elements
		PC.addProductToCart(input.get("product")); // To add product to the cart
		cartPage CP = PC.clickOnCartBtn(); // To click on the Cart icon and creating object for cartPage

		Boolean match = CP.checkCartItem(input.get("product")); // To verify if the added item is in cart
		Assert.assertTrue(match);
		checkoutPage COP = CP.chkoutBtn(); // To click on the Checkout Button and creating object for checkoutPage

		COP.selectCountry(country); // Selecting country in checkout page
		orderPage OP = COP.placeOrder(); // clicking on place order button
		
		
		String text = OP.cnfrmOrder(); //Confirming Order is Placed or Not
		Assert.assertEquals(text, "THANKYOU FOR THE ORDER.");
		
		//tearDown();
	}
	
	
	//To verify ZARA COAT 3 is displaying in orders page
	@Test(dependsOnMethods = {"submitOrder"})
	public void dependencyOrderTest() throws InterruptedException {		
		ProductCatalog productCatalogue = LP.loginApp("rajathpai078@gmail.com", "Test@1234");
		Thread.sleep(3000);
		cnfrmOrderPage COP = productCatalogue.clickOnOrdersBtn();
		Assert.assertTrue(COP.verifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
		List<HashMap<String,String>> data = getJsonDataToMap("C:\\Users\\Rajath\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\test\\java\\SeleniumTest\\data\\PurchaseOrder.json");		
		return new Object [][] {{data.get(0)},{data.get(1)}};
		
		
//		HashMap<String,String> map =new HashMap<String,String>();
//		map.put("email","rajathpai078@gmail.com");
//		map.put("Password","Test@1234");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 =new HashMap<String,String>();
//		map1.put("email","rajathpai90@gmail.com");
//		map1.put("Password","Test@1234");
//		map1.put("product","ADIDAS ORIGINAL");
	}
}
