package devep.Locale;

import org.bukkit.ChatColor;

public class EnglishLocale extends Locale {

  private String[][] translationsArr = {
    // General

    {"REMAINING_PLAYERS_TO_START", "%s players left to start"},
    {
      "INVULNERABILITY_STARTING_1",
      "All players are teleported to spawn area. Invulnerability for 4 minutes starts!"
    },
    {"GAME_STARTS_MESSAGE", "PVP is activated!"},
    {"ALL_PLAYERS_READY", "All the required players are ready"},
    {"INVULNERABILITY_STAGE_SECONDS_LEFT_COUNT", "%s seconds to start the game!"},
    {"PVP_SECONDS_LEFT_COUNT", "%s seconds to start to activate PVP"},
    {"ERROR_GAME_ALREADY_STARTED", "The game is already started"},
    {"YOU_DEAD_KILLED", "You have died!"},
    {"CANT_DROP_KIT_ITEM", "You can't drop a kit item!"},
    {"BORDER_IS_CLOSING_ALERT", "The world border is closing!"},
    {"BOSSBAR_BORDER_YOUARESAFE", "You are safe from World Border"},
    {"BOSSBAR_BORDER_DANGER", "Take care! You are near the World Border!"},
    {"COMPASS_LOCATED_PLAYER", "Located player:" + ChatColor.LIGHT_PURPLE + " %s"},

    {"INFO_WAITING_FOR_START", "Waiting for start..."},
    {"INFO_GAME_STATUS_BEFORE_START", "Waiting for players..."},
    {"INFO_GAME_STATUS_INVULNERABILITY", "Invulnerability"},
    {"INFO_GAME_STATUS_STARTED", "PVP Activated"},
    {"INFO_GAME_STATUS", "Game Status"},
    {"INFO_ALIVE_PLAYERS", "Alive Players"},
    {"INFO_BORDER_SIZE", "World-Border Size"},
    {"INFO_PVP", "PvP Countdown"},

    // Kits
    {
      "KIT_SELECTED_MESSAGE",
      ChatColor.YELLOW
          + "You have selected the "
          + ChatColor.GOLD
          + "%s"
          + ChatColor.YELLOW
          + " kit!"
    },
    {"KIT_AQUATIC_1", ChatColor.BLUE + "Allows you to move through the water with ease"},
    {"KIT_AQUATIC_2", ChatColor.GRAY + "Trident with Riptide I"},
    {"KIT_AQUATIC_3", ChatColor.GRAY + "Iron Helmet with Oxygen I"},
    {"KIT_AQUATIC_4", ChatColor.YELLOW + "The Trident has lower damage than default"},
    {"KIT_ARCHER_1", ChatColor.BLUE + "Weak but strong in medium-large distances"},
    {"KIT_ARCHER_2", ChatColor.GRAY + "Bow with Power I"},
    {"KIT_ARCHER_3", ChatColor.GRAY + "10 arrows"},
    {"KIT_ARCHER_4", ChatColor.YELLOW + "A good option for players with good aim"},
    {"KIT_ARMORER_1", ChatColor.BLUE + "A Close-combat kit"},
    {"KIT_ARMORER_2", ChatColor.GRAY + "Iron sword"},
    {"KIT_ARMORER_3", ChatColor.GRAY + "Shield"},
    {"KIT_ARMORER_4", ChatColor.YELLOW + "Kit for real warriors"},
    {"KIT_BLACKSMITH_1", ChatColor.BLUE + "A Defensive kit"},
    {"KIT_BLACKSMITH_2", ChatColor.GRAY + "Iron leggings"},
    {"KIT_BLACKSMITH_3", ChatColor.GRAY + "Iron boots"},
    {"KIT_BLACKSMITH_4", ChatColor.YELLOW + "A good defense is a good offense"},
    {"KIT_ENDERMAN_1", ChatColor.BLUE + "Teleports and item saving"},
    {"KIT_ENDERMAN_2", ChatColor.GRAY + "5 Ender Pearls"},
    {"KIT_ENDERMAN_3", ChatColor.GRAY + "Shulker Box"},
    {"KIT_ENDERMAN_4", ChatColor.DARK_PURPLE + "E N D E R"},
    {"KIT_EXPLOSIVE_1", ChatColor.BLUE + "Explosions and disasters"},
    {"KIT_EXPLOSIVE_2", ChatColor.BOLD + "4 TNT"},
    {"KIT_EXPLOSIVE_3", ChatColor.GRAY + "Flint and steel"},
    {"KIT_EXPLOSIVE_4", ChatColor.GRAY + "Leather leggings"},
    {"KIT_EXPLOSIVE_5", ChatColor.GRAY + "4 Apples"},
    {"KIT_EXPLOSIVE_6", ChatColor.YELLOW + "Addicted to explosions?"},
    {"KIT_FARMER_1", ChatColor.BLUE + "The peaceful Farmer kit"},
    {"KIT_FARMER_2", ChatColor.BOLD + "Iron Hoe"},
    {"KIT_FARMER_3", ChatColor.GRAY + "10 Wheat seeds"},
    {"KIT_FARMER_4", ChatColor.GRAY + "Golden Apple"},
    {"KIT_FARMER_5", ChatColor.GRAY + "5 Pumpkin pie"},
    {"KIT_FARMER_6", ChatColor.YELLOW + "For calm players"},
    {"KIT_FISHERMAN_1", ChatColor.BLUE + "Fishes and sea treasures"},
    {"KIT_FISHERMAN_2", ChatColor.BOLD + "Fishing Rod"},
    {"KIT_FISHERMAN_3", ChatColor.GRAY + "5 Cooked Salmons"},
    {"KIT_FISHERMAN_4", ChatColor.GRAY + "Leather leggings"},
    {"KIT_FISHERMAN_5", ChatColor.GRAY + "Leather boots"},
    {"KIT_FISHERMAN_6", ChatColor.YELLOW + "For players who love fishing"},
    {"KIT_HORSERIDER_1", ChatColor.BLUE + "Horse riding and fight"},
    {"KIT_HORSERIDER_2", ChatColor.GRAY + "Horse spawn egg"},
    {"KIT_HORSERIDER_3", ChatColor.GRAY + "Saddle"},
    {"KIT_HORSERIDER_4", ChatColor.GRAY + "Horse diamond armour"},
    {"KIT_HORSERIDER_5", ChatColor.YELLOW + "For horse lovers!"},
    {"KIT_HUNTER_1", ChatColor.BLUE + "Fight with your wolf"},
    {"KIT_HUNTER_2", ChatColor.BOLD + "You get a domesticated Wolf"},
    {"KIT_HUNTER_3", ChatColor.GRAY + "Iron Sword"},
    {"KIT_HUNTER_4", ChatColor.YELLOW + "If you love pets!"},
    {"KIT_MINER_1", ChatColor.BLUE + "Could be good for starting mining diamonds"},
    {"KIT_MINER_2", ChatColor.GRAY + "Iron Pickaxe with Efficiency I"},
    {"KIT_MINER_3", ChatColor.GRAY + "Iron Shovel with Efficiency I"},
    {"KIT_MINER_4", ChatColor.GRAY + "10 Torches"},
    {"KIT_MINER_5", ChatColor.YELLOW + "For crazy miners"},
  };

  public EnglishLocale() {
    super.translationsArr = this.translationsArr;
  }
}
