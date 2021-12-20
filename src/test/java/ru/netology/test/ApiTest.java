package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.netology.api.ApiHelper;
import ru.netology.data.DBaseQueries;
import ru.netology.data.DataHelper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DBaseQueries.*;
import static ru.netology.data.DBaseQueries.getCredit;

public class ApiTest {
    private ApiHelper api = new ApiHelper();

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
        DBaseQueries.clearAllData();
    }

    @Test
    void shouldUseApprovedCardForPay() {
        var infoValidHolder = DataHelper.Registration.getValidUser();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeSuccess();
        var response = api.sendPostStatusSuccess(infoValidHolder, pathForPay, statusCode);
        assertEquals(DataHelper.getApprovedStatus(), response);
        assertEquals(getOrder().getPayment_id(), getPayment().getTransaction_id());
        assertEquals(DataHelper.getApprovedStatus(), getPayment().getStatus());
    }

    @Test
    void shouldUseDeclinedCardForPay() {
        var infoHolderDeclinedCard = DataHelper.Registration.getDeclinedUser();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeSuccess();
        var response = api.sendPostStatusSuccess(infoHolderDeclinedCard, pathForPay, statusCode);
        assertEquals(DataHelper.getDeclinedStatus(), response);
        assertEquals(getOrder().getPayment_id(), getPayment().getTransaction_id());
        assertEquals(DataHelper.getDeclinedStatus(), getPayment().getStatus());
    }

    @Test
    void shouldUseEmptyCardNumberForPay() {
        var infoEmptyCardNumber = DataHelper.Registration.getEmptyCardNumber();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoEmptyCardNumber, pathForPay, statusCode);
    }

    @Test
    void shouldUseAnyCardNumberForPay() {
        var infoHolderAnyCardNumber = DataHelper.Registration.getAnyCardNumberUser();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolderAnyCardNumber, pathForPay, statusCode);
    }

    @Test
    void shouldUseCardWithPartNumberForPay() {
        var infoHolder = DataHelper.Registration.getPartCardNumber();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolder, pathForPay, statusCode);
    }

    @Test
    void shouldUseMoreDigitsInCardNumberForPay() {
        var infoMoreDigitsInCardNumber = DataHelper.Registration.getMoreDigitsInCardNumber();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoMoreDigitsInCardNumber, pathForPay, statusCode);
    }

    @Test
    void shouldUseCardNumberWithoutDigitForPay() {
        var infoCardNumberWithoutDigit = DataHelper.Registration.getCardNumberWithoutDigit();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoCardNumberWithoutDigit, pathForPay, statusCode);
    }

    @Test
    void shouldUseMonthDoubleZeroForPay() {
        var infoHolderDoubleZeroMonth = DataHelper.Registration.getMonthDoubleZeroCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolderDoubleZeroMonth, pathForPay, statusCode);
    }

    @Test
    void shouldUseMonthOverForPay() {
        var infoHolder13Month = DataHelper.Registration.getMonthOverCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolder13Month, pathForPay, statusCode);
    }

    @Test
    void shouldUseEmptyMonthFieldForPay() {
        var infoEmptyMonthField = DataHelper.Registration.getEmptyMonthFieldCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoEmptyMonthField, pathForPay, statusCode);
    }

    @Test
    void shouldUseCardWithOneDigitMonthForPay() {
        var infoHolderOneDigitMonth = DataHelper.Registration.getOneDigitMonthCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolderOneDigitMonth, pathForPay, statusCode);
    }

    @Test
    void shouldUseMoreDigitsInMonthForPay() {
        var infoHolderMoreDigitsInMonth = DataHelper.Registration.getMoreDigitsInMonth();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolderMoreDigitsInMonth, pathForPay, statusCode);
    }

    @Test
    void shouldUseMonthWithoutDigitForPay() {
        var infoHolderMonthWithoutDigit = DataHelper.Registration.getMonthWithoutDigit();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderMonthWithoutDigit, pathForPay, statusCode);
    }

    @Test
    void shouldUsePastYearForPay() {
        var infoHolderPastYear = DataHelper.Registration.getPastYearCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolderPastYear, pathForPay, statusCode);
    }

    @Test
    void shouldUseFutureYearOverForPay() {
        var infoHolderFutureYear = DataHelper.Registration.getFutureYearOverCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolderFutureYear, pathForPay, statusCode);
    }

    @Test
    void shouldUseEmptyYearFieldForPay() {
        var infoEmptyYearField = DataHelper.Registration.getEmptyYearFieldCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoEmptyYearField, pathForPay, statusCode);
    }

    @Test
    void shouldUseCardWithOneDigitYearForPay() {
        var infoHolderOneDigitYear = DataHelper.Registration.getOneDigitYearCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolderOneDigitYear, pathForPay, statusCode);
    }

    @Test
    void shouldUseMoreDigitsInYearForPay() {
        var infoHolderMoreDigitsInYear = DataHelper.Registration.getMoreDigitsInYearCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolderMoreDigitsInYear, pathForPay, statusCode);
    }

    @Test
    void shouldUseYearWithoutDigitForPay() {
        var infoHolderYearWithoutDigit = DataHelper.Registration.getYearWithoutDigitCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderYearWithoutDigit, pathForPay, statusCode);
    }

    @Test
    void shouldUseCardWithCurrentPeriodForPay() {
        var infoCardWithCurrentPeriod = DataHelper.Registration.getCardWithCurrentPeriod();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeSuccess();
        var response = api.sendPostStatusSuccess(infoCardWithCurrentPeriod, pathForPay, statusCode);
        assertEquals(DataHelper.getApprovedStatus(), response);
        assertEquals(getOrder().getPayment_id(), getPayment().getTransaction_id());
        assertEquals(DataHelper.getApprovedStatus(), getPayment().getStatus());
    }

    @Test
    void shouldUseCardWithCyrillicHolderForPay() {
        var infoCyrillicHolder = DataHelper.Registration.getCyrillicHolderCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoCyrillicHolder, pathForPay, statusCode);
    }

    @Test
    void shouldUseCardWithSymbolHolderForPay() {
        var infoSymbolHolder = DataHelper.Registration.getSymbolHolderCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoSymbolHolder, pathForPay, statusCode);
    }

    @Test
    void shouldUseEmptyHolderFieldForPay() {
        var infoEmptyHolderField = DataHelper.Registration.getEmptyHolderFieldCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoEmptyHolderField, pathForPay, statusCode);
    }

    @Test
    void shouldUseEmptyCvcFieldForPay() {
        var infoEmptyCvcField = DataHelper.Registration.getEmptyCvcFieldCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoEmptyCvcField, pathForPay, statusCode);
    }

    @Test
    void shouldUseCardWithOneDigitCvcForPay() {
        var infoHolderOneDigitCvc = DataHelper.Registration.getOneDigitCvcCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolderOneDigitCvc, pathForPay, statusCode);
    }

    @Test
    void shouldUseMoreDigitsInCvcForPay() {
        var infoHolderMoreDigitsInCvc = DataHelper.Registration.getMoreDigitsInCvcCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolderMoreDigitsInCvc, pathForPay, statusCode);
    }

    @Test
    void shouldUseCvcWithoutDigitForPay() {
        var infoHolderCvcWithoutDigit = DataHelper.Registration.getCvcWithoutDigitCard();
        var pathForPay = DataHelper.getPayPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderCvcWithoutDigit, pathForPay, statusCode);
    }
    // далее для кредитования

    @Test
    void shouldUseApprovedCardForCredit() {
        var infoValidHolder = DataHelper.Registration.getValidUser();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeSuccess();
        var response = api.sendPostStatusSuccess(infoValidHolder, pathForCredit, statusCode);
        assertEquals(DataHelper.getApprovedStatus(), response);
        assertEquals(getOrder().getCredit_id(), getCredit().getBank_id());
        assertEquals(DataHelper.getApprovedStatus(), getCredit().getStatus());
    }

    @Test
    void shouldUseDeclinedCardForCredit() {
        var infoHolderDeclinedCard = DataHelper.Registration.getDeclinedUser();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeSuccess();
        var response = api.sendPostStatusSuccess(infoHolderDeclinedCard, pathForCredit, statusCode);
        assertEquals(DataHelper.getDeclinedStatus(), response);
        assertEquals(getOrder().getCredit_id(), getCredit().getBank_id());
        assertEquals(DataHelper.getDeclinedStatus(), getCredit().getStatus());
    }

    @Test
    void shouldUseEmptyCardNumberForCredit() {
        var infoEmptyCardNumber = DataHelper.Registration.getEmptyCardNumber();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoEmptyCardNumber, pathForCredit, statusCode);
    }

    @Test
    void shouldUseAnyCardNumberForCredit() {
        var infoHolderAnyCardNumber = DataHelper.Registration.getAnyCardNumberUser();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolderAnyCardNumber, pathForCredit, statusCode);
    }

    @Test
    void shouldUseCardWithPartNumberForCredit() {
        var infoHolder = DataHelper.Registration.getPartCardNumber();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolder, pathForCredit, statusCode);
    }

    @Test
    void shouldUseMoreDigitsInCardNumberForCredit() {
        var infoMoreDigitsInCardNumber = DataHelper.Registration.getMoreDigitsInCardNumber();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoMoreDigitsInCardNumber, pathForCredit, statusCode);
    }

    @Test
    void shouldUseCardNumberWithoutDigitForCredit() {
        var infoCardNumberWithoutDigit = DataHelper.Registration.getCardNumberWithoutDigit();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoCardNumberWithoutDigit, pathForCredit, statusCode);
    }

    @Test
    void shouldUseMonthDoubleZeroForCredit() {
        var infoHolderDoubleZeroMonth = DataHelper.Registration.getMonthDoubleZeroCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolderDoubleZeroMonth, pathForCredit, statusCode);
    }

    @Test
    void shouldUseMonthOverForCredit() {
        var infoHolder13Month = DataHelper.Registration.getMonthOverCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolder13Month, pathForCredit, statusCode);
    }

    @Test
    void shouldUseEmptyMonthFieldForCredit() {
        var infoEmptyMonthField = DataHelper.Registration.getEmptyMonthFieldCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoEmptyMonthField, pathForCredit, statusCode);
    }

    @Test
    void shouldUseCardWithOneDigitMonthForCredit() {
        var infoHolderOneDigitMonth = DataHelper.Registration.getOneDigitMonthCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolderOneDigitMonth, pathForCredit, statusCode);
    }

    @Test
    void shouldUseMoreDigitsInMonthForCredit() {
        var infoHolderMoreDigitsInMonth = DataHelper.Registration.getMoreDigitsInMonth();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolderMoreDigitsInMonth, pathForCredit, statusCode);
    }

    @Test
    void shouldUseMonthWithoutDigitForCredit() {
        var infoHolderMonthWithoutDigit = DataHelper.Registration.getMonthWithoutDigit();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderMonthWithoutDigit, pathForCredit, statusCode);
    }

    @Test
    void shouldUsePastYearForCredit() {
        var infoHolderPastYear = DataHelper.Registration.getPastYearCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolderPastYear, pathForCredit, statusCode);
    }

    @Test
    void shouldUseFutureYearOverForCredit() {
        var infoHolderFutureYear = DataHelper.Registration.getFutureYearOverCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolderFutureYear, pathForCredit, statusCode);
    }

    @Test
    void shouldUseEmptyYearFieldForCredit() {
        var infoEmptyYearField = DataHelper.Registration.getEmptyYearFieldCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoEmptyYearField, pathForCredit, statusCode);
    }

    @Test
    void shouldUseCardWithOneDigitYearForCredit() {
        var infoHolderOneDigitYear = DataHelper.Registration.getOneDigitYearCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolderOneDigitYear, pathForCredit, statusCode);
    }

    @Test
    void shouldUseMoreDigitsInYearForCredit() {
        var infoHolderMoreDigitsInYear = DataHelper.Registration.getMoreDigitsInYearCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolderMoreDigitsInYear, pathForCredit, statusCode);
    }

    @Test
    void shouldUseYearWithoutDigitForCredit() {
        var infoHolderYearWithoutDigit = DataHelper.Registration.getYearWithoutDigitCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderYearWithoutDigit, pathForCredit, statusCode);
    }

    @Test
    void shouldUseCardWithCurrentPeriodForCredit() {
        var infoCardWithCurrentPeriod = DataHelper.Registration.getCardWithCurrentPeriod();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeSuccess();
        var response = api.sendPostStatusSuccess(infoCardWithCurrentPeriod, pathForCredit, statusCode);
        assertEquals(DataHelper.getApprovedStatus(), response);
        assertEquals(getOrder().getCredit_id(), getCredit().getBank_id());
        assertEquals(DataHelper.getApprovedStatus(), getCredit().getStatus());
    }

    @Test
    void shouldUseCardWithCyrillicHolderForCredit() {
        var infoCyrillicHolder = DataHelper.Registration.getCyrillicHolderCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoCyrillicHolder, pathForCredit, statusCode);
    }

    @Test
    void shouldUseCardWithSymbolHolderForCredit() {
        var infoSymbolHolder = DataHelper.Registration.getSymbolHolderCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoSymbolHolder, pathForCredit, statusCode);
    }

    @Test
    void shouldUseEmptyHolderFieldForCredit() {
        var infoEmptyHolderField = DataHelper.Registration.getEmptyHolderFieldCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoEmptyHolderField, pathForCredit, statusCode);
    }

    @Test
    void shouldUseEmptyCvcFieldForCredit() {
        var infoEmptyCvcField = DataHelper.Registration.getEmptyCvcFieldCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoEmptyCvcField, pathForCredit, statusCode);
    }

    @Test
    void shouldUseCardWithOneDigitCvcForCredit() {
        var infoHolderOneDigitCvc = DataHelper.Registration.getOneDigitCvcCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolderOneDigitCvc, pathForCredit, statusCode);
    }

    @Test
    void shouldUseMoreDigitsInCvcForCredit() {
        var infoHolderMoreDigitsInCvc = DataHelper.Registration.getMoreDigitsInCvcCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeServerError();
        api.sendPostStatusError(infoHolderMoreDigitsInCvc, pathForCredit, statusCode);
    }

    @Test
    void shouldUseCvcWithoutDigitForCredit() {
        var infoHolderCvcWithoutDigit = DataHelper.Registration.getCvcWithoutDigitCard();
        var pathForCredit = DataHelper.getCreditPath();
        var statusCode = DataHelper.getStatusCodeClientError();
        api.sendPostStatusError(infoHolderCvcWithoutDigit, pathForCredit, statusCode);
    }
}
