package com.game_app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.player_app.*;
import com.room_app.*;

public class Game {

    private final String WelcomeMessage = "====================================================\n" + //
    "        DUNGEON CRAWLER: THE AWAKENING\n" + //
    "====================================================";

    private final String EndingMessage = "====================================================\n" + //
    "        TRY AGAIN, ADVENTURER!\n" + //
    "====================================================";

    public static void main(String[] args) {
        Game game = new Game();
        game.start();
    }


    public void start() {
        System.out.println(WelcomeMessage + "\n");
        System.out.println("Welcome to the Dungeon Adventure! \n");

        Player player = new Player();

        // Scanner for reading input between rooms.
        Scanner scanner = new Scanner(System.in);

        Room[][] dungeon = new Room[3][3];

        // Define the layout of the dungeon.
        // Row 0
        dungeon[0][0] = new EnemyRoom();
        dungeon[0][1] = new TreasureRoom();
        dungeon[0][2] = new TrapRoom();
        // Row 1
        dungeon[1][0] = new EmptyRoom();
        dungeon[1][1] = new EmptyRoom(); // Starting room.
        dungeon[1][2] = new EnemyRoom();
        // Row 2
        dungeon[2][0] = new TreasureRoom();
        dungeon[2][1] = new TrapRoom();
        dungeon[2][2] = new ExitRoom();

        // Set the starting coordinates. In this example, we start at (1,1).
        int currentRow = 1;
        int currentCol = 1;

        while (true) {
            System.out.println("\nYou are now at position (" + currentRow + ", " + currentCol + ").");

            // Enter the current room.
            dungeon[currentRow][currentCol].enterRoom(player, scanner);

            // Check if the player's health has dropped to 0 or below.
            if (player.health <= 0) {
                System.out.println("Game Over! You died in the dungeon.");
                break;
            }

            int newRow = currentRow;
            int newCol = currentCol;

            ArrayList<String> directions = new ArrayList<>(Arrays.asList("(n)orth", "(s)outh", "(e)ast", "(w)est"));

            // Remove invalid directions based on boundaries
            if (newRow <= 0) {
                directions.remove("(n)orth");
            }
            if (newRow >= 2) {
                directions.remove("(s)outh");
            }
            if (newCol <= 0) {
                directions.remove("(w)est");
            }
            if (newCol >= 2) {
                directions.remove("(e)ast");
            }
    
            // Display available directions
            System.out.println("Choose a direction to move: " + directions);

            String direction = scanner.nextLine();

            // Calculate new coordinates based on the chosen direction.

            if (direction.equalsIgnoreCase("n")) {
                newRow = currentRow - 1;
            } else if (direction.equalsIgnoreCase("s")) {
                newRow = currentRow + 1;
            } else if (direction.equalsIgnoreCase("e")) {
                newCol = currentCol + 1;
            } else if (direction.equalsIgnoreCase("w")) {
                newCol = currentCol - 1;
            } else {
                System.out.println("Invalid direction. Please try again.");
                continue;
            }

            // Check if the new coordinates are within the dungeon's boundaries.
            if (newRow < 0 || newRow >= dungeon.length || newCol < 0 || newCol >= dungeon[0].length) {
                System.out.println("You can't move in that direction. There's a wall!");
                continue;  // Ask for a direction again.
            }

            if (player.inventory.contains("Potion")) {
                System.out.println("You have a potion, Do you want to use it? (y/n) ");

                String potion = scanner.nextLine();

                if (potion.equalsIgnoreCase("y")) {
                    int healAmount = 20;
                    player.health += healAmount;
                    // Cap the health at 100.
                    if (player.health > 100) {
                        player.health = 100;
                    }
                    System.out.println("You drink the Potion and restore " + healAmount + " health.");

                    player.inventory.remove(player.inventory.indexOf("Potion"));
                }
            }

            // Update the current position.
            currentRow = newRow;
            currentCol = newCol;
        }

        scanner.close();
        // If the player exits the last room without ending the game (like solving the riddle),
        // then we conclude the adventure.
        System.out.println("You have reached the end of your journey, but the exit remains elusive.");
        System.out.println("\n" + EndingMessage);
    }
}