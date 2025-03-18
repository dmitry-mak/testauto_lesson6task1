package ru.netology.pageobject.data;

import lombok.Value;
import org.junit.jupiter.params.provider.ValueSource;

public class DataGenerator {

    private DataGenerator() {
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "qwerty");
    }

    public static VerificationCode getVerificationCode(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    public static CardInfo getFirstCardInfo() {
        return new CardInfo(
                "92df3f1c-a033-48e6-8390-206f6b1f56c0",
                "5559 0000 0000 0001"
        );
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo(
                "0f3f5c2a-249e-4c3d-8287-09f7a039391d",
                "5559 0000 0000 0002"
        );
    }

    @Value
    public static class AuthInfo {
        String login;
        String password;
    }

    @Value
    public static class VerificationCode {
        String code;
    }

    @Value
    public static class CardInfo {
        String cardId;
        String cardNumber;
    }
}
