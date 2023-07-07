package net.alexandrade.mobile.screenplay.tasks.factory;

import net.alexandrade.mobile.screenplay.tasks.AccountProfileTask;
import net.alexandrade.mobile.screenplay.tasks.DeleteAccountTask;
import net.serenitybdd.screenplay.Task;

public class GoTo {
    public static Task accountProfile() {
        return new AccountProfileTask();
    }

    public static Task guestProfile() {
        return new DeleteAccountTask();
    }
}
