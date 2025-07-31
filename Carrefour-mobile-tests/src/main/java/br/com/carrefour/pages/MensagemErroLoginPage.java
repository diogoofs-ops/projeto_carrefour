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


    private static final By ERROR_MESSAGE = By.xpath("//android.widget.TextView[@text='Please enter a valid email address']");

    public MensagemErroLoginPage(AppiumDriver driver) {
        this.driver = driver;
    }


    public String obterMensagemDeErroLogin() {
        logger.info("Aguardando mensagem de erro após tentativa de cadastro inválido");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String mensagemErro = wait.until(ExpectedConditions.visibilityOfElementLocated(ERROR_MESSAGE)).getText();
        logger.info("Mensagem de erro recebida: " + mensagemErro);
        return mensagemErro;
    }
}

