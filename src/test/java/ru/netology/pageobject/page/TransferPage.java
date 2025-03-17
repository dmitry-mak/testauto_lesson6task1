package ru.netology.pageobject.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class TransferPage {
    private final SelenideElement amountField=$("[data-test-id='amount'] input.input__control");
    private final SelenideElement fromField=$("[data-test-id='from'] input.input__control");
    private final SelenideElement acceptButton=$("[data-test-id='action-transfer']");


    public TransferPage() {
        $$("h1").findBy(Condition.text("Пополнение карты"))
                .shouldBe(Condition.visible, Duration.ofSeconds(5));
    }
    public void fillTransferForm(int amount, String fromCard) {
        amountField.setValue(String.valueOf(amount));
        fromField.setValue("5559 0000 0000 " + fromCard);
        acceptButton.click();
    }

    public void fillTransferFormSecondCard() {
        amountField.setValue("1000");
        fromField.setValue("5559 0000 0000 0001");
        acceptButton.click();
    }
}
