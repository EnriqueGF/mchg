package devep.Locale;

public class EnglishLocale extends Locale {

    private String[][] translationsArr = {
            {"REMAINING_PLAYERS_TO_START", "%s players left to start"},
            {"ORANGE_TEST", "Orange"}
    };

    public EnglishLocale() {
        super.translationsArr = this.translationsArr;
    }
}
