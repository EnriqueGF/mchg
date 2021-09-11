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

public class ExplosiveKit implements KitsInterface {

  private String name;
  private Player player;

  public ExplosiveKit() {
    this.name = "Explosive";
  }

  public ArrayList<ItemStack> getItems() {

    ArrayList<ItemStack> items = new ArrayList<ItemStack>();
    ItemStack tnt =
        GameCore.addEnchantToItem(new ItemStack(Material.TNT, 4), Enchantment.VANISHING_CURSE, 1);
    ItemStack flintAndSteel =
        GameCore.addEnchantToItem(
            new ItemStack(Material.FLINT_AND_STEEL, 1), Enchantment.VANISHING_CURSE, 1);
    ItemStack leatherLeggings =
        GameCore.addEnchantToItem(
            new ItemStack(Material.LEATHER_LEGGINGS, 1), Enchantment.VANISHING_CURSE, 1);
    ItemStack apples =
        GameCore.addEnchantToItem(new ItemStack(Material.APPLE, 4), Enchantment.VANISHING_CURSE, 1);

    items.add(tnt);
    items.add(flintAndSteel);
    items.add(leatherLeggings);
    items.add(apples);

    return items;
  }

  public String getName() {
    return this.name;
  }

  public ItemStack getDisplayMaterial() {
    ItemStack displayIcon = new ItemStack(Material.TNT, 1);

    ItemMeta meta = displayIcon.getItemMeta();
    meta.setDisplayName(this.name);
    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    List<String> lore =
        Arrays.asList(
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_EXPLOSIVE_1"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_EXPLOSIVE_2"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_EXPLOSIVE_3"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_EXPLOSIVE_4"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_EXPLOSIVE_5"),
            "",
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_EXPLOSIVE_6"));
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
