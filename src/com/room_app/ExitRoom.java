package com.room_app;

import java.util.Random;
import java.util.Scanner;

import com.player_app.Player;

// ExitRoom: Final room where the player faces a final boss that drops a key.
// The boss can be a Riddler Boss (no retreat allowed) or a Combat Boss (player may retreat).
public class ExitRoom extends Room {

    private boolean bossInitialized = false;
    private boolean isRiddlerBoss;
    private int combatBossHealth; // Only used if the boss is a Combat Boss.
    private final int COMBAT_BOSS_DAMAGE = 15;

    private final String EndingMessageIfWon = "====================================================\n" + //
    "        CONGRATULATIONS, ADVENTURER!\n" + //
    "====================================================";

    private final String EndingMessageIfLose = "====================================================\n" + //
    "        TRY AGAIN, ADVENTURER!\n" + //
    "====================================================";

    public ExitRoom() {
        super("You finally arrive at a massive door with mysterious engravings. A final boss awaits.");
    }

    @Override
    public void enterRoom(Player player, Scanner scanner) {
        // Initialize boss only once.
        if (!bossInitialized) {
            Random random = new Random();
            isRiddlerBoss = random.nextBoolean();
            bossInitialized = true;
            if (!isRiddlerBoss) {
                // Initialize Combat Boss health.
                combatBossHealth = 100;
            }
        }

        if (isRiddlerBoss) {
            System.out.println("A Riddler Boss appears! Answer all his riddles correctly to defeat him.");

            String[][] riddles = {
                {"I speak without a mouth and hear without ears. I have no body, but I come alive with wind. What am I?", "echo"},
                {"You measure my life in hours and I serve you by expiring. I'm quick when I'm thin and slow when I'm fat. What am I?", "candle"},
                {"I have cities, but no houses; forests, but no trees; and water, but no fish. What am I?", "map"},
                {"What is seen in the middle of March and April that can't be seen at the beginning or end of either month?", "r"},
                {"You see a boat filled with people, yet there isn't a single person on board. How is that possible?", "all married"}
            };
            boolean allCorrect = true;
            for (int i = 0; i < riddles.length; i++) {
                System.out.println("Riddle " + (i + 1) + ": " + riddles[i][0]);
                System.out.print("Your answer: ");
                String answer = scanner.nextLine();
                if (!answer.equalsIgnoreCase(riddles[i][1])) {
                    System.out.println("Incorrect answer!");
                    allCorrect = false;
                    break;
                } else {
                    System.out.println("Correct!");
                }
            }
            if (allCorrect) {
                System.out.println("The Riddler Boss is defeated and drops a Key!");
                player.inventory.add("Key");
                System.out.println("You use the Key to open the exit door and escape the dungeon!");
                System.out.println(EndingMessageIfWon);
                System.exit(0);
            } else {
                System.out.println("You failed the Riddler Boss challenge. You cannot retreat from this fight.");
                System.out.println(EndingMessageIfLose);
                System.exit(0);
            }
        } else {
            System.out.println("A Combat Boss appears! Prepare for battle.");
            boolean bossDefeated = false;
            while (combatBossHealth > 0 && player.health > 0) {
                System.out.println("\nPlayer Health: " + player.health + " | Boss Health: " + combatBossHealth);
                System.out.println("Choose an action: (a)ttack, use (p)otion, or (r)etreat");
                String choice = scanner.nextLine();
                if (choice.equalsIgnoreCase("a")) {
                    int playerDamage = 15;
                    System.out.println("You attack the boss for " + playerDamage + " damage!");
                    combatBossHealth -= playerDamage;
                    if (combatBossHealth <= 0) {
                        System.out.println("You defeated the Combat Boss!");
                        bossDefeated = true;
                        break;
                    }
                    System.out.println("The boss attacks you for " + COMBAT_BOSS_DAMAGE + " damage!");
                    player.health -= COMBAT_BOSS_DAMAGE;
                    if (player.health <= 0) {
                        System.out.println("You have been defeated by the Combat Boss.");
                        break;
                    }
                } else if (choice.equalsIgnoreCase("p")) {
                    if (player.inventory.contains("Potion")) {
                        int healAmount = 10;
                        player.inventory.remove("Potion");
                        player.health += healAmount;
                        if (player.health > 100) {
                            player.health = 100;
                        }
                        System.out.println("You used a Potion and restored " + healAmount + " health.");
                        player.showStats();
                    } else {
                        System.out.println("You don't have any Potion.");
                    }
                } else if (choice.equalsIgnoreCase("r")) {
                    System.out.println("You choose to retreat from the Combat Boss. Your progress against the boss is saved.");
                    // Return from the boss encounter without resetting boss state.
                    return;
                } else {
                    System.out.println("Invalid action. Please choose 'a', 'p', or 'r'.");
                }
            }
            if (bossDefeated) {
                System.out.println("The Combat Boss drops a Key!");
                player.inventory.add("Key");
                System.out.println("You use the Key to open the exit door and escape the dungeon!");
                System.out.println(EndingMessageIfWon);
                System.exit(0);
            } else if (player.health <= 0) {
                System.out.println("You have been defeated by the Combat Boss.");
                System.out.println(EndingMessageIfLose);
                System.exit(0);
            }
        }
    }
}