package br.com.carrefour.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class FormsDropdownWebDriverPage {

    private AppiumDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(FormsDropdownWebDriverPage.class.getName());

    private static final By DROPDOWN_INPUT1 = By.xpath("//android.widget.CheckedTextView[@text='webdriver.io is awesome']");

    public FormsDropdownWebDriverPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }



    public void clicarNoDropdownWebdriverIoIsAwesome() {
        logger.info("Aguardando e selecionando opção 'webdriver.io is awesome' no dropdown");
        wait.until(ExpectedConditions.elementToBeClickable(DROPDOWN_INPUT1)).click();
    }
}