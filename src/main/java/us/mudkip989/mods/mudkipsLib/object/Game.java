package us.mudkip989.mods.mudkipsLib.object;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.scheduler.*;
import org.joml.*;
import us.mudkip989.mods.mudkipsLib.*;

import java.util.*;

public class Game {
    private UUID gameID;
    public List<Player> players;
    public ItemDisplay disp;




    public Game(Location location){

        gameID = UUID.randomUUID();
        players = new ArrayList<>();
        disp = MudkipsLib.instance.getServer().getWorld("world").spawn(location, ItemDisplay.class);
        ItemMeta meta = new ItemStack(Material.PAPER).getItemMeta();
        meta.setCustomModelData(1);
        ItemStack stack = new ItemStack(Material.PAPER);
        stack.setItemMeta(meta);
        disp.setItemStack(stack);
        disp.setTransformationMatrix(new Matrix4f(
                1, 0, 0, 0,
                0, 1, 0, 0,
                0, 0, 1, 0,
                0, 0, 0, 1));

    }








}
