package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//Changing tags to Regression will execute the purchase order cucumber file

@CucumberOptions(features = "src/test/java/cucumber", glue = "SeleniumTest.stepDefinitions", monochrome = true, tags = "@ErrorValidation", plugin = {
		"html:target/cucumber.html" })
public class TestNGTestRunner extends AbstractTestNGCucumberTests {

}
