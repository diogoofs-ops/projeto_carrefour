package br.com.carrefour.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class WebviewPage {

    private AppiumDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(WebviewPage.class.getName());

    // Locators
    private static final By GETSTARTED_BUTTON = By.xpath("//android.view.View[@content-desc=\"Get Started\"]");
    private static final By CLOSE_BUTTON = By.xpath("//android.widget.Button[@text='Close']");
    private static final By VALID_MESSAGE = By.xpath("//android.widget.TextView[@text='Getting Started']");

    public WebviewPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void clicarNoClose() {
        logger.info("Clicando no botão de fechar");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement closeButton = wait.until(ExpectedConditions.elementToBeClickable(CLOSE_BUTTON));
        closeButton.click();
    }

    public void clicarNoGetStarted() {
        logger.info("Clicando no botão de Get Started");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement getStartedButton = wait.until(ExpectedConditions.elementToBeClickable(GETSTARTED_BUTTON));
        getStartedButton.click();
    }

    public String validarTextoInformado() {
        logger.info("Aguardando visibilidade do texto 'Getting Started'");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        String mensagem = wait.until(ExpectedConditions.visibilityOfElementLocated(VALID_MESSAGE)).getText();
        logger.info("Texto validado com sucesso: " + mensagem);
        return mensagem;
    }
}