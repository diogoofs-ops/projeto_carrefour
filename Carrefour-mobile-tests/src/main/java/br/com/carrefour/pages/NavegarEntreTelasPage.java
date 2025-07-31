package br.com.carrefour.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

import java.util.logging.Logger;

public class NavegarEntreTelasPage {

    private AppiumDriver driver;
    private static final Logger logger = Logger.getLogger(NavegarEntreTelasPage.class.getName());

    private static final By HOME_SCREEN = By.xpath("//android.view.View[@content-desc='Home']");
    private static final By WEBVIEW_SCREEN = By.xpath("//android.view.View[@content-desc='Webview']");
    private static final By LOGIN_SCREEN = By.xpath("//android.view.View[@content-desc='Login']");
    private static final By FORMS_SCREEN = By.xpath("//android.view.View[@content-desc='Forms']");
    private static final By SWIPE_SCREEN = By.xpath("//android.view.View[@content-desc='Swipe']");
    private static final By DRAG_SCREEN = By.xpath("//android.view.View[@content-desc='Drag']");

    public NavegarEntreTelasPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public void clicarNaHome() {
        logger.info("Navegando para a tela Home");
        driver.findElement(HOME_SCREEN).click();
    }

    public void clicarNaWebView() {
        logger.info("Navegando para a tela WebView");
        driver.findElement(WEBVIEW_SCREEN).click();
    }

    public void clicarNoLogin() {
        logger.info("Navegando para a tela Login");
        driver.findElement(LOGIN_SCREEN).click();
    }

    public void clicarNoForms() {
        logger.info("Navegando para a tela Forms");
        driver.findElement(FORMS_SCREEN).click();
    }

    public void clicarNoSwipe() {
        logger.info("Navegando para a tela Swipe");
        driver.findElement(SWIPE_SCREEN).click();
    }

    public void clicarNoDrag() {
        logger.info("Navegando para a tela Drag");
        driver.findElement(DRAG_SCREEN).click();
    }
}


