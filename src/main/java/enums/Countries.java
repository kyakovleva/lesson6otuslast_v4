package enums;

public enum Countries {
    RUSSIA("Россия"),
    KAZAKHSTAN("Казахстан"),
    UNDEFINED("Не выбрано");

    private String translate;

    Countries(String translate) {
        this.translate = translate;
    }

    public String getTranslate() {
        return translate;
    }
}
