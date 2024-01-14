package ru.praktikum.page_object;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.function.Predicate;

public class HomePage {
    private final WebDriver driver;

    private final By appHeaderLogo = By.xpath(".//a[@class='active' and @aria-current='page']");
    private final By loginButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By profileButton = By.xpath("//p[text()='Личный Кабинет']");
    private final By createOrderButton = By.xpath("//button[text()='Оформить заказ']");
    private final By bunsHeader = By.xpath(".//span[text()='Булки']");
    private final By bunsHeaderParentElement = By.xpath(".//span[text()='Булки']/..");
    private final By saucesHeader = By.xpath(".//span[text()='Соусы']");
    private final By saucesHeaderParentElement = By.xpath(".//span[text()='Соусы']/..");
    private final By fillingsHeader = By.xpath(".//span[text()='Начинки']");
    private final By fillingsHeaderParentElement = By.xpath(".//span[text()='Начинки']/..");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидаем загрузки домашней страницы")
    public void waitUntilPageIsLoaded() {
        new WebDriverWait(driver, 3).until(ExpectedConditions.visibilityOfElementLocated(appHeaderLogo));
    }

    @Step("Нажимаем кнопку \"Войти в аккаунт\"")
    public void clickOnLogInButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажимаем кнопку \"Личный кабинет\"")
    public void clickOnProfileButton() {
        driver.findElement(profileButton).click();
    }

    @Step("Проверяем, что блок булочки не выбран")
    public void checkBunsParentElementClassIsNotSelected() {
        new WebDriverWait(driver, 3).until(
                new ClassMatchingCondition(
                        bunsHeaderParentElement,
                        elementClass -> !elementClass.contains("tab_tab_type_current")
                )
        );
    }

    @Step("Кликаем на булочки")
    public void clickOnBuns() {
        driver.findElement(bunsHeader).click();
        new WebDriverWait(driver, 3).until(
                new ClassMatchingCondition(
                        bunsHeaderParentElement,
                        elementClass -> elementClass.contains("tab_tab_type_current")
                )
        );
    }

    @Step("Проверяем, что блок соусы не выбран")
    public void checkSaucesParentElementClassNotSelected() {
        new WebDriverWait(driver, 3).until(
                new ClassMatchingCondition(
                        saucesHeaderParentElement,
                        elementClass -> !elementClass.contains("tab_tab_type_current")
                )
        );
    }

    @Step("Кликаем на соусы\"")
    public void clickOnSauces() {
        driver.findElement(saucesHeader).click();
        new WebDriverWait(driver, 3).until(
                new ClassMatchingCondition(
                        saucesHeaderParentElement,
                        elementClass -> elementClass.contains("tab_tab_type_current")
                )
        );
    }

    @Step("Проверяем, что блок начинки не выбран")
    public void checkFillingsParentElementNotSelected() {
        new WebDriverWait(driver, 3).until(
                new ClassMatchingCondition(
                        fillingsHeaderParentElement,
                        elementClass -> !elementClass.contains("tab_tab_type_current")
                )
        );
    }

    @Step("Нажать на начинки")
    public void clickOnFillings() {
        driver.findElement(fillingsHeader).click();
        new WebDriverWait(driver, 3).until(
                new ClassMatchingCondition(
                        fillingsHeaderParentElement,
                        elementClass -> elementClass.contains("tab_tab_type_current")
                )
        );
    }

    private static class ClassMatchingCondition implements ExpectedCondition<Boolean> {
        private final By selector;
        private final Predicate<String> checkClass;

        private ClassMatchingCondition(By selector, Predicate<String> checkClass) {
            this.selector = selector;
            this.checkClass = checkClass;
        }

        @Override
        public Boolean apply(WebDriver webDriver) {
            WebElement element = webDriver.findElement(selector);
            String classes = element.getAttribute("class");
            return classes != null && checkClass.test(classes);
        }
    }
}
