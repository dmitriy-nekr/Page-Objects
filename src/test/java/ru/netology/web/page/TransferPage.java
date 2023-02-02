package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class TransferPage {
    private SelenideElement additionText = $x("//h1[contains(text(), 'Пополнение карты')]");
    private SelenideElement amountOfAddition = $("[data-test-id =amount] input");
    private SelenideElement sourceAccount = $("[data-test-id=from] input");
    private SelenideElement buttonTransfer = $("[data-test-id = action-transfer]");

    public TransferPage() {
        additionText.shouldBe(Condition.visible);
    }

    public DashboardPage moneyTransfer(int amount, DataHelper.CardsInfo cardsInfo) {
        String amountString = Integer.toString(amount);
        amountOfAddition.setValue(amountString);
        sourceAccount.setValue(cardsInfo.getNumberAccount());
        buttonTransfer.click();
        return new DashboardPage();
    }
}