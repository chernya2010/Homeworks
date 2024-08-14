package tests;

import listeners.Retry;
import org.testng.Assert;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import utils.PropertyReader;

public class LoginTests extends BaseTest implements ITestConstants {
    private static final String EMPTY_USERNAME_ERROR_TEXT = "Epic sadface: Username is required";
    private static final String EMPTY_USERNAME_ERROR_TEXT_ERROR = "Epic sadface: Username is " +
            "required";
    private static final String EMPTY_PASSWORD_ERROR_TEXT = "Epic sadface: Password is required";
    private static final String INCORRECT_DATA_ERROR_TEXT = "Epic sadface: Username and password do not match any user in this service";

    /**
     * Login with empty fields test.
     */
    @Test()
    public void loginWithEmptyFieldsTest() {
        loginSteps.loginAndWaitForPageOpened("", "");
        Assert.assertEquals(loginPage.getErrorMessageText(), EMPTY_USERNAME_ERROR_TEXT);
    }

    /**
     * Login with empty fields test with error.
     */
    @Test(retryAnalyzer = Retry.class)
    public void loginWithEmptyFieldsTestWithError() {
        loginSteps.loginAndWaitForPageOpened("", "");
        Assert.assertEquals(loginPage.getErrorMessageText(), EMPTY_USERNAME_ERROR_TEXT_ERROR);
    }

    /**
     * Login with empty username test.
     */
    @Test
    public void loginWithEmptyUsernameTest() {
        loginSteps.loginAndWaitForPageOpened("", PASSWORD);
        Assert.assertEquals(loginPage.getErrorMessageText(), EMPTY_USERNAME_ERROR_TEXT);
    }

    /**
     * Login with empty password test.
     */
    @Test
    public void loginWithEmptyPasswordTest() {
        loginSteps.loginAndWaitForPageOpened(USERNAME, "");
        Assert.assertEquals(loginPage.getErrorMessageText(), EMPTY_PASSWORD_ERROR_TEXT);
    }

    /**
     * Login with incorrect data test.
     */
    @Test
    public void loginWithIncorrectDataTest() {
        loginSteps.loginAndWaitForPageOpened("evnev", "evev");
        Assert.assertEquals(loginPage.getErrorMessageText(), INCORRECT_DATA_ERROR_TEXT);
    }

    /**
     * Login with incorrect data test with parameters.
     *
     * @param username the username
     * @param password the password
     */
    @Test
    @Parameters({"username", "password"})
    public void loginWithIncorrectDataTestWithParameters(@Optional("optinalUsername") String username,
            @Optional("optinalPassword") String password) {
        loginSteps.loginAndWaitForPageOpened(username, password);
        Assert.assertEquals(loginPage.getErrorMessageText(), INCORRECT_DATA_ERROR_TEXT);
    }

    /**
     * Login correct data test.
     */
    @Test()
    public void loginCorrectDataTest() {
        loginSteps.loginAndWaitForPageOpened(USERNAME, PASSWORD);
        Assert.assertEquals(productsPage.getProductText(), "Products");
    }

    /**
     * Login correct data test with system parameters.
     */
    @Test()
    public void loginCorrectDataTestWithSystemParameters() {
        loginSteps.loginAndWaitForPageOpened(
                System.getProperty("username", "123"),
                System.getProperty("password", "123"));
        Assert.assertEquals(productsPage.getProductText(), "Products");
    }

    /**
     * Login correct data test with config parameters.
     */
    @Test()
    public void loginCorrectDataTestWithConfigParameters() {
        loginSteps.loginAndWaitForPageOpened(
                System.getProperty("username", PropertyReader.getProperty("username")),
                System.getProperty("password", PropertyReader.getProperty("password")));
        Assert.assertEquals(productsPage.getProductText(), "Products");
    }

    //отличие обычного использования локаторов и PageFactory
//    @FindBy(xpath = "//*[contains(text(),'Add ')]")
//    WebElement addButton;
//
//    @FindBy(xpath = "//button[contains(text(),'Delete')]")
//    WebElement deleteButton;
//
//    @Test
//    public void addWithoutPageFactory() {
//        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
//        WebElement addButton = driver.findElement(By.xpath("//*[contains(text(),'Add ')]"));
//        addButton.click();
//        WebElement deleteButton = driver.findElement(By.xpath("//button[contains(text(),'Delete')]"));
//        deleteButton.click();
//        addButton.click();
//        deleteButton.click();
//    }
//
//    @Test
//    public void addWithPageFactory() {
//        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");
//        WebElement addButtonPageFactory = addButton;
//        addButtonPageFactory.click();
//        WebElement deleteButtonPageFactory = deleteButton;
//        deleteButtonPageFactory.click();
//        addButtonPageFactory.click();
//        deleteButtonPageFactory.click();
//    }
}
