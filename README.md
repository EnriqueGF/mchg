# Classic Hunger Games README

Welcome to the Classic Hunger Games, a thrilling gamemode for Minecraft servers that blends the excitement of the original Minecraft Hardcore Games (HG) with elements of modern Ultra Hardcore (UHC). This project utilizes the Spigot API and Maven for streamlined package management.

## Gamemode Overview

### World Setup

Upon server initialization, a fresh world is generated using a vanilla seed to ensure every game is unique and challenging. We recommend the following commands in your server startup script to facilitate this:

```bash
rmdir /S /q world
java -Xms1G -Xmx1G -jar paper-1.18.2-350.jar nogui
pause
```

This setup ensures that players spawn into a newly generated world, enhancing the unpredictability and excitement of each session.

### Gameplay

Before the game starts, a minimum of 70 players is required (this value is currently hardcoded). During the waiting period, players can choose from a variety of **Kits** which will equip them with specific items and abilities once the game begins:

![Kit Selection Example](https://i.gyazo.com/f64c447a10cbbdd9d6043488c74f3d3d.png)

#### Available Kits:

- **Aquatic**: Trident with Riptide I, Iron Helmet with Respiration I
- **Archer**: Bow with Power I, 10 arrows
- **Armorer**: Iron Sword, Shield
- **Blacksmith**: Iron leggings, Iron boots
- **Enderman**: 5 ender pearls, shulker box
- **Explosive**: Flint and steel, Leather leggings, 4 apples
- **Farmer**: 10 wheat seeds, Golden Apple, 5 pumpkin pies
- **Fisherman**: 5 Cooked Salmons, Leather Leggings, Leather boots
- **Horse Rider**: Horse spawn egg, saddle, Diamond Horse Armor
- **Hunter**: Domesticated wolf, Iron Sword
- **Miner**: Iron Pickaxe with Efficiency I, Iron Shovel with Efficiency I, 10 Torches

Players are free to explore within the confines of a world border while waiting for the game to start. The game begins with a countdown followed by a 5-minute invulnerability period, allowing players to strategize and find a suitable location without immediate threat of PvP.

![Game Start](https://i.gyazo.com/100367626a8c67499bfb732378988fc0.png)

As the game progresses, PvP is enabled and the world border begins to shrink, intensifying the gameplay. The pace of the border shrinkage increases as fewer players remain, adding a dynamic battle royale element to the game.

![Worldborder Shrinking](https://i.gyazo.com/8bf4622db49c83a8c9dcb5b54e360523.png)

Additional features include a top bar displaying the distance to the border and player detection mechanics using a compass, among others.

## Multilingual Support

The plugin automatically detects the client's language setting and currently supports both Spanish and English.

## Dependencies

This plugin is compatible with Minecraft version 1.18.2 and relies on the following external resources:

- [Titlemanager](https://www.spigotmc.org/resources/titlemanager.1049/) v2.3.4
- [PlaceholderAPI](https://www.spigotmc.org/resources/placeholderapi.6245/) v2.11.1
- [WorldBorderAPI](https://www.spigotmc.org/resources/player-worldborder-api.67734/) v1.171.0

## Commands

- `/forceGameStart`: Allows an administrator to forcibly start the game, even if the minimum player threshold has not been met.

Enjoy your adventure in the Classic Hunger Games, where strategy, skill, and a bit of luck determine the victor!
