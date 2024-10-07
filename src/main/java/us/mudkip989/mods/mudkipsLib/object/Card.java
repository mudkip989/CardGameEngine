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

    public static Vector3d[] RotateVectors(Vector3d[] vecs, Quaterniond quad){
        Vector3d[] newarr = new Vector3d[3];
        int i = 0;
        for(Vector3d vec: vecs){
            Quaterniond vecq = new Quaterniond(vec.x, vec.y, vec.z, 0);
            Quaterniond iquad = new Quaterniond(quad.x * -1,quad.y * -1,quad.z * -1,quad.w);
            Quaterniond result = multiply(multiply(quad, vecq), iquad);
            newarr[i] = new Vector3d(result.x, result.y, result.z);
            i++;
        }


        return newarr;
    }

    public static Quaterniond multiply(Quaterniond quada, Quaterniond quadb){
        double cx = quada.x;
        double cy = quada.y;
        double cz = quada.z;
        double cw = quada.w;
        double rx = quadb.x;
        double ry = quadb.y;
        double rz = quadb.z;
        double rw = quadb.w;

        double w = cw*rw - cx*rx - cy*ry - cz*rz;
        double x = cw*rx + cx*rw + cy*rz - cz*ry;
        double y = cw*ry - cx*rz + cy*rw + cz*rx;
        double z = cw*rz + cx*ry - cy*rx + cz*rw;

        return new Quaterniond(x, y, z, w);

    }


}
