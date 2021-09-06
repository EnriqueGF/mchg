package devep.Locale;

import org.bukkit.ChatColor;

public class EnglishLocale extends Locale {

    private String[][] translationsArr = {
            // General

            {"REMAINING_PLAYERS_TO_START", "%s players left to start"},
            {"INVULNERABILITY_STARTING_1", "All players are teleported to spawn area. Invulnerability for 4 minutes starts!"},
            {"GAME_STARTS_MESSAGE", "PVP is activated!"},
            {"ALL_PLAYERS_READY", "All the required players are ready"},
            {"INVULNERABILITY_STAGE_SECONDS_LEFT_COUNT", "%s seconds to start the game!"},
            {"PVP_SECONDS_LEFT_COUNT", "%s seconds to start to activate PVP"},
            {"ERROR_GAME_ALREADY_STARTED", "The game is already started"},
            {"YOU_DEAD_KILLED", "You have died!"},
            {"CANT_DROP_KIT_ITEM", "You can't drop a kit item!"},

            // Kits
            {"KIT_AQUATIC_1", ChatColor.BLUE + "Allows you to move through the water with ease"},
            {"KIT_AQUATIC_2", ChatColor.GRAY + "Trident with Riptide I"},
            {"KIT_AQUATIC_3", ChatColor.GRAY + "Iron Helmet with Oxygen I"},

            {"KIT_ARCHER_1", ChatColor.BLUE + "Weak but strong in medium-large distances"},
            {"KIT_ARCHER_2", ChatColor.GRAY + "Bow with Power I"},
            {"KIT_ARCHER_3", ChatColor.GRAY + "10 arrows"},
    };

    public EnglishLocale() {
        super.translationsArr = this.translationsArr;
    }
}
