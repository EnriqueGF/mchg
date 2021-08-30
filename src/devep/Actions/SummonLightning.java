package devep.Actions;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDeathEvent;

public class SummonLightning implements ActionInterface {

    @Override
    public void executeAction(Event e) {
        EntityDeathEvent event = (EntityDeathEvent)e;

        Entity entity = event.getEntity();

        World world = entity.getWorld();
        Location location = entity.getLocation();

        world.strikeLightning(location);
    }
}
