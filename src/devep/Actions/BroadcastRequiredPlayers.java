package devep.Actions;

import devep.Game.GameSettings;
import devep.Game.GameStatusEnum;
import devep.ScheduleTasks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Event;

public class BroadcastRequiredPlayers implements ActionInterface {
    private GameSettings gameSettings;
    private ScheduleTasks scheduleTasks;

    public BroadcastRequiredPlayers(GameSettings gameSettings, ScheduleTasks scheduleTasks) {
        this.gameSettings = gameSettings;
        this.scheduleTasks = scheduleTasks;
    }

    @Override
    public void executeAction(Event event) {

        if (gameSettings.gameStatus == GameStatusEnum.STARTED) {
            return;
        }

        int onlinePlayersCount = Bukkit.getOnlinePlayers().size();

        if (event.getEventName().equals("PlayerQuitEvent")) {
            onlinePlayersCount -= 1;
        }

        if (onlinePlayersCount >= gameSettings.getRequiredPlayersToStart()) {
            Bukkit.broadcastMessage(ChatColor.GREEN + "[SalvosMC-RPG]  Todos los jugadores se han unido. La partida va a comenzar.");
            gameSettings.gameStatus = GameStatusEnum.STARTED;

            this.scheduleTasks.sendWorldBorderPackets();
        } else {
            int playersLeft = gameSettings.getRequiredPlayersToStart() - onlinePlayersCount;
            Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "Faltan " + playersLeft + " jugadores para empezar.");
        }
    }
}
