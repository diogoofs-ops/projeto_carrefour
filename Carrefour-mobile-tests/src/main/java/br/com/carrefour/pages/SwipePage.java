package br.com.carrefour.pages;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Arrays;
import java.util.logging.Logger;

public class SwipePage {

    private AppiumDriver driver;
    private WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(SwipePage.class.getName());

    private static final By SUCCESS_SWIPE = By.xpath("//android.widget.TextView[@text='GREAT COMMUNITY']");
    private static final By CARD1_SCREEN = By.xpath("//android.widget.TextView[@text='FULLY OPEN SOURCE']");
    private static final By CARD2_SCREEN = By.xpath("//android.widget.TextView[@text='GREAT COMMUNITY']");
    //private static final By CARD3_SCREEN = By.xpath("//android.widget.TextView[@text='JS.FOUNDATION']");
//    private static final By CARD4_SCREEN = By.xpath("//android.widget.TextView[@text='SUPPORT VIDEOS']");
//    private static final By CARD5_SCREEN = By.xpath("//android.widget.TextView[@text='EXTENDABLE']");
    //private static final By CARD6_SCREEN = By.xpath("//android.widget.TextView[@text='COMPATIBLE']");

    public SwipePage(AppiumDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private void clicar(By locator, String nomeCard) {
        logger.info("Clicando no card '" + nomeCard + "'");
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (Exception e) {
            logger.severe("Erro ao clicar no card '" + nomeCard + "': " + e.getMessage());
            throw e;
        }
    }

    public void scrollParaDireita() {
        logger.info("Executando scroll horizontal para a direita");

        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.8);
        int endX = (int) (size.width * 0.2);
        int y = (int) (size.height * 0.5);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);
        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, y));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(800), PointerInput.Origin.viewport(), endX, y));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        driver.perform(Arrays.asList(swipe));
        esperar(); // espera após scroll
    }

    private void esperar() {
        try {
            Thread.sleep(1500); // tempo para animação e renderização
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warning("Thread interrompida durante espera.");
        }
    }

    public void navegarPorTodosOsCardsComScroll() {
        clicar(CARD1_SCREEN, "FULLY OPEN SOURCE");
        scrollParaDireita();

        clicar(CARD2_SCREEN, "GREAT COMMUNITY");
        scrollParaDireita();

//        clicar(CARD3_SCREEN, "JS.FOUNDATION");
//        scrollParaDireita();

//        clicar(CARD4_SCREEN, "SUPPORT VIDEOS");
//        scrollParaDireita();
//
//        clicar(CARD5_SCREEN, "EXTENDABLE");
//        scrollParaDireita();
//
//        clicar(CARD6_SCREEN, "COMPATIBLE");
//        esperar();
    }

    public String obterMensagemSwipe() {
        logger.info("Aguardando mensagem de sucesso após swipe");
        String mensagem = wait.until(ExpectedConditions.visibilityOfElementLocated(SUCCESS_SWIPE)).getText();
        logger.info("Mensagem de swipe recebida: " + mensagem);
        return mensagem;
    }
}