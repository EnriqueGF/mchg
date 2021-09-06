package devep.Actions;

import devep.Game.GameCore;
import devep.Game.GameSettings;
import devep.Game.GameStatusEnum;
import devep.ScheduleTasks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Event;

public class BroadcastRequiredPlayers implements ActionInterface {
    private GameSettings gameSettings;
    private ScheduleTasks scheduleTasks;
    private GameCore gameCore;

    public BroadcastRequiredPlayers(GameSettings gameSettings, ScheduleTasks scheduleTasks, GameCore gameCore) {
        this.gameSettings = gameSettings;
        this.scheduleTasks = scheduleTasks;
        this.gameCore = gameCore;
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

        if (onlinePlayersCount < gameSettings.getRequiredPlayersToStart()) {
            int playersLeft = gameSettings.getRequiredPlayersToStart() - onlinePlayersCount;
            gameCore.sendLocaleMessageToAllPlayers("REMAINING_PLAYERS_TO_START", Integer.toString(playersLeft), ChatColor.GOLD);
        }
    }
}
