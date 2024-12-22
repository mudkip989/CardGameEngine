package us.mudkip989.mods.cge.example;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import us.mudkip989.mods.cge.*;
import us.mudkip989.mods.cge.event.*;
import us.mudkip989.mods.cge.object.*;

import java.util.*;

import static org.joml.Math.round;

public class BlackJack extends Game {

    public Card[] cards;
    public Interaction inter;
    public int tick;
    public Card thing;


    public BlackJack(Location location) {
        super(location);
        tick = 0;
        Location blockLocation = location.toCenterLocation();
        blockLocation.setPitch(0);
        blockLocation.setYaw((round(location.getYaw()/90)*90));

        cards = new Card[1];
        for (int i = 0; i < cards.length; i++){

            ItemMeta meta = new ItemStack(Material.BOOK).getItemMeta();
            meta.setCustomModelData(18);
            ItemStack stack = new ItemStack(Material.BOOK);
            stack.setItemMeta(meta);

            cards[i] = new Card(stack, location);

        }
        ItemMeta meta = new ItemStack(Material.BOOK).getItemMeta();
        meta.setCustomModelData(18);
        ItemStack stack = new ItemStack(Material.BOOK);
        stack.setItemMeta(meta);

        thing = new Card(stack, location);

        //Create Interaction
        inter = Objects.requireNonNull(CGE.instance.getServer().getWorld("world")).spawn(blockLocation, Interaction.class);
        inter.setInteractionHeight(1);
        inter.setInteractionWidth(1);
        inter.addScoreboardTag("interactable");
        inter.addScoreboardTag("game-"+gameID.toString());
        inter.addScoreboardTag("event-toggle");
        inter.addScoreboardTag("cge");
        CGE.instance.RegisteredElements.add(inter.getUniqueId().toString());

    }


    @Override
    public void tickGame() {
        tick++;

    }

    @Override
    public void runEvent(Event event, String args, Player p){

        switch(event){
            case Event.DELETE:


                break;
            case Event.HIDE:


                break;
            case Event.SHOW:


                break;
            case Event.TOGGLE:
                tick = 0;

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
