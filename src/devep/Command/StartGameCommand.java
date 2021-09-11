package devep.Command;

import devep.Game.GameSettings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class StartGameCommand implements CommandExecutor {

  private GameSettings gameSettings;

  public StartGameCommand(GameSettings gameSettings) {
    this.gameSettings = gameSettings;
  }

  @Override
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player && sender.isOp()) {
      this.gameSettings.requiredPlayersToStart = 1;
    }

    return true;
  }
}
