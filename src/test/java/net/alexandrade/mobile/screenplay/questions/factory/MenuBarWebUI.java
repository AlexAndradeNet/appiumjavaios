package net.alexandrade.mobile.screenplay.questions.factory;

import java.util.List;
import net.alexandrade.mobile.screenplay.questions.NumberOfMenuInDashboardQuestion;
import net.alexandrade.mobile.screenplay.questions.PresentMenusInDashboardQuestion;
import net.serenitybdd.screenplay.Question;

public class MenuBarWebUI {
    public static Question<Integer> numberOfItems() {
        return new NumberOfMenuInDashboardQuestion();
    }

    public static Question<List<String>> displayedWithStrictOrder() {
        return new PresentMenusInDashboardQuestion();
    }
}
