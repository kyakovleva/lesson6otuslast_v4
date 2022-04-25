package Utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JsOperations {

    private static JavascriptExecutor js;

    public JsOperations(WebDriver driver) {
        js =(JavascriptExecutor)driver;
    }

    public static void moveToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(false)", element);
    }

}
