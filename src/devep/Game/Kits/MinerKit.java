package devep.Game.Kits;

import devep.Game.GameCore;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class MinerKit implements KitsInterface {

    private String name;
    private Player player;

    public MinerKit() {
        this.name = "Miner";
    }

    public ArrayList<ItemStack> getItems() {

        ArrayList<ItemStack> items = new ArrayList<ItemStack>();

        ItemStack ironPickaxe = GameCore.addEnchantToItem(new ItemStack(Material.IRON_PICKAXE, 1), Enchantment.BINDING_CURSE,1);
        ItemStack ironShovel = GameCore.addEnchantToItem(new ItemStack(Material.IRON_PICKAXE, 1), Enchantment.BINDING_CURSE,1);

        items.add(ironPickaxe);
        items.add(ironShovel);

        return items;
    }

    public String getName() {
        return this.name;
    }

    public ItemStack getDisplayMaterial() {
        ItemStack displayIcon = new ItemStack(Material.IRON_PICKAXE, 1);

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
