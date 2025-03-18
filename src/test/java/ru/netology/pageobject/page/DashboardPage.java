package ru.netology.pageobject.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import lombok.val;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private final ElementsCollection cards = $$("li.list__item div");


    public DashboardPage() {
        $("[data-test-id='dashboard']")
                .shouldBe(Condition.visible, Duration.ofSeconds(5));
    }

    //    метод извлечения баланса карточки с id = cardID
    public int getCardBalanceByID(String cardID) {
        return extractBalance(findCardByID(cardID).getText());
    }

    //    метод получения номера карты по cardID
//    public String getCardNumberByID(String cardID) {
//        String text = findCardByID(cardID).text();
//        String cardNumberStart = "**** **** **** ";
//        int start = text.indexOf(cardNumberStart) + cardNumberStart.length();
//        int finish = text.indexOf(",", start); // Ищем запятую после номера карты
//        return text.substring(start, finish).trim(); // Возвращаем номер карты
//    }

    //     метод поиска карты по ID в списке элементов data-test-id
    private SelenideElement findCardByID(String cardID) {
        return cards.findBy(Condition.attribute("data-test-id", cardID));
    }

    //    пополнение карты
    public TransferPage replenishCard(String cardId) {
        $("[data-test-id='" + cardId + "'] button[data-test-id='action-deposit']").click();
        return new TransferPage();
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
