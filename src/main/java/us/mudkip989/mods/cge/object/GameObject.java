package us.mudkip989.mods.cge.object;

import org.bukkit.*;

public class GameObject {
    private Location location;

    public GameObject(Location loc){
        location = loc;
    }

    public void teleport(Location loc) {
        location = loc;
    }

    public void teleport(GameObject ob){
        location = ob.getLocation();

    }

    public Location getLocation(){
        return location;
    }

    public void remove(){



    }




}
