package persPageBlocks;

import config.ServerConfig;
import config.model.Contact;
import enums.ContactTypes;
import org.aeonbits.owner.ConfigFactory;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.PersPage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContactsBlock extends BaseComponent {
    private static final Logger logger = LogManager.getLogger(PersPage.class);
    private final ServerConfig serverConfig = ConfigFactory.create(ServerConfig.class);
    private final By deleteButtonContainer = By.xpath(".//div[(contains(@class,'container__col') and contains(@class,'container__col_12') and contains(@class,'container__col_md-0'))]");
    private final By deleteButton = By.xpath(".//button[contains(@class, 'js-formset-delete')]");

    @FindBy(xpath = "//div[contains(@class,'js-formset-row')]")
    private List<WebElement> contactContainer;
    @FindBy(xpath = "//button[contains(text(),'Добавить')]")
    private WebElement contactAddButton;


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
        List<WebElement> existingContacts = contactContainer;
        //надо запоминать сколько было контактов,т.к. если создавать новые, то он продолжает нумерацию на форме
        int contactCount = existingContacts.size();

        //если контакты нашлись,то удаляем их
        if (CollectionUtils.isNotEmpty(existingContacts)) {
            for (WebElement existingContact : existingContacts) {
                deleteContact(existingContact);
            }
        }

        for (Contact contact : serverConfig.contacts()) {
            addContact(contactCount, contact.type(), contact.text());
            contactCount = contactCount + 1;
        }
    }

    private void addContact(int contactCount, ContactTypes contactType, String text) {
        logger.info("Добавляем контакт с типом {}", contactType);
        contactAddButton.click();

        setContact(contactCount, contactType.getFrontTitle(), text);
    }

    public void checkContactsData() {
        Map<ContactTypes, Contact> contactMap = new HashMap<>();
        serverConfig.contacts().forEach(contact -> contactMap.put(contact.type(), contact));

        List<WebElement> existingContacts = contactContainer;

        assert contactMap.size() == existingContacts.size() : "Количество контактов не совпадает с ожидаемым";
        int contactNumber = 0;

        for (WebElement existingContact : existingContacts) {
            WebElement typeContact = existingContact.findElement(By.xpath(".//div[contains(@class,'lk-cv-block__input')]"));
            js.moveToElement(typeContact);
            String type = typeContact.getText();
            ContactTypes contactTypes = ContactTypes.getByFrontTitle(type);

            WebElement textContact = existingContact.findElement(By.xpath(String.format("//input[@type='text'][@name='contact-%d-value']", contactNumber)));
            String text = textContact.getDomAttribute("value");

            Contact contact = contactMap.get(contactTypes);
            contactNumber = contactNumber + 1;
            if (contact != null) {
                assert contact.text().equals(text) : String.format("Контакт %d не корректен", contactNumber);
            } else {
                throw new IllegalArgumentException(String.format("Контакт с типом %s не найден среди исходных контактов проверки", type));
            }
        }
        logger.info("Данные контактов отображаются корректно");
    }

}
