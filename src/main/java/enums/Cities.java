package enums;

public enum Cities {
    MSK("Москва", Countries.RUSSIA),
    KAZ("Казань", Countries.RUSSIA),
    UNDEFINED("Не выбрано", Countries.UNDEFINED);

    private String translate;
    private Countries country;

    Cities(String translate, Countries country) {
        this.translate = translate;
        this.country = country;
    }

    public Countries getCountry() {
        return country;
    }

    public String getTranslate() {
        return translate;
    }
}
