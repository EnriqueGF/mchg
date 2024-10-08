package devep.Game.Kits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import devep.Game.GameCore;
import devep.Locale.LocaleFactory;

public class ThunderHammerKit implements KitsInterface {

  private String name;
  private Player player;

  public ThunderHammerKit() {
    this.name = "Thunder Hammer";
  }

  public ArrayList<ItemStack> getItems() {

    ArrayList<ItemStack> items = new ArrayList<ItemStack>();

    ItemStack mace =
        GameCore.addEnchantToItem(
            new ItemStack(Material.MACE, 1), Enchantment.VANISHING_CURSE, 1);

    items.add(mace);

    return items;
  }

  public String getName() {
    return this.name;
  }

  @Override
  public ItemStack getDisplayMaterial() {
    ItemStack displayIcon = new ItemStack(Material.MACE, 1);

    ItemMeta meta = displayIcon.getItemMeta();
    List<String> lore =
        Arrays.asList(
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_THUNDERHAMMER_1"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_THUNDERHAMMER_2"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_THUNDERHAMMER_3"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_THUNDERHAMMER_4"),
            "",
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_THUNDERHAMMER_5"));

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
