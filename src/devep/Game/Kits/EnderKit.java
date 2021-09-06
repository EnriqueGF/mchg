package devep.Game.Kits;

import devep.Game.GameCore;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class EnderKit implements KitsInterface {

    private String name;
    private Player player;

    public EnderKit() {
        this.name = "Enderman";
    }

    public ArrayList<ItemStack> getItems() {

        ArrayList<ItemStack> items = new ArrayList<ItemStack>();

        ItemStack enderPearls = GameCore.addEnchantToItem(new ItemStack(Material.ENDER_PEARL, 5), Enchantment.BINDING_CURSE,1);
        ItemStack shulkerBox = GameCore.addEnchantToItem(new ItemStack(Material.SHULKER_BOX, 1), Enchantment.BINDING_CURSE,1);

        items.add(enderPearls);
        items.add(shulkerBox);

        return items;
    }

    public String getName() {
        return this.name;
    }

    public ItemStack getDisplayMaterial() {
        ItemStack displayIcon = new ItemStack(Material.ENDER_PEARL, 1);

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
