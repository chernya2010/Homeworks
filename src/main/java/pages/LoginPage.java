package pages;

import constants.IConstants;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage implements IConstants {
    @FindBy(xpath = "//*[@id='user-name']")
    WebElement usernameInput;

    @FindBy(xpath = "//*[@id='password']")
    WebElement passwordInput;

    @FindBy(xpath = "//*[@id='login-button']")
    WebElement loginButton;

    @FindBy(xpath = "//*[@data-test='error']")
    WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Open page login page.
     *
     * @return the login page
     */
    @Step("Open Login Page")
    public LoginPage openPage() {
        driver.get(LOGIN_PAGE_URL);
        return this;
    }

    /**
     * This method logins on site
     * @param username
     * @param password
     * @return ProductsPage
     */
    public ProductsPage login(String username, String password) {
        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginButton.click();
        return new ProductsPage(driver);
    }

    /**
     * Gets error message text.
     *
     * @return the error message text
     */
    public String getErrorMessageText() {
        return errorMessage.getText();
    }

    /**
     * Wait for page opened login page.
     *
     * @return the login page
     */
    public LoginPage waitForPageOpened() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOf(loginButton));
        return this;
    }

    //example of fluent wait
//    public void waitForPageOpenedWithFluent() {
//        Wait<WebDriver> fluent = new FluentWait<>(driver)
//                .withTimeout(Duration.ofSeconds(30))
//                .pollingEvery(Duration.ofSeconds(5))
//                .ignoring(NoSuchElementException.class);
//        fluent.until(ExpectedConditions.visibilityOf(driver.findElement(LOGIN_BUTTON)));
//    }
}
