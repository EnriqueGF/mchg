package devep;

import devep.Game.GameCore;
import devep.Game.GameSettings;
import devep.Locale.LocaleFactory;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.time.Instant;

public class PlaceholdersExpansion extends PlaceholderExpansion {

  @Override
  public String getAuthor() {
    return "EnriqueGF";
  }

  @Override
  public String getIdentifier() {
    return "classichc";
  }

  @Override
  public String getVersion() {
    return "1.0.0";
  }

  @Override
  public String getRequiredPlugin() {
    return "Classic-HC";
  }

  @Override
  public String onRequest(OfflinePlayer player, String params) {
    // Vars
    if (params.equalsIgnoreCase("pvpcount")) {
      return getInvulnerbilityCountdown(player.getPlayer());
    }

    if (params.equalsIgnoreCase("gamestatus")) {
      return getGameStatusText(player);
    }

    // Texts
    if (params.equalsIgnoreCase("text-gamestatus")) {
      return LocaleFactory.getLocale(player.getPlayer().getLocale()).getTranslatedText("INFO_GAME_STATUS");
    }

    if (params.equalsIgnoreCase("text-aliveplayers")) {
      return LocaleFactory.getLocale(player.getPlayer().getLocale()).getTranslatedText("INFO_ALIVE_PLAYERS");
    }

    if (params.equalsIgnoreCase("text-bordersize")) {
      return LocaleFactory.getLocale(player.getPlayer().getLocale()).getTranslatedText("INFO_BORDER_SIZE");
    }

    if (params.equalsIgnoreCase("text-pvp")) {
      return LocaleFactory.getLocale(player.getPlayer().getLocale()).getTranslatedText("INFO_PVP");
    }
    return null;
  }


  @Override
  public boolean persist() {
    return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
  }

  private String getGameStatusText(OfflinePlayer player) {
    String text = "";

    switch(GameSettings.gameStatus) {
      case BEFORE_START:
        text = LocaleFactory.getLocale(player.getPlayer().getLocale()).getTranslatedText("INFO_GAME_STATUS_BEFORE_START");
        break;
      case INVULNERABILITY:
        text = LocaleFactory.getLocale(player.getPlayer().getLocale()).getTranslatedText("INFO_GAME_STATUS_INVULNERABILITY");
        break;
      case STARTED:
        text = LocaleFactory.getLocale(player.getPlayer().getLocale()).getTranslatedText("INFO_GAME_STATUS_STARTED");
        break;
      default:
    }

    return text;
  }

  private String getInvulnerbilityCountdown(Player player) {
    String restantTime = "";
    long secondsLeft = 0;

    if (GameCore.invulnerabilityStartTimestamp != 0) {
      secondsLeft = GameCore.invulnerabilityStartTimestamp - Instant.now().getEpochSecond();
      if (secondsLeft > 0) {
        restantTime = formatSeconds((int)secondsLeft);
      }
    }

    if (secondsLeft == 0) {
      restantTime = LocaleFactory.getLocale(player.getPlayer().getLocale()).getTranslatedText("INFO_WAITING_FOR_START");
    }

    if (secondsLeft < 0) {
      restantTime = "--:--:--";
    }

    return restantTime;
  }

  private String formatSeconds(int timeInSeconds)
  {
    int secondsLeft = timeInSeconds % 3600 % 60;
    int minutes = (int) Math.floor(timeInSeconds % 3600 / 60);
    int hours = (int) Math.floor(timeInSeconds / 3600);

    String HH = ((hours       < 10) ? "0" : "") + hours;
    String MM = ((minutes     < 10) ? "0" : "") + minutes;
    String SS = ((secondsLeft < 10) ? "0" : "") + secondsLeft;

    return HH + ":" + MM + ":" + SS;
  }

}
