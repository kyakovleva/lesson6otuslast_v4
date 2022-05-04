package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitMethods {

    private final Wait<WebDriver> wait;

    public WaitMethods(WebDriver driver, int timeout, int sleep) {

        wait = new WebDriverWait(driver, Duration.ofSeconds(timeout), Duration.ofSeconds(sleep));
    }

    public void waitUntilTextPresents(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public void waitUntilAttrPresents(WebElement save, String attr, String value) {
        wait.until(ExpectedConditions.attributeToBe(save, attr, value));
    }
}


