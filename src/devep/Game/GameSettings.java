package devep.Game;

import org.bukkit.ChatColor;

import java.time.Instant;

public class GameSettings {
    // General
    public Integer requiredPlayersToStart = 2;
    public Integer invulnerabilityStageSeconds = 300;
    public static GameStatusEnum gameStatus = GameStatusEnum.BEFORE_START;
    public static String announcementsPrefix = ChatColor.GREEN + "[Classic HG] ";

    // World Border
    public Integer worldBorderSize = 2200; // Radio entre 2 para obtener el tamaño deseado
    public static Integer timeBetweenBorderCloses = 300;
    public static double borderRadius;
    public static long lastEdgeCloses = Instant.now().getEpochSecond();

    public GameSettings() {
    }

    public Integer getRequiredPlayersToStart() {
        return requiredPlayersToStart;
    }

    public Integer getWorldBorderSize() {
        return worldBorderSize;
    }

    public void setWorldBorderSize(Integer worldBorderSize) {
        this.worldBorderSize = worldBorderSize;
    }
}
