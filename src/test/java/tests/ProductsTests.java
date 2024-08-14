package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProductsTests extends BaseTest implements ITestConstants {

    /**
     * Is add to cart button displayed test.
     */
    @Test
    public void isAddToCartButtonDisplayedTest() {
        loginSteps.loginAndWaitForPageOpened(USERNAME, PASSWORD);
        Assert.assertTrue(productsPage.isAddToCartButtonDisplayed(SAUCE_LABS_BACKPACK));
    }

    /**
     * Is remove button displayed test.
     */
    @Test
    public void isRemoveButtonDisplayedTest() {
        productSteps.loginAndAddProduct(USERNAME, PASSWORD, SAUCE_LABS_BACKPACK);
        Assert.assertTrue(productsPage.isRemoveButtonDisplayed(SAUCE_LABS_BACKPACK));
    }
}
