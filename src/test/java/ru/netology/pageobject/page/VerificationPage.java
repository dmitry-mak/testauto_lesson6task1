package ru.netology.pageobject.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {

    private final SelenideElement verificationCode = $("[data-test-id='code'] input.input__control");
    private final SelenideElement verifyButton = $("[data-test-id='action-verify']");

    public VerificationPage() {
        $("[data-test-id='code'] input.input__control")
                .shouldBe(Condition.visible, Duration.ofSeconds(5));
    }

    public void verifyLogin(String code) {
        verificationCode.setValue(code);
        verifyButton.click();
    }
}
