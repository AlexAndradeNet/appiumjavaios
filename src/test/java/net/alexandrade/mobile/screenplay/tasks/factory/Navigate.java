package net.alexandrade.mobile.screenplay.tasks.factory;

import net.alexandrade.mobile.screenplay.tasks.AccessTask;
import net.alexandrade.mobile.screenplay.tasks.ShowMenuPanelTask;
import net.serenitybdd.screenplay.Task;

public class Navigate {

    public static Task menuPanel() {
        return new ShowMenuPanelTask();
    }

    public static Task menuWithLabel(String label) {
        return new AccessTask(label);
    }
}
