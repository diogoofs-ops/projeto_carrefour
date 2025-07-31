package br.com.carrefour.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.logging.Logger;

public class FormsDropdownAppiumPage {

    private AppiumDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(FormsDropdownAppiumPage.class.getName());

    private static final By INPUT_FIELD = By.xpath("//android.widget.EditText[@content-desc=\"text-input\"]");
    private static final By SWITCH_BUTTON = By.className("android.widget.Switch");
    private static final By DROPDOWN_BUTTON = By.xpath("//android.view.ViewGroup[@content-desc=\"Dropdown\"]/android.view.ViewGroup/android.widget.EditText");
    private static final By DROPDOWN_INPUT2 = By.xpath("//android.widget.CheckedTextView[@text='Appium is awesome']");
    private static final By ACTIVE_BUTTON = By.xpath("//*[@content-desc=\"button-Active\"]");
    private static final By OK_BUTTON = By.id("android:id/button1");
    private static final By CLICK_BUTTON = By.xpath("//android.widget.TextView[@text=\"You have typed:\"]");


    public FormsDropdownAppiumPage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void informarUmTextoNoInputField(String field)  {
        logger.info("Informando texto no campo de input: " + field);
        driver.findElement(INPUT_FIELD).sendKeys(field);
    }
    public void clicarNInputField()  {
        logger.info("Informando texto no campo de input");
        driver.findElement(INPUT_FIELD).click();
        driver.findElement(CLICK_BUTTON).click();
    }

    public void clicarNoSwitchOff() {
        logger.info("Clicando no botão switch");
        driver.findElement(SWITCH_BUTTON).click();
    }
    public void clicarOcultarTeclado() {
        logger.info("Clicando para ocultar o teclado");
        driver.findElement(CLICK_BUTTON).click();
    }

    public void clicarNoDropdown() {
        logger.info("Clicando no dropdown");
        driver.findElement(DROPDOWN_BUTTON).click();
    }

    public void clicarNoDropdownAppiumIsAwesome() {
        logger.info("Selecionando opção 'Appium is awesome' no dropdown");
        driver.findElement(DROPDOWN_INPUT2).click();
    }

    public void clicarNoActive() {
        logger.info("Clicando no botão 'Active'");
        driver.findElement(ACTIVE_BUTTON).click();
    }

    public void clicarNoOk() {
        logger.info("Clicando no botão 'OK' da caixa de diálogo");
        driver.findElement(OK_BUTTON).click();
    }
    }