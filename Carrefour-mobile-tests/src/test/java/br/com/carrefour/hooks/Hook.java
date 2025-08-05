package br.com.carrefour.hooks;

import br.com.carrefour.pages.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;

import static utils.DriverFactory.driver;

public class Hook {

    private LoginPage loginPage;

    @After
    public void fecharAplicativo(Scenario scenario) {
        if (driver != null) {
            loginPage = new LoginPage(driver);
            loginPage.fecharApp();
            System.out.println("🔒 App encerrado após o cenário: " + scenario.getName());
        }
    }
}
