package devep.Game;

import devep.SalvosMCRPG;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class GameCore {
    private GameSettings gameSettings;
    private int checkForEnoughPlayersScheduleID;

    public GameCore(GameSettings gameSettings) {
        this.gameSettings = gameSettings;

        checkForInvulnerabilityStage();
    }

    private void checkForInvulnerabilityStage() {

        this.checkForEnoughPlayersScheduleID = Bukkit.getScheduler().scheduleSyncRepeatingTask(SalvosMCRPG.plugin, new Runnable() {
            @Override
            public void run() {
                if (Bukkit.getOnlinePlayers().size() >= gameSettings.getRequiredPlayersToStart()) {
                    Bukkit.broadcastMessage(ChatColor.GREEN + "[SalvosMC-RPG]  Todos los jugadores se han unido. La partida va a comenzar.");
                    setInvulnerabilityStage();
                }
            }
        }, 0L, 20 * 5); //0 Tick initial delay, 20 Tick (1 Second) between repeats
    }

    public void setInvulnerabilityStage() {
        Bukkit.getScheduler().cancelTask(this.checkForEnoughPlayersScheduleID);

        SalvosMCRPG.plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SalvosMC-RPG] Iniciando invulnerabilidad por 4 minutos");

        this.gameSettings.gameStatus = GameStatusEnum.INVULNERABILITY;

        SalvosMCRPG.plugin.getServer().getScheduler().scheduleSyncDelayedTask(SalvosMCRPG.plugin, new Runnable() {
            public void run() {
                SalvosMCRPG.plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SalvosMC-RPG] Vulnerabilidad ha acabado");
                gameSettings.gameStatus = GameStatusEnum.BEFORE_START;
                teleportPlayersToSpawn();
            }
          }, 20 * gameSettings.invulnerabilityStageSeconds); // 20 (one second in ticks) * 5 (seconds to wait)
    }

    private void teleportPlayersToSpawn() {

        SalvosMCRPG.plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SalvosMC-RPG] Todos teletransportados al spawn");
        SalvosMCRPG.plugin.getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[SalvosMC-RPG] Comienza el no pvp por 3 minutos");

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.teleport(SalvosMCRPG.spawnLocation);
        }
    }
}
