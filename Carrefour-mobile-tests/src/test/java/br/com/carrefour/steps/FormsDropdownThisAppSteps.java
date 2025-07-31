package br.com.carrefour.steps;

import br.com.carrefour.pages.*;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.DriverFactory;
import utils.ScreenshotUtil;

import java.net.MalformedURLException;
import java.util.logging.Logger;

import static utils.DriverFactory.driver;

public class FormsDropdownThisAppSteps {


    LoginPage loginPage;
    NavegarEntreTelasPage navegarEntreTelasPage;
    FormsDropdownAppiumPage formsDropdownAppiumPage;
    FormsDropdownThisAppPage formsDropdownThisAppPage;

    @Given("que o usuario esteja na tela de Forms this app")
    public void que_o_usuario_esteja_na_tela_de_Forms_this_app() throws MalformedURLException {
        AppiumDriver driver = DriverFactory.setUp();
        loginPage = new LoginPage(driver);
        navegarEntreTelasPage = new NavegarEntreTelasPage(driver);
        formsDropdownAppiumPage = new FormsDropdownAppiumPage(driver);
        formsDropdownThisAppPage = new FormsDropdownThisAppPage(driver);

        navegarEntreTelasPage.clicarNoForms();
        ScreenshotUtil.captureScreenshot(driver, "tela_forms");
    }

    @When("preencher o Input field do formulario this app")
    public void preencher_o_Input_field_do_formulario_this_app() throws InterruptedException {
        this.formsDropdownAppiumPage = new FormsDropdownAppiumPage(driver);
        Thread.sleep(5000);
        formsDropdownAppiumPage.clicarNInputField();
        Thread.sleep(2000);
        formsDropdownAppiumPage.informarUmTextoNoInputField("12345");
    }

    @And("Swtich como off this app")
    public void Swtich_como_off_this_app() {
        formsDropdownAppiumPage.clicarOcultarTeclado();
        formsDropdownAppiumPage.clicarNoSwitchOff();
    }
    @And("preencher o Dropdown como This app is awesome")
    public void preencher_o_Dropdown_como_This_app_is_awesome() {
        formsDropdownAppiumPage.clicarNoDropdown();
        formsDropdownThisAppPage.clicarNoDropdownThisAppIsAwesome();
        ScreenshotUtil.captureScreenshot(driver, "tela_dropdown");

    }
    @Then("devo validar o botao active com OK para esse menu this app")
    public void devo_validar_o_botao_active_com_OK_para_esse_menu_this_app() throws InterruptedException {

        formsDropdownAppiumPage.clicarNoActive();
        formsDropdownAppiumPage.clicarNoOk();
        Thread.sleep(2000);
        loginPage = new LoginPage(driver);
        loginPage.fecharApp();

    }
}
