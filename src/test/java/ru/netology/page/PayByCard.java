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
    private ElementsCollection subs = $$(".input__sub");

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

    public void errorMessage() {
        message.last().shouldBe(visible, Duration.ofSeconds(15));
        message.last().shouldHave(text("Ошибка! Банк отказал в проведении операции."));
        $$(".notification__closer").last().click();
        message.last().shouldBe(hidden);
        message.first().shouldBe(hidden);
    }

    public void subtitles() {
        subs.first().shouldBe(visible).shouldHave(text("Неверный формат"));
        subs.get(1).shouldBe(visible).shouldHave(text("Неверный формат"));
        subs.get(2).shouldBe(visible).shouldHave(text("Неверный формат"));
        subs.get(3).shouldBe(visible).shouldHave(text("Поле обязательно для заполнения"));
        subs.last().shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    public void subWrongFormat() {
        subs.first().shouldBe(visible).shouldHave(text("Неверный формат"));
    }

    public void setUpCardNumber(String number, String digit) {
        cardNumber.setValue(number + digit);
        cardNumber.shouldHave(value(number));
    }

    public void setUpCardNumberSymbol(String number, String digit) {
        cardNumber.setValue(number + digit);
        cardNumber.shouldHave(value(digit));
    }
}
