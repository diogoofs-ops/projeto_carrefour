package br.com.carrefour.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class FormsDropdownThisAppPage {

    private AppiumDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(FormsDropdownThisAppPage.class.getName());

    private static final By DROPDOWN_INPUT3 = By.xpath("//android.widget.CheckedTextView[@text='This app is awesome']");

    public FormsDropdownThisAppPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clicarNoDropdownThisAppIsAwesome() {
        logger.info("Aguardando e selecionando opção 'This app is awesome' no dropdown");
        wait.until(ExpectedConditions.elementToBeClickable(DROPDOWN_INPUT3)).click();
    }
}