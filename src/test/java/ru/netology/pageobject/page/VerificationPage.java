package ru.netology.pageobject.page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private final SelenideElement verificationCode = $("[data-test-id='code'] input.input__control");
    private final SelenideElement verifyButton = $("[data-test-id='action-verify']");

    public void verifyLogin(String code) {
        verificationCode.setValue(code);
        verifyButton.click();
    }
}
