package devep.Game.Kits;

import devep.Game.GameCore;
import devep.Locale.LocaleFactory;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArcherKit implements KitsInterface {

    private String name;
    private Player player;

    public ArcherKit() {
        this.name = "Archer";
    }

    public ArrayList<ItemStack> getItems() {

        ArrayList<ItemStack> items = new ArrayList<ItemStack>();

        ItemStack bow = GameCore.addEnchantToItem(new ItemStack(Material.BOW, 1), Enchantment.VANISHING_CURSE,1);
        ItemMeta meta = bow.getItemMeta();
        meta.addEnchant(Enchantment.ARROW_DAMAGE, 1, false);
        meta.addEnchant(Enchantment.VANISHING_CURSE, 1, false);

        bow.setItemMeta(meta);

        items.add(bow);
        items.add(new ItemStack(Material.ARROW, 10));

        return items;
    }

    public String getName() {
        return this.name;
    }

    public ItemStack getDisplayMaterial() {
        ItemStack displayIcon = new ItemStack(Material.BOW, 1);

        ItemMeta meta = displayIcon.getItemMeta();
        meta.setDisplayName(this.name);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lore = Arrays.asList(
                LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_ARCHER_1"),
                LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_ARCHER_2"),
                LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_ARCHER_3"),
                "",
                LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_ARCHER_4")
        );
        meta.setLore(lore);
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
