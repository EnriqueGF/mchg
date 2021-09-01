package devep.Locale;

public class EnglishLocale extends Locale {

    private String[][] translationsArr = {
            {"REMAINING_PLAYERS_TO_START", "%s players left to start"},
            {"INVULNERABILITY_STARTING_1", "All players are teleported to spawn area. Invulnerability for 4 minutes starts!"},
            {"GAME_STARTS_MESSAGE", "PVP is activated!"},
            {"ALL_PLAYERS_READY", "All the required players are ready"},
            {"INVULNERABILITY_STAGE_SECONDS_LEFT_COUNT", "%s seconds to start the game!"},
            {"PVP_SECONDS_LEFT_COUNT", "%s seconds to start to activate PVP"},
            {"ERROR_GAME_ALREADY_STARTED", "The game is already started"},
            {"YOU_DEAD_KILLED", "You have died!"},
    };

    public EnglishLocale() {
        super.translationsArr = this.translationsArr;
    }
}
