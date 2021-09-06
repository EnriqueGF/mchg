package devep.Locale;

public class SpanishLocale extends Locale {

    private String translationsArr[][] = {
            {"REMAINING_PLAYERS_TO_START", "Faltan %s jugadores para comenzar"},
            {"INVULNERABILITY_STARTING_1", "Todos los jugadores son teletransportados al spawn. ¡La invulnerabilidad por 4 minutos comienza!"},
            {"GAME_STARTS_MESSAGE", "¡El PVP está activado!"},
            {"ALL_PLAYERS_READY", "Todos los jugadores necesarios están listos"},
            {"INVULNERABILITY_STAGE_SECONDS_LEFT_COUNT", "%s segundos para empezar la partida!"},
            {"PVP_SECONDS_LEFT_COUNT", "%s segundos restantes para activar el PVP"},
            {"ERROR_GAME_ALREADY_STARTED", "La partida ya ha empezado."},
            {"YOU_DEAD_KILLED", "¡Has muerto!"},
            {"CANT_DROP_KIT_ITEM", "¡No puedes soltar un item de tu kit!"},
    };

    public SpanishLocale() {
        super.translationsArr = this.translationsArr;
    }
}
