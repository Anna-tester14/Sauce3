package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class LoginPage extends BasePage {

    private static final By USERNAME_INPUT = By.id("user-name");
    private static final By PASSWORD_INPUT = By.id("password");
    private static final By LOGIN_BUTTON = By.id("login-button");
    private final By errorMessage = By.cssSelector("h3");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие браузера")
    public LoginPage open() {
        driver.get(BASE_URL);
        return this;
    }

    @Step("Вводим данные {user} и {password}")
    public LoginPage login(String user, String password) {
        fillUserInput(user);
        fillPasswordInput(password);
        clickSubmitBtn();
        return this;
    }

    public void fillUserInput(String user) {
        driver.findElement(USERNAME_INPUT).sendKeys(user);
    }

    public void fillPasswordInput(String password) {
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
    }

    public void clickSubmitBtn() {
        driver.findElement(LOGIN_BUTTON).submit();
    }

    @Step("Получаем текст из сообщения об ошибке")
    public String getErrorMessage() {
        return driver.findElement(errorMessage).getText();
    }
}
