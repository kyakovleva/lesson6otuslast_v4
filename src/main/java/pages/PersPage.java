package pages;

import utils.WaitMethods;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class PersPage {

    private final WebDriver driver;
    private static final Logger logger = LogManager.getLogger(PersPage.class);
    private final WaitMethods wait;

    private final By authMenu = By.xpath("//p[contains(@class,'header2-menu__item-text__username')]");
    private final By profile = By.xpath("//b[contains(@class,'header2-menu__dropdown-text_name')]");

    private final By saveLaterButton = By.xpath("//button[@title='Сохранить и заполнить позже']");
    private final By messageSaveAlert = By.xpath("//span[contains(@class,'messages')]");


    public PersPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WaitMethods(driver, 5, 1);
    }

    public void open() {
        Actions actions = new Actions(driver);
        WebElement authMenuElement = driver.findElement(authMenu);
        WebElement profileElement = driver.findElement(profile);
        actions.moveToElement(authMenuElement).build().perform();
        actions.moveToElement(profileElement).build().perform();
        driver.findElement(profile).click();
        logger.info("Открыта страница Персональные данные");
    }


    public void saveData() {
        Actions actions = new Actions(driver);
        WebElement save = driver.findElement(saveLaterButton);
        actions.moveToElement(save);
        save.click();

        save = driver.findElement(messageSaveAlert);
        wait.waitUntilTextPresents(save, "Данные успешно сохранены");

        logger.info("Данные сохранены");
    }
}
