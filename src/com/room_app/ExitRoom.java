package com.room_app;

import java.util.Scanner;

import com.player_app.Player;

public class ExitRoom extends Room {

    private final String EndingMessage = "====================================================\n" + //
    "        CONGRATULATIONS, ADVENTURER!\n" + //
    "====================================================";

    public ExitRoom() {
        super("You finally arrive at a massive door with mysterious engravings. A riddle is inscribed upon it.");
    }

    // When the player enters, they must solve a riddle to win.
    @Override
    public void enterRoom(Player player, Scanner scanner) {
        System.out.println(description);
        System.out.println("Riddle: I have keys but no locks, I have space but no room. You can enter but can’t go outside. What am I?");
        
        // Allow the player up to three attempts to answer the riddle.
        int attempts = 3;
        boolean solved = false;
        while (attempts > 0 && !solved) {
            System.out.print("Your answer: ");
            String answer = scanner.nextLine();
            
            if (answer.equalsIgnoreCase("keyboard")) {
                System.out.println("The door creaks open... Congratulations, you have found the exit and escaped the dungeon!");
                solved = true;

                System.out.println(EndingMessage);
                
                System.exit(0);
            } else {
                attempts--;
                if (attempts > 0) {
                    System.out.println("Incorrect! Try again. Attempts left: " + attempts);
                }
            }
        }
        if (!solved) {
            System.out.println("You failed to solve the riddle. The door remains locked, and the dungeon traps you in darkness.");
        }
    }
}