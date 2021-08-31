package devep;

import com.github.yannicklamprecht.worldborder.api.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Instant;

public class WorldBorder {

    public static int borderDistance;
    public static long lastEdgeCloses;

    public static void sendWorldPacket(Player player, int borderDistance) {
        WorldBorder.borderDistance = borderDistance;

        SalvosMCRPG.worldBorderAPI.setBorder(player, borderDistance, SalvosMCRPG.spawnLocation);

        PersistentWorldBorderApi persistentWorldBorderApi = ((PersistentWorldBorderApi) SalvosMCRPG.worldBorderAPI);
        WorldBorderData worldBorderData = persistentWorldBorderApi.getWorldBorderData(player);

        worldBorderData.setWarningTimeSeconds(1);
        worldBorderData.setDamageAmount(20.0);
        worldBorderData.setDamageBuffer(1);
        worldBorderData.setWarningDistance(5);

        if (worldBorderData != null) {
            IWorldBorder worldBorder = SalvosMCRPG.worldBorderAPI.getWorldBorder(player);
            worldBorderData.applyAll(worldBorder);
            worldBorder.send(player, WorldBorderAction.INITIALIZE);
        }

    }

    public static boolean isPlayerOutsideBorder(Player p, IWorldBorder border) {
        Location loc = p.getLocation();

        double size = border.getSize()/2;

        Position center = border.getCenter();
        double x = loc.getX() - center.x(), z = loc.getZ() - center.z();

        return ((x > size || (-x) > size) || (z > size || (-z) > size));
    }

}