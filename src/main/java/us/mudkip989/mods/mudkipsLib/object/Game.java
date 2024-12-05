package us.mudkip989.mods.mudkipsLib.object;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.joml.*;
import us.mudkip989.mods.mudkipsLib.*;

import java.util.*;

import static org.joml.Math.*;

public class Game {
    private final UUID gameID;
    public Player[] players;
    public ItemDisplay disp;
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
        Location blockLocation = location.toCenterLocation();
        blockLocation.setPitch(0);
        blockLocation.setYaw((round(location.getYaw()/90)*90));
        gameID = UUID.randomUUID();
        players = new Player[4];




        disp = Objects.requireNonNull(MudkipsLib.instance.getServer().getWorld("world")).spawn(blockLocation, ItemDisplay.class);
        ItemMeta meta = new ItemStack(Material.BOOK).getItemMeta();
        meta.setCustomModelData(18);
        ItemStack stack = new ItemStack(Material.BOOK);

        stack.setItemMeta(meta);
        disp.setItemStack(stack);
        disp.setTransformationMatrix(new Matrix4f(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1));
        inter = Objects.requireNonNull(MudkipsLib.instance.getServer().getWorld("world")).spawn(blockLocation, Interaction.class);
        inter.setInteractionHeight(1);
        inter.setInteractionWidth(1);
        inter.addScoreboardTag("interactable");
        inter.addScoreboardTag("game-"+gameID.toString());
        inter.addScoreboardTag("event-toggle");
        MudkipsLib.games.put(gameID, this);
    }

//    Add Functions:
//    -addPlayer
//    -removePlayer
//    -resetGame
//    -startGame


    public void runEvent(String event, String args, Player p){

        switch(event){
            case "delete":
                disp.remove();
                inter.remove();
                break;
            case "hide":


                break;
            case "show":


                break;
            case "toggle":
                if(p.canSee(disp)) {
                    p.hideEntity(MudkipsLib.instance, disp);
                }else{
                    p.showEntity(MudkipsLib.instance, disp);
                }

                break;
            default:

                break;
        }

    }





}
