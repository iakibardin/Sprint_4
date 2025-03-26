package pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import static pageObject.constants.CreateOrderButton.DOWN_BUTTON;
import static pageObject.constants.CreateOrderButton.UP_BUTTON;


public class MainPage {
    WebDriver driver;
    private final By mainHeader = By.className("Header_Header__214zg");
    private final By upOrderButton = By.className("Button_Button__ra12g");
    private final By downOrderButton = By.className("Button_Middle__1CSJM");
    private final By questionsHeader = By.className("Home_FourPart__1uthg");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // метод ожидания загрузки главной страницы
    public MainPage waitForLoadMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver -> (driver.findElement(mainHeader).getText() != null
                && !driver.findElement(mainHeader).getText().isEmpty()
        ));
        return this;
    }
    // метод клика верхняя кнопка заказа
    public MainPage clickUpOrderButton() {
        driver.findElement(upOrderButton).click();
        return this;
    }
    // нижняя кнопка заказа
    public MainPage clickDownOrderButton() {
        driver.findElement(downOrderButton).click();
        return this;
    }
    //метод прокрутки ко второй кнопке "Заказать"
    public MainPage scrollToDownOrderButton() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(downOrderButton));
        return this;
    }


    //метод прокрутки к блоку "Вопросы о важном"
    public MainPage scrollToQuestions() {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(questionsHeader));
        return this;
    }

    //метод ожидания загрузки ответа на вопрос
    public void waitLoadAfterClickQuestion(By accordionLabel) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(accordionLabel).getText() != null
                && !driver.findElement(accordionLabel).getText().isEmpty()
        ));
    }

    public void clickCreateOrderButton(Enum button) {
        if (button.equals(UP_BUTTON)) {
            clickUpOrderButton();
        } else if (button.equals(DOWN_BUTTON)) {
            scrollToDownOrderButton();
            clickDownOrderButton();
        }
    }

    public MainPage clickQuestion(By question) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(question))
                .click();
        return this;
    }

}