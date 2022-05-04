package persPageBlocks;

import config.ServerConfig;
import enums.Cities;
import enums.Countries;
import org.aeonbits.owner.ConfigFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PersPage;

public class CountryBlock extends BaseComponent {
    //    private final WebDriver driver;
    private static final Logger logger = LogManager.getLogger(PersPage.class);
    private final ServerConfig serverConfig = ConfigFactory.create(ServerConfig.class);
//    private final WaitMethods wait;

    @FindBy(xpath = "//input[@name='country']/following-sibling::div")
    private WebElement countryField;
    //    private final By countryField = By.xpath("//input[@name='country']/following-sibling::div");
    @FindBy(xpath = "//div[contains(@class,'lk-cv-block__select-scroll_country')]")
    private WebElement countrySelectField;
    //    private final By countryRus = By.xpath("//div[contains(@class,'lk-cv-block__select-scroll_country')]/button[@title='Россия']");
    @FindBy(xpath = "//input[@name='city']/following-sibling::div")
    private WebElement cityField;
    //    private final By cityField = By.xpath("//input[@name='city']/following-sibling::div");
    @FindBy(xpath = "//div[contains(@class,'lk-cv-block__select-scroll_city')]")
    private WebElement citySelectField;
    //    private final By cityMoscow = By.xpath("//div[contains(@class,'lk-cv-block__select-scroll_city')]/button[@title='Москва']");


    public CountryBlock(WebDriver driver) {
        super(driver);
    }


    public void fillCountryData() {
        Countries configCountry = serverConfig.country();
        Cities configCity = serverConfig.city();
        assert configCountry.equals(configCity.getCountry()) : "Заданный в конфиге город не принадлежит заданной стране";
//        WebElement country = driver.findElement(countryField);//Страна
        countryField.click();
//        country.click();
//        WebElement countryList = driver.findElement(countryRus);
//        countryList.click();
        countrySelectField.findElement(By.xpath(String.format("button[@title='%s']", configCountry.getTranslate()))).click();
//        cityField.click();
        wait.waitUntilTextPresents(cityField, "Город");
        cityField.click();
        citySelectField.findElement(By.xpath(String.format("button[@title='%s']", configCity.getTranslate()))).click();
//        WebElement city = driver.findElement(cityField);//Город
//
//        city.click();
//        WebElement cityList = driver.findElement(cityMoscow);
//        cityList.click();
    }


    public void checkCountryData() {
        Countries configCountry = serverConfig.country();
        Cities configCity = serverConfig.city();
        String getCountry = countryField.getText();
        String getCity = cityField.getText();

        assert configCountry.getTranslate().equals(getCountry) : "Страна не корректна";
        assert configCity.getTranslate().equals(getCity) : "Город не корректен";

        logger.info("Данные сохраненных страны, города и языка отображаются корректно");
    }
}
