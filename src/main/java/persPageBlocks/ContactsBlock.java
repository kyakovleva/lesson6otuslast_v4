package persPageBlocks;

import config.ServerConfig;
import config.utils.JsOperations;
import config.utils.WaitMethods;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.util.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.PersPage;

import java.util.List;

public class ContactsBlock extends BaseComponent {
//    private final WebDriver driver;
    private static final Logger logger = LogManager.getLogger(PersPage.class);
    private final ServerConfig serverConfig = ConfigFactory.create(ServerConfig.class);
//    private final WaitMethods wait;
//    private JsOperations js;

    private final By deleteButtonContainer = By.xpath("//div[(contains(@class,'container__col') and contains(@class,'container__col_12') and contains(@class,'container__col_md-0'))]");
    private final By deleteButton = By.xpath(".//button[contains(@class, 'js-formset-delete')]");
    private final By contactContainer = By.xpath("//div[contains(@class,'js-formset-row')]");
    private final By contactAddButton = By.xpath("//button[contains(text(),'Добавить')]");
    private final By firstContactField = By.xpath("//input[@type='text'][@name='contact-0-value']");
    private final By secondContactField = By.xpath("//input[@type='text'][@name='contact-1-value']");


    public ContactsBlock(WebDriver driver) {
        super(driver);
    }

    private void deleteContact(WebElement contact) {
        WebElement deleteButtonDiv = contact.findElement(deleteButtonContainer);
        deleteButtonDiv.findElement(deleteButton).click();
    }

    private void setContact(int cntNumber, String title, String text) {
        String contactBlock = String.format("//input[@name='contact-%d-service']/following::div", cntNumber);

        WebElement contact = driver.findElement(By.xpath(contactBlock));
        contact.click();

        contactBlock = String.format("//div[(contains(@class,'lk-cv-block__select-options') or contains(@class,'lk-cv-block__select-options_left')) and not(contains(@class,'hide'))]//button[@title='%1s']", title);

        WebElement contactType = driver.findElement(By.xpath(contactBlock));
        contactType.click();

        contactBlock = String.format("//input[@type='text'][@name='contact-%d-value']", cntNumber);

        WebElement setContact = driver.findElement(By.xpath(contactBlock));
        setContact.clear();
        setContact.sendKeys(text);

        wait.waitUntilAttrPresents(setContact, "value", text);
    }

    public void addContacts() {
        //ищем все контакты,а точнее их верхние div формы
        List<WebElement> existingContacts = driver.findElements(contactContainer);
        //надо запоминать сколько было контактов,т.к. если создавать новые, то он продолжает нумерацию на форме
        int contactCount = existingContacts.size();

        //если контакты нашлись,то удаляем их
        if (CollectionUtils.isNotEmpty(existingContacts)) {
            for (WebElement existingContact : existingContacts) {
                deleteContact(existingContact);
            }
        }

        logger.info("Добавляем 1 контакт");
        WebElement addButton = driver.findElement(contactAddButton);
        addButton.click();

        setContact(contactCount++, "VK", serverConfig.vk());

        logger.info("Добавляем 2 контакт");

        addButton = driver.findElement(contactAddButton);
        addButton.click();
        setContact(contactCount++, "Тelegram", serverConfig.tg());
    }

    public void checkContactsData() {
        WebElement tgElement = driver.findElement(firstContactField);
        js.moveToElement(tgElement);
        String getTg = tgElement.getDomAttribute("value");

        WebElement vkElement = driver.findElement(secondContactField);
        js.moveToElement(vkElement);
        String getVk = vkElement.getDomAttribute("value");


        Assert.assertEquals("Контакт1 не корректен", "https://t.me/test", getTg);
        Assert.assertEquals("Контакт2 не корректен", "https://vk.com/test", getVk);
        logger.info("Данные контактов отображаются корректно");
    }

}
