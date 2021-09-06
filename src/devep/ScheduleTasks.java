package devep;

import com.github.yannicklamprecht.worldborder.api.IWorldBorder;
import devep.Game.GameCore;
import devep.Game.GameSettings;
import devep.Game.GameStatusEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.time.Instant;

public class ScheduleTasks {

    private Plugin plugin;
    private int lookForGameFinishScheduleID;

    public ScheduleTasks(Plugin plugin) {
        this.plugin = plugin;

        InitializeScheduling();
    }

    private void InitializeScheduling() {
        checkPlayersOutsideBorders();
        lookForGameFinish();
    }

    public void sendWorldBorderPackets() {

        int waitBetweenEdgeClosesSeconds = GameSettings.timeBetweenBorderCloses;

        Thread t = new Thread(() -> {
            try {
                while (true) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    int onlinePlayersCount = Bukkit.getOnlinePlayers().size();
                    int edgeCloseLength = 0;


                    if (Math.abs(Instant.now().getEpochSecond() - WorldBorder.lastEdgeCloses) >= waitBetweenEdgeClosesSeconds) {

                        WorldBorder.lastEdgeCloses = Instant.now().getEpochSecond();

                        if (onlinePlayersCount <= 5 && WorldBorder.borderRadius > 800) {
                            edgeCloseLength = 350;
                        } else if (onlinePlayersCount <= 10 && WorldBorder.borderRadius > 1500) {
                            edgeCloseLength = 500;
                        } else if (onlinePlayersCount <= 20 && WorldBorder.borderRadius > 2000) {
                            edgeCloseLength = 600;
                        } else if (onlinePlayersCount <= 30 && WorldBorder.borderRadius > 2500) {
                            edgeCloseLength = 800;
                        } else if (onlinePlayersCount <= 50 && WorldBorder.borderRadius > 2900) {
                            edgeCloseLength = 500;
                        } else {
                            edgeCloseLength = 0;
                        }

                        if (edgeCloseLength != 0) {
                            // Close border

                            GameCore.sendLocaleMessageToAllPlayers("BORDER_IS_CLOSING_ALERT", "", ChatColor.RED);

                            for (int i = 0; i < edgeCloseLength; i++) {
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    try {
                                        Bukkit.getScheduler().runTask(plugin, new Runnable() {
                                            @Override
                                            public void run() {
                                                WorldBorder.sendWorldPacket(player, WorldBorder.borderRadius - 0.065);
                                            }
                                        });

                                    } catch (Exception ex) {
                                        System.out.println("sendWorldBorderPackets() : " + ex.toString());
                                    }

                                    try {
                                        Thread.sleep(40);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println("Excepción producida en sendWorldBorderPackets(): " + ex.toString());
            }
        });

        t.start();
    }

    public void checkPlayersOutsideBorders() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(SalvosMCRPG.plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        IWorldBorder worldBorder = SalvosMCRPG.worldBorderAPI.getWorldBorder(player);
                        if (worldBorder != null) {
                            if (WorldBorder.isPlayerOutsideBorder(player, worldBorder)) {
                                player.damage(1);
                            }
                        }
                    }
                } catch(Exception ex) {
                    System.out.println("Excepción producida en checkPlayersOutsideBorders(): " + ex.toString());
                }
            }
        }, 0L, 20 * 1);
    }

    public void lookForGameFinish() {
        lookForGameFinishScheduleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SalvosMCRPG.plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    if (GameSettings.gameStatus == GameStatusEnum.BEFORE_START) {
                        return;
                    }

                    if (Bukkit.getOnlinePlayers().size() == 1) {
                        Player winPlayer = Bukkit.getOnlinePlayers().iterator().next();
                        Bukkit.getServer().getWorld("world").spawnEntity(winPlayer.getLocation(), EntityType.FIREWORK);
                        winPlayer.sendMessage(ChatColor.GOLD + "You win!!");
                        winPlayer.sendMessage(ChatColor.GREEN + "Congrats!!");

                        Bukkit.getScheduler().cancelTask(lookForGameFinishScheduleID);
                    }

                    if (Bukkit.getOnlinePlayers().size() == 0) {
                        System.out.println("game finish");
                    }

                } catch(Exception ex) {
                    System.out.println("Excepción producida en lookForGameFinish(): " + ex.toString());
                }
            }
        }, 0L, 20 * 3); //0 Tick initial delay, 20 Tick (1 Second) between repeats
    }

}
