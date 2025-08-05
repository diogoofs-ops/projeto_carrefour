package br.com.carrefour.steps;

import br.com.carrefour.pages.LoginPage;
import br.com.carrefour.pages.NavegarEntreTelasPage;
import br.com.carrefour.pages.WebviewPage;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.bouncycastle.cms.jcajce.JceKEKAuthenticatedRecipient;
import org.junit.Assert;
import utils.DriverFactory;
import utils.LogUtil;
import utils.ScreenshotUtil;

import java.net.MalformedURLException;

import static utils.DriverFactory.driver;

public class WebviewSteps {


    LoginPage loginPage;
    WebviewPage webviewPage;
    NavegarEntreTelasPage navegarEntreTelasPage;


    @Given("que o usuario esteja na tela de Webview")
    public void que_o_usuario_esteja_na_tela_de_Webview() throws MalformedURLException {
        AppiumDriver driver = DriverFactory.setUp();
        loginPage = new LoginPage(driver);
        navegarEntreTelasPage = new NavegarEntreTelasPage(driver);
        webviewPage = new WebviewPage(driver);

        navegarEntreTelasPage.clicarNaWebView();
        ScreenshotUtil.captureScreenshot(driver, "tela_webview");
    }

    @When("realizar um get started no webview")
    public void realizar_um_get_started_no_webview() throws InterruptedException {
        Thread.sleep(10000);
        webviewPage.clicarNoClose();
        Thread.sleep(10000);
        webviewPage.clicarNoGetStarted();
        Thread.sleep(10000);
    }

    @Then("devo validar campo com sucesso")
    public void devo_validar_campo_com_sucesso() {
        String mensagem = webviewPage.validarTextoInformado();

        if (mensagem == null || !mensagem.contains("Getting Started")) {
            Assert.fail("❌ O texto esperado 'Getting Started' não foi encontrado. Texto recebido: '" + mensagem + "'");
        }

        Assert.assertTrue("✅ O texto 'Getting Started' foi validado com sucesso.", mensagem.contains("Getting Started"));

        ScreenshotUtil.captureScreenshot(driver, "tela_sucesso");
        LogUtil.salvarLog("resultado.txt", "Login realizado via interface.");

        loginPage = new LoginPage(driver);
        loginPage.fecharApp();
    }
}
