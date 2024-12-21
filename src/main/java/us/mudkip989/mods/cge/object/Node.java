package us.mudkip989.mods.cge.object;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;

public class Node extends GameObject{
    private ItemDisplay dot;

    public Node(Location location) {
        super(location);
        dot = Bukkit.getServer().getWorld(location.getWorld().getName()).spawn(location, ItemDisplay.class);
        dot.setItemStack(new ItemStack(Material.GLASS));
        dot.addScoreboardTag("cge");

    }

    @Override
    public void teleport(Location loc) {
        super.teleport(loc);
        dot.teleport(loc);
    }

    @Override
    public void teleport(GameObject ob) {
        super.teleport(ob);
        dot.teleport(ob.getLocation());
    }
}
