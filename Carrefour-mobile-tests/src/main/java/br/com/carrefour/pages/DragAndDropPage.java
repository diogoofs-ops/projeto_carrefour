package br.com.carrefour.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.LongPressOptions;
import io.appium.java_client.touch.offset.ElementOption;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.logging.Logger;

public class DragAndDropPage {

    private AppiumDriver driver;
    private static final Logger logger = Logger.getLogger(DragAndDropPage.class.getName());

    private static final By SUCCESS_DRAG = By.xpath("//android.widget.TextView[@text='Drag and Drop']");
    private static final By DROPI2_BUTTON = By.xpath("//android.view.ViewGroup[@content-desc=\"drop-l2\"]");
    private static final By DRAGI2_BUTTON = By.xpath("//android.view.ViewGroup[@content-desc=\"drag-l2\"]");

    public DragAndDropPage(AppiumDriver driver) {
        this.driver = driver;
    }

    public void clicarDropI2() {
        logger.info("Clicando no botão de drop (drop-l2)");
        driver.findElement(DROPI2_BUTTON).click();
    }

    public void clicarDragI2() {
        logger.info("Clicando no botão de drag (drag-l2)");
        driver.findElement(DRAGI2_BUTTON).click();
    }

    public void realizarDragAndDrop() {
        logger.info("Iniciando ação de drag and drop entre drag-l2 e drop-l2");

        WebElement dragElement = driver.findElement(DRAGI2_BUTTON);
        WebElement dropElement = driver.findElement(DROPI2_BUTTON);

        TouchAction<?> action = new TouchAction<>((PerformsTouchActions) driver);
        action.longPress(LongPressOptions.longPressOptions()
                        .withElement(ElementOption.element(dragElement))
                        .withDuration(Duration.ofSeconds(1)))
                .moveTo(ElementOption.element(dropElement))
                .release()
                .perform();

        logger.info("Drag and drop realizado com sucesso");
    }

    public String obterMensagemDrag() {
        logger.info("Aguardando mensagem de sucesso após drag and drop");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String mensagem = wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_DRAG)).getText();
        logger.info("Mensagem recebida: " + mensagem);
        return mensagem;
    }
}



