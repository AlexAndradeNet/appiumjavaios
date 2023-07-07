package net.alexandrade.mobile.screenplay.ui;

import net.serenitybdd.screenplay.targets.Target;

public class HomePage {
    public static Target acceptButton =
            Target.the("Accept Button").locatedBy("//android.widget.Button[@text='ACCEPT']");
}
