package com.room_app;

import java.util.Scanner;

import com.player_app.*;

// Abstract Room class that serves as a blueprint for all room types.
public abstract class Room {
    String description;

    public Room(String description) {
        this.description = description;
    }

    // The enterRoom method will be defined differently for each type of room.
    public abstract void enterRoom(Player player, Scanner scanner);
}
