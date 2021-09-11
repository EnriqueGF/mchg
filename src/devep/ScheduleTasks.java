package devep;

import devep.Game.GameCore;
import devep.Game.GameSettings;
import devep.Game.GameStatusEnum;
import lombok.extern.java.Log;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.time.Instant;
import java.util.HashMap;

public class ScheduleTasks {

  private Plugin plugin;
  private int lookForGameFinishScheduleID;
  private HashMap<Player, BossBar> bossBars = new HashMap<>();

  public ScheduleTasks(Plugin plugin) {
    this.plugin = plugin;

    InitializeScheduling();
  }

  private void InitializeScheduling() {
    // checkPlayersOutsideBorders();
    borderDistanceBossBarSchedule();
    lookForGameFinish();
  }

  public void initWorldBorderClosingCheck() {

    int waitBetweenEdgeClosesSeconds = GameSettings.timeBetweenBorderCloses;

    Thread t =
        new Thread(
            () -> {
              try {
                while (true) {

                  try {
                    Thread.sleep(3000);
                  } catch (InterruptedException e) {
                    e.printStackTrace();
                  }

                  int onlinePlayersCount = Bukkit.getOnlinePlayers().size();
                  double edgeClosePercent = 1;

                  if (Math.abs(Instant.now().getEpochSecond() - GameSettings.lastEdgeCloses)
                      >= waitBetweenEdgeClosesSeconds) {

                    GameSettings.lastEdgeCloses = Instant.now().getEpochSecond();

                    if (onlinePlayersCount <= 5 && ClassicHC.worldBorder.getSize() > 700) {
                      edgeClosePercent = 0.15;
                    } else if (onlinePlayersCount <= 10 && ClassicHC.worldBorder.getSize() > 1300) {
                      edgeClosePercent = 0.15;
                    } else if (onlinePlayersCount <= 20 && ClassicHC.worldBorder.getSize() > 1800) {
                      edgeClosePercent = 0.15;
                    } else if (onlinePlayersCount <= 30 && ClassicHC.worldBorder.getSize() > 1800) {
                      edgeClosePercent = 0.15;
                    } else if (onlinePlayersCount <= 50 && ClassicHC.worldBorder.getSize() > 2000) {
                      edgeClosePercent = 0.15;
                    } else {
                      edgeClosePercent = 1;
                    }

                    if (edgeClosePercent != 1) {
                      GameCore.sendLocaleMessageToAllPlayers(
                          "BORDER_IS_CLOSING_ALERT", "", ChatColor.RED);
                      double closepercent = edgeClosePercent;

                      Bukkit.getScheduler()
                          .runTask(
                              plugin,
                              new Runnable() {
                                @Override
                                public void run() {
                                  System.out.println(
                                      "Tamaño actual borde: " + ClassicHC.worldBorder.getSize());
                                  System.out.println(
                                      "Cerrando borde por porcentaje: " + closepercent);

                                  ClassicHC.worldBorder.setSize(
                                      ClassicHC.worldBorder.getSize()
                                          - (ClassicHC.worldBorder.getSize() * closepercent),
                                      140);
                                }
                              });
                    }
                  }
                }
              } catch (Exception ex) {
                System.out.println(
                    "Excepción producida en sendWorldBorderPackets(): " + ex.toString());
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

  private void lookForGameFinish() {
    lookForGameFinishScheduleID =
        Bukkit.getScheduler()
            .scheduleSyncRepeatingTask(
                ClassicHC.plugin,
                new Runnable() {
                  @Override
                  public void run() {
                    try {
                      if (GameSettings.gameStatus == GameStatusEnum.BEFORE_START) {
                        return;
                      }

                      if (Bukkit.getOnlinePlayers().size() == 1) {
                        Player winPlayer = Bukkit.getOnlinePlayers().iterator().next();
                        Bukkit.getServer()
                            .getWorld("world")
                            .spawnEntity(winPlayer.getLocation(), EntityType.FIREWORK);
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

                    } catch (Exception ex) {
                      System.out.println(
                          "Excepción producida en lookForGameFinish(): " + ex.toString());
                    }
                  }
                },
                0L,
                20 * 3); // 0 Tick initial delay, 20 Tick (1 Second) between repeats
  }

  private void borderDistanceBossBarSchedule() {
    Bukkit.getScheduler()
            .scheduleSyncRepeatingTask(
                    ClassicHC.plugin,
                    new Runnable() {
                      @Override
                      public void run() {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                          double result;
                          String bossBarText = "";
                          BarColor barColor;

                          Location playerLocation = player.getLocation();
                          double borderSize = Bukkit.getWorld("world").getWorldBorder().getSize() / 2;

                          if (Math.abs(playerLocation.getX()) > Math.abs(playerLocation.getZ())) {
                            result = Math.abs(playerLocation.getX()) - borderSize;
                          } else {
                            result = Math.abs(playerLocation.getZ()) - borderSize;
                          }

                          if (bossBars.containsKey(player)) {
                            bossBars.get(player).removeAll();
                            bossBars.remove(player);
                          }

                          double percentage =
                                  (((Math.abs(result) * 100) / ClassicHC.worldBorder.getSize()) / 100);

                          if (percentage >= 0.25) {
                            bossBarText = "You are safe from World Border";
                            barColor = BarColor.GREEN;
                          } else {
                            bossBarText = "Take care! You are near the World Border!";
                            barColor = BarColor.RED;
                          }

                          BossBar bossBar =
                                  Bukkit.createBossBar(bossBarText, barColor, BarStyle.SOLID);

                          bossBar.setProgress(percentage);
                          bossBar.addPlayer(player);

                          bossBars.put(player, bossBar);
                        }
                      }
                    },
                    0L,
                    20 * 1);
  }
}
