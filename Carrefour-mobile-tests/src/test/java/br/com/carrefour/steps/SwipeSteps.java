package br.com.carrefour.steps;

import br.com.carrefour.pages.LoginPage;
import br.com.carrefour.pages.NavegarEntreTelasPage;
import br.com.carrefour.pages.SwipePage;
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
import java.util.logging.Logger;

public class SwipeSteps {


    private AppiumDriver driver;
    private LoginPage loginPage;
    private NavegarEntreTelasPage navegarEntreTelasPage;
    private SwipePage swipePage;
    Response response;

    @Given("que o usuario esteja na tela de Swipe")
    public void que_o_usuario_esteja_na_tela_de_Swipe() throws MalformedURLException {

        DriverFactory.setUp();
        driver = DriverFactory.driver;

        loginPage = new LoginPage(driver);
        navegarEntreTelasPage = new NavegarEntreTelasPage(driver);
        swipePage = new SwipePage(driver);
        navegarEntreTelasPage.clicarNoSwipe();
    }

    @When("fizer o Swipe horizontal dentro do app")
    public void fizer_o_Swipe_horizontal_dentro_do_app() {

        swipePage.navegarPorTodosOsCardsComScroll();
        ScreenshotUtil.captureScreenshot(driver, "tela_swipe");
    }

    @Then("devo validar Swipe com sucesso")
    public void devo_validar_Swipe_com_sucesso() {
        String mensagem = swipePage.obterMensagemSwipe();

        // ✅ Validação com mensagem de erro clara
        if (mensagem == null || !mensagem.contains("GREAT COMMUNITY")) {
            Assert.fail("❌ A mensagem esperada 'GREAT COMMUNITY' não foi exibida após o swipe. Mensagem recebida: '" + mensagem + "'");
        }

        Assert.assertTrue("✅ Mensagem de swipe correspondente ao esperado.", mensagem.contains("GREAT COMMUNITY"));

        ScreenshotUtil.captureScreenshot(driver, "tela_sucesso");
        LogUtil.salvarLog("resultado.txt", "Login realizado via interface.");
        loginPage.fecharApp();
    }
}