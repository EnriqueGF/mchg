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

public class HorseRiderKit implements KitsInterface {

  private String name;
  private Player player;

  public HorseRiderKit() {
    this.name = "Horse Rider";
  }

  public ArrayList<ItemStack> getItems() {
    ArrayList<ItemStack> items = new ArrayList<ItemStack>();

    ItemStack horseSpawnEgg =
        GameCore.addEnchantToItem(
            new ItemStack(Material.HORSE_SPAWN_EGG, 1), Enchantment.VANISHING_CURSE, 1);
    ItemStack saddle =
        GameCore.addEnchantToItem(
            new ItemStack(Material.SADDLE, 1), Enchantment.VANISHING_CURSE, 1);
    ItemStack diamondHorseArmor =
        GameCore.addEnchantToItem(
            new ItemStack(Material.DIAMOND_HORSE_ARMOR, 1), Enchantment.VANISHING_CURSE, 1);

    ItemStack lead =
            GameCore.addEnchantToItem(
                    new ItemStack(Material.LEAD, 1), Enchantment.VANISHING_CURSE, 1);

    items.add(horseSpawnEgg);
    items.add(saddle);
    items.add(diamondHorseArmor);
    items.add(lead);

    return items;
  }

  public String getName() {
    return this.name;
  }

  public ItemStack getDisplayMaterial() {
    ItemStack displayIcon = new ItemStack(Material.SADDLE, 1);

    ItemMeta meta = displayIcon.getItemMeta();
    List<String> lore =
        Arrays.asList(
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_HORSERIDER_1"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_HORSERIDER_2"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_HORSERIDER_3"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_HORSERIDER_4"),
            "",
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_HORSERIDER_5"));

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
