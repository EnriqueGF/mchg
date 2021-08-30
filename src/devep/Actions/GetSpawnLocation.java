package devep.Actions;

import devep.SalvosMCRPG;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.event.world.WorldLoadEvent;

public class GetSpawnLocation implements ActionInterface {

    @Override
    public void executeAction(Event e) {
        WorldLoadEvent event = (WorldLoadEvent)e;

        if (!event.getWorld().getName().equals("world")) {
            return;
        }

        World world = event.getWorld();

        SalvosMCRPG.spawnLocation = world.getSpawnLocation();
    }
}
