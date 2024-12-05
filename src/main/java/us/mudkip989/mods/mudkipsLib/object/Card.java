package us.mudkip989.mods.mudkipsLib.object;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.joml.*;

public class Card extends GameObject {

    public ItemDisplay card,blankCard;
    public ItemStack cardItem, blankCardItem;



    public Card(ItemStack card_item, ItemStack blank_card_item, Location location){
        super(location);


        cardItem = card_item;
        blankCardItem = blank_card_item;
        location.setYaw(0);
        location.setPitch(0);
        location.toCenterLocation();
        card = Bukkit.getServer().getWorld(location.getWorld().getName()).spawn(location, ItemDisplay.class);
        blankCard = Bukkit.getServer().getWorld(location.getWorld().getName()).spawn(location, ItemDisplay.class);
        card.setItemStack(cardItem);
        blankCard.setItemStack(blankCardItem);


    }


    @Override
    public void teleport(Location loc) {
        super.teleport(loc);


    }

    public void flip(){

        Quaterniond q = new Quaterniond(0, 0, 0.707106781, 0.707106781);


    }

}
