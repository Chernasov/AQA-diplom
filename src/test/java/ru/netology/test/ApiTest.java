package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.ApiPage;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApiTest {
    private ApiPage api = new ApiPage();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @Test
    void shouldUseApprovedCardForPay() {
        var infoValidHolder = DataHelper.Registration.getValidUser();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus200(infoValidHolder, pathForPay);
        assertEquals(DataHelper.getApprovedStatus(), response);
    }

    @Test
    void shouldUseDeclinedCardForPay() {
        var infoHolderDeclinedCard = DataHelper.Registration.getDeclinedUser();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus200(infoHolderDeclinedCard, pathForPay);
        assertEquals(DataHelper.getDeclinedStatus(), response);
    }

    @Test
    void shouldUseEmptyCardNumberForPay() {
        var infoEmptyCardNumber = DataHelper.Registration.getEmptyCardNumber();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus500(infoEmptyCardNumber, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseAnyCardNumberForPay() {
        var infoHolderAnyCardNumber = DataHelper.Registration.getAnyCardNumberUser();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus500(infoHolderAnyCardNumber, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseCardWithPartNumberForPay() {
        var infoHolder = DataHelper.Registration.getPartCardNumber();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus500(infoHolder, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseMoreDigitsInCardNumberForPay() {
        var infoMoreDigitsInCardNumber = DataHelper.Registration.getMoreDigitsInCardNumber();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus500(infoMoreDigitsInCardNumber, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseCardNumberWithoutDigitForPay() {
        var infoCardNumberWithoutDigit = DataHelper.Registration.getCardNumberWithoutDigit();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus500(infoCardNumberWithoutDigit, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseMonthDoubleZeroForPay() {
        var infoHolderDoubleZeroMonth = DataHelper.Registration.getMonthDoubleZeroCard();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus500(infoHolderDoubleZeroMonth, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseMonthOverForPay() {
        var infoHolder13Month = DataHelper.Registration.getMonthOverCard();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus500(infoHolder13Month, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseEmptyMonthFieldForPay() {
        var infoEmptyMonthField = DataHelper.Registration.getEmptyMonthFieldCard();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus500(infoEmptyMonthField, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseCardWithOneDigitMonthForPay() {
        var infoHolderOneDigitMonth = DataHelper.Registration.getOneDigitMonthCard();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus500(infoHolderOneDigitMonth, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseMoreDigitsInMonthForPay() {
        var infoHolderMoreDigitsInMonth = DataHelper.Registration.getMoreDigitsInMonth();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus500(infoHolderMoreDigitsInMonth, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseMonthWithoutDigitForPay() {
        var infoHolderMonthWithoutDigit = DataHelper.Registration.getMonthWithoutDigit();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus400(infoHolderMonthWithoutDigit, pathForPay);
        assertEquals(400, response);
    }

    @Test
    void shouldUsePastYearForPay() {
        var infoHolderPastYear = DataHelper.Registration.getPastYearCard();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus500(infoHolderPastYear, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseFutureYearOverForPay() {
        var infoHolderFutureYear = DataHelper.Registration.getFutureYearOverCard();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus500(infoHolderFutureYear, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseEmptyYearFieldForPay() {
        var infoEmptyYearField = DataHelper.Registration.getEmptyYearFieldCard();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus500(infoEmptyYearField, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseCardWithOneDigitYearForPay() {
        var infoHolderOneDigitYear = DataHelper.Registration.getOneDigitYearCard();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus500(infoHolderOneDigitYear, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseMoreDigitsInYearForPay() {
        var infoHolderMoreDigitsInYear = DataHelper.Registration.getMoreDigitsInYearCard();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus500(infoHolderMoreDigitsInYear, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseYearWithoutDigitForPay() {
        var infoHolderYearWithoutDigit = DataHelper.Registration.getYearWithoutDigitCard();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus400(infoHolderYearWithoutDigit, pathForPay);
        assertEquals(400, response);
    }

    @Test
    void shouldUseCardWithCurrentPeriodForPay() {
        var infoCardWithCurrentPeriod = DataHelper.Registration.getCardWithCurrentPeriod();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus200(infoCardWithCurrentPeriod, pathForPay);
        assertEquals(DataHelper.getApprovedStatus(), response);
    }

    @Test
    void shouldUseCardWithCyrillicHolderForPay() {
        var infoCyrillicHolder = DataHelper.Registration.getCyrillicHolderCard();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus500(infoCyrillicHolder, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseCardWithSymbolHolderForPay() {
        var infoSymbolHolder = DataHelper.Registration.getSymbolHolderCard();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus500(infoSymbolHolder, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseEmptyHolderFieldForPay() {
        var infoEmptyHolderField = DataHelper.Registration.getEmptyHolderFieldCard();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus500(infoEmptyHolderField, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseEmptyCvcFieldForPay() {
        var infoEmptyCvcField = DataHelper.Registration.getEmptyCvcFieldCard();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus500(infoEmptyCvcField, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseCardWithOneDigitCvcForPay() {
        var infoHolderOneDigitCvc = DataHelper.Registration.getOneDigitCvcCard();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus500(infoHolderOneDigitCvc, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseMoreDigitsInCvcForPay() {
        var infoHolderMoreDigitsInCvc = DataHelper.Registration.getMoreDigitsInCvcCard();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus500(infoHolderMoreDigitsInCvc, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseCvcWithoutDigitForPay() {
        var infoHolderCvcWithoutDigit = DataHelper.Registration.getCvcWithoutDigitCard();
        var pathForPay = DataHelper.getPayPath();
        var response = api.sendPostStatus400(infoHolderCvcWithoutDigit, pathForPay);
        assertEquals(400, response);
    }
    // далее для кредитования

    @Test
    void shouldUseApprovedCardForCredit() {
        var infoValidHolder = DataHelper.Registration.getValidUser();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus200(infoValidHolder, pathForCredit);
        assertEquals(DataHelper.getApprovedStatus(), response);
    }

    @Test
    void shouldUseDeclinedCardForCredit() {
        var infoHolderDeclinedCard = DataHelper.Registration.getDeclinedUser();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus200(infoHolderDeclinedCard, pathForCredit);
        assertEquals(DataHelper.getDeclinedStatus(), response);
    }

    @Test
    void shouldUseEmptyCardNumberForCredit() {
        var infoEmptyCardNumber = DataHelper.Registration.getEmptyCardNumber();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoEmptyCardNumber, pathForCredit);
        assertEquals(500, response);
    }

    @Test
    void shouldUseAnyCardNumberForCredit() {
        var infoHolderAnyCardNumber = DataHelper.Registration.getAnyCardNumberUser();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoHolderAnyCardNumber, pathForCredit);
        assertEquals(500, response);
    }

    @Test
    void shouldUseCardWithPartNumberForCredit() {
        var infoHolder = DataHelper.Registration.getPartCardNumber();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoHolder, pathForCredit);
        assertEquals(500, response);
    }

    @Test
    void shouldUseMoreDigitsInCardNumberForCredit() {
        var infoMoreDigitsInCardNumber = DataHelper.Registration.getMoreDigitsInCardNumber();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoMoreDigitsInCardNumber, pathForCredit);
        assertEquals(500, response);
    }

    @Test
    void shouldUseCardNumberWithoutDigitForCredit() {
        var infoCardNumberWithoutDigit = DataHelper.Registration.getCardNumberWithoutDigit();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoCardNumberWithoutDigit, pathForCredit);
        assertEquals(500, response);
    }

    @Test
    void shouldUseMonthDoubleZeroForCredit() {
        var infoHolderDoubleZeroMonth = DataHelper.Registration.getMonthDoubleZeroCard();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoHolderDoubleZeroMonth, pathForCredit);
        assertEquals(500, response);
    }

    @Test
    void shouldUseMonthOverForCredit() {
        var infoHolder13Month = DataHelper.Registration.getMonthOverCard();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoHolder13Month, pathForCredit);
        assertEquals(500, response);
    }

    @Test
    void shouldUseEmptyMonthFieldForCredit() {
        var infoEmptyMonthField = DataHelper.Registration.getEmptyMonthFieldCard();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoEmptyMonthField, pathForCredit);
        assertEquals(500, response);
    }

    @Test
    void shouldUseCardWithOneDigitMonthForCredit() {
        var infoHolderOneDigitMonth = DataHelper.Registration.getOneDigitMonthCard();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoHolderOneDigitMonth, pathForCredit);
        assertEquals(500, response);
    }

    @Test
    void shouldUseMoreDigitsInMonthForCredit() {
        var infoHolderMoreDigitsInMonth = DataHelper.Registration.getMoreDigitsInMonth();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoHolderMoreDigitsInMonth, pathForCredit);
        assertEquals(500, response);
    }

    @Test
    void shouldUseMonthWithoutDigitForCredit() {
        var infoHolderMonthWithoutDigit = DataHelper.Registration.getMonthWithoutDigit();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus400(infoHolderMonthWithoutDigit, pathForCredit);
        assertEquals(400, response);
    }

    @Test
    void shouldUsePastYearForCredit() {
        var infoHolderPastYear = DataHelper.Registration.getPastYearCard();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoHolderPastYear, pathForCredit);
        assertEquals(500, response);
    }

    @Test
    void shouldUseFutureYearOverForCredit() {
        var infoHolderFutureYear = DataHelper.Registration.getFutureYearOverCard();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoHolderFutureYear, pathForCredit);
        assertEquals(500, response);
    }

    @Test
    void shouldUseEmptyYearFieldForCredit() {
        var infoEmptyYearField = DataHelper.Registration.getEmptyYearFieldCard();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoEmptyYearField, pathForCredit);
        assertEquals(500, response);
    }

    @Test
    void shouldUseCardWithOneDigitYearForCredit() {
        var infoHolderOneDigitYear = DataHelper.Registration.getOneDigitYearCard();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoHolderOneDigitYear, pathForCredit);
        assertEquals(500, response);
    }

    @Test
    void shouldUseMoreDigitsInYearForCredit() {
        var infoHolderMoreDigitsInYear = DataHelper.Registration.getMoreDigitsInYearCard();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoHolderMoreDigitsInYear, pathForCredit);
        assertEquals(500, response);
    }

    @Test
    void shouldUseYearWithoutDigitForCredit() {
        var infoHolderYearWithoutDigit = DataHelper.Registration.getYearWithoutDigitCard();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus400(infoHolderYearWithoutDigit, pathForCredit);
        assertEquals(400, response);
    }

    @Test
    void shouldUseCardWithCurrentPeriodForCredit() {
        var infoCardWithCurrentPeriod = DataHelper.Registration.getCardWithCurrentPeriod();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus200(infoCardWithCurrentPeriod, pathForCredit);
        assertEquals(DataHelper.getApprovedStatus(), response);
    }

    @Test
    void shouldUseCardWithCyrillicHolderForCredit() {
        var infoCyrillicHolder = DataHelper.Registration.getCyrillicHolderCard();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoCyrillicHolder, pathForCredit);
        assertEquals(500, response);
    }

    @Test
    void shouldUseCardWithSymbolHolderForCredit() {
        var infoSymbolHolder = DataHelper.Registration.getSymbolHolderCard();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoSymbolHolder, pathForCredit);
        assertEquals(500, response);
    }

    @Test
    void shouldUseEmptyHolderFieldForCredit() {
        var infoEmptyHolderField = DataHelper.Registration.getEmptyHolderFieldCard();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoEmptyHolderField, pathForCredit);
        assertEquals(500, response);
    }

    @Test
    void shouldUseEmptyCvcFieldForCredit() {
        var infoEmptyCvcField = DataHelper.Registration.getEmptyCvcFieldCard();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoEmptyCvcField, pathForCredit);
        assertEquals(500, response);
    }

    @Test
    void shouldUseCardWithOneDigitCvcForCredit() {
        var infoHolderOneDigitCvc = DataHelper.Registration.getOneDigitCvcCard();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoHolderOneDigitCvc, pathForCredit);
        assertEquals(500, response);
    }

    @Test
    void shouldUseMoreDigitsInCvcForCredit() {
        var infoHolderMoreDigitsInCvc = DataHelper.Registration.getMoreDigitsInCvcCard();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoHolderMoreDigitsInCvc, pathForCredit);
        assertEquals(500, response);
    }

    @Test
    void shouldUseCvcWithoutDigitForCredit() {
        var infoHolderCvcWithoutDigit = DataHelper.Registration.getCvcWithoutDigitCard();
        var pathForCredit = DataHelper.getCreditPath();
        var response = api.sendPostStatus400(infoHolderCvcWithoutDigit, pathForCredit);
        assertEquals(400, response);
    }
}
