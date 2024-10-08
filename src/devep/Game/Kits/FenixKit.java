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

import devep.Locale.LocaleFactory;

public class FenixKit implements KitsInterface {

  private String name;
  private Player player;

  public FenixKit() {
    this.name = "Fenix";
  }

  @Override
  public ArrayList<ItemStack> getItems() {

    ArrayList<ItemStack> items = new ArrayList<ItemStack>();

    ItemStack elytra = new ItemStack(Material.ELYTRA, 1);
    ItemMeta elytraMeta = elytra.getItemMeta();
    elytraMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
    elytraMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    elytra.setItemMeta(elytraMeta);

    ItemStack rockets = new ItemStack(Material.FIREWORK_ROCKET, 5);
    ItemMeta rocketsMeta = rockets.getItemMeta();
    rocketsMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, true);
    rocketsMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
    rockets.setItemMeta(rocketsMeta);

    items.add(elytra);
    items.add(rockets);

    return items;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public ItemStack getDisplayMaterial() {
    ItemStack displayIcon = new ItemStack(Material.ELYTRA, 1);

    ItemMeta meta = displayIcon.getItemMeta();
    List<String> lore =
        Arrays.asList(
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_FENIX_1"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_FENIX_2"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_FENIX_3"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_FENIX_4"),
            "",
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_FENIX_5"));

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
    // For example, grant temporary flight, enhanced speed, or fire-related abilities
    // Since Elytra enchantments are not standard, consider implementing custom behaviors
  }
}
