package ru.praktikum.tests.registration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.praktikum.model.UserData;
import ru.praktikum.page_object.HomePage;
import ru.praktikum.page_object.LoginPage;
import ru.praktikum.page_object.RegistrationPage;

import static junit.framework.TestCase.assertEquals;

@RunWith(Parameterized.class)
public class TooShortPasswordRegistrationTest {
    private WebDriver driver;

    private final String password;

    public TooShortPasswordRegistrationTest(String password) {
        this.password = password;
    }

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--no-sandbox",
                "--headless",
                "--disable-dev-shm-usage"
        );
        // System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        // options.setBinary("C:\\Users\\annav\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        driver = new ChromeDriver(options);
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Test
    public void tooShortPassword() {
        HomePage homePage = new HomePage(driver);
        homePage.waitUntilPageIsLoaded();
        homePage.clickOnLogInButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitUntilPageIsLoaded();
        loginPage.clickOnRegisterButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.waitUntilPageIsLoaded();

        UserData userData = new UserData(UserData.EMAIL, password, UserData.NAME);
        registrationPage.registerUser(userData);

        registrationPage.waitForIncorrectPasswordElement();
    }

    @After
    public void cleanUp() {
        driver.quit();
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"1"},
                {"22"},
                {"333"},
                {"4444"},
                {"55555"}
        };
    }
}
