package ru.netology.test;

import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.ApiPage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.netology.data.DBaseQueries.getOrder;
import static ru.netology.data.DBaseQueries.getPayment;

public class ApiTest {
    private ApiPage api = new ApiPage();

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

    // далее для кредитования

    @Test
    void shouldUseApprovedCardForCredit() {
        var infoValidHolder = DataHelper.Registration.getValidUser();
        var pathForPay = DataHelper.getCreditPath();
        var response = api.sendPostStatus200(infoValidHolder, pathForPay);
        assertEquals(DataHelper.getApprovedStatus(), response);
    }

    @Test
    void shouldUseDeclinedCardForCredit() {
        var infoHolderDeclinedCard = DataHelper.Registration.getDeclinedUser();
        var pathForPay = DataHelper.getCreditPath();
        var response = api.sendPostStatus200(infoHolderDeclinedCard, pathForPay);
        assertEquals(DataHelper.getDeclinedStatus(), response);
    }

    @Test
    void shouldUseEmptyCardNumberForCredit() {
        var infoEmptyCardNumber = DataHelper.Registration.getEmptyCardNumber();
        var pathForPay = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoEmptyCardNumber, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseAnyCardNumberForCredit() {
        var infoHolderAnyCardNumber = DataHelper.Registration.getAnyCardNumberUser();
        var pathForPay = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoHolderAnyCardNumber, pathForPay);
        assertEquals(500, response);
    }

    @Test
    void shouldUseCardWithPartNumberForCredit() {
        var infoHolder = DataHelper.Registration.getPartCardNumber();
        var pathForPay = DataHelper.getCreditPath();
        var response = api.sendPostStatus500(infoHolder, pathForPay);
        assertEquals(500, response);
    }

}
