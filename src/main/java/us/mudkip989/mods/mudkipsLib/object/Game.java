package us.mudkip989.mods.mudkipsLib.object;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.joml.*;
import us.mudkip989.mods.mudkipsLib.*;

import java.util.*;

public class Game {
    private final UUID gameID;
    public List<Player> players;
    public ItemDisplay disp;
    public Interaction inter;



    public Game(Location location){

        gameID = UUID.randomUUID();
        players = new ArrayList<>();
        disp = Objects.requireNonNull(MudkipsLib.instance.getServer().getWorld("world")).spawn(location, ItemDisplay.class);
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
        inter = Objects.requireNonNull(MudkipsLib.instance.getServer().getWorld("world")).spawn(location, Interaction.class);
        inter.setInteractionHeight(1);
        inter.setInteractionWidth(1);
        inter.addScoreboardTag("interactable");
        inter.addScoreboardTag("game-"+gameID.toString());
        inter.addScoreboardTag("event-toggle:<player>");
        MudkipsLib.games.put(gameID, this);
    }

//    Add Functions:
//    -removePlayer
//    -resetGame
//    -startGame


    public void runEvent(String event, String args){

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
                Player p = Bukkit.getPlayer(args);
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
