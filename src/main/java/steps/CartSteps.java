package steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.CartPage;

public class CartSteps {
    private CartPage cartPage;

    public CartSteps(WebDriver driver) {
        cartPage = new CartPage(driver);
    }

    /**
     * Open cart page and remove product.
     *
     * @param productName the product name
     */
    @Step("Remove product:{productName} from cart")
    public void openCartPageAndRemoveProduct(String productName) {
        cartPage
                .openPage()
                .removeProductFromCart(productName);
    }

    /**
     * Open cart page.
     */
    @Step("Open Cart Page")
    public void openCartPage() {
        cartPage
                .openPage();
    }
}
