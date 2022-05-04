package persPageBlocks;


import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PersonalBlock extends BaseComponent {

    private final ServerConfig serverConfig = ConfigFactory.create(ServerConfig.class);

    private final By nameField = By.xpath("//input[@type='text'][@name='fname']");
    private final By nameEngField = By.xpath("//input[@type='text'][@name='fname_latin']");
    private final By surnameField = By.xpath("//input[@type='text'][@name='lname']");
    private final By surnameEngField = By.xpath("//input[@type='text'][@name='lname_latin']");
    private final By nickNameField = By.xpath("//input[@type='text'][@name='blog_name']");
    private final By dateOfBirthField = By.xpath("//input[@name='date_of_birth']");
    private final By dateOfBirthArea = By.xpath("//label[contains(text(),'Дата рождения')]");

    public PersonalBlock(WebDriver driver) {
        super(driver);
    }

    public void fillPersonalData() {
        WebElement addName = driver.findElement(nameField);//Поле Имя
        addName.clear();
        addName.click();
        addName.sendKeys(serverConfig.name());

        WebElement addEnName = driver.findElement(nameEngField);//Поле Имя (латиницей)
        addEnName.clear();
        addEnName.click();
        addEnName.sendKeys(serverConfig.name());

        WebElement addSurname = driver.findElement(surnameField);//Поле Фамилия
        addSurname.clear();
        addSurname.click();
        addSurname.sendKeys(serverConfig.surname());

        WebElement addEnSurname = driver.findElement(surnameEngField);//Поле Фамилия (латиницей)
        addEnSurname.clear();
        addEnSurname.click();
        addEnSurname.sendKeys(serverConfig.surname());

        WebElement addNick = driver.findElement(nickNameField);//Имя в блоге
        addNick.clear();
        addNick.click();
        addNick.sendKeys(serverConfig.name());

        WebElement addBirth = driver.findElement(dateOfBirthField);//Дата рождения
        addBirth.clear();
        addBirth.click();
        addBirth.sendKeys(serverConfig.birth());

        driver.findElement(dateOfBirthArea).click();

    }

}
