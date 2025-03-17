package ru.netology.pageobject.test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
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

        verificationPage.verifyLogin(verificationCode.getCode());

        var dashboardPage = new DashboardPage();
        String firstCardID = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
        String secondCardID = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
        String firstCardNumber = dashboardPage.getCardNumberByID(firstCardID);
        String secondCardNumber = dashboardPage.getCardNumberByID(secondCardID);
        int startBalanceFirstCard = dashboardPage.getCardBalanceByID(firstCardID);
        int startBalanceSecondCard = dashboardPage.getCardBalanceByID(secondCardID);

        dashboardPage.replenishCard(secondCardID);

        var transferPage = new TransferPage();
        int transferAmount = 1000;
        transferPage.fillTransferForm(transferAmount, firstCardNumber);

        int finishBalanceFirstCard = dashboardPage.getCardBalanceByID(firstCardID);
        int finishBalanceSecondCard = dashboardPage.getCardBalanceByID(secondCardID);

        assert finishBalanceFirstCard == startBalanceFirstCard - transferAmount;
        assert finishBalanceSecondCard == startBalanceSecondCard + transferAmount;
    }
}
