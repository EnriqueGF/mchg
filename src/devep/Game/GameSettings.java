package devep.Game;

import org.bukkit.ChatColor;

public class GameSettings {
    public Integer requiredPlayersToStart;
    public Integer worldBorderRadius = 3000;
    public Integer invulnerabilityStageSeconds = 20;
    public GameStatusEnum gameStatus = GameStatusEnum.BEFORE_START;
    public String announcementsPrefix = ChatColor.GREEN + "[Classic HG] ";
    public boolean deleteWorld = false;

    public GameSettings(Integer requiredPlayersToStart) {
        this.requiredPlayersToStart = requiredPlayersToStart;
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
