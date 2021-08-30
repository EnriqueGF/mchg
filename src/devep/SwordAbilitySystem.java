package devep;

import com.github.yannicklamprecht.worldborder.api.IWorldBorder;
import com.github.yannicklamprecht.worldborder.api.PersistentWorldBorderApi;
import com.github.yannicklamprecht.worldborder.api.WorldBorderAction;
import com.github.yannicklamprecht.worldborder.api.WorldBorderData;
import devep.Actions.GetSpawnLocation;
import devep.Actions.SummonLightning;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.world.WorldLoadEvent;

public class SwordAbilitySystem implements Listener {

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        SummonLightning sL = new SummonLightning();
        sL.executeAction(event);

    }

    @EventHandler
    public void WorldLoadEvent(WorldLoadEvent event) {

        GetSpawnLocation gSL = new GetSpawnLocation();
        gSL.executeAction(event);

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {

        if (SalvosMCRPG.worldBorderAPI instanceof PersistentWorldBorderApi) {

            Bukkit.getScheduler().runTaskLater(SalvosMCRPG.plugin, () -> {
                WorldBorder.sendWorldPacket(playerJoinEvent.getPlayer(), 500);
            }, 20 * 3);
        }
    }


}


