package testRunners;



 
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(
     features = "src/main/resources/M3_BedsAvailable.feature",
     glue = {"stepDefinitions"})
public class CucumberTest {
} 