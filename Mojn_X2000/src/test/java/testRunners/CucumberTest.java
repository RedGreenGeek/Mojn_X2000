package testRunners;



 
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
 
@RunWith(Cucumber.class)
@CucumberOptions(
     features = "src/main/resources/",
     glue = {"src/test/java/stepDefinitions"})
public class CucumberTest {
} 