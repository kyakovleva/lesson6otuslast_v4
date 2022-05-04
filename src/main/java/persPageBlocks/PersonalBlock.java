package persPageBlocks;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PersonalBlock extends BaseComponent {

        @FindBy(css = "input[type='text'][name='fname']")
    private WebElement nameField;
    //    private final By nameField = By.cssSelector("input[type='text'][name='fname']");
    @FindBy(css = "input[type='text'][name='fname_latin']")
    private WebElement nameEngField;
    //    private final By nameEngField = By.cssSelector("input[type='text'][name='fname_latin']");
    @FindBy(css = "input[type='text'][name='lname']")
    private WebElement surnameField;
    //    private final By surnameField = By.cssSelector("input[type='text'][name='lname']");
    @FindBy(css = "input[type='text'][name='blog_name']")
    private WebElement surnameEngField;
    //    private final By surnameEngField = By.cssSelector("input[type='text'][name='lname_latin']");
    @FindBy(css = "input[type='text'][name='blog_name']")
    private WebElement nickNameField;
    //    private final By nickNameField = By.cssSelector("input[type='text'][name='blog_name']");
    @FindBy(css = "input[name='date_of_birth']")
    private WebElement dateOfBirthField;
    //    private final By dateOfBirthField = By.cssSelector("input[name='date_of_birth']");
    @FindBy(xpath = "//label[contains(text(),'Дата рождения')]")
    private WebElement dateOfBirthArea;
//    private final By dateOfBirthArea = By.xpath("//label[contains(text(),'Дата рождения')]");

    public PersonalBlock(WebDriver driver) {
        super(driver);
//        PageFactory.initElements(driver,this);
    }

    public void fillName() {
//        WebElement addName = driver.findElement(nameField);//Поле Имя
        nameField.clear();
        nameField.click();
        nameField.sendKeys(serverConfig.name());
    }

    public void fillEngName() {
//        WebElement addEnName = driver.findElement(nameEngField);//Поле Имя (латиницей)
        nameEngField.clear();
        nameEngField.click();
        nameEngField.sendKeys(serverConfig.name());
    }

    public void fillSurname() {
//        WebElement addSurname = driver.findElement(surnameField);//Поле Фамилия
        surnameField.clear();
        surnameField.click();
        surnameField.sendKeys(serverConfig.surname());
    }

    public void fillEngSurname() {
//        WebElement addEnSurname = driver.findElement(surnameEngField);//Поле Фамилия (латиницей)
        surnameEngField.clear();
        surnameEngField.click();
        surnameEngField.sendKeys(serverConfig.surname());
    }

    public void fillNickName() {
//        WebElement addNick = driver.findElement(nickNameField);//Имя в блоге
        nickNameField.clear();
        nickNameField.click();
        nickNameField.sendKeys(serverConfig.name());
    }

    public void fillBirthDate() {
//        WebElement addBirth = driver.findElement(dateOfBirthField);//Дата рождения
        dateOfBirthField.clear();
        dateOfBirthField.click();
        dateOfBirthField.sendKeys(serverConfig.birth());
        dateOfBirthArea.click();
//        driver.findElement(dateOfBirthArea).click();
    }

    public void fillPersonalData() {
        fillName();
        fillEngName();
        fillSurname();
        fillEngSurname();
        fillNickName();
        fillBirthDate();
    }
}
