package devep.Game.Kits;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BlacksmithKit implements KitsInterface {

    private String name;
    private Player player;

    public BlacksmithKit() {
        this.name = "Blacksmith";
    }

    public ArrayList<ItemStack> getItems() {

        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        items.add(new ItemStack(Material.IRON_HELMET, 1));
        items.add(new ItemStack(Material.IRON_BOOTS, 1));

        return items;
    }

    public String getName() {
        return this.name;
    }

    public ItemStack getDisplayMaterial() {
        ItemStack displayIcon = new ItemStack(Material.ANVIL, 1);

        ItemMeta meta = displayIcon.getItemMeta();
        meta.setDisplayName(this.name);

        displayIcon.setItemMeta(meta);

        return displayIcon;
    }

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void executePlayerAction() {}
}
