package devep.Hooks;

import com.github.yannicklamprecht.worldborder.api.PersistentWorldBorderApi;
import devep.Actions.BroadcastRequiredPlayers;
import devep.Actions.GetSpawnLocation;
import devep.Actions.GiveKitItem;
import devep.Actions.SummonLightning;
import devep.Game.GameCore;
import devep.Game.GameSettings;
import devep.Game.Gui.KitGui;
import devep.Locale.LocaleFactory;
import devep.ClassicHC;
import devep.ScheduleTasks;
import devep.WorldBorder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.event.world.WorldLoadEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import java.util.Iterator;

public class EventHooks implements Listener {

    private GameSettings gameSettings;
    private ScheduleTasks scheduleTasks;
    private BroadcastRequiredPlayers broadcastRequiredPlayers;
    private GameCore gameCore;

    public EventHooks(GameSettings gameSettings, ScheduleTasks scheduleTasks, GameCore gameCore) {
        this.gameSettings = gameSettings;
        this.scheduleTasks = scheduleTasks;
        this.gameCore = gameCore;
        this.broadcastRequiredPlayers = new BroadcastRequiredPlayers(this.gameSettings, this.scheduleTasks, this.gameCore);
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {

        SummonLightning sL = new SummonLightning();
        sL.executeAction(event);

    }

    @EventHandler
    public void WorldLoadEvent(WorldLoadEvent event) {

        GetSpawnLocation gSL = new GetSpawnLocation();
        gSL.executeAction(event);

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent playerJoinEvent) {

        GiveKitItem gKI = new GiveKitItem();
        gKI.executeAction(playerJoinEvent);

        if (ClassicHC.worldBorderAPI instanceof PersistentWorldBorderApi) {
            Bukkit.getScheduler().runTaskLater(ClassicHC.plugin, () -> {
                WorldBorder.sendWorldPacket(playerJoinEvent.getPlayer(), gameSettings.getWorldBorderRadius());
            }, 20 * 5);
        }

        this.broadcastRequiredPlayers.executeAction(playerJoinEvent);
        playerJoinEvent.getPlayer().setCollidable(false);

        Bukkit.getScheduler().scheduleSyncDelayedTask(ClassicHC.plugin, new Runnable() {
            @Override
            public void run() {
                ClassicHC.titleManagerAPI.giveScoreboard(playerJoinEvent.getPlayer());
                ClassicHC.titleManagerAPI.setScoreboardTitle(playerJoinEvent.getPlayer(), "${shine:[fade-in,stay,fade-out][primary-color,secondary-color]Classic Hunger Games}");
                ClassicHC.titleManagerAPI.setScoreboardValueWithPlaceholders(playerJoinEvent.getPlayer(), 1, "Players remaining: %{online} %{ping}");


                //ClassicHC.titleManagerAPI.sendTitle(playerJoinEvent.getPlayer(), "TITLE 1");
                //ClassicHC.titleManagerAPI.sendSubtitle(playerJoinEvent.getPlayer(), "TITLE 2");
            }
        }, 20*5);

    }

    @EventHandler
    public void onPlayerInteractItemClickEvent(PlayerInteractEvent event) {

        Player player = event.getPlayer();

        // Compass functionality
        Player target = gameCore.getNearestPlayer(player, 300.0);

        if((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && player.getItemInHand().getType() == Material.COMPASS && target != null) {
            GameCore.sendPlayerMessage("COMPASS_LOCATED_PLAYER", target.getName(), ChatColor.YELLOW, player);
            player.setCompassTarget(target.getLocation());
        }

        // Nether Start kit item
        if(player.getItemInHand() != null &&
           player.getItemInHand().getItemMeta() != null &&
           player.getItemInHand().getItemMeta().getDisplayName() != null
            && player.getItemInHand().getItemMeta().getDisplayName().equals("Kits")) {

            if (event.getHand() == EquipmentSlot.HAND && (event.getAction() == Action.RIGHT_CLICK_AIR ||event.getAction() == Action.RIGHT_CLICK_BLOCK)) {
                KitGui kitGui;

                if (KitGui.playersGuis.containsKey(player)) {
                    kitGui = KitGui.playersGuis.get(player);
                } else {
                    KitGui newKitGui = new KitGui(player);
                    KitGui.playersGuis.put(player, newKitGui);
                    kitGui = newKitGui;
                }

                kitGui.openGUIForPlayer(event.getPlayer());
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent playerQuitEvent) {

        this.broadcastRequiredPlayers.executeAction(playerQuitEvent);

    }

    @EventHandler
    public void onServerListPingEvent(ServerListPingEvent event)
    {
        String motd = "";

        switch(gameSettings.gameStatus) {
            case BEFORE_START:
                motd = "0";
            break;
            case INVULNERABILITY:
                motd = "1";
            break;

            case STARTED:
                motd = "2";
            break;

            default:
        }
        event.setMotd(motd);
    }

    @EventHandler
    public void onPlayerDeathDrop(PlayerDeathEvent e) {

        if (e.getDrops() == null) {
            return;
        }

        Iterator<ItemStack> i = e.getDrops().iterator();
        while (i.hasNext()) {
            ItemStack item = i.next();
            if (item.getEnchantments().containsKey(Enchantment.BINDING_CURSE)) {
                i.remove();
            }
        }

    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event){

        if (event.getItemDrop() == null || event.getItemDrop().getItemStack() == null || event.getItemDrop().getItemStack().getEnchantments() == null) {
            return;
        }

        if (event.getItemDrop().getItemStack().getEnchantments().containsKey(Enchantment.BINDING_CURSE)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(
                    LocaleFactory.getLocale(event.getPlayer().getLocale()).getTranslatedText("CANT_DROP_KIT_ITEM")
            );
        }
    }

}


