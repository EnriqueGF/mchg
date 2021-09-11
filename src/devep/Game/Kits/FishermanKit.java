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

public class FishermanKit implements KitsInterface {

  private String name;
  private Player player;

  public FishermanKit() {
    this.name = "Fisherman";
  }

  public ArrayList<ItemStack> getItems() {

    ArrayList<ItemStack> items = new ArrayList<ItemStack>();

    ItemStack fishRoad =
        GameCore.addEnchantToItem(
            new ItemStack(Material.FISHING_ROD, 1), Enchantment.VANISHING_CURSE, 1);

    ItemMeta meta = fishRoad.getItemMeta();
    meta.addEnchant(Enchantment.LUCK, 3, false);
    meta.addEnchant(Enchantment.LURE, 3, false);
    meta.addEnchant(Enchantment.DURABILITY, 3, false);

    fishRoad.setItemMeta(meta);

    ItemStack cookedSalmons =
        GameCore.addEnchantToItem(
            new ItemStack(Material.COOKED_SALMON, 5), Enchantment.VANISHING_CURSE, 1);
    ItemStack leatherLeggings =
        GameCore.addEnchantToItem(
            new ItemStack(Material.LEATHER_LEGGINGS, 1), Enchantment.VANISHING_CURSE, 1);
    ItemStack leatherBoots =
        GameCore.addEnchantToItem(
            new ItemStack(Material.LEATHER_BOOTS, 1), Enchantment.VANISHING_CURSE, 1);

    items.add(fishRoad);
    items.add(cookedSalmons);
    items.add(leatherLeggings);
    items.add(leatherBoots);

    return items;
  }

  public String getName() {
    return this.name;
  }

  public ItemStack getDisplayMaterial() {
    ItemStack displayIcon = new ItemStack(Material.FISHING_ROD, 1);

    ItemMeta meta = displayIcon.getItemMeta();
    List<String> lore =
        Arrays.asList(
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_FISHERMAN_1"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_FISHERMAN_2"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_FISHERMAN_3"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_FISHERMAN_4"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_FISHERMAN_5"),
            "",
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_FISHERMAN_6"));

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
