package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;
import steps.CartSteps;
import steps.LoginSteps;
import steps.ProductSteps;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

@Listeners(TestListener.class)
public class BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    ProductSteps productSteps;
    CartSteps cartSteps;
    LoginSteps loginSteps;

    /**
     * Init test.
     */
    @BeforeMethod
    public void initTest(ITestContext iTestContext) {
        WebDriverManager.chromedriver().setup();//скачиваем хромдрайвер и сетаем его в системные
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_settings.popups", 0);
        System.setProperty("user.dir", System.getProperty("user.dir") + "\\src\\test\\java\\tests");
        chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);
        driver = new ChromeDriver(options);
        // настройки
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless=new");
//        driver = new ChromeDriver(options);//инициализируем объект вебдрайвера
//        if (System.getProperty("browser").equals("chrome")) {
//            driver = new ChromeDriver();//инициализируем объект вебдрайвера
//        } else if(System.getProperty("browser").equals("firefox")) {
//            driver = new FirefoxDriver();//инициализируем объект вебдрайвера
//        }
//        driver = new ChromeDriver();//инициализируем объект вебдрайвера
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        initPages();
        PageFactory.initElements(driver, this);
        iTestContext.setAttribute("driver", driver);
    }

    /**
     * Init pages.
     */
    public void initPages() {
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        cartSteps = new CartSteps(driver);
        productSteps = new ProductSteps(driver);
        loginSteps = new LoginSteps(driver);
    }

    /**
     * End test.
     */
    @AfterMethod(alwaysRun = true)
    public void endTest() {
        driver.quit();
    }

    public boolean isFileDownloaded(String downloadFileName) {
        File folder = new File(System.getProperty("user.dir"));
        File[] listOfFiles = folder.listFiles();
        boolean found = false;
        File f = null;
        for (File listOfFile : listOfFiles) {
            if (listOfFile.isFile()) {
                String nameOfTheFile = listOfFile.getName();
                System.out.println("File " + listOfFile.getName());
                if (nameOfTheFile.matches(downloadFileName)) {
                    f = new File(nameOfTheFile);
                    found = true;
                }
            }
        }
        return found;
    }

    @Test
    public void fileDownloadTest() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/download");
        driver.findElement(By.xpath(String.format("//a[@href='download/%s']", "some-file.txt"))).click();
        Assert.assertTrue(isFileDownloaded("some-file.txt"));
    }
}
