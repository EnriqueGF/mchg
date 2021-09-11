package devep.Game;

import devep.Game.Gui.KitGui;
import devep.Game.Kits.KitsInterface;
import devep.Locale.LocaleFactory;
import devep.ClassicHC;
import devep.ScheduleTasks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.time.Instant;
import java.util.Map;

public class GameCore {
    private GameSettings gameSettings;
    private ScheduleTasks scheduleTasks;
    private int checkForEnoughPlayersScheduleID;
    private int countInvulnerabilityScheduleID;
    private int countPVPEnabledScheduleID;
    public static int secondsCountInvulnerabilityStart = 10;
    public static int secondsCountPVPEnable = 5;
    public static long invulnerabilityStartTimestamp = 0;

    public GameCore(GameSettings gameSettings, ScheduleTasks scheduleTasks) {
        this.gameSettings = gameSettings;
        this.scheduleTasks = scheduleTasks;

        initCheckingForInvulnerabilityStage();
    }

    private void initCheckingForInvulnerabilityStage() {

        this.checkForEnoughPlayersScheduleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(ClassicHC.plugin, new Runnable() {
            @Override
            public void run() {
                    if (Bukkit.getOnlinePlayers().size() < gameSettings.getRequiredPlayersToStart()) {
                        return;
                    }

                    sendLocaleMessageToAllPlayers("ALL_PLAYERS_READY", "", ChatColor.LIGHT_PURPLE);

                    countInvulnerabilityScheduleID = ClassicHC.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(ClassicHC.plugin, new Runnable() {
                        public void run() {
                            for (Player p : Bukkit.getOnlinePlayers()){
                                p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                            }

                            sendLocaleMessageToAllPlayers("INVULNERABILITY_STAGE_SECONDS_LEFT_COUNT", Integer.toString(secondsCountInvulnerabilityStart), ChatColor.BLUE);
                            secondsCountInvulnerabilityStart = secondsCountInvulnerabilityStart - 1;
                            if (secondsCountInvulnerabilityStart <= 0) {
                                setInvulnerabilityStage();
                            }
                        }
                    }, 20*2, 20);

                    Bukkit.getScheduler().cancelTask(checkForEnoughPlayersScheduleID);

            }
        }, 0L, 20 * 5);
    }

    public void setInvulnerabilityStage() {
        Bukkit.getScheduler().cancelTask(countInvulnerabilityScheduleID);

        this.gameSettings.gameStatus = GameStatusEnum.INVULNERABILITY;

        ClassicHC.plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[Classic-HC] Iniciando invulnerabilidad por 4 minutos");

        teleportPlayersToSpawn();
    }

    private void teleportPlayersToSpawn() {

        ClassicHC.plugin.getServer().getWorld("world").setTime(0);

        sendLocaleMessageToAllPlayers("INVULNERABILITY_STARTING_1", "", ChatColor.GOLD);

        try {
            for (Player player : Bukkit.getOnlinePlayers()) {
                player.setCollidable(true);
                player.getInventory().clear();
                player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue());
                player.setFoodLevel(20);
                player.setSaturation(6.0f);
                player.teleport(ClassicHC.spawnLocation);
            }
        } catch (Exception ex) {
            System.out.println("Excepcion teletransportando players al spawn: " + ex);
        }

        try {
            applyPlayersKits();
        } catch (Exception ex) {
            System.out.println("Excepcion en ApplyPlayersKit: " + ex);
        }

        invulnerabilityStartTimestamp = Instant.now().getEpochSecond() + gameSettings.invulnerabilityStageSeconds;

    ClassicHC.plugin
        .getServer()
        .getScheduler()
        .scheduleSyncDelayedTask(
            ClassicHC.plugin,
            new Runnable() {
              public void run() {
                startMatchGame();
              }
            },
            20 * gameSettings.invulnerabilityStageSeconds);

    countPVPEnabledScheduleID =
        ClassicHC.plugin
            .getServer()
            .getScheduler()
            .scheduleSyncRepeatingTask(
                ClassicHC.plugin,
                new Runnable() {
                  public void run() {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                      p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                    }

                    sendLocaleMessageToAllPlayers(
                        "PVP_SECONDS_LEFT_COUNT",
                        Integer.toString(secondsCountPVPEnable),
                        ChatColor.BLUE);
                    secondsCountPVPEnable = secondsCountPVPEnable - 1;
                    if (secondsCountPVPEnable <= 0) {
                      Bukkit.getScheduler().cancelTask(countPVPEnabledScheduleID);
                    }
                  }
                },
                20 * (gameSettings.invulnerabilityStageSeconds - 5),
                20);
    }

    private void applyPlayersKits() {
        for (Map.Entry<Player, KitsInterface> entry : KitGui.playersKits.entrySet()) {
            Player player = entry.getKey();
            KitsInterface kit = entry.getValue();

            if (player.isOnline() && player.isValid() && player.getHealth() > 0) {
                KitGui.applyKitToPlayer(player, kit);
            }

        }
    }

    private void startMatchGame() {

        sendLocaleMessageToAllPlayers("GAME_STARTS_MESSAGE", "", ChatColor.RED);
        gameSettings.gameStatus = GameStatusEnum.STARTED;

        this.scheduleTasks.initWorldBorderClosingCheck();
    }

    public static void sendLocaleMessageToAllPlayers(String message, String replaceString, ChatColor textColor) {

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getTicksLived() >= 105) {
                sendPlayerMessage(message, replaceString, textColor, player);
            } else {
                // Delayed message due to waiting for client language packet on login
                Bukkit.getScheduler().runTaskLater(ClassicHC.plugin, () -> {
                    sendPlayerMessage(message, replaceString, textColor, player);
                }, 105);
            }
        }
    }
    public static void sendPlayerMessage(String message, String replaceString, ChatColor textColor, Player player) {

        String msg = LocaleFactory.getLocale(player.getLocale()).getTranslatedText(message);

        if (replaceString != "") {
            msg = msg.replace("%s", replaceString);
        }

        String finalMsg = msg;
        player.sendMessage(GameSettings.announcementsPrefix + textColor + finalMsg);

    };

    private void giveKitItemToPlayer(PlayerJoinEvent playerJoinEvent) {
        ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("Kits");
        item.setItemMeta(meta);
        playerJoinEvent.getPlayer().getInventory().addItem(item);
    }

    public static ItemStack addEnchantToItem(ItemStack item, Enchantment enchantment, int enchantmentLevel) {
        ItemMeta meta = item.getItemMeta();
        meta.addEnchant(enchantment, 1, false);

        item.setItemMeta(meta);

        return item;
    }

    public Player getNearestPlayer(Player player, Double range) {
        double distance = Double.POSITIVE_INFINITY; // To make sure the first
        // player checked is closest
        Player target = null;
        for (Entity entity : player.getNearbyEntities(range, range, range)) {
            if (!(entity instanceof Player))
                continue;
            if(entity == player) continue; //Added this check so you don't target yourself.
            double distanceto = player.getLocation().distance(entity.getLocation());
            if (distanceto > distance)
                continue;
            distance = distanceto;
            target = (Player) entity;
        }
        return target;
    }

}
