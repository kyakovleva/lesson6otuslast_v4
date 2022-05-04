package persPageBlocks;

import config.ServerConfig;
import org.aeonbits.owner.ConfigFactory;
import utils.JsOperations;
import utils.WaitMethods;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class BaseComponent {
    protected final WebDriver driver;
    protected final WaitMethods wait;
    protected final JsOperations js;
    protected final ServerConfig serverConfig = ConfigFactory.create(ServerConfig.class);

    public BaseComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitMethods(driver, 5, 1);
        this.js = new JsOperations(driver);
        PageFactory.initElements(driver,this);
    }
}
