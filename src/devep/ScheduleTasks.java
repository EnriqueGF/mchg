package devep;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class ScheduleTasks {

    private Plugin plugin;

    public ScheduleTasks(Plugin plugin) {
        this.plugin = plugin;
    }

    public void sendWorldBorderPackets() {
        long sec = 1;

        this.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(this.plugin, new Runnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    WorldBorder.sendWorldPacket(player, WorldBorder.borderDistance - 1);
                }
            }
        }, 10L * sec, 10L * sec);
    }
}
