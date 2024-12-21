package us.mudkip989.mods.cge.object;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.joml.*;
import us.mudkip989.mods.cge.*;

public class Card extends GameObject {

    private ItemDisplay card;
    private ItemStack cardItem;



    public Card(ItemStack card_item, Location location){
        super(location);


        cardItem = card_item;
        location.setYaw(0);
        location.setPitch(0);
        location.toCenterLocation();
        card = Bukkit.getServer().getWorld(location.getWorld().getName()).spawn(location, ItemDisplay.class);
        card.setItemStack(cardItem);
        card.addScoreboardTag("cge");
        CGE.instance.RegisteredElements.add(card.getUniqueId().toString());


    }


    @Override
    public void teleport(Location loc) {
        super.teleport(loc);
        card.teleport(loc);
    }

    @Override
    public void teleport(GameObject ob) {
        super.teleport(ob);
        card.teleport(ob.getLocation());
    }

    public void flip(){

        Quaterniond q = new Quaterniond(0, 0, 0.707106781, 0.707106781);


    }



}
