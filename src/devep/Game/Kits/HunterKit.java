package devep.Game.Kits;

import devep.Game.GameCore;
import devep.Locale.LocaleFactory;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HunterKit implements KitsInterface {

    private String name;
    private Player player;

    public HunterKit() {
        this.name = "Hunter";
    }

    public ArrayList<ItemStack> getItems() {

        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        ItemStack ironSword = GameCore.addEnchantToItem(new ItemStack(Material.IRON_SWORD, 1), Enchantment.VANISHING_CURSE,1);

        items.add(ironSword);

        return items;
    }

    public String getName() {
        return this.name;
    }

    public ItemStack getDisplayMaterial() {
        ItemStack displayIcon = new ItemStack(Material.BONE, 1);

        ItemMeta meta = displayIcon.getItemMeta();
        List<String> lore = Arrays.asList(
                LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_HUNTER_1"),
                LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_HUNTER_2"),
                LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_HUNTER_3"),
                "",
                LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_HUNTER_4")
        );

        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        meta.setLore(lore);
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
