package com.room_app;

import java.util.Scanner;

import com.player_app.*;

// EmptyRoom is a simple room that extends Room.
// It represents a room with no dangers—just atmosphere.
public class EmptyRoom extends Room {
    
    // Here i had an error something about "--enable-preview"
    public EmptyRoom() {

        super("");
        
        final String[] EmptyRoomDescriptions = {
            "You step into the room and feel an immediate drop in temperature. The air is still and heavy, and the \nsilence seems almost oppressive. A single beam of light from the far corner reveals faded, cracked stone \nwalls. There's no sign of movement, but you can almost feel something watching.\nPerhaps you're meant to simply wait here… or maybe it's a trick of the mind.",
    
            "The room is unnervingly quiet, save for the soft rustle of something unseen—perhaps the wind shifting \nthrough the cracks in the walls. The floor is dusted with old footprints, their origin long forgotten.\nAt the far end of the room, a large, ornate mirror reflects nothing but shadows. For a brief moment, you swear\nthe reflection doesn't match your movements.",
    
            "You enter a large, empty hall. The stone walls are worn and chipped, a clear sign of age. Old cobwebs hang\nin the corners, and the floor creaks beneath your feet. The air smells faintly of mildew, and the silence is\nalmost too loud. A cracked mosaic on the floor depicts what looks like a scene from an ancient \nbattle—but something about it feels... wrong. Out of place."
        };

        this.description = EmptyRoomDescriptions[(int) (Math.random() * EmptyRoomDescriptions.length)] + "\n";

    }

    // display the description and the player's stats.
    @Override
    public void enterRoom(Player player, Scanner scanner) {
        System.out.println(description);
        player.showStats();
    }
}

