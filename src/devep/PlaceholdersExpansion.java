package devep;

import devep.Game.GameCore;
import devep.Game.GameSettings;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;

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
    if (params.equalsIgnoreCase("pvpcount")) {
      return getInvulnerbilityCountdown();
    }

    return null;
  }

  private String getInvulnerbilityCountdown() {
    String restantTime = "";
    long secondsLeft = 0;

    if (GameCore.invulnerabilityStartTimestamp != 0) {
      secondsLeft = GameCore.invulnerabilityStartTimestamp - Instant.now().getEpochSecond();
      if (secondsLeft > 0) {
        restantTime = formatSeconds((int)secondsLeft);
      }
    }

    if (secondsLeft == 0) {
      restantTime = "Waiting for start...";
    }

    if (secondsLeft < 0) {
      restantTime = "PVP activated!";
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
