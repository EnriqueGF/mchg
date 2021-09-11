package devep.Locale;

import org.bukkit.ChatColor;

public class SpanishLocale extends Locale {

  private final String translationsArr[][] = {
    // General

    {"REMAINING_PLAYERS_TO_START", "Faltan %s jugadores para comenzar"},
    {
      "INVULNERABILITY_STARTING_1",
      "Todos los jugadores son teletransportados al spawn. ¡La invulnerabilidad por 4 minutos comienza!"
    },
    {"GAME_STARTS_MESSAGE", "¡El PVP está activado!"},
    {"ALL_PLAYERS_READY", "Todos los jugadores necesarios están listos"},
    {"INVULNERABILITY_STAGE_SECONDS_LEFT_COUNT", "%s segundos para empezar la partida!"},
    {"PVP_SECONDS_LEFT_COUNT", "%s segundos restantes para activar el PVP"},
    {"ERROR_GAME_ALREADY_STARTED", "La partida ya ha empezado."},
    {"YOU_DEAD_KILLED", "¡Has muerto!"},
    {"CANT_DROP_KIT_ITEM", "¡No puedes soltar un item de tu kit!"},
    {"BORDER_IS_CLOSING_ALERT", "¡El borde del mundo se está cerrando!"},
    {"COMPASS_LOCATED_PLAYER", "Jugador localizado:" + ChatColor.LIGHT_PURPLE + " %s"},

    // Kits
    {
      "KIT_SELECTED_MESSAGE",
      ChatColor.YELLOW
          + "¡Has seleccionado el kit "
          + ChatColor.GOLD
          + "%s"
          + ChatColor.YELLOW
          + "!"
    },
    {"KIT_AQUATIC_1", ChatColor.BLUE + "Te permite moverte por el agua con facilidad"},
    {"KIT_AQUATIC_2", ChatColor.GRAY + "Tridente con Riptide I"},
    {"KIT_AQUATIC_3", ChatColor.GRAY + "Casco de hierro con Oxígeno I"},
    {"KIT_AQUATIC_4", ChatColor.YELLOW + "El Tridente tiene menos daño que por defecto"},
    {"KIT_ARCHER_1", ChatColor.BLUE + "Débil pero fuerte en medias y largas distancias"},
    {"KIT_ARCHER_2", ChatColor.GRAY + "Arco con Poder I"},
    {"KIT_ARCHER_3", ChatColor.GRAY + "10 flechas"},
    {"KIT_ARCHER_4", ChatColor.YELLOW + "Una buena opción para jugadores con puntería"},
    {"KIT_ARMORER_1", ChatColor.BLUE + "Un kit para combate cuerpo a cuerpo"},
    {"KIT_ARMORER_2", ChatColor.GRAY + "Espada de hierro"},
    {"KIT_ARMORER_3", ChatColor.GRAY + "Escudo"},
    {"KIT_ARMORER_4", ChatColor.YELLOW + "Un kit para verdaderos guerreros"},
    {"KIT_BLACKSMITH_1", ChatColor.BLUE + "Kit defensivo"},
    {"KIT_BLACKSMITH_2", ChatColor.GRAY + "Pantalones de hierro"},
    {"KIT_BLACKSMITH_3", ChatColor.GRAY + "Botas de hierro"},
    {"KIT_BLACKSMITH_4", ChatColor.YELLOW + "Una buena defensa es un buen ataque"},
    {"KIT_ENDERMAN_1", ChatColor.BLUE + "Teletransportes y guardado de items"},
    {"KIT_ENDERMAN_2", ChatColor.GRAY + "5 Ender Pearls"},
    {"KIT_ENDERMAN_3", ChatColor.GRAY + "Shulker Box"},
    {"KIT_ENDERMAN_4", ChatColor.DARK_PURPLE + "E N D E R"},
    {"KIT_EXPLOSIVE_1", ChatColor.BLUE + "Explosiones y desastres"},
    {"KIT_EXPLOSIVE_2", ChatColor.BOLD + "4 TNT"},
    {"KIT_EXPLOSIVE_3", ChatColor.GRAY + "Mechero"},
    {"KIT_EXPLOSIVE_4", ChatColor.GRAY + "Pantalones de cuero"},
    {"KIT_EXPLOSIVE_5", ChatColor.GRAY + "4 Manzanas"},
    {"KIT_EXPLOSIVE_6", ChatColor.YELLOW + "¿Adicto a las explosiones?"},
    {"KIT_FARMER_1", ChatColor.BLUE + "El kit del granjero pacífico"},
    {"KIT_FARMER_2", ChatColor.BOLD + "Hazada de hierro"},
    {"KIT_FARMER_3", ChatColor.GRAY + "10 semillas de trigo"},
    {"KIT_FARMER_4", ChatColor.GRAY + "Manzana dorada"},
    {"KIT_FARMER_5", ChatColor.GRAY + "5 pasteles de calabaza"},
    {"KIT_FARMER_6", ChatColor.YELLOW + "Para jugadores tranquilos"},
    {"KIT_FISHERMAN_1", ChatColor.BLUE + "Peces y tesoros marinos"},
    {"KIT_FISHERMAN_2", ChatColor.BOLD + "Caña de pescar"},
    {"KIT_FISHERMAN_3", ChatColor.GRAY + "5 salmones cocinados"},
    {"KIT_FISHERMAN_4", ChatColor.GRAY + "Pantalones de cuero"},
    {"KIT_FISHERMAN_5", ChatColor.GRAY + "Botas de cuero"},
    {"KIT_FISHERMAN_6", ChatColor.YELLOW + "Para los jugadores que aman la pesca"},
    {"KIT_HORSERIDER_1", ChatColor.BLUE + "Monta y lucha a caballo"},
    {"KIT_HORSERIDER_2", ChatColor.GRAY + "Huevo de spawn de caballo"},
    {"KIT_HORSERIDER_3", ChatColor.GRAY + "Montura"},
    {"KIT_HORSERIDER_4", ChatColor.GRAY + "Armadura de diamante de caballo"},
    {"KIT_HORSERIDER_5", ChatColor.YELLOW + "¡Para los amantes de los caballos!"},
    {"KIT_HUNTER_1", ChatColor.BLUE + "Lucha junto a tu lobo"},
    {"KIT_HUNTER_2", ChatColor.BOLD + "Obtienes un Lobo domesticado"},
    {"KIT_HUNTER_3", ChatColor.GRAY + "Espada de hierro"},
    {"KIT_HUNTER_4", ChatColor.YELLOW + "¡Si te gustan las mascotas!"},
    {"KIT_MINER_1", ChatColor.BLUE + "Para empezar a minar diamantes"},
    {"KIT_MINER_2", ChatColor.GRAY + "Pico de Hierro con Eficiencia I"},
    {"KIT_MINER_3", ChatColor.GRAY + "Pala de Hierro con Eficiencia I"},
    {"KIT_MINER_4", ChatColor.GRAY + "10 antorchas"},
    {"KIT_MINER_5", ChatColor.YELLOW + "Para mineros locos"},
  };

    public SpanishLocale() {
        super.translationsArr = this.translationsArr;
    }
}
