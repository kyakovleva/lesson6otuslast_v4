import org.junit.Before;
import persPageBlocks.LanguageBlock;
import utils.DriverManager;
import enums.WebDriverName;
import exceptions.DriverNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import pages.MainPage;
import pages.PersPage;
import persPageBlocks.ContactsBlock;
import persPageBlocks.CountryBlock;
import persPageBlocks.PersonalBlock;

import java.util.List;

public class MainTest {
    private static WebDriver driver;
    private static final Logger logger = LogManager.getLogger(MainTest.class);
    private static final WebDriverName webDriverName = WebDriverName.valueOf(System.getProperty("driverName")); //chrome

    @BeforeClass
    public static void startUpDriver() throws DriverNotFoundException {
        DriverManager.startUp(WebDriverName.valueOf(System.getProperty("driverName")));
    }

    @Before
    public void startInitBrowser() throws DriverNotFoundException {
        driver = DriverManager.initDriver(webDriverName, List.of(System.getProperty("browser.mode")));
    }

    @After
    public void end() {
        DriverManager.end(driver);
    }


    @Test
    public void testPersPageFill() throws DriverNotFoundException {
        persPageFill();
        persPageCheck();
    }

    private void persPageCheck() throws DriverNotFoundException {
        //Проверить, что в разделе "О себе" отображаются указанные ранее данные
        startInitBrowser();
        MainPage mainPage = new MainPage(driver);
        PersPage persPage = new PersPage(driver);
        CountryBlock countryBlock = new CountryBlock(driver);
        ContactsBlock contactsBlock = new ContactsBlock(driver);
        LanguageBlock languageBlock = new LanguageBlock(driver);

        mainPage.open();
        mainPage.auth();
        persPage.open();
        countryBlock.checkCountryData();
        contactsBlock.checkContactsData();
        languageBlock.checkLanguageLevel();
    }

    private void persPageFill() {
        // Открыть Chrome в режиме полного экрана
        MainPage mainPage = new MainPage(driver);
        PersPage persPage = new PersPage(driver);
        PersonalBlock personalData = new PersonalBlock(driver);
        CountryBlock countryData = new CountryBlock(driver);
        ContactsBlock contactsBlock = new ContactsBlock(driver);
        LanguageBlock languageBlock = new LanguageBlock(driver);

        //Перейти на https://otus.ru
        mainPage.open();
        mainPage.auth();

        //Открыть страницу Персональные данные
        persPage.open();

        //Заполнение Персональных данных
        personalData.fillPersonalData();

        //Заполнение данных о местоположении
        countryData.fillCountryData();

        //Заполнение данных о знании языка
        languageBlock.fillLanguageLevel();

        //Добавление двух контактов
        contactsBlock.addContacts();

        //Нажать сохранить
        persPage.saveData();
        //Закрыть страницу
        DriverManager.close(driver);
    }
}








