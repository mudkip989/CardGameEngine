package us.mudkip989.mods.mudkipsLib.object;

import org.bukkit.*;

public class GameObject {
    public Location location;

    public GameObject(Location loc){
        location = loc;
    }

    public void teleport(Location loc) {
        location = loc;
    }




}
