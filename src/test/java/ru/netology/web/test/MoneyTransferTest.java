package ru.netology.web.test;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPageV1;


import static com.codeborne.selenide.Selenide.open;

class MoneyTransferTest {
  @Test
  void shouldTransferMoneyBetweenOwnCardsV1() {
    open("http://localhost:9999");
    var loginPage = new LoginPageV1();

    var authInfo = DataHelper.getAuthInfo();
    var verificationPage = loginPage.validLogin(authInfo);
    var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
    var dashboardPage = verificationPage.validVerify(verificationCode);
    var startAmount = dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0");
    var addAmount = 2000;
    dashboardPage.addMoneyAccount(1, 2,addAmount);
    int expected = startAmount + addAmount;
    int actual = dashboardPage.getCardBalance("92df3f1c-a033-48e6-8390-206f6b1f56c0");
    Assertions.assertEquals(expected, actual);


  }
}

