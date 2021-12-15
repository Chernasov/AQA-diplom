package ru.netology.page;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;

public class BuyOnCredit {
    public BuyOnCredit() {
        $$("h3").find(text("Кредит по данным карты")).shouldBe(visible);
    }
}
