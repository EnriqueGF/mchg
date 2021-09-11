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

public class FarmerKit implements KitsInterface {

  private String name;
  private Player player;

  public FarmerKit() {
    this.name = "Farmer";
  }

  public ArrayList<ItemStack> getItems() {

    ArrayList<ItemStack> items = new ArrayList<ItemStack>();
    ItemStack ironHoe =
        GameCore.addEnchantToItem(
            new ItemStack(Material.IRON_HOE, 1), Enchantment.VANISHING_CURSE, 1);
    ItemStack seeds =
        GameCore.addEnchantToItem(
            new ItemStack(Material.WHEAT_SEEDS, 10), Enchantment.VANISHING_CURSE, 1);
    ItemStack goldenApple =
        GameCore.addEnchantToItem(
            new ItemStack(Material.GOLDEN_APPLE, 1), Enchantment.VANISHING_CURSE, 1);
    ItemStack pumpkinPies =
        GameCore.addEnchantToItem(
            new ItemStack(Material.PUMPKIN_PIE, 5), Enchantment.VANISHING_CURSE, 1);

    items.add(seeds);
    items.add(ironHoe);
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

    List<String> lore =
        Arrays.asList(
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_FARMER_1"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_FARMER_2"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_FARMER_3"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_FARMER_4"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_FARMER_5"),
            "",
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_FARMER_6"));

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
