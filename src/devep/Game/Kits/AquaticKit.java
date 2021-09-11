package devep.Game.Kits;

import devep.Locale.LocaleFactory;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class AquaticKit implements KitsInterface {

  private String name;
  private Player player;

  public AquaticKit() {
    this.name = "Aquatic";
  }

  public ArrayList<ItemStack> getItems() {

    ArrayList<ItemStack> items = new ArrayList<ItemStack>();
    ItemStack trident = new ItemStack(Material.TRIDENT, 1);
    ItemMeta meta = trident.getItemMeta();

    AttributeModifier attackSpeedModifier =
        new AttributeModifier(
            UUID.randomUUID(),
            "generic.attackSpeed",
            -3,
            AttributeModifier.Operation.ADD_NUMBER,
            EquipmentSlot.HAND);
    AttributeModifier damageModifier =
        new AttributeModifier(
            UUID.randomUUID(),
            "generic.attackDamage",
            4,
            AttributeModifier.Operation.ADD_NUMBER,
            EquipmentSlot.HAND);
    meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, damageModifier);
    meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attackSpeedModifier);
    meta.addEnchant(Enchantment.RIPTIDE, 1, false);
    meta.addEnchant(Enchantment.VANISHING_CURSE, 1, false);

    trident.setItemMeta(meta);

    ItemStack helmet = new ItemStack(Material.IRON_HELMET, 1);
    ItemMeta helmetMeta = helmet.getItemMeta();
    helmetMeta.addEnchant(Enchantment.OXYGEN, 1, false);
    helmetMeta.addEnchant(Enchantment.VANISHING_CURSE, 1, false);

    helmet.setItemMeta(helmetMeta);

    items.add(trident);
    items.add(helmet);

    return items;
  }

  public String getName() {
    return this.name;
  }

  public ItemStack getDisplayMaterial() {
    ItemStack displayIcon = new ItemStack(Material.TRIDENT, 1);

    ItemMeta meta = displayIcon.getItemMeta();
    meta.setDisplayName(this.name);
    meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

    List<String> lore =
        Arrays.asList(
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_AQUATIC_1"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_AQUATIC_2"),
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_AQUATIC_3"),
            "",
            LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_AQUATIC_4"));
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
