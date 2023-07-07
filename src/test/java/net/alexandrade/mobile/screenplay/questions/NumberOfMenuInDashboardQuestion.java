package net.alexandrade.mobile.screenplay.questions;

import net.alexandrade.mobile.screenplay.ui.DashboardPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class NumberOfMenuInDashboardQuestion implements Question<Integer> {

    @Override
    public Integer answeredBy(Actor actor) {
        return DashboardPage.MENU_ITEMS.resolveAllFor(actor).size();
    }
}
