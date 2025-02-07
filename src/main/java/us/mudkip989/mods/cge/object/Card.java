package us.mudkip989.mods.cge.object;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.util.*;
import org.joml.*;
import us.mudkip989.mods.cge.*;

import java.util.*;

public class Card extends GameObject {

    private ItemDisplay card;
    private ItemStack cardItem;
    public String value = "";
    public String suit = "";
    public int modelData = 0;




    public Card(ItemStack card_item, Location location){
        super(location);


        cardItem = card_item;
        location.setYaw(0);
        location.setPitch(0);
        location.toCenterLocation();
        card = Objects.requireNonNull(location.getWorld()).spawn(location, ItemDisplay.class);
        card.setItemStack(cardItem);
        card.addScoreboardTag("cge");
        CGE.instance.RegisteredElements.add(card.getUniqueId().toString());
        card.setInterpolationDuration(5);
        card.setTeleportDuration(5);


    }

    public void setValue(String val){
        value = val;
    }

    public void setSuit(String val){
        suit = val;
    }

    public void setModelData(int val){
        modelData = val;
    }

    @Override
    public void teleport(Location loc) {
        super.teleport(loc);
        CGE.queueEntityTeleport(card, loc);
    }

    @Override
    public void teleport(GameObject ob) {
        super.teleport(ob);
        CGE.queueEntityTeleport(card, ob.getLocation());
    }

    public void flip(){

        Quaterniond q = new Quaterniond(0, 0, 0.707106781, 0.707106781);


    }

    @Override
    public void setTransform(Transformation trans){
        card.setTransformation(trans);
    }

    @Override
    public void remove() {
        card.remove();
        super.remove();
    }
}
