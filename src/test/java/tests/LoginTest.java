package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.PropertyReader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static utils.AllureUtils.takeScreenshot;


public class
LoginTest extends BaseTest {

    @Epic("Модуль логина интернет-магазина")
    @Feature("TMS-56")
    @Story("TNS-56.67")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Anna")
    @TmsLink("UrnSu")
    @Test(description = "авторизация под верными данными")
    @Issue("3")
    @Description("Проверка входа в систему интернет-магазина")
    @Flaky
    public void correctLogin() {
        loginPage
                .open()
                .login(user, password);
        assertEquals(productsPage.getTitle(), "Products");
        assertTrue(productsPage.isDispl(), "");
        takeScreenshot(driver);
    }

    @DataProvider()
    public Object[][] loginData() {
        String user = PropertyReader.getProperty("sauce.user");
        String password = PropertyReader.getProperty("sauce.password");
        return new Object[][]{
                {"locked_out_user", password, "Epic sadface: Sorry, this user has been locked out."},
                {user, "", "Epic sadface: Password is required"},
                {"", password, "Epic sadface: Username is required"}
        };
    }

    @Test(dataProvider = "loginData")
    public void lockedUserLogin(String user, String pass, String errorMsg) {
        loginPage
                .open()
                .login(user, pass);
        assertEquals(loginPage.getErrorMessage(), errorMsg);
    }
}
