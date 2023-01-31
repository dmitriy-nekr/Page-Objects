package ru.netology.web.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.SelenideElement;
import lombok.val;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
  private SelenideElement heading = $("[data-test-id=dashboard]");
  private final String balanceStart = "баланс: ";
  private final String balanceFinish = " р.";
  private ElementsCollection buttons  = $$("[data-test-id = action-deposit]");




  public DashboardPage() {
    heading.shouldBe(visible);
  }

  public DashboardPage addMoneyAccount (int numberAccount,int sourceAccount, int amount){
    buttons.get(numberAccount-1).click();
    String amountString = Integer.toString(amount);
    $("[data-test-id =amount] input").setValue(amountString);
    $("[data-test-id=from] input").setValue("5559 0000 0000 000"+sourceAccount);
    $("[data-test-id = action-transfer]").click();
    return new DashboardPage();
  }
  public int getCardBalance(String id) {
    // TODO: перебрать все карты и найти по атрибуту data-test-id
    val text = $("[data-test-id='"+id+"']").text();
    return extractBalance(text);
  }

  private int extractBalance(String text) {
    val start = text.indexOf(balanceStart);
    val finish = text.indexOf(balanceFinish);
    val value = text.substring(start + balanceStart.length(), finish);
    return Integer.parseInt(value);
  }



  }

