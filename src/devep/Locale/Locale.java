package devep.Locale;

public abstract class Locale {

  protected String[][] translationsArr;

  public String getTranslatedText(String key) {

    for (String[] arr : translationsArr) {
      if (arr[0].equals(key)) {
        return arr[1];
      }
    }

    return "KEY_" + key + "_NOT_FOUND";
  }
}
