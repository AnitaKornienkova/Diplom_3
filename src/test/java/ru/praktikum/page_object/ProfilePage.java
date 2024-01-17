package ru.praktikum.page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage {
    private final WebDriver driver;

    private final By profileInfoNote =
            By.xpath("//p[text()='В этом разделе вы можете изменить свои персональные данные']");
    private final By appHeaderLogo =
            By.xpath(".//div/header/nav/div/a[@href='/']");
    private final By constructorButton =
            By.xpath(".//div/header/nav/ul/li[1]/a[@href='/' and contains(@class, 'AppHeader_header')]");
    private final By logoutButton = By.xpath(".//button[text()='Выход']");


    public ProfilePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидаем загрузки страницы с личным кабинетом")
    public WebElement waitUntilPageIsLoaded() {
        return new WebDriverWait(driver, 3)
                .until(ExpectedConditions.visibilityOfElementLocated(profileInfoNote));
    }

    @Step("Нажимаем кнопку \"Выход\"")
    public void clickOnLogoutButton() {
        driver.findElement(logoutButton).click();
    }

    @Step("Нажимаем логотип (по совместительству хоум баттон)")
    public void clickOnLogo() {
        driver.findElement(appHeaderLogo).click();
    }

    @Step("Нажимаем кнопку \"Конструктор\"")
    public void clickOnConstructorButton() {
        driver.findElement(constructorButton).click();
    }
}
