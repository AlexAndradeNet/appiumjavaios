package net.alexandrade.mobile.screenplay.questions;

import net.alexandrade.mobile.screenplay.ui.AccountPage;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.converters.converters.StringConverter;

public class SelectedAccountQuestion implements Question<Boolean> {

    @Override
    public Boolean answeredBy(Actor actor) {
        new StringConverter().convert("true");
        return !AccountPage.SELECTED_ACCOUNT.resolveFor(actor).isVisible();
    }

    public static Question<Boolean> disappear() {
        return new SelectedAccountQuestion();
    }
}
