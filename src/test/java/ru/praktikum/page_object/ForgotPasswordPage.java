package ru.praktikum.page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ForgotPasswordPage {
    private final WebDriver driver;

    private final By recoverButton = By.xpath(".//button[text()='Восстановить']");
    private final By loginButton = By.xpath("//a[@href='/login']");

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидаем загрузки страницы восстановления пароля")
    public void waitUntilPageIsLoaded() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(recoverButton));
    }

    @Step("Нажать на кнопку \"Войти\"")
    public void clickOnLoginButton() {
        driver.findElement(loginButton).click();
    }
}
