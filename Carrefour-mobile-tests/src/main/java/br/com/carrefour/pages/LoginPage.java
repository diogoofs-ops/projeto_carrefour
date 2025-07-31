package br.com.carrefour.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import java.util.logging.Logger;

public class LoginPage {

    private AppiumDriver driver;
    private static final Logger logger = Logger.getLogger(LoginPage.class.getName());


    private static final By EMAIL_INPUT = By.xpath("//*[@content-desc='input-email']");
    private static final By PASSWORD_INPUT = By.xpath("//*[@content-desc='input-password']");
    private static final By LOGIN_BUTTON = By.xpath("//*[@content-desc='button-LOGIN']/*[1]");
    private static final By LOGIN_SCREEN = By.xpath("//android.view.View[@content-desc='Login']");
    private static final By OK_BUTTON = By.id("android:id/button1");

    public LoginPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public void loginScreen() {
        logger.info("Acessando a tela de login");
        driver.findElement(LOGIN_SCREEN).click();
    }

    public void preencherEmail(String email) {
        logger.info("Preenchendo campo de email" + email);
        driver.findElement(EMAIL_INPUT).sendKeys(email);
    }

    public void preencherSenha(String password) {
        logger.info("Preenchendo campo de senha");
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    public void clicarLogin() {
        logger.info("Clicando no botão de login");
        driver.findElement(LOGIN_BUTTON).click();
    }

    public void clicarOk() {
        logger.info("Clicando no botão ok");
        driver.findElement(OK_BUTTON).click();
    }

    public void fecharApp() {
        logger.info("Fechando o aplicativo");
        if (driver != null) {
            driver.quit();
        }
    }
}
