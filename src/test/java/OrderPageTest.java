import pageObject.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;
import static pageObject.constants.CreateOrderButton.DOWN_BUTTON;
import static pageObject.constants.CreateOrderButton.UP_BUTTON;
import static pageObject.constants.DaysRentConstants.*;
import static pageObject.constants.ScooterColours.*;

@RunWith(Parameterized.class)
public class OrderPageTest {
    private WebDriver driver;
    private final String site = "https://qa-scooter.praktikum-services.ru/";
    private final String name;
    private final String lastname;
    private final String address;
    private final int stateMetroNumber;
    private final String telephoneNumber;
    private final String date;
    private final String duration;
    private final Enum colour;
    private final String comment;
    private final String expectedHeader = "Заказ оформлен";
    private final Enum button;

    public OrderPageTest(Enum button, String name, String lastname, String address, int stateMetroNumber, String telephoneNumber,
                         String date, String duration, Enum colour, String comment) {
        this.button = button;
        this.name = name;
        this.lastname = lastname;
        this.address = address;
        this.stateMetroNumber = stateMetroNumber;
        this.telephoneNumber = telephoneNumber;
        this.date = date;
        this.duration = duration;
        this.colour = colour;
        this.comment = comment;
    }

    @Parameterized.Parameters
    public static Object[][] getParameters() {
        return new Object[][]{
                {UP_BUTTON, "Тест Один", "Фамилия", "Адрес 1", 123, "89999999990", "25.03.2025", ONE_DAY, GREY, "comments one"},
                {UP_BUTTON, "Тест Два", "Фамилия", "Адрес 2", 7, "89999999980", "25.03.2025", TWO_DAYS, BLACK, "comments two"},
                {UP_BUTTON, "Тест Три", "Фамилия", "Адрес 3", 10, "89999999970", "25.03.2025", THREE_DAYS, BLACK, "comments three"},
                {DOWN_BUTTON, "Тест Один", "Фамилия", "Адрес 1", 123, "89999999960", "25.03.2025", FOUR_DAYS, GREY, "comments one"},
                {DOWN_BUTTON, "Тест Два", "Фамилия", "Адрес 2", 7, "89999999950", "25.03.2025", FIVE_DAYS, BLACK, "comments two"},
                {DOWN_BUTTON, "Тест Три", "Фамилия", "Адрес 3", 10, "89999999940", "25.03.2025", SIX_DAYS, BLACK, "comments three"},
        };
    }

    @Before
    public void startUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(site);
    }

    @After
    public void teardown() {
        driver.quit();
    }

    @Test
    public void testCreateOrder() {
        new MainPage(driver)
                .waitForLoadMainPage()
                .clickCreateOrderButton(button);

        new OrderPage(driver)
                .waitForLoadOrderPage()
                .inputName(name)
                .inputLastName(lastname)
                .inputAddress(address)
                .changeStateMetro(stateMetroNumber)
                .inputTelephone(telephoneNumber)
                .clickNextButton();

        new OrderPage(driver)
                .waitAboutRentHeader()
                .inputDate(date)
                .inputDuration(duration)
                .changeColour(colour)
                .inputComment(comment)
                .clickButtonCreateOrder();
        PopUpWindow popUpWindow = new PopUpWindow(driver);
        popUpWindow.clickButtonYes();

        assertTrue(popUpWindow.getHeaderAfterCreateOrder().contains(expectedHeader));
    }

}