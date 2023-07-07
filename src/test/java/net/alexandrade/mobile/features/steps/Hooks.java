/** */
package net.alexandrade.mobile.features.steps;

import static net.alexandrade.mobile.screenplay.ui.HomePage.acceptButton;
import static net.serenitybdd.screenplay.actors.OnStage.theActorCalled;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.thucydides.core.webdriver.ThucydidesWebDriverSupport.getDriver;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.thucydides.core.annotations.Managed;
import net.thucydides.core.webdriver.WebDriverFacade;
import org.openqa.selenium.WebDriver;

public class Hooks {
    @Managed(driver = "Appium")
    public WebDriver hisMobileDevice;

    String jacob = "Trader";

    @Before(order = 1)
    public void setTheStage() {
        OnStage.setTheStage(new OnlineCast());
    }

    @Given("Jacob is in the Home Page")
    public void theActorIsInTheHomePage() {
        theActorCalled(jacob).can(BrowseTheWeb.with(hisMobileDevice));
        theActorInTheSpotlight().attemptsTo(Click.on(acceptButton), Click.on(acceptButton));
    }

    @After
    public void afterAll() {
        ((AndroidDriver) ((WebDriverFacade) getDriver()).getProxiedDriver()).resetApp();
    }
}
