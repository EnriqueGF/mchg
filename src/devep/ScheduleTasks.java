package devep;

import devep.Game.GameCore;
import devep.Game.GameSettings;
import devep.Game.GameStatusEnum;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
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
        //checkPlayersOutsideBorders();
        lookForGameFinish();
    }


    public void initWorldBorderClosingCheck() {

        int waitBetweenEdgeClosesSeconds = GameSettings.timeBetweenBorderCloses;

        Thread t = new Thread(() -> {
            try {
                while (true) {

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    int onlinePlayersCount = Bukkit.getOnlinePlayers().size();
                    double edgeClosePercent = 1;


                    if (Math.abs(Instant.now().getEpochSecond() - GameSettings.lastEdgeCloses) >= waitBetweenEdgeClosesSeconds) {

                        GameSettings.lastEdgeCloses = Instant.now().getEpochSecond();

                        // Radio entre 2 para obtener el tamaño deseado
                        if (onlinePlayersCount <= 5 && ClassicHC.worldBorder.getSize() > 200) {
                            edgeClosePercent = 0.50;
                        } else if (onlinePlayersCount <= 10 && ClassicHC.worldBorder.getSize() > 500) {
                            edgeClosePercent = 0.40;
                        } else if (onlinePlayersCount <= 20 && ClassicHC.worldBorder.getSize() > 1000) {
                            edgeClosePercent = 0.20;
                        } else if (onlinePlayersCount <= 30 && ClassicHC.worldBorder.getSize() > 1350) {
                            edgeClosePercent = 0.20;
                        } else if (onlinePlayersCount <= 50 && ClassicHC.worldBorder.getSize() > 1700) {
                            edgeClosePercent = 0.15;
                        } else {
                            edgeClosePercent = 1;
                        }

                        if (edgeClosePercent != 1) {
                            GameCore.sendLocaleMessageToAllPlayers("BORDER_IS_CLOSING_ALERT", "", ChatColor.RED);
                            double closepercent = edgeClosePercent;

                            Bukkit.getScheduler().runTask(plugin, new Runnable() {
                                @Override
                                public void run() {
                                    System.out.println("Tamaño actual borde: " + ClassicHC.worldBorder.getSize());
                                    System.out.println("Cerrando borde por porcentaje: " + closepercent);
                                    System.out.println("Cerrando a: " + ClassicHC.worldBorder.getSize() * closepercent);

                                    ClassicHC.worldBorder.setSize(ClassicHC.worldBorder.getSize() - (ClassicHC.worldBorder.getSize() * closepercent), 140);
                                }
                            });
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println("Excepción producida en sendWorldBorderPackets(): " + ex.toString());
            }
        });

        t.start();
    }

    /*
    public void checkPlayersOutsideBorders() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(ClassicHC.plugin, new Runnable() {
            @Override
            public void run() {
                try {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        IWorldBorder worldBorder = ClassicHC.worldBorderAPI.getWorldBorder(player);
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

     */

    public void lookForGameFinish() {
        lookForGameFinishScheduleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(ClassicHC.plugin, new Runnable() {
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

                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        String command = "restart";
                        Bukkit.dispatchCommand(console, command);
                    }

                    if (Bukkit.getOnlinePlayers().size() == 0) {
                        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                        String command = "restart";
                        Bukkit.dispatchCommand(console, command);
                    }

                } catch(Exception ex) {
                    System.out.println("Excepción producida en lookForGameFinish(): " + ex.toString());
                }
            }
        }, 0L, 20 * 3); //0 Tick initial delay, 20 Tick (1 Second) between repeats
    }

}
