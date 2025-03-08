package com.room_app;

import java.util.Random;
import java.util.Scanner;

import com.player_app.Player;
import com.enemy_app.Enemy;

public class EnemyRoom extends Room {
    public EnemyRoom() {
        super("You step into a dark room and sense danger lurking around.");
    }

    @Override
    public void enterRoom(Player player, Scanner scanner) {
        System.out.println(description);

        // Randomly choose an enemy.
        Enemy enemy = getRandomEnemy();
        System.out.println("An enemy appears: " + enemy.name + " (Health: " + enemy.health + ")");

        // Combat loop.
        while (enemy.health > 0 && player.health > 0) {
            System.out.println("\nPlayer Health: " + player.health + " | " + enemy.name + " Health: " + enemy.health);
            System.out.println("Choose an action: (a)ttack, (r)un, or use (p)otion");
            String choice = scanner.nextLine();

            if (choice.equalsIgnoreCase("a")) {
                int playerDamage = 15;
                System.out.println("You attack the " + enemy.name + " for " + playerDamage + " damage!");
                enemy.health -= playerDamage;

                if (enemy.health <= 0) {
                    System.out.println("You defeated the " + enemy.name + "!");
                    // Reward the player with a trophy named after the enemy.
                    player.inventory.add(enemy.name + " Trophy");
                    break;
                }

                System.out.println("The " + enemy.name + " attacks you for " + enemy.damage + " damage!");
                player.health -= enemy.damage;
                if (player.health <= 0) {
                    System.out.println("You have been defeated by the " + enemy.name + "...");
                }
            } else if (choice.equalsIgnoreCase("r")) {
                System.out.println("You try to run away!");
                System.out.println("The " + enemy.name + " gets a free attack for " + enemy.damage + " damage as you run!");
                player.health -= enemy.damage;
                break;
            } else if (choice.equalsIgnoreCase("p")) {
                if (player.inventory.contains("Potion")) {
                    int healAmount = 20;
                    player.inventory.remove("Potion");
                    player.health += healAmount;
                    if (player.health > 100) {
                        player.health = 100;
                    }
                    System.out.println("You used a Potion and restored " + healAmount + " health.");
                    player.showStats();
                } else {
                    System.out.println("You don't have any Potion to use.");
                }
            } else {
                System.out.println("Invalid input. Please choose 'a', 'r', or 'p'.");
            }
        }
        player.showStats();
    }

    // Returns a random enemy from a set of predefined enemies.
    private Enemy getRandomEnemy() {
        Random random = new Random();
        int enemyType = random.nextInt(4); // 0, 1, 2, or 3
        switch (enemyType) {
            case 0:
                return new Enemy("Goblin", 30, 10);
            case 1:
                return new Enemy("Orc", 40, 15);
            case 2:
                return new Enemy("Skeleton", 25, 12);
            case 3:
                return new Enemy("Bat", 20, 8);
            default:
                return new Enemy("Goblin", 30, 10);
        }
    }
}