package ru.netology.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import ru.netology.data.DataHelper;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PayByCard {
    private SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private SelenideElement monthField = $("[placeholder='08']");
    private SelenideElement yearField = $("[placeholder='22']");;
    private SelenideElement holderField = $(byXpath("//span[text()='Владелец']/..//input"));
    private SelenideElement cvcField = $("[placeholder='999']");;
    private SelenideElement buttonCont = $$("button").find(text("Продолжить"));
    private SelenideElement messageSuccess = $(".icon_name_ok");
    private SelenideElement messageError = $(".icon_name_error");
//    private ElementsCollection message = $$(".notification__content");
    private ElementsCollection subs = $$(".input__sub");

    public PayByCard() {
        $$("h3").find(text("Оплата по карте")).shouldBe(visible);
    }

    public void setUp(DataHelper.AuthInfo info) {
        cardNumberField.setValue(info.getNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        holderField.setValue(info.getHolder());
        cvcField.setValue(info.getCvc());
        buttonCont.click();
    }

    public void successMessage() {
        messageSuccess.shouldBe(visible, Duration.ofSeconds(15));
        $$(".notification__closer").first().click();
        messageSuccess.shouldBe(hidden);
        messageError.shouldBe(hidden);
    }

    public void errorMessage() {
        messageError.shouldBe(visible, Duration.ofSeconds(15));
        $$(".notification__closer").last().click();
        messageError.shouldBe(hidden);
        messageSuccess.shouldBe(hidden);
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
        cardNumberField.setValue(number + digit);
        cardNumberField.shouldHave(value(number));
    }

    public void setUpCardNumberSymbol(String number, String digit) {
        cardNumberField.setValue(number + digit);
        cardNumberField.shouldHave(value(digit));
    }
}
