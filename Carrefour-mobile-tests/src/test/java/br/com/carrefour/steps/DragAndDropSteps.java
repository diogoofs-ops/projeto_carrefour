package br.com.carrefour.steps;

import br.com.carrefour.pages.DragAndDropPage;
import br.com.carrefour.pages.LoginPage;
import br.com.carrefour.pages.NavegarEntreTelasPage;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.DriverFactory;
import utils.ScreenshotUtil;

import java.net.MalformedURLException;
import java.util.logging.Logger;

import static utils.DriverFactory.driver;

public class DragAndDropSteps {


    LoginPage loginPage;
    NavegarEntreTelasPage navegarEntreTelasPage;
    DragAndDropPage dragAndDropPage;

    @Given("que o usuario esteja na tela do Drag")
    public void que_o_usuario_esteja_na_tela_do_Drag() throws MalformedURLException {
        AppiumDriver driver = DriverFactory.setUp();
        loginPage = new LoginPage(driver);
        navegarEntreTelasPage = new NavegarEntreTelasPage(driver);
        dragAndDropPage = new DragAndDropPage(driver);

        navegarEntreTelasPage.clicarNoDrag();
        ScreenshotUtil.captureScreenshot(driver, "tela_drag");

    }

    @When("fizer o Drag and Drop")
    public void fizer_o_Drag_and_Drop() {
        dragAndDropPage.clicarDropI2();
        dragAndDropPage.clicarDragI2();
        dragAndDropPage.realizarDragAndDrop();
        ScreenshotUtil.captureScreenshot(driver, "tela_drop");

    }

    @Then("devo validar Drag com sucesso")
    public void devo_validar_Drag_com_sucesso() {

        String mensagem = dragAndDropPage.obterMensagemDrag();
        Assert.assertTrue(mensagem.contains("Drag and Drop"));
        ScreenshotUtil.captureScreenshot(driver, "tela_sucesso");

        loginPage.fecharApp();
    }
}

