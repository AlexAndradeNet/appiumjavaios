package net.alexandrade.mobile.screenplay.driver;

import io.appium.java_client.ios.IOSDriver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.webdriver.WebDriverFacade;

public class IOSDriverObject {

    public void hideKeyboard(Actor actor) {
        IOSDriver(actor).hideKeyboard();
    }

    @SuppressWarnings("unchecked")
    private IOSDriver IOSDriver(Actor actor) {
        return (IOSDriver) ((WebDriverFacade) getDriver(actor)).getProxiedDriver();
    }

    private static WebDriverFacade getDriver(Actor actor) {
        return ((WebDriverFacade) BrowseTheWeb.as(actor).getDriver());
    }
}
