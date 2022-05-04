package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {

    private WebDriver driver;
    private static final Logger logger = LogManager.getLogger(MainPage.class);

    private By authButton = By.xpath("//span[@class='header2__auth-reg']");
    private By emailField = By.xpath("//div[@class='new-log-reg__body']//input[@name='email']");
    private By passwordField = By.xpath("//div[@class='new-log-reg__body']//input[@name='password']");
    private By loginButton = By.xpath("//div[@class='new-log-reg__body']//button");
    private By loginName = By.xpath("//p[contains(@class,'header2-menu__item-text__username')]");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(System.getProperty("base.url"));
    }

    public void auth() {
        driver.findElement(authButton).click();

        driver.findElement(emailField).sendKeys(System.getProperty("base.email"));
        driver.findElement(passwordField).sendKeys(System.getProperty("base.password"));
        driver.findElement(loginButton).click();

        String newUserName = driver.findElement(loginName).getText();

        Assert.assertEquals("Пользователь не верен", "toyey", newUserName);
        logger.info("Пользователь авторизован");
    }
}
