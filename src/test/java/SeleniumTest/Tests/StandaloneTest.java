package SeleniumTest.Tests;

import java.time.Duration;	
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import SeleniumTest.pageobjects.LandingPage;

public class StandaloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","C:\\Users\\Rajath\\Documents\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();	
		driver.manage().window().maximize();
		
		String productName = "ZARA COAT 3";
		String country = "India";
		
		//Hello World
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //implicit wait
		
		
		WebDriverWait w = new WebDriverWait(driver,Duration.ofSeconds(10)); //explicit wait
		
		//invoke browser
		driver.get("https://rahulshettyacademy.com/client");
		
		//Log-In
		LandingPage LP = new LandingPage(driver); //creating object for LandingPage class
		driver.findElement(By.id("userEmail")).sendKeys("rajathpai078@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Test@1234");
		driver.findElement(By.id("login")).click();
		
		//To get List of elements
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement>products = driver.findElements(By.cssSelector(".mb-3"));
		
		//Using stream to traverse through the list and find the product ZARA COAT 3	
		WebElement prod = products.stream().filter(product->
	    product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//Same flow as streams above using for loop and if condition
//		for(WebElement prod:products) {
//			System.out.println(prod);
//			if(prod.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")) {
//				prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
//			}
//		}
		
		//code to check for the toast message of added to cart appear and disappear
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		w.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		
		//Code to check if the added item is present in the cart
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		w.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cartSection h3")));
		Boolean match = cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		//System.out.println(match);
		Assert.assertTrue(match);
		
		//Checkout Button Code
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		//Suggestive Drop-down Code
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys(country);
		Actions a = new Actions(driver);
		w.until(ExpectedConditions.visibilityOfElementLocated(By.className("ta-results")));
		a.sendKeys(driver.findElement(By.xpath("(//button[@type='button'])[2]")), country).build().perform();
		
		//Place Order Button Code
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		String text = driver.findElement(By.className("hero-primary")).getText();
		Assert.assertEquals(text,"THANKYOU FOR THE ORDER.");
	
		
		
		
		
		
		
	}
	

}
