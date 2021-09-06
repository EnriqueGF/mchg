package devep.Game.Kits;

import devep.Game.GameCore;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class FarmerKit implements KitsInterface {

    private String name;
    private Player player;

    public FarmerKit() {
        this.name = "Farmer";
    }

    public ArrayList<ItemStack> getItems() {

        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        ItemStack goldenApple = GameCore.addEnchantToItem(new ItemStack(Material.GOLDEN_APPLE, 1), Enchantment.BINDING_CURSE,1);
        ItemStack pumpkinPies = GameCore.addEnchantToItem(new ItemStack(Material.PUMPKIN_PIE, 10), Enchantment.BINDING_CURSE,1);

        items.add(goldenApple);
        items.add(pumpkinPies);

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

    @Override
    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void executePlayerAction() {}


}
