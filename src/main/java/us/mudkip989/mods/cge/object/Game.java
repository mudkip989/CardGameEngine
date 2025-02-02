package us.mudkip989.mods.cge.object;

import org.bukkit.*;
import org.bukkit.entity.*;
import us.mudkip989.mods.cge.*;
import us.mudkip989.mods.cge.event.*;

import java.util.*;

public abstract class Game {
    public final UUID gameID;


    //Parses a value from Entity Tags
    public static String parseValuefromEntity(Entity e, String key){
        Set<String> tags = e.getScoreboardTags();

        String val = null;

        for(String tag: tags){

            if(tag.startsWith(key + "-")){
                val = tag.replaceFirst(key + "-", "");
            }

        }


        return val;
    }


    //Game Constructor method
    public Game(Location location){

        //Important Game Setup
        gameID = UUID.randomUUID();





        //Add Game to Game List
        CGE.games.put(gameID, this);
    }




    //Runs every 1/20th of a second. used for time specific things in the game.
    public void tickGame(){

    }


    //Event listener for when a player clicks an Interactive.
    public void runEvent(Event event, String args, Player p){

        switch(event){
            case Event.DELETE:


                break;
            case Event.HIDE:


                break;
            case Event.SHOW:


                break;
            case Event.TOGGLE:


                break;
            case Event.JOIN:


                break;
            case Event.LEAVE:


                break;
            case Event.FUNCTION:



                break;

            default:

                break;
        }

    }





}
