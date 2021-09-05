package devep.Game.Kits;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class AquaticKit implements KitsInterface {

    private String name;

    public AquaticKit() {
        this.name = "Farmer";
    }

    public ArrayList<ItemStack> getItems() {

        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        items.add(new ItemStack(Material.GOLDEN_APPLE, 1));
        items.add(new ItemStack(Material.PUMPKIN_PIE, 10));

        return items;
    }

    public String getName() {
        return this.name;
    }

    public ItemStack getDisplayMaterial() {
        ItemStack displayIcon = new ItemStack(Material.IRON_HOE, 1);

        ItemMeta meta = displayIcon.getItemMeta();
        meta.setDisplayName(this.name);

        displayIcon.setItemMeta(meta);

        return displayIcon;
    }


}
