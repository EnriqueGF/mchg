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

public class MinerKit implements KitsInterface {

    private String name;
    private Player player;

    public MinerKit() {
        this.name = "Miner";
    }

    public ArrayList<ItemStack> getItems() {

        ArrayList<ItemStack> items = new ArrayList<ItemStack>();

        ItemStack ironPickaxe = GameCore.addEnchantToItem(new ItemStack(Material.IRON_PICKAXE, 1), Enchantment.VANISHING_CURSE,1);
        ironPickaxe.addEnchantment(Enchantment.DIG_SPEED, 1);

        ItemStack ironShovel = GameCore.addEnchantToItem(new ItemStack(Material.IRON_SHOVEL, 1), Enchantment.VANISHING_CURSE,1);
        ironShovel.addEnchantment(Enchantment.DIG_SPEED, 1);

        ItemStack torches = GameCore.addEnchantToItem(new ItemStack(Material.TORCH, 10), Enchantment.VANISHING_CURSE,1);

        items.add(ironPickaxe);
        items.add(ironShovel);
        items.add(torches);

        return items;
    }

    public String getName() {
        return this.name;
    }

    public ItemStack getDisplayMaterial() {
        ItemStack displayIcon = new ItemStack(Material.IRON_PICKAXE, 1);

        ItemMeta meta = displayIcon.getItemMeta();
        List<String> lore = Arrays.asList(
                LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_MINER_1"),
                LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_MINER_2"),
                LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_MINER_3"),
                LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_MINER_4"),
                "",
                LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_MINER_5")
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
    public void executePlayerAction() {}
}
