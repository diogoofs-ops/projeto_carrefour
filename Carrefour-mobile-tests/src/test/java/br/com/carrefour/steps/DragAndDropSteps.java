package br.com.carrefour.steps;

import br.com.carrefour.pages.DragAndDropPage;
import br.com.carrefour.pages.LoginPage;
import br.com.carrefour.pages.NavegarEntreTelasPage;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.junit.Assert;
import utils.DriverFactory;
import utils.LogUtil;
import utils.ScreenshotUtil;

import java.net.MalformedURLException;

import static utils.DriverFactory.driver;

public class DragAndDropSteps {


    LoginPage loginPage;
    NavegarEntreTelasPage navegarEntreTelasPage;
    DragAndDropPage dragAndDropPage;
    Response response;

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

        // ✅ Verifica se a mensagem está presente
        if (mensagem == null || !mensagem.contains("Drag and Drop")) {
            Assert.fail("❌ A mensagem 'Drag and Drop' não foi exibida na tela.");
        }

        // ✅ Se passou, confirma sucesso
        Assert.assertTrue("✅ A mensagem esperada 'Drag and Drop' foi encontrada com sucesso.",
                mensagem.contains("Drag and Drop"));

        ScreenshotUtil.captureScreenshot(driver, "tela_sucesso");

        LogUtil.salvarLog("resultado.txt", "Login realizado via interface.");

        loginPage.fecharApp();
    }
}

