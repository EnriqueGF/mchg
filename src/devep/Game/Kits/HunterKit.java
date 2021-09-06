package devep.Game.Kits;

import devep.Game.GameCore;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class HunterKit implements KitsInterface {

    private String name;
    private Player player;

    public HunterKit() {
        this.name = "Hunter";
    }

    public ArrayList<ItemStack> getItems() {

        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        ItemStack ironSword = GameCore.addEnchantToItem(new ItemStack(Material.IRON_SWORD, 1), Enchantment.BINDING_CURSE,1);

        items.add(ironSword);

        return items;
    }

    public String getName() {
        return this.name;
    }

    public ItemStack getDisplayMaterial() {
        ItemStack displayIcon = new ItemStack(Material.BONE, 1);

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
    public void executePlayerAction() {
        Wolf wolf = (Wolf) this.player.getWorld().spawnEntity(this.player.getLocation(), EntityType.WOLF);
        wolf.setOwner(player);
    }
}
