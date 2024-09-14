package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions (features="src/test/java/cucumber", tags = "@Regression" ,glue = "rahulshettyacadrmy.stepDefinition", monochrome = true, plugin= {"html:target/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests {
	//this file will run all the feature file in src/test/java/cucumber one by one and mapping with the discription file in rahulshettyacadrmy.stepDefinition
	//the result will be returned in target folder and wrapped in html file named "cucumber.html"
}
