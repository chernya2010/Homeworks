package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Log4j2
public class ProductsPage extends HeaderPage {
    public static final By PRODUCTS = By.xpath("//*[@data-test='title']");
    public static final String PRODUCT_ITEM = "//*[text()='%s']/ancestor::*[@class" +
            "='inventory_item']";
    public static final String ADD_PRODUCT_TO_CART_BUTTON = PRODUCT_ITEM + "//button[contains" +
            "(text(), 'Add')]";
    public static final String REMOVE_PRODUCT_FROM_CART_BUTTON = PRODUCT_ITEM + "//button" +
            "[contains(text(), 'Remove')]";
    private static final String PRODUCT_PRICE = PRODUCT_ITEM + "//*[@class='inventory_item_price']";

    /**
     * Instantiates a new Products page.
     *
     * @param driver the driver
     */
    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Gets product text.
     *
     * @return the product text
     */
    public String getProductText() {
        return driver.findElement(PRODUCTS).getText();
    }

    /**
     * Add product to cart products page.
     *
     * @param productName the product name
     * @return the products page
     */
    @Step("Add product: {productName} to Cart")
    public ProductsPage addProductToCart(String productName) {
        log.info(String.format("Add product %s to cart", productName));
        driver.findElement(By.xpath(String.format(ADD_PRODUCT_TO_CART_BUTTON, productName))).click();
        return this;
    }

    /**
     * Is add to cart button displayed boolean.
     *
     * @param productName the product name
     * @return the boolean
     */
    public boolean isAddToCartButtonDisplayed(String productName) {
        return driver.findElement(By.xpath(String.format(ADD_PRODUCT_TO_CART_BUTTON, productName))).isDisplayed();
    }

    /**
     * Is remove button displayed boolean.
     *
     * @param productName the product name
     * @return the boolean
     */
    public boolean isRemoveButtonDisplayed(String productName) {
        return driver.findElement(By.xpath(String.format(REMOVE_PRODUCT_FROM_CART_BUTTON, productName))).isDisplayed();
    }

    /**
     * This method gets product price
     * @param productName
     * @return String 'price of the product'
     */
    public String getProductPrice(String productName) {
        log.info(String.format("Get price for product: %s", productName));
        return driver.findElement(By.xpath(String.format(PRODUCT_PRICE, productName))).getText();
    }
}
