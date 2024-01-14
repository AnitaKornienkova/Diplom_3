package ru.praktikum.tests.constructor;

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
import ru.praktikum.page_object.HomePage;
import ru.praktikum.page_object.LoginPage;
import ru.praktikum.page_object.ProfilePage;

public class ConstructorTest {
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
    public void openConstructorByHomeButton() {
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

        profilePage.clickOnLogo();
        homePage.waitUntilPageIsLoaded();
    }

    @Test
    public void openConstructorByConstructorButton() {
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

        profilePage.clickOnConstructorButton();
        homePage.waitUntilPageIsLoaded();
    }

    @Test
    public void focusOnBunsInConstructor() {
        HomePage homePage = new HomePage(driver);
        homePage.waitUntilPageIsLoaded();
        homePage.clickOnLogInButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitUntilPageIsLoaded();
        loginPage.login(UserData.TEST_USER.toCredentials());

        homePage.waitUntilPageIsLoaded();
        homePage.clickOnSauces();
        homePage.checkBunsParentElementClassIsNotSelected();

        homePage.clickOnBuns();
    }

    @Test
    public void focusOnSaucesInConstructor() {
        HomePage homePage = new HomePage(driver);
        homePage.waitUntilPageIsLoaded();
        homePage.clickOnLogInButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitUntilPageIsLoaded();
        loginPage.login(UserData.TEST_USER.toCredentials());

        homePage.waitUntilPageIsLoaded();
        homePage.checkSaucesParentElementClassNotSelected();

        homePage.clickOnSauces();
    }

    @Test
    public void focusOnFillingsInConstructor() {
        HomePage homePage = new HomePage(driver);
        homePage.waitUntilPageIsLoaded();
        homePage.clickOnLogInButton();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.waitUntilPageIsLoaded();
        loginPage.login(UserData.TEST_USER.toCredentials());

        homePage.waitUntilPageIsLoaded();
        homePage.checkFillingsParentElementNotSelected();

        homePage.clickOnFillings();
    }

    @After
    public void cleanUp() {
        UserApiClient.removeUser(token);

        driver.quit();
    }
}
