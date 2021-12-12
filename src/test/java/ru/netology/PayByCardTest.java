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
        var infoValidHolder = DataHelper.getValidUser();
        payByCard.setUp(infoValidHolder);
        payByCard.successMessage();
    }

    @Test
    void shouldUseDeclinedCard() {
        var infoHolder = DataHelper.getDeclinedUser();
        payByCard.setUp(infoHolder);
        payByCard.errorMessage();
    }

    @Test
    void shouldUseAnyCardNumber () {
        var infoHolderAnyCardNumber = new DataHelper.AuthInfo(DataHelper.getCVC(),DataHelper.getHolder(),
                DataHelper.getMonth(),DataHelper.getRandomCardNumber(),DataHelper.getYearFutureInPeriod());
        payByCard.setUp(infoHolderAnyCardNumber);
        payByCard.errorMessage();
    }
}
