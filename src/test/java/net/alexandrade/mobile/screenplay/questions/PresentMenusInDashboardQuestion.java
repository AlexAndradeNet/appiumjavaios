package net.alexandrade.mobile.screenplay.questions;

import java.util.List;
import net.alexandrade.mobile.screenplay.ui.builder.MenuBuilder;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class PresentMenusInDashboardQuestion implements Question<List<String>> {
    @Override
    public List<String> answeredBy(Actor actor) {
        return MenuBuilder.getMenuListInUI(actor);
    }
}
