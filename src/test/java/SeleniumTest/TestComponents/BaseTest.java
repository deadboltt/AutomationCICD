package SeleniumTest.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import SeleniumTest.pageobjects.LandingPage;

public class BaseTest {

	public WebDriver driver;
	public LandingPage LP;

	public WebDriver initializeDriver() throws IOException {

		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				"C:\\Users\\Rajath\\eclipse-workspace\\SeleniumFrameworkDesign\\src\\main\\java\\SeleniumTest\\Resources\\GlobalData.properties");
		prop.load(fis);
		
		String browserName = System.getProperty("browser")!=null? System.getProperty("browser") : prop.getProperty("browser");
		//String browserName = prop.getProperty("browser");

		if (browserName.contains("chrome")) {
			// Chrome
			ChromeOptions options = new ChromeOptions();

			if(browserName.contains("headless")) {
			options.addArguments("headless");
			options.addArguments("start-maximized"); //to open browser in full screen when in headless mode
//			Duration duration = Duration.of(10, ChronoUnit.SECONDS);
//			options.setImplicitWaitTimeout(duration);
			}
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\Rajath\\Documents\\chromedriver-win64\\chromedriver.exe");
			
			driver = new ChromeDriver(options);
			driver.manage().window().setSize(new Dimension (1920,1080));
			
		} else if (browserName.equals("firefox")) {
			//System.setProperty("webdriver.gecko.driver","C:\\Users\\Rajath\\Documents\\geckodriver-v0.36.0-win64\\geckodriver.exe	");
			driver = new FirefoxDriver();

		} else if (browserName.equals("edge")) {
			// Edge
			driver = new EdgeDriver();
		}

		driver.manage().window().maximize();
		return driver;
	}

	// Code to read JSON File content and convert it to HashMap
	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException {

		// reading Json to String
		String jsoncontent = FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);

		// Converting String to hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsoncontent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;
	}

	@BeforeMethod(alwaysRun = true)
	public LandingPage launchApplication() throws IOException {
		driver = initializeDriver();
		// driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		LP = new LandingPage(driver); // creating object for LandingPage class
		LP.goTo(); // Invoke Browser & Navigate to URL
		return LP;
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		driver.close();
	}

	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
		FileUtils.copyFile(source, file);
		return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
	}
}
