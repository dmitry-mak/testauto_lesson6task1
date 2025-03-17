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

    public static VerificationCode getVerificationCode (AuthInfo authInfo){
        return new VerificationCode("12345");
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
}
