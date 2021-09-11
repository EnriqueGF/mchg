package devep.Game.Gui;

import dev.triumphteam.gui.builder.item.ItemBuilder;
import dev.triumphteam.gui.guis.Gui;
import dev.triumphteam.gui.guis.GuiItem;
import devep.Game.Kits.KitsInterface;
import devep.Locale.LocaleFactory;
import io.github.classgraph.ClassGraph;
import io.github.classgraph.ScanResult;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KitGui {
    private Gui gui;
    private Player player;

    public KitGui(Player player) {
        this.player = player;
        createGUI();
    }

    public static HashMap<Player, KitGui> playersGuis = new HashMap<>();
    public static HashMap<Player, KitsInterface> playersKits = new HashMap<>();

    private void createGUI() {
        Gui gui = Gui.gui()
                .title(Component.text("Kits"))
                .rows(2)
                .create();


        int index = 0;
    for (KitsInterface kit : getKits()) {

            kit.setPlayer(this.player);

            GuiItem guiItem = ItemBuilder.from(kit.getDisplayMaterial()).asGuiItem(event -> {
                Player player = Bukkit.getPlayer(event.getWhoClicked().getUniqueId());
                player.closeInventory();

                if (playersKits.containsKey(player)) {
                    playersKits.remove(player);
                }

                playersKits.put(player, kit);

                player.sendMessage(LocaleFactory.getLocale(player.getLocale()).getTranslatedText("KIT_SELECTED_MESSAGE").replace("%s", kit.getName()));
                player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 1);
            });

            gui.setItem(index++, guiItem);
        }

        this.gui = gui;
    }

    public static void applyKitToPlayer(Player player, KitsInterface kit) {
        kit.setPlayer(player);

        for(ItemStack item : kit.getItems()) {
            player.getInventory().addItem(item);
        }

        kit.executePlayerAction();
    }

    private ArrayList<KitsInterface> getKits() {

        ArrayList<KitsInterface> kits = new ArrayList<KitsInterface>();

        try {
            List<String> classNames = new ArrayList<>();
            try (ScanResult scanResult = new ClassGraph().acceptPackages("devep.Game.Kits").enableClassInfo().scan()) {
                classNames.addAll(scanResult.getAllClasses().getNames());
            }

            for (String classPath : classNames) {

                if (classPath.contains("KitsInterface")) {
                    continue;
                }

                Class<?> clazz = Class.forName(classPath);
                KitsInterface kit = (KitsInterface) clazz.getDeclaredConstructor().newInstance();
                kits.add(kit);

            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return kits;
    }

    public void openGUIForPlayer(Player player) {
        this.gui.open(player);
    }
}
