package enums;

public enum ContactTypes {
    VK("VK"),
    FACEBOOK("Facebook"),
    TELEGRAM("Тelegram"),
    OK("OK"),
    SKYPE("Skype"),
    VIBER("Viber"),
    WHATSAAP("WhatsApp"),
    HABR("Habr"),
    UNDEFINED("Не выбрано");

    private String frontTitle;

    ContactTypes(String frontName) {
        this.frontTitle = frontName;
    }

    public static ContactTypes getByFrontTitle(String frontTitle) {
        for (ContactTypes type : ContactTypes.values()) {
            if (type.frontTitle.equalsIgnoreCase(frontTitle)) return type;
        }
        return ContactTypes.UNDEFINED;
    }

    public String getFrontTitle() {
        return frontTitle;
    }
}
