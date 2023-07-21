package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {
                /*"src/test/java/features/ProductComparison.feature",
                "src/test/java/features/Login.feature",
                "src/test/java/features/AddProductToCart.feature",
                "src/test/java/features/Checkout.feature",
                "src/test/java/features/AddThreeStarProductToCart.feature",*/
                "src/test/java/features/Shopping_Cart.feature",
        },
        glue = "stepDefinition",
        plugin = {"pretty", "html:src/test/java/reporting/report.html"}

)
public class TestRunner {

}
