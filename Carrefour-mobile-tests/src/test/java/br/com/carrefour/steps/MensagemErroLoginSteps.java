package br.com.carrefour.steps;

import br.com.carrefour.pages.LoginPage;
import br.com.carrefour.pages.MensagemErroLoginPage;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import utils.DriverFactory;
import utils.ScreenshotUtil;

import java.net.MalformedURLException;

public class MensagemErroLoginSteps {

    private AppiumDriver driver;
    private LoginPage loginPage;
    private MensagemErroLoginPage mensagemErroLoginPage;

    @Given("que o usuario incorreto esteja na tela de login")
    public void que_o_usuario_incorreto_esteja_na_tela_de_login() throws MalformedURLException {
        driver = DriverFactory.setUp();
        loginPage = new LoginPage(driver);
        mensagemErroLoginPage = new MensagemErroLoginPage(driver);

        loginPage.loginScreen();
        ScreenshotUtil.captureScreenshot(driver, "tela_login");
    }

    @When("inserir email incorreto na tela de login")
    public void inserir_email_incorreto_na_tela_de_login() {
        loginPage.preencherEmail("teste@teste");
        loginPage.clicarLogin();
        ScreenshotUtil.captureScreenshot(driver, "preencher_dados");
    }

    @Then("devo validar mensagem de erro para login")
    public void devo_validar_mensagem_de_erro_para_login() {
        String mensagem = mensagemErroLoginPage.obterMensagemDeErroLogin();
        Assert.assertTrue("Mensagem de erro não corresponde ao esperado",
                mensagem.contains("Please enter a valid email address"));

        ScreenshotUtil.captureScreenshot(driver, "tela_mensagem_erro");
        loginPage.fecharApp();
    }
}