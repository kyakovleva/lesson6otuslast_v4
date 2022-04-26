package utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JsOperations {

    private JavascriptExecutor js;

    public JsOperations(WebDriver driver) {
        js = (JavascriptExecutor) driver;
    }

    public void moveToElement(WebElement element) {
        js.executeScript("arguments[0].scrollIntoView(false)", element);
    }

}
