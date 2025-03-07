package com.game_app;

import com.player_app.*;
import com.room_app.*;

// The Game class contains the main game loop.
public class Game {
    private final String WelcomeMessage = "====================================================\n" + //
                "        DUNGEON CRAWLER: THE AWAKENING\n" + //
                "====================================================";
    
    private final String EndingMessage = "====================================================\n" + //
    "        CONGRATULATIONS, ADVENTURER!\n" + //
    "====================================================";
    

    public static void main(String[] args) {
        // Create a new Game instance and start it.
        Game game = new Game();
        game.start();
    }

    public void start() {
        System.out.println(WelcomeMessage + "\n");

        Player player = new Player();

        Room currentRoom = new EmptyRoom();

        currentRoom.enterRoom(player);

        System.out.println("\n" + EndingMessage);
    }
}
