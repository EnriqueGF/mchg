package devep.Game.Gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class KitGui {
    private Gui gui;

    public KitGui() {
        createGUI();
    }

    private void createGUI() {
        Gui gui = Gui.gui()
                .title(Component.text("Kits"))
                .rows(6)
                .create();

        GuiItem guiItem = ItemBuilder.from(Material.IRON_HOE).asGuiItem(event -> {
            Player player = Bukkit.getPlayer(event.getWhoClicked().getUniqueId());
            player.closeInventory();

            player.sendMessage("Has elegido el kit del granjero");
            farmerKit(player);
        });

        gui.setItem(0, guiItem);

        this.gui = gui;
    }

    private void farmerKit(Player player) {
        player.getInventory().addItem(new ItemStack(Material.GOLDEN_APPLE, 1));
        player.getInventory().addItem(new ItemStack(Material.PUMPKIN_PIE, 10));
    }

    public void openGUIForPlayer(Player player) {

        this.gui.open(player);

    }
}
