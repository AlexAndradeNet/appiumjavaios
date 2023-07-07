package net.alexandrade.mobile.screenplay.interactions;

import static net.alexandrade.mobile.screenplay.ui.DashboardPage.MAIN_NAVIGATE_BUTTON;
import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isCurrentlyEnabled;

import net.alexandrade.mobile.screenplay.ui.DashboardPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntilTargetIsReady;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.NoSuchElementException;

public class MenuBoardAction implements Interaction {
    @Override
    @Step("{0} show the menu dashboard")
    public <T extends Actor> void performAs(T actor) {
        try {
            actor.attemptsTo(
                    new WaitUntilTargetIsReady(DashboardPage.MAIN_NAVIGATE_BUTTON, isCurrentlyEnabled())
                            .forNoMoreThan(60)
                            .seconds(),
                    Click.on(MAIN_NAVIGATE_BUTTON));
        } catch (NoSuchElementException e) {
            actor.attemptsTo(Click.on(MAIN_NAVIGATE_BUTTON));
        }
    }

    public static MenuBoardAction show() {
        return instrumented(MenuBoardAction.class);
    }
}
