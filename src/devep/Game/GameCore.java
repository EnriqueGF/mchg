package devep.Game;

import devep.Locale.LocaleFactory;
import devep.SalvosMCRPG;
import devep.ScheduleTasks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class GameCore {
    private GameSettings gameSettings;
    private ScheduleTasks scheduleTasks;
    private int checkForEnoughPlayersScheduleID;
    private int countInvulnerabilityScheduleID;
    private int countPVPEnabledScheduleID;
    public static int secondsCountInvulnerabilityStart = 10;
    public static int secondsCountPVPEnable = 5;

    public GameCore(GameSettings gameSettings, ScheduleTasks scheduleTasks) {
        this.gameSettings = gameSettings;
        this.scheduleTasks = scheduleTasks;

        initCheckingForInvulnerabilityStage();
    }

    private void initCheckingForInvulnerabilityStage() {

        this.checkForEnoughPlayersScheduleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SalvosMCRPG.plugin, new Runnable() {
            @Override
            public void run() {
                    if (Bukkit.getOnlinePlayers().size() < gameSettings.getRequiredPlayersToStart()) {
                        return;
                    }

                    sendLocaleMessageToAllPlayers("ALL_PLAYERS_READY", "", ChatColor.LIGHT_PURPLE);

                    countInvulnerabilityScheduleID = SalvosMCRPG.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(SalvosMCRPG.plugin, new Runnable() {
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

        SalvosMCRPG.plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SalvosMC-RPG] Iniciando invulnerabilidad por 4 minutos");

        teleportPlayersToSpawn();
    }

    private void teleportPlayersToSpawn() {

        sendLocaleMessageToAllPlayers("INVULNERABILITY_STARTING_1", "", ChatColor.GOLD);

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.teleport(SalvosMCRPG.spawnLocation);
        }

        SalvosMCRPG.plugin.getServer().getScheduler().scheduleSyncDelayedTask(SalvosMCRPG.plugin, new Runnable() {
            public void run() {
                startMatchGame();
            }
        }, 20 * gameSettings.invulnerabilityStageSeconds);


        countPVPEnabledScheduleID = SalvosMCRPG.plugin.getServer().getScheduler().scheduleSyncRepeatingTask(SalvosMCRPG.plugin, new Runnable() {
            public void run() {
                for (Player p : Bukkit.getOnlinePlayers()){
                    p.playSound(p.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 10, 1);
                }

                sendLocaleMessageToAllPlayers("PVP_SECONDS_LEFT_COUNT", Integer.toString(secondsCountPVPEnable), ChatColor.BLUE);
                secondsCountPVPEnable = secondsCountPVPEnable - 1;
                if (secondsCountPVPEnable <= 0) {
                    Bukkit.getScheduler().cancelTask(countPVPEnabledScheduleID);
                }
            }
        }, 20 * (gameSettings.invulnerabilityStageSeconds - 5), 20);

    }

    private void startMatchGame() {

        sendLocaleMessageToAllPlayers("GAME_STARTS_MESSAGE", "", ChatColor.RED);
        gameSettings.gameStatus = GameStatusEnum.STARTED;

        this.scheduleTasks.sendWorldBorderPackets();
    }

    public void sendLocaleMessageToAllPlayers(String message, String replaceString, ChatColor textColor) {

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (player.getTicksLived() >= 105) {
                sendPlayerMessage(message, replaceString, textColor, player);
            } else {
                // Delayed message due to waiting for client language packet on login
                Bukkit.getScheduler().runTaskLater(SalvosMCRPG.plugin, () -> {
                    sendPlayerMessage(message, replaceString, textColor, player);
                }, 105);
            }
        }
    }
    private void sendPlayerMessage(String message, String replaceString, ChatColor textColor, Player player) {

        String msg = LocaleFactory.getLocale(player.getLocale()).getTranslatedText(message);

        if (replaceString != "") {
            msg = msg.replace("%s", replaceString);
        }

        String finalMsg = msg;
        player.sendMessage(gameSettings.announcementsPrefix + textColor + finalMsg);

    };


}
