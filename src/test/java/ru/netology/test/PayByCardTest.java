package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.MainPage;

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
        var infoHolderDeclinedCard = DataHelper.Registration.getDeclinedUser();
        payByCard.setUp(infoHolderDeclinedCard);
        payByCard.errorMessage();
    }

    @Test
    void shouldUseEmptyCardNumber() {
        var payByCard = mainPage.payByCard();
        var infoEmptyCardNumber = DataHelper.Registration.getEmptyCardNumber();
        payByCard.setUp(infoEmptyCardNumber);
        payByCard.subWrongFormat();
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
        payByCard.setUpCardNumberField(cardNumber, digit);
    }

    @Test
    void shouldUseCardNumberWithoutDigit() {
        var payByCard = mainPage.payByCard();
        var cardNumber = DataHelper.getSymbolString(16);
        var digit = DataHelper.getOneDigit();
        payByCard.setUpCardNumberFieldWithSymbol(cardNumber, digit);
    }

    @Test
    void shouldUseMonthDoubleZero() {
        var payByCard = mainPage.payByCard();
        var infoHolderDoubleZeroMonth = DataHelper.Registration.getMonthDoubleZeroCard();
        payByCard.setUp(infoHolderDoubleZeroMonth);
        payByCard.subWrongPeriod();
    }

    @Test
    void shouldUseMonthOver() {
        var payByCard = mainPage.payByCard();
        var infoHolder13Month = DataHelper.Registration.getMonthOverCard();
        payByCard.setUp(infoHolder13Month);
        payByCard.subWrongPeriod();
    }

    @Test
    void shouldUseEmptyMonthField() {
        var payByCard = mainPage.payByCard();
        var infoEmptyMonthField = DataHelper.Registration.getEmptyMonthFieldCard();
        payByCard.setUp(infoEmptyMonthField);
        payByCard.subWrongFormat();
    }

    @Test
    void shouldUseCardWithOneDigitMonth() {
        var payByCard = mainPage.payByCard();
        var infoHolderOneDigitMonth = DataHelper.Registration.getOneDigitMonthCard();
        payByCard.setUp(infoHolderOneDigitMonth);
        payByCard.subWrongFormat();
    }

    @Test
    void shouldUseMoreDigitsInMonth() {
        var payByCard = mainPage.payByCard();
        var month = DataHelper.getMonth();
        var digit = DataHelper.getOneDigit();
        payByCard.setUpMonthField(month, digit);
    }

    @Test
    void shouldUseMonthWithoutDigit() {
        var payByCard = mainPage.payByCard();
        var month = DataHelper.getSymbolString(2);
        var digit = DataHelper.getOneDigit();
        payByCard.setUpMonthFieldWithSymbol(month, digit);
    }

    @Test
    void shouldUsePastYear() {
        var payByCard = mainPage.payByCard();
        var infoHolderPastYear = DataHelper.Registration.getPastYearCard();
        payByCard.setUp(infoHolderPastYear);
        payByCard.subExpired();
    }

    @Test
    void shouldUseFutureYearOver() {
        var payByCard = mainPage.payByCard();
        var infoHolderFutureYear = DataHelper.Registration.getFutureYearOverCard();
        payByCard.setUp(infoHolderFutureYear);
        payByCard.subWrongPeriod();
    }

    @Test
    void shouldUseEmptyYearField() {
        var payByCard = mainPage.payByCard();
        var infoEmptyYearField = DataHelper.Registration.getEmptyYearFieldCard();
        payByCard.setUp(infoEmptyYearField);
        payByCard.subWrongFormat();
    }

    @Test
    void shouldUseCardWithOneDigitYear() {
        var payByCard = mainPage.payByCard();
        var infoHolderOneDigitYear = DataHelper.Registration.getOneDigitYearCard();
        payByCard.setUp(infoHolderOneDigitYear);
        payByCard.subWrongFormat();
    }

    @Test
    void shouldUseMoreDigitsInYear() {
        var payByCard = mainPage.payByCard();
        var year = DataHelper.getYearFutureInPeriod();
        var digit = DataHelper.getOneDigit();
        payByCard.setUpYearField(year, digit);
    }

    @Test
    void shouldUseYearWithoutDigit() {
        var payByCard = mainPage.payByCard();
        var year = DataHelper.getSymbolString(2);
        var digit = DataHelper.getOneDigit();
        payByCard.setUpYearFieldWithSymbol(year, digit);
    }

    @Test
    void shouldUseCardWithCurrentPeriod() {
        var payByCard = mainPage.payByCard();
        var infoCardWithCurrentPeriod = DataHelper.Registration.getCardWithCurrentPeriod();
        payByCard.setUp(infoCardWithCurrentPeriod);
        payByCard.successMessage();
    }

    @Test
    void shouldUseCardWithCyrillicHolder() {
        var payByCard = mainPage.payByCard();
        var infoCyrillicHolder = DataHelper.Registration.getCyrillicHolderCard();
        payByCard.setUp(infoCyrillicHolder);
        payByCard.subWrongFormat();
    }

    @Test
    void shouldUseCardWithSymbolHolder() {
        var payByCard = mainPage.payByCard();
        var infoSymbolHolder = DataHelper.Registration.getSymbolHolderCard();
        payByCard.setUp(infoSymbolHolder);
        payByCard.subWrongFormat();
    }

    @Test
    void shouldUseEmptyHolderField() {
        var payByCard = mainPage.payByCard();
        var infoEmptyHolderField = DataHelper.Registration.getEmptyHolderFieldCard();
        payByCard.setUp(infoEmptyHolderField);
        payByCard.subNecessarilyField();
    }

}
