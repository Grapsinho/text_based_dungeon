package com.game_app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
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

        // Define dungeon dimensions.
        int rows = 3;
        int cols = 3;

        // Generate the dungeon dynamically.
        Room[][] dungeon = generateDungeon(rows, cols);

        // Set the starting position at the center.
        int currentRow = rows / 2;
        int currentCol = cols / 2;
        // Force the starting room to be safe.
        dungeon[currentRow][currentCol] = new EmptyRoom();

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
                    int healAmount = 10;
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


    // Generates a dungeon (2D grid) with random rooms.
    private Room[][] generateDungeon(int rows, int cols) {
        Room[][] dungeon = new Room[rows][cols];
        Random random = new Random();

        // Fill the grid with random room types.
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double r = random.nextDouble(); // 0.0 <= r < 1.0
                if (r < 0.4) {
                    dungeon[i][j] = new EmptyRoom();
                } else if (r < 0.7) {
                    dungeon[i][j] = new EnemyRoom();
                } else if (r < 0.9) {
                    dungeon[i][j] = new TrapRoom();
                } else {
                    dungeon[i][j] = new TreasureRoom();
                }
            }
        }
        // Choose a random cell (excluding the starting position) to be the ExitRoom.
        int exitRow, exitCol;
        do {
            exitRow = random.nextInt(rows);
            exitCol = random.nextInt(cols);
        } while (exitRow == rows / 2 && exitCol == cols / 2);
        dungeon[exitRow][exitCol] = new ExitRoom();
        
        return dungeon;
    }
}
