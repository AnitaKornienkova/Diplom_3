package ru.praktikum.tests.profile;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ru.praktikum.client.UserApiClient;
import ru.praktikum.model.UserData;
import ru.praktikum.page_object.*;

public class ProfileManagementTest {
    private WebDriver driver;
    private String token;

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

        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site";

        token = UserApiClient.registerUser(UserData.TEST_USER);
    }

    @Test
    public void openUserProfilePage() {
        HomePage homePage = new HomePage(driver);
        homePage.waitUntilPageIsLoaded();
        homePage.clickOnLogInButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitUntilPageIsLoaded();
        loginPage.login(UserData.TEST_USER.toCredentials());

        homePage.waitUntilPageIsLoaded();
        homePage.clickOnProfileButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitUntilPageIsLoaded();
    }

    @Test
    public void logout() {
        HomePage homePage = new HomePage(driver);
        homePage.waitUntilPageIsLoaded();
        homePage.clickOnLogInButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitUntilPageIsLoaded();
        loginPage.login(UserData.TEST_USER.toCredentials());

        homePage.waitUntilPageIsLoaded();
        homePage.clickOnProfileButton();

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.waitUntilPageIsLoaded();
        profilePage.clickOnLogoutButton();

        loginPage.waitUntilPageIsLoaded();
    }

    @After
    public void cleanUp() {
        UserApiClient.removeUser(token);

        driver.quit();
    }
}
