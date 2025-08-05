package br.com.carrefour.runner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "html:target/cucumber-report.html"},
        features = "src/test/resources/features",
        glue = {"br.com.carrefour.steps", "br.com.carrefour.hooks"},
        tags = ""
)
public class TestRunner {
}
