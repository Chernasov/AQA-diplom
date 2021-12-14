package ru.netology.page;

import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    public PayByCard payByCard () {
        $$(".button__text").first().click();
        return new PayByCard();
    }
}
