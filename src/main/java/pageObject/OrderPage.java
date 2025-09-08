package pageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static pageObject.constants.ScooterColours.BLACK;
import static pageObject.constants.ScooterColours.GREY;

public class OrderPage {
    WebDriver driver;
    private final By orderHeader = By.className("Order_Header__BZXOb");
    private final By name = By.xpath(".//input[@placeholder='* Имя']");
    private final By lastname = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By address = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By stateMetro = By.className("select-search__input");
    private final By telephone = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By buttonNext = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
    private final String nameStateMetro = ".//button[@value='%s']";
    private final By rentHeader = By.className("Order_Header__BZXOb");
    private final By date = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By durationRent = By.xpath(".//span[@class='Dropdown-arrow']");
    private final By colourBlack = By.id("black");
    private final By colourGrey = By.id("grey");
    private final By comment = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By createOrderButton = By.xpath(".//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

    //метод ожидания загруки страницы заказа
    public OrderPage waitForLoadOrderPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(orderHeader).getText() != null
                && !driver.findElement(orderHeader).getText().isEmpty()
        ));
        return this;
    }

    public OrderPage inputName(String newName) {
        driver.findElement(name).sendKeys(newName);
        return this;
    }

    public OrderPage inputLastName(String newLastName) {
        driver.findElement(lastname).sendKeys(newLastName);
        return this;
    }

    public OrderPage inputAddress(String newAddress) {
        driver.findElement(address).sendKeys(newAddress);
        return this;
    }

    public OrderPage changeStateMetro(int stateNumber) {
        driver.findElement(stateMetro).click();
        By newStateMetro = By.xpath(String.format(nameStateMetro, stateNumber));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", driver.findElement(newStateMetro));
        driver.findElement(newStateMetro).click();
        return this;
    }

    public OrderPage inputTelephone(String newTelephone) {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(telephone));
        driver.findElement(telephone).sendKeys(newTelephone);
        return this;
    }

    public void clickNextButton() {
        driver.findElement(buttonNext).click();
    }

    //метод ожидания загрузки страницы
    public OrderPage waitAboutRentHeader() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(driver -> (driver.findElement(rentHeader).getText() != null
                && !driver.findElement(rentHeader).getText().isEmpty()
        ));
        return this;
    }

    public OrderPage inputDate(String newDate) {
        driver.findElement(date).sendKeys(newDate);
        return this;
    }

    public OrderPage inputDuration(String newDuration) {
        driver.findElement(durationRent).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.elementToBeClickable(By.className("Dropdown-menu")));
        List<WebElement> options = driver.findElements(By.xpath("//div[@class='Dropdown-option' and @role='option']"));
        for (WebElement opt : options) {
            if (opt.getText().equals(newDuration)) {
                opt.click();
                break;
            }
        }
        return this;
    }

    public OrderPage changeColour(Enum colour) {
        if (colour.equals(BLACK)) {
            driver.findElement(colourBlack).click();
        } else if (colour.equals(GREY)) {
            driver.findElement(colourGrey).click();
        }
        return this;
    }

    public OrderPage inputComment(String newComment) {
        driver.findElement(comment).sendKeys(newComment);
        return this;
    }

    public void clickButtonCreateOrder() {
        driver.findElement(createOrderButton).click();
    }

}