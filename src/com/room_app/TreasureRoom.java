package com.room_app;

import java.util.Scanner;

import com.player_app.*;

public class TreasureRoom extends Room {
    public TreasureRoom() {
        super("You enter a brightly lit room that sparkles with hidden treasures.");
    }

    @Override
    public void enterRoom(Player player, Scanner scanner) {
        System.out.println(description);
        System.out.println("You found a Sword and a Potion!");

        // Automatically add the Sword to the player's inventory.
        if (player.inventory.contains("Sword " + player.swordLvl)) {
            player.inventory.remove(player.inventory.indexOf("Sword " + player.swordLvl));
            player.swordLvl += 1;
        }
        player.inventory.add("Sword " + player.swordLvl);
        player.power += 5;
        System.out.println("The Sword has been added to your inventory. Your power leveled up: " + player.power);

        // Ask if the player wants to use the Potion immediately.
        System.out.println("Do you want to use the Potion immediately to restore health? (y/n)");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("y")) {
            int healAmount = 10;
            player.health += healAmount;

            if (player.health > 100) {
                player.health = 100;
            }
            System.out.println("You drink the Potion and restore " + healAmount + " health.");
        } else {
            // If the player doesn't use it immediately, add the Potion to the inventory.
            player.inventory.add("Potion");
            System.out.println("You add the Potion to your inventory.");
        }
        player.showStats();
    }
}
