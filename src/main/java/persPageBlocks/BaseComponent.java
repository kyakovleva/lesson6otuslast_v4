package persPageBlocks;

import config.utils.JsOperations;
import config.utils.WaitMethods;
import org.openqa.selenium.WebDriver;

public class BaseComponent {
    protected WebDriver driver;
    protected WaitMethods wait;
    protected JsOperations js;

    public BaseComponent(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitMethods(driver, 5, 1);
        this.js = new JsOperations(driver);
    }
}
