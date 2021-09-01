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
    private GameCore gameCore;
    private int lookForGameFinishScheduleID;

    public ScheduleTasks(Plugin plugin) {
        this.plugin = plugin;
    }

    public void sendWorldBorderPackets() {

        //int waitBetweenEdgeClosesSeconds = 900;
        int waitBetweenEdgeClosesSeconds = 50;

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

                        if (onlinePlayersCount <= 5 && WorldBorder.borderDistance > 800) {
                            edgeCloseLength = 350;
                        } else if (onlinePlayersCount <= 10 && WorldBorder.borderDistance > 1500) {
                            edgeCloseLength = 500;
                        } else if (onlinePlayersCount <= 20 && WorldBorder.borderDistance > 2000) {
                            edgeCloseLength = 600;
                        } else if (onlinePlayersCount <= 30 && WorldBorder.borderDistance > 2500) {
                            edgeCloseLength = 800;
                        } else if (onlinePlayersCount <= 50 && WorldBorder.borderDistance > 2900) {
                            edgeCloseLength = 500;
                        } else {
                            edgeCloseLength = 0;
                        }

                        if (edgeCloseLength != 0) {

                            for (int i = 0; i < edgeCloseLength; i++) {
                                for (Player player : Bukkit.getOnlinePlayers()) {
                                    try {
                                        WorldBorder.sendWorldPacket(player, WorldBorder.borderDistance - 1);
                                    } catch (Exception ex) {
                                        System.out.println("sendWorldBorderPackets() : " + ex.toString());
                                    }

                                    try {
                                        Thread.sleep(300);
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
        }, 0L, 20 * 1); //0 Tick initial delay, 20 Tick (1 Second) between repeats
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
                    }

                    Bukkit.getScheduler().cancelTask(lookForGameFinishScheduleID);

                } catch(Exception ex) {
                    System.out.println("Excepción producida en lookForGameFinish(): " + ex.toString());
                }
            }
        }, 0L, 20 * 3); //0 Tick initial delay, 20 Tick (1 Second) between repeats
    }

}
