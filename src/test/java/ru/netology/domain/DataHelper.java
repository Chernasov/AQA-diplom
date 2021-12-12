package ru.netology.domain;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DataHelper {
    private static final Faker faker = new Faker();

    private DataHelper () {}

    @Value
    public static class AuthInfo {
        String number;
        String month;
        String year;
        String holder;
        String cvc;
    }

    public static String getApprovedCardNumber () {
        return "1111 2222 3333 4444";
    }

    public static String getDeclinedCardNumber () {
        return "5555 6666 7777 8888";
    }

    public static String getRandomCardNumber () {
        return faker.numerify("#### #### #### ####");
    }

    public static String getMonth () {
        return LocalDate.now().plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getYearFutureOverPeriod () {
        return LocalDate.now().plusYears(faker.number().numberBetween(10, 16)).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getYearFutureInPeriod () {
        return LocalDate.now().plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getYearPast () {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getHolder () {
        return faker.name().name();
    }

    public static String getCVC () {
        return faker.numerify("###");
    }

    public static AuthInfo getValidUser () {
        return new AuthInfo(getApprovedCardNumber(), getMonth(), getYearFutureInPeriod(), getHolder(), getCVC());
    }
 }
