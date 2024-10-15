package us.mudkip989.mods.mudkipsLib.object;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.joml.*;

public class Card {

    public ItemDisplay card,blankCard;
    public Vector3d[] transformVectors;
    public ItemStack cardItem, blankCardItem;



    public Card(ItemStack card_item, ItemStack blank_card_item, Location location){
        transformVectors = new Vector3d[3];
        transformVectors[0] = new Vector3d(1, 0, 0);
        transformVectors[1] = new Vector3d(0, 1, 0);
        transformVectors[2] = new Vector3d(0, 0, 1);
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




}
