package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.MainPage;
import ru.netology.page.PayByCard;

import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

public class PayByCardTest {
    private MainPage mainPage = new MainPage();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUpPage() {
        open("http://localhost:8080/");
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldUseApprovedCard() {
        var payByCard = mainPage.payByCard();
        var infoValidHolder = DataHelper.Registration.getValidUser();
        payByCard.setUp(infoValidHolder);
        payByCard.successMessage();
    }

    @Test
    void shouldUseDeclinedCard() {
        var payByCard = mainPage.payByCard();
        var infoHolder = DataHelper.Registration.getDeclinedUser();
        payByCard.setUp(infoHolder);
        payByCard.errorMessage();
    }

    @Test
    void shouldEmptyUser() {
        var payByCard = mainPage.payByCard();
        var infoEmptyUser = DataHelper.Registration.getEmptyUser();
        payByCard.setUp(infoEmptyUser);
        payByCard.subtitles();
    }

    @Test
    void shouldUseAnyCardNumber() {
        var payByCard = mainPage.payByCard();
        var infoHolderAnyCardNumber = DataHelper.Registration.getAnyCardNumberUser();
        payByCard.setUp(infoHolderAnyCardNumber);
        payByCard.errorMessage();
    }

    @Test
    void shouldUseCardWithPartNumber() {
        var payByCard = mainPage.payByCard();
        var infoHolder = DataHelper.Registration.getPartCardNumber();
        payByCard.setUp(infoHolder);
        payByCard.subWrongFormat();
    }

    @Test
    void shouldUseMoreDigitsInCardNumber() {
        var payByCard = mainPage.payByCard();
        var cardNumber = DataHelper.getRandomCardNumber();
        var digit = DataHelper.getOneDigit();
        payByCard.setUpCardNumber(cardNumber, digit);
    }

    @Test
    void shouldUseCardNumberWithoutDigit() {
        var payByCard = mainPage.payByCard();
        var cardNumber = DataHelper.getSymbolString(16);
        var digit = DataHelper.getOneDigit();
        payByCard.setUpCardNumberSymbol(cardNumber, digit);
    }
}
