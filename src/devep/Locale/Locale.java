package devep.Locale;

public abstract class Locale {

    protected String translationsArr[][];

    public String getTranslatedText(String key) {

        for(String[] arr2: translationsArr)
        {
            for(String val: arr2)
                return val;
        }

        return "KEY_" + key + "_NOT_FOUND";
    }
}
