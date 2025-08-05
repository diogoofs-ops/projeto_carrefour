package br.com.carrefour.steps;

import br.com.carrefour.pages.FormsDropdownWebDriverPage;
import br.com.carrefour.pages.FormsDropdownAppiumPage;
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
import java.util.logging.Logger;

import static utils.DriverFactory.driver;

public class FormsDropdownAppiumSteps {


    LoginPage loginPage;
    NavegarEntreTelasPage navegarEntreTelasPage;
    FormsDropdownAppiumPage formsDropdownAppiumPage;
    FormsDropdownWebDriverPage formsDropdownWebDriverPage;
    Response response;

    @Given("que o usuario esteja na tela de Forms Appium")
    public void que_o_usuario_esteja_na_tela_de_Forms_Appium() throws MalformedURLException, InterruptedException {

        AppiumDriver driver = DriverFactory.setUp();
        loginPage = new LoginPage(driver);
        navegarEntreTelasPage = new NavegarEntreTelasPage(driver);
        navegarEntreTelasPage.clicarNoForms();
        Thread.sleep(5000);
    }

    @When("preencher o Input field do formulario Appium")
    public void preencher_o_Input_field_do_formulario_Appium() throws InterruptedException {

        this.formsDropdownAppiumPage = new FormsDropdownAppiumPage(driver);
        Thread.sleep(5000);
        formsDropdownAppiumPage.clicarNInputField();
        Thread.sleep(2000);
        formsDropdownAppiumPage.informarUmTextoNoInputField("12345");
    }

    @And("Swtich como off Appium")
    public void Swtich_como_off_Appium() {
        formsDropdownAppiumPage.clicarOcultarTeclado();
        formsDropdownAppiumPage.clicarNoSwitchOff();
    }

    @And("preencher o Dropdown como Appium is awesome")
    public void preencher_o_Dropdown_como_Appium_is_awesome() {
        formsDropdownAppiumPage.clicarNoDropdown();
        formsDropdownAppiumPage.clicarNoDropdownAppiumIsAwesome();
        ScreenshotUtil.captureScreenshot(driver, "tela_dropdown");

    }

    @Then("devo validar o botao active com OK para esse menu Appium")
    public void devo_validar_o_botao_active_com_OK_para_esse_menu_Appium() throws InterruptedException {
        formsDropdownAppiumPage.clicarNoActive();

        // ✅ Verifica se a confirmação foi exibida
        if (!formsDropdownAppiumPage.isConfirmacaoOkExibida()) {
            Assert.fail("❌ A confirmação 'OK' não foi exibida após clicar no botão Active.");
        }

        Assert.assertTrue("✅ Confirmação após clicar em OK foi exibida com sucesso", formsDropdownAppiumPage.isConfirmacaoOkExibida());

        formsDropdownAppiumPage.clicarNoOk();

        Thread.sleep(2000);

        LogUtil.salvarLog("resultado.txt", "Login realizado via interface.");
        loginPage = new LoginPage(driver);
        loginPage.fecharApp();
    }
}