package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.Data;
import lombok.Value;

import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataHelper {
    private static final Faker faker = new Faker();
    private static final Faker fakerRU = new Faker(new Locale("ru"));

    private DataHelper() {
    }

    @Value
    @Data
    public static class AuthInfo {
        String cvc;
        String holder;
        String month;
        String number;
        String year;
    }

    public static String getApprovedCardNumber() {
        return "1111 2222 3333 4444";
    }

    public static String getDeclinedCardNumber() {
        return "5555 6666 7777 8888";
    }

    public static String getRandomCardNumber() {
        return faker.numerify("#### #### #### ####");
    }

    public static String getMonth() {
        return LocalDate.now().plusMonths(faker.number().numberBetween(1, 13)).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getCurrentMonth() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String getDoubleZero() {
        return "00";
    }

    public static String get13Month() {
        return "13";
    }

    public static String getYearFutureOverPeriod() {
        return LocalDate.now().plusYears(faker.number().numberBetween(10, 16)).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getYearFutureInPeriod() {
        return LocalDate.now().plusYears(faker.number().numberBetween(1, 4)).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getYearPast() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getCurrentYear() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yy"));
    }

    public static String getHolder() {
        return faker.name().name();
    }

    public static String getCyrillicHolder() {
        return fakerRU.name().firstName() + " " + fakerRU.name().lastName();
    }

    public static String getCVC() {
        return faker.numerify("###");
    }

    public static String getEmptyField() {
        return "";
    }

    public static String getOneDigit() {
        return faker.numerify("#");
    }

    public static String getSymbolString(int length) {
        byte[] array = new byte[256];
        new Random().nextBytes(array);
        String randomString = new String(array, Charset.forName("UTF-8"));
        StringBuffer r = new StringBuffer();
        for (int i = 0; i < randomString.length(); i++) {
            char ch = randomString.charAt(i);
            if (((ch >= ' ' && ch <= '/')
                    || (ch >= ':' && ch <= '~')
            )
                    && (length > 0)) {
                r.append(ch);
                length--;
            }
        }
        return r.toString();
    }

    public static class Registration {
        private Registration() {
        }

        public static AuthInfo getValidUser() {
            return new AuthInfo(getCVC(), getHolder(), getMonth(), getApprovedCardNumber(), getYearFutureInPeriod());
        }

        public static AuthInfo getDeclinedUser() {
            return new AuthInfo(getCVC(), getHolder(), getMonth(), getDeclinedCardNumber(), getYearFutureInPeriod());
        }

        public static AuthInfo getAnyCardNumberUser() {
            return new AuthInfo(getCVC(), getHolder(), getMonth(), getRandomCardNumber(), getYearFutureInPeriod());
        }

        public static AuthInfo getEmptyCardNumber() {
            return new AuthInfo(getCVC(), getHolder(), getMonth(), getEmptyField(), getYearFutureInPeriod());
        }

        public static AuthInfo getPartCardNumber() {
            return new AuthInfo(getCVC(), getHolder(), getMonth(), getOneDigit(), getYearFutureInPeriod());
        }

        public static AuthInfo getMonthDoubleZeroCard() {
            return new AuthInfo(getCVC(), getHolder(), getDoubleZero(), getApprovedCardNumber(), getYearFutureInPeriod());
        }

        public static AuthInfo getMonthOverCard() {
            return new AuthInfo(getCVC(), getHolder(), get13Month(), getApprovedCardNumber(), getYearFutureInPeriod());
        }

        public static AuthInfo getEmptyMonthFieldCard() {
            return new AuthInfo(getCVC(), getHolder(), getEmptyField(), getApprovedCardNumber(), getYearFutureInPeriod());
        }

        public static AuthInfo getOneDigitMonthCard() {
            return new AuthInfo(getCVC(), getHolder(), getOneDigit(), getApprovedCardNumber(), getYearFutureInPeriod());
        }

        public static AuthInfo getPastYearCard() {
            return new AuthInfo(getCVC(), getHolder(), getMonth(), getApprovedCardNumber(), getYearPast());
        }

        public static AuthInfo getFutureYearOverCard() {
            return new AuthInfo(getCVC(), getHolder(), getMonth(), getApprovedCardNumber(), getYearFutureOverPeriod());
        }

        public static AuthInfo getEmptyYearFieldCard() {
            return new AuthInfo(getCVC(), getHolder(), getMonth(), getApprovedCardNumber(), getEmptyField());
        }

        public static AuthInfo getOneDigitYearCard() {
            return new AuthInfo(getCVC(), getHolder(), getMonth(), getApprovedCardNumber(), getOneDigit());
        }

        public static AuthInfo getCardWithCurrentPeriod() {
            return new AuthInfo(getCVC(), getHolder(), getCurrentMonth(), getApprovedCardNumber(), getCurrentYear());
        }

        public static AuthInfo getCyrillicHolderCard() {
            return new AuthInfo(getCVC(), getCyrillicHolder(), getMonth(), getApprovedCardNumber(), getYearFutureInPeriod());
        }

        public static AuthInfo getSymbolHolderCard() {
            return new AuthInfo(getCVC(), getSymbolString(8), getMonth(), getApprovedCardNumber(), getYearFutureInPeriod());
        }

        public static AuthInfo getEmptyHolderFieldCard() {
            return new AuthInfo(getCVC(), getEmptyField(), getMonth(), getApprovedCardNumber(), getYearFutureInPeriod());
        }

        public static AuthInfo getEmptyCvcFieldCard() {
            return new AuthInfo(getEmptyField(), getHolder(), getMonth(), getApprovedCardNumber(), getYearFutureInPeriod());
        }

        public static AuthInfo getOneDigitCvcCard() {
            return new AuthInfo(getOneDigit(), getHolder(), getMonth(), getApprovedCardNumber(), getYearFutureInPeriod());
        }
    }
}
