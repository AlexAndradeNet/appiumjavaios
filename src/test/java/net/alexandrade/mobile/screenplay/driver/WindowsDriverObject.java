package net.alexandrade.mobile.screenplay.driver;

import io.appium.java_client.windows.WindowsDriver;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.thucydides.core.webdriver.WebDriverFacade;

public class WindowsDriverObject {

    /* public void click(Actor actor) {
    	WinDriver(actor).findElementByAccessibilityId("AddAlarmButton").click();
    } */

    @SuppressWarnings("unchecked")
    private WindowsDriver WinDriver(Actor actor) {
        return (WindowsDriver) ((WebDriverFacade) getDriver(actor)).getProxiedDriver();
    }

    private static WebDriverFacade getDriver(Actor actor) {
        return ((WebDriverFacade) BrowseTheWeb.as(actor).getDriver());
    }
}
