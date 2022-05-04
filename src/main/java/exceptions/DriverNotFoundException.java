package exceptions;

import enums.WebDriverName;

public class DriverNotFoundException extends Exception {

    public DriverNotFoundException(WebDriverName driverName) {
        super(String.format("Driver for browser %s not found", driverName));
    }
}


