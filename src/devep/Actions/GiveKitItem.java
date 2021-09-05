package devep.Actions;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveKitItem implements ActionInterface {

    @Override
    public void executeAction(Event e) {

        PlayerJoinEvent event = (PlayerJoinEvent)e;

        ItemStack item = new ItemStack(Material.NETHER_STAR, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Kits");
        item.setItemMeta(meta);
        event.getPlayer().getInventory().addItem(item);

    }
}
