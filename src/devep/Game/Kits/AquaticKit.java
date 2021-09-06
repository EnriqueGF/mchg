package devep.Game.Kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class AquaticKit implements KitsInterface {

    private String name;
    private Player player;

    public AquaticKit() {
        this.name = "Aquatic";
    }

    public ArrayList<ItemStack> getItems() {

        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        ItemStack trident = new ItemStack(Material.TRIDENT, 1);
        ItemMeta meta = trident.getItemMeta();
        meta.addEnchant(Enchantment.RIPTIDE, 1, false);
        meta.addEnchant(Enchantment.BINDING_CURSE, 1, false);

        trident.setItemMeta(meta);

        ItemStack helmet = new ItemStack(Material.IRON_HELMET, 1);
        ItemMeta helmetMeta = helmet.getItemMeta();
        helmetMeta.addEnchant(Enchantment.OXYGEN, 1, false);

        helmet.setItemMeta(helmetMeta);

        items.add(trident);
        items.add(helmet);

        return items;
    }

    public String getName() {
        return this.name;
    }

    public ItemStack getDisplayMaterial() {
        ItemStack displayIcon = new ItemStack(Material.TRIDENT, 1);

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
