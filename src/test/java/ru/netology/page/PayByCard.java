package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.domain.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$$;

public class PayByCard {
    private SelenideElement cardNumber = $$(".input__control").first();
    private SelenideElement month = $$(".input__control").get(1);
    private SelenideElement year = $$(".input__control").get(2);
    private SelenideElement holder = $$(".input__control").get(3);
    private SelenideElement cvc = $$(".input__control").last();
    private SelenideElement buttonCont = $$("button").find(text("Продолжить"));
    private ElementsCollection message = $$(".notification__content");

    public PayByCard() {
        $$("h3").find(text("Оплата по карте")).shouldBe(visible);
    }

    public void setUp(DataHelper.AuthInfo info) {
        cardNumber.setValue(info.getNumber());
        month.setValue(info.getMonth());
        year.setValue(info.getYear());
        holder.setValue(info.getHolder());
        cvc.setValue(info.getCvc());
        buttonCont.click();
    }

    public void successMessage() {
        message.first().shouldBe(visible, Duration.ofSeconds(15));
        message.first().shouldHave(text("Операция одобрена Банком."));
        $$(".notification__closer").first().click();
        message.first().shouldBe(hidden);
        message.last().shouldBe(hidden);
    }


}
