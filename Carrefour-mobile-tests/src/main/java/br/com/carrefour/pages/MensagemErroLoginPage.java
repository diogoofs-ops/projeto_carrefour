package br.com.carrefour.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;


public class MensagemErroLoginPage {


    private AppiumDriver driver;
    private static final Logger logger = Logger.getLogger(MensagemErroLoginPage.class.getName());

    public MensagemErroLoginPage(AppiumDriver driver) {
        this.driver = driver;
    }


    private By mensagemErro = By.xpath("//android.widget.TextView[@text='Please enter a valid email address']");

    public boolean isMensagemErroVisivel() {
        try {
            return driver.findElement(mensagemErro).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String obterMensagemDeErroLogin() {
        try {
            return driver.findElement(mensagemErro).getText();
        } catch (Exception e) {
            return "";
        }
    }
}
