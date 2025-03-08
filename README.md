# Dungeon Crawler: The Awakening

A text-based dungeon crawler game written in Java. In this game, you explore a dynamically generated dungeon, battle enemies, solve riddles, collect items, and face a final boss to escape the dungeon!

## Overview

Dungeon Crawler: The Awakening challenges you to navigate through a randomly generated dungeon. Each room may present unique challenges such as:

- **Empty Rooms:** Atmospheric spaces with no immediate threat.
- **Enemy Rooms:** Engage in turn-based combat with randomly selected enemies (e.g., Goblin, Orc, Skeleton, Bat).
- **Treasure Rooms:** Collect valuable items like a Sword and Potions. Decide whether to use a Potion immediately or save it for later.
- **Trap Rooms:** Solve a riddle to avoid damage from traps.
- **Exit Room (Final Boss):** Face the final boss, which may be either a Riddler Boss (requires answering five riddles correctly with no retreat option) or a Combat Boss (100 health, 15 damage per hit, with an option to retreat).

## Features

- **Dynamic Dungeon Generation:** Each playthrough offers a different 3×3 dungeon layout.
- **Multiple Room Types:** Variety of challenges including enemies, traps, and treasures.
- **Item & Inventory Management:** Collect and manage items like Sword and Potion.
- **Turn-based Combat:** Engage enemies with attack, potion usage, and retreat options (for the Combat Boss).
- **Final Boss Encounter:** Two distinct final boss types with unique gameplay mechanics:
  - _Riddler Boss:_ Answer five riddles correctly; no option to retreat.
  - _Combat Boss:_ Battle using attacks and potions; you can retreat and return later with the boss’s state preserved.

## How to Play

1. **Start the Game:** Run the game to enter the dungeon.
2. **Navigation:** Choose a direction (north, south, east, or west) to move between rooms. Use the option to use a potion when available.
3. **Combat:** When encountering enemies, choose to attack, use a potion, or run.
4. **Room Challenges:** Solve riddles in Trap Rooms and interact with various room types.
5. **Final Boss:**
   - _Riddler Boss:_ Answer all five riddles correctly to win.
   - _Combat Boss:_ Engage in battle; if you retreat, the boss’s health is preserved for a later encounter.
6. **Escape:** Defeat the final boss to obtain the key needed to unlock the exit door and escape the dungeon.

## Installation

Make sure you have Java (JDK 8 or higher) installed on your computer. Then follow these steps:

1. **Clone the Repository:**

   ```bash
   git clone https://github.com/Grapsinho/text_based_dungeon.git
   ```

2. **Compile the Game:**

   ```bat
    @echo off
    echo Compiling Java files...

    javac -d bin src/com/player_app/*.java src/com/room_app/*.java src/com/enemy_app/*.java src/com/game_app/*.java

    if %ERRORLEVEL% NEQ 0 (
        echo Compilation failed.
        exit /b %ERRORLEVEL%
    )

    echo Running the program...
    java -cp bin com.game_app.Game
   ```
