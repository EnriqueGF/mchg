package devep.Game.Kits;

import devep.Game.GameCore;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ArmerKit implements KitsInterface {

    private String name;
    private Player player;

    public ArmerKit() {
        this.name = "Armer";
    }

    public ArrayList<ItemStack> getItems() {

        ArrayList<ItemStack> items = new ArrayList<ItemStack>();

        ItemStack ironSword = GameCore.addEnchantToItem(new ItemStack(Material.IRON_SWORD, 1), Enchantment.BINDING_CURSE,1);
        ItemStack shield = GameCore.addEnchantToItem(new ItemStack(Material.SHIELD, 1), Enchantment.BINDING_CURSE,1);

        items.add(ironSword);
        items.add(shield);

        return items;
    }

    public String getName() {
        return this.name;
    }

    public ItemStack getDisplayMaterial() {
        ItemStack displayIcon = new ItemStack(Material.IRON_SWORD, 1);

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
