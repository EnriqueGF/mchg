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

public class BlacksmithKit implements KitsInterface {

    private String name;
    private Player player;

    public BlacksmithKit() {
        this.name = "Blacksmith";
    }

    public ArrayList<ItemStack> getItems() {

        ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        ItemStack ironHelmet = GameCore.addEnchantToItem(new ItemStack(Material.IRON_LEGGINGS, 1), Enchantment.BINDING_CURSE,1);
        ItemStack ironBoots = GameCore.addEnchantToItem(new ItemStack(Material.IRON_BOOTS, 1), Enchantment.BINDING_CURSE,1);

        items.add(ironHelmet);
        items.add(ironBoots);

        return items;
    }

    public String getName() {
        return this.name;
    }

    public ItemStack getDisplayMaterial() {
        ItemStack displayIcon = new ItemStack(Material.ANVIL, 1);

        ItemMeta meta = displayIcon.getItemMeta();
        meta.setDisplayName(this.name);
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        List<String> lore = Arrays.asList(
                LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_BLACKSMITH_1"),
                LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_BLACKSMITH_2"),
                LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_BLACKSMITH_3"),
                "",
                LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_BLACKSMITH_4")
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
