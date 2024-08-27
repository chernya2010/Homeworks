package pages;

import constants.IConstants;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@Log4j2
public class CartPage extends HeaderPage implements IConstants {
    public static final String PRODUCT_ITEM = "//*[text()='%s']/ancestor::*[@class='cart_item']";
    public static final String PRODUCT_PRICE = PRODUCT_ITEM + "//*[@class='inventory_item_price']";
    public static final String PRODUCT_REMOVE = PRODUCT_ITEM + "//button";
    public static final String PRODUCTS_CONTAINER = "//*[@class='cart_item']";

    public CartPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Open page cart page.
     *
     * @return the cart page
     */
    @Step("Opening Cart Page")
    public CartPage openPage() {
        driver.get(CART_PAGE_URL);
        log.info("Open Cart Page URL " + CART_PAGE_URL);
        return this;
    }

    /**
     *
     * @param productName
     * @return
     */
    public void removeProductFromCart(String productName) {
        driver.findElement(By.xpath(String.format(PRODUCT_REMOVE, productName))).click();
        log.info(String.format("Remove %s product from cart", productName));
    }

    /**
     * This method gets product price.
     * @param productName
     * @return the product price
     */
    public String getProductPrice(String productName){
        String productPrice = driver.findElement(By.xpath(String.format(PRODUCT_PRICE, productName))).getText();
        log.info(String.format("Get price for product: %s. Price: %s", productName, productPrice));
        return productPrice;
    }

    /**
     * This method gets quantity of products
     * @return size of webElements list
     */
    public int getProductsCount() {
        int count = driver.findElements(By.xpath(PRODUCTS_CONTAINER)).size();
        log.info(String.format("Products count: %s", count));
        return count;
    }

    /**
     *  Is product displayed (boolean)
     * @param productName
     * @return boolean
     */
    public boolean isProductDisplayed(String productName) {
        return !driver.findElements(By.xpath(String.format(PRODUCT_ITEM, productName))).isEmpty();
    }
}
