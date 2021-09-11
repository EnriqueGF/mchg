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

public class ArmerKit implements KitsInterface {

  private String name;
  private Player player;

  public ArmerKit() {
    this.name = "Armorer";
  }

  public ArrayList<ItemStack> getItems() {

    ArrayList<ItemStack> items = new ArrayList<ItemStack>();

    ItemStack ironSword =
        GameCore.addEnchantToItem(
            new ItemStack(Material.IRON_SWORD, 1), Enchantment.VANISHING_CURSE, 1);
    ItemStack shield =
        GameCore.addEnchantToItem(
            new ItemStack(Material.SHIELD, 1), Enchantment.VANISHING_CURSE, 1);

    items.add(ironSword);
    items.add(shield);

    return items;
  }

  public String getName() {
    return this.name;
  }

  public ItemStack getDisplayMaterial() {
    ItemStack displayIcon = new ItemStack(Material.IRON_SWORD, 1);

    ItemMeta meta = displayIcon.getItemMeta();
    meta.setDisplayName(this.name);
    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    List<String> lore =
        Arrays.asList(
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_ARMORER_1"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_ARMORER_2"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_ARMORER_3"),
            "",
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_ARMORER_4"));
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
