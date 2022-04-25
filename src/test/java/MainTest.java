import Utils.DriverManager;
import Utils.WebDriverFactory;
import Utils.WebDriverName;
import config.ServerConfig;
import exceptions.DriverNotFoundException;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;
import pages.PersPage;
import pages.persContainers.ContactsData;
import pages.persContainers.CountryData;
import pages.persContainers.PersonalData;

import java.time.Duration;
import java.util.List;


public class MainTest {
    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(MainTest.class);
    private final WebDriverName webDriverName = WebDriverName.valueOf(System.getProperty("driverName")); //chrome

    @BeforeClass
    public static void startUp() throws DriverNotFoundException {
        DriverManager.startUp(WebDriverName.valueOf(System.getProperty("driverName")));
    }

    @After
    public void end() {
        DriverManager.end(driver);
    }

    @Test
    public void testPersPageFill() throws DriverNotFoundException {
        // Открыть Chrome в режиме полного экрана
        driver = DriverManager.initDriver(webDriverName, List.of("start-maximized"));

        MainPage mainPage = new MainPage(driver);
        PersPage perspage = new PersPage(driver);
        PersonalData personalData = new PersonalData(driver);
        CountryData countryData = new CountryData(driver);
        ContactsData contactsData = new ContactsData(driver);

        //Перейти на https://otus.ru
        mainPage.open();
        mainPage.auth();
        logger.info("Начали заполнять персональные данные");

        //Открыть страницу Персональные данные
        perspage.open();

        //Заполнение Персональных данных
        personalData.fillPersonalData();

        //Заполнение данных о местоположении
        countryData.fillCountryData();

        //Добавление двух контактов
        contactsData.addContacts();

        //Нажать сохранить
        perspage.saveData();
        //Закрыть страницу
        DriverManager.close(driver);
    }

    @Test
    public void testPersPageData() throws DriverNotFoundException {
        driver = DriverManager.initDriver(webDriverName, List.of("start-maximized"));

        MainPage mainPage = new MainPage(driver);
        PersPage perspage = new PersPage(driver);
        CountryData countryData = new CountryData(driver);
        ContactsData contactsData = new ContactsData(driver);

        //Проверить, что в разделе "О себе" отображаются указанные ранее данные
        mainPage.open();
        mainPage.auth();
        perspage.open();
        countryData.checkCountryData();
        contactsData.checkContactsData();
    }


}








