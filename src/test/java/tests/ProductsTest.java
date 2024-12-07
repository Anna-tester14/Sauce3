package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import static utils.AllureUtils.takeScreenshot;

public class ProductsTest extends BaseTest {
    @Test
    public void addGoods() {
        loginPage
                .open()
                .login(user, password);
        productsPage.addToCart("Sauce Labs Bolt T-Shirt");
        productsPage.openCart();
        takeScreenshot(driver);
        assertTrue(productsPage.getProductsNames().contains("Sauce Labs Bolt T-Shirt"));
    }
}
