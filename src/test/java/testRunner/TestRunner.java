package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/java/features/ProductComparison.feature",
        glue = "src/test/java/stepDefinition/productComparison.java"
)
public class TestRunner {
}
