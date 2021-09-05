package devep.Game.Kits;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;

public interface KitsInterface {
    ArrayList<ItemStack> getItems();
    String getName();
    ItemStack getDisplayMaterial();

    void setPlayer(Player player);
    void executePlayerAction();
}
