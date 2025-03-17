package ru.netology.pageobject.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import lombok.val;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    //    private final ElementsCollection cards = $$("[data-test-id='card-balance'] div");
    private final ElementsCollection cards = $$("li.list__item div");


    //    private final SelenideElement firstCardBalance = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private final SelenideElement firstCardReplenishButton = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");

    //    private final SelenideElement secondCardBalance = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private final SelenideElement secondCardReplenishButton = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");

    public DashboardPage() {
        $("[data-test-id='dashboard']")
                .shouldBe(Condition.visible, Duration.ofSeconds(5));
    }

//    public int getFirstCardBalance() {
//        return extractBalance(firstCardBalance.getText());
//    }
//
//    public int getSecondCardBalance() {
//        return extractBalance(secondCardBalance.getText());
//    }

    //    метод извлечения баланса карточки с id = cardID
    public int getCardBalanceByID(String cardID) {
        return extractBalance(findCardByID(cardID).getText());
    }

    public String getCardNumberByID(String cardID) {
        String text = findCardByID(cardID).text();
        String cardNumberStart = "**** **** **** ";
        int start = text.indexOf(cardNumberStart) + cardNumberStart.length();
        int finish = text.indexOf(",", start); // Ищем запятую после номера карты
        return text.substring(start, finish).trim(); // Возвращаем номер карты
    }

    //     метод поиска карты по ID в списке элементов data-test-id
    private SelenideElement findCardByID(String cardID) {
        return cards.findBy(Condition.attribute("data-test-id", cardID));
    }

    public void replenishCard(String cardId){
        $("[data-test-id='"+cardId +"'] button[data-test-id='action-deposit']").click();
    }


    public void replenishSecondCard() {
        $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button[data-test-id='action-deposit']").click();
    }

    private int extractBalance(String text) {
        String balanceStart = "баланс: ";
        String balanceFinish = " р.";
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);

        return Integer.parseInt(value);
    }


}
