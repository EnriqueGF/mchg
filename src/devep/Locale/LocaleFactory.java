package devep.Locale;

public class LocaleFactory {
    public static Locale getLocale(String localeName){
        if("en".equalsIgnoreCase(localeName)) return new EnglishLocale();
        else if("es".equalsIgnoreCase(localeName)) return new SpanishLocale();

        return null;
    }
}
