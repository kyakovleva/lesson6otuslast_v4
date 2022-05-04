package enums;

public enum EnglishLevels {
    BEGINNER("Начальный уровень (Beginner)"),
    PREINTERMEDIATE("Ниже среднего (Pre-Intermediate)"),
    INTERMEDIATE("Средний (Intermediate)"),
    UPPERINTERMEDIATE("Выше среднего (Upper Intermediate)"),
    ADVANCED("Продвинутый (Advanced)"),
    FLUENT("Супер продвинутый (Mastery)"),
    UNDEFINED("Не выбрано");

    private String languageDescr;

    EnglishLevels(String languageDescr) {
        this.languageDescr = languageDescr;
    }

    public String getLanguageDescr() {
        return this.languageDescr;
    }
}
