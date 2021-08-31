package devep.Locale;

public class SpanishLocale extends Locale {

    private String translationsArr[][] = {
            {"REMAINING_PLAYERS_TO_START", "Faltan %s jugadores para comenzar"},
            {"ORANGE_TEST", "Naranja"}
    };

    public SpanishLocale() {
        super.translationsArr = this.translationsArr;
    }
}
