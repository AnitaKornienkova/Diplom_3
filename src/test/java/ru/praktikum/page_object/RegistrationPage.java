package ru.praktikum.page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.praktikum.model.UserData;

public class RegistrationPage {
    private final WebDriver driver;

    private final By nameInput = By.xpath("//fieldset[1]/div/div/input[@type='text']");
    private final By emailInput = By.xpath("//fieldset[2]/div/div/input[@type='text']");
    private final By passwordInput = By.xpath(".//input[@type='password']");
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By incorrectPassword = By.xpath(".//p[text()='Некорректный пароль']");
    private final By loginButton = By.xpath("//a[@href='/login']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидаем загрузки авторизации регистрации")
    public void waitUntilPageIsLoaded() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(registerButton));
    }

    @Step("Регистрация пользователя")
    public void registerUser(UserData userData) {
        setName(userData.getName());
        setEmail(userData.getEmail());
        setPassword(userData.getPassword());
        clickOnRegisterButton();
    }

    @Step("Устанавливаем имя пользователя")
    public void setName(String name) {
        WebElement nameInputElement = driver.findElement(nameInput);
        nameInputElement.click();
        nameInputElement.clear();
        nameInputElement.sendKeys(name);
    }

    @Step("Устанавливаем email")
    public void setEmail(String email) {
        WebElement emailInputElement = driver.findElement(emailInput);
        emailInputElement.click();
        emailInputElement.clear();
        emailInputElement.sendKeys(email);
    }

    @Step("Устанавливаем пароль")
    public void setPassword(String password) {
        WebElement passwordInputElement = driver.findElement(passwordInput);
        passwordInputElement.click();
        passwordInputElement.clear();
        passwordInputElement.sendKeys(password);
    }

    @Step("Нажимаем кнопку \"Зарегистрироваться\"")
    public void clickOnRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Нажимаем кнопку \"Конструктор\"")
    public void waitForIncorrectPasswordElement() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(incorrectPassword));
    }

    @Step("Нажимаем кнопку \"Войти\"")
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }
}
