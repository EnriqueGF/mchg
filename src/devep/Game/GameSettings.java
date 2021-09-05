package devep.Game;

import org.bukkit.ChatColor;

public class GameSettings {
    public Integer requiredPlayersToStart = 2;
    public Integer worldBorderRadius = 3000;
    public Integer invulnerabilityStageSeconds = 300;
    public static GameStatusEnum gameStatus = GameStatusEnum.BEFORE_START;
    public String announcementsPrefix = ChatColor.GREEN + "[Classic HG] ";

    public GameSettings() {
    }

    public Integer getRequiredPlayersToStart() {
        return requiredPlayersToStart;
    }

    public Integer getWorldBorderRadius() {
        return worldBorderRadius;
    }

    public void setWorldBorderRadius(Integer worldBorderRadius) {
        this.worldBorderRadius = worldBorderRadius;
    }
}
