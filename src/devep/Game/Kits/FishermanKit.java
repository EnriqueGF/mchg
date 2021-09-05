package devep.Game.Kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class FishermanKit implements KitsInterface {

    private String name;
    private Player player;

    public FishermanKit() {
        this.name = "Fisherman";
    }

    public ArrayList<ItemStack> getItems() {

        ArrayList<ItemStack> items = new ArrayList<ItemStack>();

        ItemStack fishRoad = new ItemStack(Material.FISHING_ROD, 1);
        ItemMeta meta = fishRoad.getItemMeta();
        meta.addEnchant(Enchantment.LUCK, 3, false);
        meta.addEnchant(Enchantment.LURE, 3, false);
        meta.addEnchant(Enchantment.DURABILITY, 3, false);

        fishRoad.setItemMeta(meta);

        items.add(fishRoad);
        items.add(new ItemStack(Material.COOKED_SALMON, 5));
        items.add(new ItemStack(Material.LEATHER_LEGGINGS, 1));
        items.add(new ItemStack(Material.LEATHER_BOOTS, 1));

        return items;
    }

    public String getName() {
        return this.name;
    }

    public ItemStack getDisplayMaterial() {
        ItemStack displayIcon = new ItemStack(Material.FISHING_ROD, 1);

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
