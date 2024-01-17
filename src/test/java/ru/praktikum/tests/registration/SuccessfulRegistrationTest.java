package ru.praktikum.tests.registration;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.praktikum.client.UserApiClient;
import ru.praktikum.model.UserData;
import ru.praktikum.page_object.HomePage;
import ru.praktikum.page_object.LoginPage;
import ru.praktikum.page_object.RegistrationPage;

import static org.junit.Assert.assertTrue;
import static ru.praktikum.tests.utils.Constants.STELLAR_BURGERS_URI;

public class SuccessfulRegistrationTest {
    private WebDriver driver;

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
        driver.get(STELLAR_BURGERS_URI);

        RestAssured.baseURI = STELLAR_BURGERS_URI;
    }

    @Test
    public void successfulRegistration() {
        HomePage homePage = new HomePage(driver);
        homePage.waitUntilPageIsLoaded();
        homePage.clickOnLogInButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitUntilPageIsLoaded();
        loginPage.clickOnRegisterButton();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.waitUntilPageIsLoaded();
        registrationPage.registerUser(UserData.TEST_USER);

        WebElement expectedElement = loginPage.waitUntilPageIsLoaded();
        assertTrue(expectedElement.isDisplayed());
    }

    @After
    public void cleanUp() {
        String token = UserApiClient.authUser(UserData.TEST_USER.toCredentials());
        UserApiClient.removeUser(token);

        driver.quit();
    }
}
