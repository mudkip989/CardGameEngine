package us.mudkip989.mods.cge.object;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import us.mudkip989.mods.cge.*;
import us.mudkip989.mods.cge.event.*;

import java.util.*;

import static org.joml.Math.*;

public class Game {
    private final UUID gameID;
    public Player[] players;
    public Card[] cards;
    public Interaction inter;

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


    public Game(Location location){

        //Important Game Setup
        Location blockLocation = location.toCenterLocation();
        blockLocation.setPitch(0);
        blockLocation.setYaw((round(location.getYaw()/90)*90));
        gameID = UUID.randomUUID();


        //Create Player List
        players = new Player[4];



        //Create Display
//        disp = Objects.requireNonNull(CGE.instance.getServer().getWorld("world")).spawn(blockLocation, ItemDisplay.class);
//        ItemMeta meta = new ItemStack(Material.BOOK).getItemMeta();
//        meta.setCustomModelData(18);
//        ItemStack stack = new ItemStack(Material.BOOK);
//
//        stack.setItemMeta(meta);
//        disp.setItemStack(stack);
//        disp.setTransformationMatrix(new Matrix4f(
//                1, 0, 0, 0,
//                0, 1, 0, 0,
//                0, 0, 1, 0,
//                0, 0, 0, 1));

        //Create Card Objects
        cards = new Card[1];
        for (int i = 0; i < cards.length; i++){

            ItemMeta meta = new ItemStack(Material.BOOK).getItemMeta();
            meta.setCustomModelData(18);
            ItemStack stack = new ItemStack(Material.BOOK);
            stack.setItemMeta(meta);

            cards[i] = new Card(stack, location);

        }



        //Create Interaction
        inter = Objects.requireNonNull(CGE.instance.getServer().getWorld("world")).spawn(blockLocation, Interaction.class);
        inter.setInteractionHeight(1);
        inter.setInteractionWidth(1);
        inter.addScoreboardTag("interactable");
        inter.addScoreboardTag("game-"+gameID.toString());
        inter.addScoreboardTag("event-toggle");
        inter.addScoreboardTag("cge");
        CGE.instance.RegisteredElements.add(inter.getUniqueId().toString());


        //Add Game to Game List
        CGE.games.put(gameID, this);
    }

//    Add Functions:
//    -addPlayer
//    -removePlayer
//    -resetGame
//    -startGame

    public boolean addPlayer(Player p){


        return false;
    }

    public boolean removePlayer(Player p){


        return false;
    }



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
