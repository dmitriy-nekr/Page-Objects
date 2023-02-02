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
        var card1Info = DataHelper.getCardInfo();
        var card2Info = DataHelper.getOtherCardInfo();
        var start1Amount = dashboardPage.getCardBalance(card1Info);
        var start2Amount = dashboardPage.getCardBalance(card2Info);
        var amount = 2000;
        var transferPage = dashboardPage.additionClick(1);
        transferPage.moneyTransfer(amount, card2Info);
        var expected1 = start1Amount + amount;
        var expected2 = start2Amount - amount;
        var actual1 = dashboardPage.getCardBalance(card1Info);
        var actual2 = dashboardPage.getCardBalance(card2Info);
        Assertions.assertEquals(expected1, actual1);
        Assertions.assertEquals(expected2, actual2);

    }
}

