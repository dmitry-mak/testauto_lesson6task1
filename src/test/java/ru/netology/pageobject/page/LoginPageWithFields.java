package ru.netology.pageobject.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.pageobject.data.DataGenerator;

import static com.codeborne.selenide.Selenide.$;

public class LoginPageWithFields {

    private final SelenideElement loginField = $("[data-test-id='login'] input.input__control");
    private final SelenideElement passwordField = $("[data-test-id='password'] input.input__control");
    private final SelenideElement loginButton = $("[data-test-id='action-login']");

    public VerificationPage validLogin(DataGenerator.AuthInfo info) {
        login(info);
        return new VerificationPage();
    }

    public void login(DataGenerator.AuthInfo info) {
        loginField.setValue(info.getLogin());
        passwordField.setValue(info.getPassword());
        loginButton.click();
    }
}
