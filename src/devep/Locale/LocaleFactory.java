package devep.Locale;

public class LocaleFactory {
  public static Locale getLocale(String localeName) {
    if (localeName.contains("en")) return new EnglishLocale();
    else if (localeName.contains("es")) return new SpanishLocale();

    return new EnglishLocale(); // Fallback language
  }
}
