package pages.persContainers;

import Utils.WaitMethods;
import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.PersPage;

public class CountryData {
    private final WebDriver driver;
    private static final Logger logger = LogManager.getLogger(PersPage.class);
    private final ServerConfig serverConfig = ConfigFactory.create(ServerConfig.class);
    private final WaitMethods wait;

    private final By countryField = By.xpath("//input[@name='country']/following-sibling::div");
    private final By countryRus = By.xpath("//div[contains(@class,'lk-cv-block__select-scroll_country')]/button[@title='Россия']");
    private final By cityField = By.xpath("//input[@name='city']/following-sibling::div");
    private final By cityMoscow = By.xpath("//div[contains(@class,'lk-cv-block__select-scroll_city')]/button[@title='Москва']");
    private final By engLevelField = By.xpath("//input[@name='english_level']/following-sibling::div");
    private final By getEngLevelUnd = By.xpath("//div[contains(@class,'lk-cv-block__select-scroll') and not(contains(@class,'country')) and not(contains(@class,'city'))]/button[2]");


    public CountryData(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitMethods(driver, 5, 1);
    }

    public void fillCountryData() {
        WebElement country = driver.findElement(countryField);//Страна
        country.click();
        WebElement countryList = driver.findElement(countryRus);
        countryList.click();

        WebElement city = driver.findElement(cityField);//Город
        wait.waitUntilTextPresents(city, "Город");
        city.click();
        WebElement cityList = driver.findElement(cityMoscow);
        cityList.click();

        WebElement engLevel = driver.findElement(engLevelField); //Уровень английского
        engLevel.click();
        WebElement engLevelList = driver.findElement(getEngLevelUnd);
        engLevelList.click();
    }

    public void checkCountryData() {
        String getCountry = driver.findElement(countryField).getText();
        String getCity = driver.findElement(cityField).getText();
        String getEngLevel = driver.findElement(engLevelField).getText();

        Assert.assertEquals("Город не корректен", "Россия", getCountry);
        Assert.assertEquals("Город не корректен", "Москва", getCity);
        Assert.assertEquals("Уровень языка не корректен", "Начальный уровень (Beginner)", getEngLevel);
        logger.info("Данные сохраненных страны, города и языка отображаются корректно");
    }
}
