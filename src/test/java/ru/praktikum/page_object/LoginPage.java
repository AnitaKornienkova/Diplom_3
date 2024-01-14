package ru.praktikum.page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikum.model.UserCredentials;
import ru.praktikum.model.UserData;

public class LoginPage {
    private final WebDriver driver;

    private final By appHeaderLogo = By.xpath(".//a[@class='active' and @aria-current='page']");
    private final By registerButton = By.xpath("//a[@href='/register']");
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    private final By emailInput = By.xpath(".//input[@type='text']");
    private final By passwordInput = By.xpath(".//input[@type='password']");
    private final By forgotPasswordButton = By.xpath("//a[@href='/forgot-password']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидаем загрузки страницы авторизации")
    public void waitUntilPageIsLoaded() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    @Step("Нажимаем на кнопку \"Зарегистрироваться\"")
    public void clickOnRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Логинимся")
    public void login(UserCredentials userCredentials) {
        setEmail(userCredentials.getEmail());
        setPassword(userCredentials.getPassword());
        clickOnLoginButton();
    }

    @Step("Устанавливаем email")
    private void setEmail(String email) {
        WebElement emailInputElement = driver.findElement(emailInput);
        emailInputElement.click();
        emailInputElement.clear();
        emailInputElement.sendKeys(email);
    }

    @Step("Устанавливаем пароль")
    private void setPassword(String password) {
        WebElement passwordInputElement = driver.findElement(passwordInput);
        passwordInputElement.click();
        passwordInputElement.clear();
        passwordInputElement.sendKeys(password);
    }

    @Step("Нажимаем кнопку \"Войти\"")
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажимаем кнопку \"Восстановить пароль\"")
    public void clickOnForgotPasswordButton() {
        driver.findElement(forgotPasswordButton).click();
    }
}
