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

//        $("[data-test-id='login'] input.input__control").setValue(info.getLogin());
//        $("[data-test-id='password'] input.input__control").setValue(info.getPassword());
//        $("[data-test-id='action-login']").click();
//        var loginPage = new LoginPage();
        var loginPage = new LoginPageWithFields();
        var verificationPage = loginPage.validLogin(info);

        $("[data-test-id='code'] input.input__control")
                .shouldBe(Condition.visible, Duration.ofSeconds(5));

// Перенесено в конструктор public DashboardPage()
//        $("[data-test-id='code'] input.input__control").setValue(verificationCode.getCode());
//        $("[data-test-id='action-verify']").click();


        verificationPage.verifyLogin(verificationCode.getCode());

        $("[data-test-id='dashboard']")
                .shouldBe(Condition.visible, Duration.ofSeconds(5));

        var dashboardPage = new DashboardPage();
        String firstCardID = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
        String secondCardID = "0f3f5c2a-249e-4c3d-8287-09f7a039391d";
        String firstCardNumber = dashboardPage.getCardNumberByID(firstCardID);
        String secondCardNumber = dashboardPage.getCardNumberByID(secondCardID);
        int startBalanceFirstCard = dashboardPage.getCardBalanceByID(firstCardID);
        int startBalanceSecondCard = dashboardPage.getCardBalanceByID(secondCardID);

//        $("[data-test-id='action-deposit']").click();
        dashboardPage.replenishCard(secondCardID);

//        $("[data-test-id='dashboard'] heading.heading").
//                shouldBe(Condition.visible, Duration.ofSeconds(5))
//                .shouldHave(Condition.text("Пополнение карты"));

//       Перенесено в конструктор TransferPage
//        $$("h1").findBy(Condition.text("Пополнение карты"))
//                .shouldBe(Condition.visible, Duration.ofSeconds(5));


        var transferPage = new TransferPage();
        int transferAmount = 1000;
        transferPage.fillTransferForm(transferAmount, firstCardNumber);

        int finishBalanceFirstCard = dashboardPage.getCardBalanceByID(firstCardID);
        int finishBalanceSecondCard = dashboardPage.getCardBalanceByID(secondCardID);
        System.out.println("Starting First:  " + startBalanceFirstCard);
        System.out.println("Starting Second:  " + startBalanceSecondCard);
        System.out.println("Finish First: " + finishBalanceFirstCard);
        System.out.println("Finish Second: " + finishBalanceSecondCard);
        System.out.println(firstCardNumber);
        System.out.println(secondCardNumber);

        assert finishBalanceFirstCard == startBalanceFirstCard - transferAmount;
        assert finishBalanceSecondCard == startBalanceSecondCard + transferAmount;
//        $(dashboardPage.getFirstCardBalance)
//                .shouldHave(Condition.text(String.valueOf(startBalanceFirstCard + transferAmount)));
//
//        $(dashboardPage.secondCardBalance)
//                .shouldHave(Condition.text(String.valueOf(startBalanceSecondCard - transferAmount)));

    }
}
