package devep;

import devep.Actions.GetSpawnLocation;
import devep.Actions.SummonLightning;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerMoveEvent;
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
    public void PlayerMoveEvent(PlayerMoveEvent event) {

        Location newPosition = event.getTo();

        if (newPosition.distance(SalvosMCRPG.spawnLocation) >= 1000) {
            newPosition.getBlock().setType(Material.ACACIA_LEAVES);
            event.setTo(event.getFrom());
        }

    }

}
