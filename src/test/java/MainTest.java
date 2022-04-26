import org.junit.runners.MethodSorters;
import utils.DriverManager;
import utils.WebDriverName;
import exceptions.DriverNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.PersPage;
import pages.persContainers.ContactsData;
import pages.persContainers.CountryData;
import pages.persContainers.PersonalData;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
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
    public void test1PersPageFill() throws DriverNotFoundException {
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
    public void test2PersPageData() throws DriverNotFoundException {
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








