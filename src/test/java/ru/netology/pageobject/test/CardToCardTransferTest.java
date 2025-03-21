package ru.netology.pageobject.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import ru.netology.pageobject.data.DataGenerator;
import ru.netology.pageobject.page.*;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static ru.netology.pageobject.data.DataGenerator.getAuthInfo;
import static ru.netology.pageobject.data.DataGenerator.getVerificationCode;

public class CardToCardTransferTest {

    @Test
    public void shouldTransferBetweenAccounts() {

        var info = getAuthInfo();
        var verificationCode = getVerificationCode(info);

        Selenide.open("http://localhost:9999/");

        var loginPage = new LoginPageWithFields();
        var verificationPage = loginPage.validLogin(info);

        var dashboardPage = verificationPage.verifyLogin(verificationCode.getCode());

        var firstCard = DataGenerator.getFirstCardInfo();
        var secondCard = DataGenerator.getSecondCardInfo();

        int startBalanceFirstCard = dashboardPage.getCardBalanceByID(firstCard.getCardId());
        int startBalanceSecondCard = dashboardPage.getCardBalanceByID(secondCard.getCardId());

//        выбираем карты для пополнения:

        var transferPage = dashboardPage.replenishCard(firstCard.getCardId());

        int transferAmount = startBalanceFirstCard / 2;

//        заполняем форму перевода: сумму и номер карты с которой должен списаться перевод
        transferPage.fillTransferForm(transferAmount, secondCard.getCardNumber());

        int finishBalanceFirstCard = dashboardPage.getCardBalanceByID(firstCard.getCardId());
        int finishBalanceSecondCard = dashboardPage.getCardBalanceByID(secondCard.getCardId());

        assert finishBalanceFirstCard == startBalanceFirstCard + transferAmount;
        assert finishBalanceSecondCard == startBalanceSecondCard - transferAmount;
    }
}
