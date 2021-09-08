package devep;

import com.github.yannicklamprecht.worldborder.api.*;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Instant;

public class WorldBorder {

    public static double borderRadius;
    public static long lastEdgeCloses = Instant.now().getEpochSecond();

    public static void sendWorldPacket(Player player, double borderRadius) {

        WorldBorder.borderRadius = borderRadius;

        ClassicHC.worldBorderAPI.setBorder(player, borderRadius, ClassicHC.spawnLocation);

        PersistentWorldBorderApi persistentWorldBorderApi = ((PersistentWorldBorderApi) ClassicHC.worldBorderAPI);
        WorldBorderData worldBorderData = persistentWorldBorderApi.getWorldBorderData(player);

        worldBorderData.setWarningTimeSeconds(1);
        worldBorderData.setDamageAmount(20.0);
        worldBorderData.setDamageBuffer(1);
        worldBorderData.setWarningDistance(5);

        if (worldBorderData != null) {
            IWorldBorder worldBorder = ClassicHC.worldBorderAPI.getWorldBorder(player);
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

    public static void loadWorldBorderAPI() {
        new BukkitRunnable() {
            @Override
            public void run() {

                RegisteredServiceProvider<WorldBorderApi> worldBorderApiRegisteredServiceProvider = ClassicHC.plugin.getServer().getServicesManager().getRegistration(WorldBorderApi.class);

                if (worldBorderApiRegisteredServiceProvider == null) {
                    ClassicHC.plugin.getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Classic-HC] No se encuentra la API de WorldBorder");
                    ClassicHC.plugin.getServer().getPluginManager().disablePlugin(ClassicHC.plugin);
                    return;
                }

                WorldBorderApi worldBorderAPI = worldBorderApiRegisteredServiceProvider.getProvider();
                if (worldBorderAPI != null) {
                    ClassicHC.plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Classic-HC] API de WorldBorder Cargada");
                }

                ClassicHC.worldBorderAPI = worldBorderAPI;
            }

        }.runTaskLater(ClassicHC.plugin, 30);
    }


}