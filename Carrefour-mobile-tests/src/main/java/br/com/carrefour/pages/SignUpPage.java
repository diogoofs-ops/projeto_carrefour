package br.com.carrefour.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class SignUpPage {

    private AppiumDriver driver;
    private static final Logger logger = Logger.getLogger(SignUpPage.class.getName());

    private static final By LOGIN_SCREEN = By.xpath("//android.view.View[@content-desc='Login']");
    private static final By SIGNUP_SCREEN = By.xpath("//*[@content-desc=\"button-sign-up-container\"]//android.widget.TextView");
    private static final By CONFIRM_PASSWORD_INPUT = By.xpath("//*[@content-desc='input-repeat-password']");
    private static final By SIGNUP_BUTTON = By.xpath("//android.view.ViewGroup[@content-desc=\"button-SIGN UP\"]/android.view.ViewGroup/android.widget.TextView");
    private static final By OKSIGNUP_BUTTON = By.xpath("//android.widget.Button[contains(@text,'OK')]");
    private static final By EMAIL_INPUT = By.xpath("//*[@content-desc='input-email']");
    private static final By PASSWORD_INPUT = By.xpath("//*[@content-desc='input-password']");

    public SignUpPage(AppiumDriver driver) {
        this.driver = driver;
    }
    public void clicarLoginScreen() {
        logger.info("Clicando no botão Login");
        driver.findElement(LOGIN_SCREEN).click();
    }

    public void clicarSignUpScreen() {
        logger.info("Clicando no botão Sign Up");
        driver.findElement(SIGNUP_SCREEN).click();
    }
    public void preencherEmail(String email) {
        logger.info("Preenchendo campo de email" + email);
        driver.findElement(EMAIL_INPUT).sendKeys(email);
    }

    public void preencherSenha(String password) {
        logger.info("Preenchendo campo de senha");
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    public void confirmarSenha(String password) {
        logger.info("Confirmando senha no campo de repetição");
        driver.findElement(CONFIRM_PASSWORD_INPUT).sendKeys(password);
    }

    public void clicarSignUp() {
        logger.info("Clicando no botão Sign Up");
        driver.findElement(SIGNUP_BUTTON).click();
    }

    public void clicarOkSignUp() {
        logger.info("Clicando no botão OK Sign Up");
        driver.findElement(OKSIGNUP_BUTTON).click();
    }
}