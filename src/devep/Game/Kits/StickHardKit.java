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

public class StickHardKit implements KitsInterface {

  private String name;
  private Player player;

  public StickHardKit() {
    this.name = "Stick Hard";
  }

  @Override
  public ArrayList<ItemStack> getItems() {

    ArrayList<ItemStack> items = new ArrayList<ItemStack>();

    ItemStack stickHard =
        GameCore.addEnchantToItem(
            new ItemStack(Material.STICK, 1), Enchantment.KNOCKBACK, 2);
    // stickHard.addEnchantment(Enchantment.KNOCKBACK, 1);

    ItemStack leatherChestplate =
    GameCore.addEnchantToItem(
            new ItemStack(Material.LEATHER_CHESTPLATE, 1), Enchantment.VANISHING_CURSE, 1);
    leatherChestplate.addEnchantment(Enchantment.UNBREAKING, 2);

    items.add(stickHard);
    items.add(leatherChestplate);

    return items;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public ItemStack getDisplayMaterial() {
    ItemStack displayIcon = new ItemStack(Material.STICK, 1);

    ItemMeta meta = displayIcon.getItemMeta();
    List<String> lore =
        Arrays.asList(
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_STICKHARD_1"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_STICKHARD_2"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_STICKHARD_3"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_STICKHARD_4"),
            "",
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_STICKHARD_5"));

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
    // Define the action when a player uses this kit
    // For example, provide special abilities or behaviors
  }
}
