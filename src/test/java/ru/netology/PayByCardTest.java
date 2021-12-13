package ru.netology;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.DataHelper;
import ru.netology.page.PayByCard;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class PayByCardTest {
    private PayByCard payByCard;

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUpPage() {
        open("http://localhost:8080/");
        $$(".button__text").first().click();
        payByCard = new PayByCard();
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldUseApprovedCard() {
        var infoValidHolder = DataHelper.Registration.getValidUser();
        payByCard.setUp(infoValidHolder);
        payByCard.successMessage();
    }

    @Test
    void shouldUseDeclinedCard() {
        var infoHolder = DataHelper.Registration.getDeclinedUser();
        payByCard.setUp(infoHolder);
        payByCard.errorMessage();
    }

    @Test
    void shouldEmptyUser() {
        var infoEmptyUser = DataHelper.Registration.getEmptyUser();
        payByCard.setUp(infoEmptyUser);
        payByCard.subtitles();
    }

    @Test
    void shouldUseAnyCardNumber() {
        var infoHolderAnyCardNumber = DataHelper.Registration.getAnyCardNumberUser();
        payByCard.setUp(infoHolderAnyCardNumber);
        payByCard.errorMessage();
    }

    @Test
    void shouldUseCardWithPartNumber() {
        var infoHolder = DataHelper.Registration.getPartCardNumber();
        payByCard.setUp(infoHolder);
        payByCard.subWrongFormat();
    }

    @Test
    void shouldUseMoreDigitsInCardNumber() {
        var cardNumber = DataHelper.getRandomCardNumber();
        var digit = DataHelper.getOneDigit();
        payByCard.setUpCardNumber(cardNumber, digit);
    }

    @Test
    void shouldUseCardNumberWithoutDigit() {
        var cardNumber = DataHelper.getSymbolString(16);
        var digit = DataHelper.getOneDigit();
        payByCard.setUpCardNumberSymbol(cardNumber, digit);
    }
}
