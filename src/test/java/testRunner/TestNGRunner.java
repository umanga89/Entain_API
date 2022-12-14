package testRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;

@CucumberOptions(features = {"src/test/java/features"},
                glue = {"steps","util"},
                tags = ("@api_functional or @ui_functional"),
                plugin = {"pretty","html:target/cucumber-report-chrome.html", "json:target/cucumber.json"})
public class TestNGRunner extends AbstractTestNGCucumberTests {

}
