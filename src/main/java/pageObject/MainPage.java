package pageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;



public class MainPage {
    WebDriver driver;
    private final By upOrderButton = By.className("Button_Button__ra12g");
    private final By downOrderButton = By.className("Button_Button__ra12g Button_Middle__1CSJM");
    private final By mainHeader = By.id("root");
    private final By qustion1 = By.id("accordion__heading-0");
    private final By qustion2 = By.id("accordion__heading-1");
    private final By qustion3 = By.id("accordion__heading-2");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    public MainPage clickUpOrderButton() {
        driver.findElement(upOrderButton).click();
        return this;
    }

    public MainPage waitForLoadMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver -> (driver.findElement(mainHeader).getText() != null
                && !driver.findElement(mainHeader).getText().isEmpty()
        ));
        return this;
    }

}