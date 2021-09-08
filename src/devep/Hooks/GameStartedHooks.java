package devep.Hooks;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import devep.ClassicHC;
import devep.Game.GameSettings;
import devep.Game.GameStatusEnum;
import devep.Locale.LocaleFactory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

public class GameStartedHooks implements Listener {

    private GameSettings gameSettings;

    public GameStartedHooks(GameSettings gameSettings) {
        this.gameSettings = gameSettings;
    }

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent playerJoinEvent) {
        if(gameSettings.gameStatus == GameStatusEnum.STARTED || gameSettings.gameStatus == GameStatusEnum.INVULNERABILITY) {
            playerJoinEvent.disallow(
                    AsyncPlayerPreLoginEvent.Result.KICK_OTHER,
                    LocaleFactory.getLocale("en").getTranslatedText("ERROR_GAME_ALREADY_STARTED")
            );
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        if(gameSettings.gameStatus == GameStatusEnum.STARTED || gameSettings.gameStatus == GameStatusEnum.INVULNERABILITY) {
            //event.getEntity().kickPlayer(LocaleFactory.getLocale(event.getEntity().getLocale()).getTranslatedText("YOU_DEAD_KILLED"));
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("Connect");
            out.writeUTF("lobby");

            event.getEntity().sendPluginMessage(ClassicHC.plugin, "BungeeCord", out.toByteArray());
        }
    }
}


