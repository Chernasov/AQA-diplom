package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DBaseQueries;
import ru.netology.data.DataHelper;
import ru.netology.page.MainPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DBaseQueries.*;

public class BuyOnCreditTest {
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
        DBaseQueries.clearAllData();
    }

    @Test
    void shouldUseApprovedCard() {
        var buyOnCredit = mainPage.buyOnCredit();
        var infoValidHolder = DataHelper.Registration.getValidUser();
        buyOnCredit.setUp(infoValidHolder);
        buyOnCredit.successMessage();
        assertEquals(getOrder().getCredit_id(), getCredit().getBank_id());
        assertEquals(DataHelper.getApprovedStatus(), getCredit().getStatus());
    }

    @Test
    void shouldUseDeclinedCard() {
        var buyOnCredit = mainPage.buyOnCredit();
        var infoHolderDeclinedCard = DataHelper.Registration.getDeclinedUser();
        buyOnCredit.setUp(infoHolderDeclinedCard);
        buyOnCredit.errorMessage();
        assertEquals(getOrder().getCredit_id(), getCredit().getBank_id());
        assertEquals(DataHelper.getDeclinedStatus(), getCredit().getStatus());
    }

    @Test
    void shouldUseEmptyCardNumber() {
        var buyOnCredit = mainPage.buyOnCredit();
        var infoEmptyCardNumber = DataHelper.Registration.getEmptyCardNumber();
        buyOnCredit.setUp(infoEmptyCardNumber);
        buyOnCredit.subWrongFormat();
    }

    @Test
    void shouldUseAnyCardNumber() {
        var buyOnCredit = mainPage.buyOnCredit();
        var infoHolderAnyCardNumber = DataHelper.Registration.getAnyCardNumberUser();
        buyOnCredit.setUp(infoHolderAnyCardNumber);
        buyOnCredit.errorMessage();
    }

    @Test
    void shouldUseCardWithPartNumber() {
        var buyOnCredit = mainPage.buyOnCredit();
        var infoHolder = DataHelper.Registration.getPartCardNumber();
        buyOnCredit.setUp(infoHolder);
        buyOnCredit.subWrongFormat();
    }

    @Test
    void shouldUseMoreDigitsInCardNumber() {
        var buyOnCredit = mainPage.buyOnCredit();
        var cardNumber = DataHelper.getRandomCardNumber();
        var digit = DataHelper.getOneDigit();
        buyOnCredit.setUpCardNumberField(cardNumber, digit);
    }

    @Test
    void shouldUseCardNumberWithoutDigit() {
        var buyOnCredit = mainPage.buyOnCredit();
        var cardNumber = DataHelper.getSymbolString(16);
        var digit = DataHelper.getOneDigit();
        buyOnCredit.setUpCardNumberFieldWithSymbol(cardNumber, digit);
    }

    @Test
    void shouldUseMonthDoubleZero() {
        var buyOnCredit = mainPage.buyOnCredit();
        var infoHolderDoubleZeroMonth = DataHelper.Registration.getMonthDoubleZeroCard();
        buyOnCredit.setUp(infoHolderDoubleZeroMonth);
        buyOnCredit.subWrongPeriod();
    }

    @Test
    void shouldUseMonthOver() {
        var buyOnCredit = mainPage.buyOnCredit();
        var infoHolder13Month = DataHelper.Registration.getMonthOverCard();
        buyOnCredit.setUp(infoHolder13Month);
        buyOnCredit.subWrongPeriod();
    }

    @Test
    void shouldUseEmptyMonthField() {
        var buyOnCredit = mainPage.buyOnCredit();
        var infoEmptyMonthField = DataHelper.Registration.getEmptyMonthFieldCard();
        buyOnCredit.setUp(infoEmptyMonthField);
        buyOnCredit.subWrongFormat();
    }

    @Test
    void shouldUseCardWithOneDigitMonth() {
        var buyOnCredit = mainPage.buyOnCredit();
        var infoHolderOneDigitMonth = DataHelper.Registration.getOneDigitMonthCard();
        buyOnCredit.setUp(infoHolderOneDigitMonth);
        buyOnCredit.subWrongFormat();
    }

    @Test
    void shouldUseMoreDigitsInMonth() {
        var buyOnCredit = mainPage.buyOnCredit();
        var month = DataHelper.getMonth();
        var digit = DataHelper.getOneDigit();
        buyOnCredit.setUpMonthField(month, digit);
    }

    @Test
    void shouldUseMonthWithoutDigit() {
        var buyOnCredit = mainPage.buyOnCredit();
        var month = DataHelper.getSymbolString(2);
        var digit = DataHelper.getOneDigit();
        buyOnCredit.setUpMonthFieldWithSymbol(month, digit);
    }

    @Test
    void shouldUsePastYear() {
        var buyOnCredit = mainPage.buyOnCredit();
        var infoHolderPastYear = DataHelper.Registration.getPastYearCard();
        buyOnCredit.setUp(infoHolderPastYear);
        buyOnCredit.subExpired();
    }

    @Test
    void shouldUseFutureYearOver() {
        var buyOnCredit = mainPage.buyOnCredit();
        var infoHolderFutureYear = DataHelper.Registration.getFutureYearOverCard();
        buyOnCredit.setUp(infoHolderFutureYear);
        buyOnCredit.subWrongPeriod();
    }

    @Test
    void shouldUseEmptyYearField() {
        var buyOnCredit = mainPage.buyOnCredit();
        var infoEmptyYearField = DataHelper.Registration.getEmptyYearFieldCard();
        buyOnCredit.setUp(infoEmptyYearField);
        buyOnCredit.subWrongFormat();
    }

    @Test
    void shouldUseCardWithOneDigitYear() {
        var buyOnCredit = mainPage.buyOnCredit();
        var infoHolderOneDigitYear = DataHelper.Registration.getOneDigitYearCard();
        buyOnCredit.setUp(infoHolderOneDigitYear);
        buyOnCredit.subWrongFormat();
    }

    @Test
    void shouldUseMoreDigitsInYear() {
        var buyOnCredit = mainPage.buyOnCredit();
        var year = DataHelper.getYearFutureInPeriod();
        var digit = DataHelper.getOneDigit();
        buyOnCredit.setUpYearField(year, digit);
    }

    @Test
    void shouldUseYearWithoutDigit() {
        var buyOnCredit = mainPage.buyOnCredit();
        var year = DataHelper.getSymbolString(2);
        var digit = DataHelper.getOneDigit();
        buyOnCredit.setUpYearFieldWithSymbol(year, digit);
    }

    @Test
    void shouldUseCardWithCurrentPeriod() {
        var buyOnCredit = mainPage.buyOnCredit();
        var infoCardWithCurrentPeriod = DataHelper.Registration.getCardWithCurrentPeriod();
        buyOnCredit.setUp(infoCardWithCurrentPeriod);
        buyOnCredit.successMessage();
        assertEquals(getOrder().getCredit_id(), getCredit().getBank_id());
        assertEquals(DataHelper.getApprovedStatus(), getCredit().getStatus());
    }

    @Test
    void shouldUseCardWithCyrillicHolder() {
        var buyOnCredit = mainPage.buyOnCredit();
        var infoCyrillicHolder = DataHelper.Registration.getCyrillicHolderCard();
        buyOnCredit.setUp(infoCyrillicHolder);
        buyOnCredit.subWrongFormat();
    }

    @Test
    void shouldUseCardWithSymbolHolder() {
        var buyOnCredit = mainPage.buyOnCredit();
        var infoSymbolHolder = DataHelper.Registration.getSymbolHolderCard();
        buyOnCredit.setUp(infoSymbolHolder);
        buyOnCredit.subWrongFormat();
    }

    @Test
    void shouldUseEmptyHolderField() {
        var buyOnCredit = mainPage.buyOnCredit();
        var infoEmptyHolderField = DataHelper.Registration.getEmptyHolderFieldCard();
        buyOnCredit.setUp(infoEmptyHolderField);
        buyOnCredit.subNecessarilyField();
    }

    @Test
    void shouldUseEmptyCvcField() {
        var buyOnCredit = mainPage.buyOnCredit();
        var infoEmptyCvcField = DataHelper.Registration.getEmptyCvcFieldCard();
        buyOnCredit.setUp(infoEmptyCvcField);
        buyOnCredit.subWrongFormat();
    }

    @Test
    void shouldUseCardWithOneDigitCvc() {
        var buyOnCredit = mainPage.buyOnCredit();
        var infoHolderOneDigitCvc = DataHelper.Registration.getOneDigitCvcCard();
        buyOnCredit.setUp(infoHolderOneDigitCvc);
        buyOnCredit.subWrongFormat();
    }

    @Test
    void shouldUseMoreDigitsInCvc() {
        var buyOnCredit = mainPage.buyOnCredit();
        var cvc = DataHelper.getCVC();
        var digit = DataHelper.getOneDigit();
        buyOnCredit.setUpCvcField(cvc, digit);
    }

    @Test
    void shouldUseCvcWithoutDigit() {
        var buyOnCredit = mainPage.buyOnCredit();
        var cvc = DataHelper.getSymbolString(3);
        var digit = DataHelper.getOneDigit();
        buyOnCredit.setUpCvcFieldWithSymbol(cvc, digit);
    }

    @Test
    void shouldSwitchByCredit() {
        var buyOnCredit = mainPage.buyOnCredit();
        buyOnCredit.payByCard();
    }
}
