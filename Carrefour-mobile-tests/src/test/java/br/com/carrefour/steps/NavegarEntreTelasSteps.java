package br.com.carrefour.steps;

import br.com.carrefour.pages.LoginPage;
import br.com.carrefour.pages.NavegarEntreTelasPage;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
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

public class NavegarEntreTelasSteps {
    LoginPage loginPage;
    NavegarEntreTelasPage navegarEntreTelasPage;
    Response response;


    @Given("que o usuario esteja na Home do app")
    public void que_o_usuario_esteja_na_Home_do_app() throws MalformedURLException {
        AppiumDriver driver = DriverFactory.setUp();
        navegarEntreTelasPage = new NavegarEntreTelasPage(driver);
        navegarEntreTelasPage.clicarNaHome();
        ScreenshotUtil.captureScreenshot(driver, "tela_home");
    }

    @When("passar pelas telas de Webview e Login")
    public void passar_pelas_telas_de_Webview_e_Login() throws InterruptedException {
        Thread.sleep(2000);
        navegarEntreTelasPage.clicarNaWebView();
        navegarEntreTelasPage.clicarNoLogin();
        ScreenshotUtil.captureScreenshot(driver, "tela_login");
    }

    @And("pelas telas de Forms e Swipe")
    public void pelas_telas_de_Forms_e_Swipe() throws InterruptedException {
        Thread.sleep(2000);
        navegarEntreTelasPage.clicarNoForms();
        navegarEntreTelasPage.clicarNoSwipe();
        ScreenshotUtil.captureScreenshot(driver, "tela_swipe");
    }

    @Then("devo validar a navegacao pela tela de Drag")
    public void devo_validar_a_navegacao_pela_tela_de_Drag() throws InterruptedException {
        Thread.sleep(2000);
        navegarEntreTelasPage.clicarNoDrag();
        Thread.sleep(2000);

        // ✅ Validação com mensagem de erro clara
        if (!navegarEntreTelasPage.isTelaDragVisivel()) {
            Assert.fail("❌ A tela de Drag não foi exibida após a navegação.");
        }

        Assert.assertTrue("✅ Tela de Drag foi exibida após a navegação.", navegarEntreTelasPage.isTelaDragVisivel());

        ScreenshotUtil.captureScreenshot(driver, "tela_drag_navegada");
        LogUtil.salvarLog("resultado.txt", "Login realizado via interface.");

        loginPage = new LoginPage(driver);
        loginPage.fecharApp();
    }
}