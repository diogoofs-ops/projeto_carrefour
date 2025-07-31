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
import utils.DriverFactory;
import utils.ScreenshotUtil;

import java.net.MalformedURLException;

import static utils.DriverFactory.driver;

public class FormsDropdownWebDriverSteps {
    LoginPage loginPage;
    NavegarEntreTelasPage navegarEntreTelasPage;
    FormsDropdownAppiumPage formsDropdownAppiumPage;
    FormsDropdownWebDriverPage formsDropdownWebDriverPage;

    @Given("que o usuario esteja na tela de Forms webDriver")
    public void que_o_usuario_esteja_na_tela_de_Forms_webDriver() throws MalformedURLException, InterruptedException {
        AppiumDriver driver = DriverFactory.setUp();
        formsDropdownWebDriverPage = new FormsDropdownWebDriverPage(driver);
        navegarEntreTelasPage = new NavegarEntreTelasPage(driver);
        navegarEntreTelasPage.clicarNoForms();
        Thread.sleep(5000);
    }

    @When("preencher o Input field do formulario webDriver")
    public void preencher_o_Input_field_do_formulario_webDriver() throws InterruptedException {
        this.formsDropdownAppiumPage = new FormsDropdownAppiumPage(driver);
        Thread.sleep(5000);
        formsDropdownAppiumPage.clicarNInputField();
        Thread.sleep(2000);
        formsDropdownAppiumPage.informarUmTextoNoInputField("12345");
    }

    @And("Swtich como off webDriver")
    public void Swtich_como_off_webDriver() {
        formsDropdownAppiumPage.clicarOcultarTeclado();
        formsDropdownAppiumPage.clicarNoSwitchOff();
    }

    @And("preencher o Dropdown como webdriver.io is awesome")
    public void preencher_o_Dropdown_como_webdriver_io_is_awesome() {
        formsDropdownAppiumPage.clicarNoDropdown();
        formsDropdownWebDriverPage.clicarNoDropdownWebdriverIoIsAwesome();
        ScreenshotUtil.captureScreenshot(driver, "tela_dropdown");
    }
    @Then("devo validar o botao active com OK para esse menu webDriver")
    public void devo_validar_o_botao_active_com_OK_para_esse_menu_webDriver() throws InterruptedException {

        formsDropdownAppiumPage.clicarNoActive();
        formsDropdownAppiumPage.clicarNoOk();
        Thread.sleep(2000);
        loginPage = new LoginPage(driver);
        loginPage.fecharApp();
    }
}

