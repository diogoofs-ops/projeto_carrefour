package br.com.carrefour.steps;

import br.com.carrefour.pages.LoginPage;
import br.com.carrefour.pages.SignUpPage;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.DriverFactory;
import utils.ScreenshotUtil;
import java.net.MalformedURLException;


import static utils.DriverFactory.driver;

public class SignUpSteps {


    LoginPage loginPage;
    SignUpPage signUpPage;


    @Given("que o usuario esteja na tela de Sign up")
    public void que_o_usuario_esteja_na_tela_de_Sign_up() throws MalformedURLException, InterruptedException {
        AppiumDriver driver = DriverFactory.setUp();
        signUpPage = new SignUpPage(driver);

        signUpPage.clicarLoginScreen();
        Thread.sleep(3000);
        signUpPage.clicarSignUpScreen();;
        ScreenshotUtil.captureScreenshot(driver, "tela_SignUplogin");
        Thread.sleep(2000);
    }

    @When("inserir email e password e confirm password")
    public void inserir_email_e_password_e_confirm_password() throws InterruptedException {
        signUpPage.preencherEmail("teste@teste.com");
        signUpPage.preencherSenha("123456789");
        signUpPage.confirmarSenha("123456789");
        Thread.sleep(2000);
        signUpPage.clicarSignUp();
        ScreenshotUtil.captureScreenshot(driver, "tela_signup");

    }

    @Then("devo validar sign up com sucesso")
    public void devo_validar_sign_up_com_sucesso(){
        signUpPage.clicarOkSignUp();
        loginPage = new LoginPage(driver);
        loginPage.fecharApp();

    }
}

