package exceptions;

import config.utils.WebDriverName;

public class DriverNotFoundException extends Exception {
    private final WebDriverName webDriverName;

    public DriverNotFoundException() {
        super(String.format("Driver for browser %s not found", WebDriverName.class);
        webDriverName = new WebDriverName;
    }
}


