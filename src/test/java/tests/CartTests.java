package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static tests.ITestConstants.*;

public class CartTests extends BaseTest {
    //1) добавить товар в корзину и проверить, что у него отображается верная цена
    //2) удалить товар из корзины и проверить, что он удалился
    //3) добавть 2 товара в корзину и проверить, что количество добавленных товаров = 2

    /**
     * Products object [ ].
     *
     * @return the object [ ]
     */
    @DataProvider(name = "products")
    public Object[] products () {
        return new Object[] {
                SAUCE_LABS_BACKPACK,
                SAUCE_LABS_BIKE_LIGHT,
                SAUCE_LABS_BOLT_T_SHIRT,
                SAUCE_LABS_FLEECE_JACKET,
                SAUCE_LABS_ONESIE,
                T_SHIRT_RED
        };
    }

    /**
     * Products and price data object [ ] [ ].
     *
     * @return the object [ ] [ ]
     */
    @DataProvider(name = "productsAndPriceData")
    public Object[][] productsAndPriceData() {
        return new Object[][] {
                {SAUCE_LABS_BACKPACK, "$29.99"},
                {SAUCE_LABS_BIKE_LIGHT, "$9.99"},
                {SAUCE_LABS_BOLT_T_SHIRT, "$15.99"},
                {SAUCE_LABS_FLEECE_JACKET, "$49.99"},
                {SAUCE_LABS_ONESIE, "$7.99"},
                {T_SHIRT_RED, "$15.99"}
        };
    }

    /**
     * Add product to cart test.
     *
     * @param productName the product name
     * @param price       the price
     */
    @Test(alwaysRun = true, dataProvider = "productsAndPriceData")
    public void addProductToCartTest(String productName, String price) {
        productSteps.loginAndAddProduct(USERNAME, PASSWORD, productName);
        cartPage.openPage();
        Assert.assertEquals(cartPage.getProductPrice(productName), price);
    }

    /**
     * Add product to cart test w ith error.
     */
    @Test()
    public void addProductToCartTestWIthError() {
        productSteps.loginAndAddProduct(USERNAME, PASSWORD, SAUCE_LABS_BACKPACK);
        cartSteps.openCartPage();
        Assert.assertEquals(cartPage.getProductPrice(SAUCE_LABS_BACKPACK), "16");
    }

    /**
     * Remove one of products from cart test.
     */
    @Test
    public void removeOneOfProductsFromCartTest() {
        loginSteps.loginAndWaitForPageOpened(USERNAME, PASSWORD);
        productSteps
                .addProductToCart(SAUCE_LABS_BACKPACK)
                .addProductToCart(SAUCE_LABS_FLEECE_JACKET);
        cartSteps.openCartPageAndRemoveProduct(SAUCE_LABS_BACKPACK);
        Assert.assertEquals(cartPage.getProductsCount(), 1);
    }

    /**
     * Remove product from cart test.
     *
     * @param productName the product name
     */
    @Test(dataProvider = "products", groups = "dataProvider")
    public void removeProductFromCartTest(String productName) {
        productSteps.loginAndAddProduct(USERNAME, PASSWORD, productName);
        cartSteps.openCartPageAndRemoveProduct(productName);
        Assert.assertFalse(cartPage.isProductDisplayed(productName));
    }

    /**
     * Add two products to cart and check count test.
     */
    @Test
    public void addTwoProductsToCartAndCheckCountTest() {
        loginSteps.loginAndWaitForPageOpened(USERNAME, PASSWORD);
        productSteps
                .addProductToCart(SAUCE_LABS_BACKPACK)
                .addProductToCart(SAUCE_LABS_FLEECE_JACKET);
        cartSteps.openCartPage();
        Assert.assertEquals(cartPage.getProductsCount(), 2);
    }
}
