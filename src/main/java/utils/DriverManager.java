package utils;

import enums.WebDriverName;
import exceptions.DriverNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.List;

public class DriverManager {
    private static final Logger logger = LogManager.getLogger(DriverManager.class);

    public static void startUp(WebDriverName webDriverName) throws DriverNotFoundException {
        WebDriverFactory.initDriverManager(webDriverName);
    }

    public static WebDriver initDriver(WebDriverName webDriverName, List<String> options) throws DriverNotFoundException {
        WebDriver driver = WebDriverFactory.create(webDriverName, options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        logger.info("Драйвер поднят с опциями = {}", options);
        return driver;
    }

    public static void end(WebDriver driver) {
        if (driver != null)
            driver.quit();
    }

    public static void close(WebDriver driver) {
        if (driver != null)
            driver.close();
    }
}
