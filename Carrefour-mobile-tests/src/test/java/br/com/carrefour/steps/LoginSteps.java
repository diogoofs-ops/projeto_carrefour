package br.com.carrefour.steps;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.*;
import br.com.carrefour.pages.LoginPage;
import org.junit.Assert;
import utils.DriverFactory;
import utils.LogUtil;
import utils.ScreenshotUtil;

import java.net.MalformedURLException;

import io.restassured.response.Response;


import static utils.DriverFactory.driver;

public class LoginSteps {


    LoginPage loginPage;
    Response response;


    @Given("que o usuario esteja na tela de login")
    public void que_o_usuario_esteja_na_tela_de_login() throws MalformedURLException {
        AppiumDriver driver = DriverFactory.setUp();
        loginPage = new LoginPage(driver);
        loginPage.loginScreen();
        ScreenshotUtil.captureScreenshot(driver, "tela_login");

    }

    @When("inserir email e password")
    public void inserir_email_e_password() {
        loginPage.preencherEmail("teste@teste.com");
        loginPage.preencherSenha("@Brasil2025");
        loginPage.clicarLogin();
        ScreenshotUtil.captureScreenshot(driver, "preencher_dados");

    }

    @Then("devera ser validado com sucesso")
    public void devera_ser_validado_com_sucesso() {
        boolean sucesso = loginPage.isLoginBemSucedido();

        // ✅ Captura a tela antes da validação
        ScreenshotUtil.captureScreenshot(driver, "login_validado");

        // ✅ Validação com mensagem de erro clara
        if (!sucesso) {
            Assert.fail("❌ Falha na validação de login. A tela de sucesso não foi exibida.");
        }

        Assert.assertTrue("✅ Login foi validado com sucesso", sucesso);

        LogUtil.salvarLog("resultado.txt", "Login realizado via interface.");
        loginPage.clicarOk();
        loginPage.fecharApp();
    }
}

