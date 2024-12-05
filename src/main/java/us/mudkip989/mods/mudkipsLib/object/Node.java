package us.mudkip989.mods.mudkipsLib.object;

import org.bukkit.*;
import org.bukkit.entity.*;
import us.mudkip989.mods.mudkipsLib.*;

import java.io.*;
import java.util.*;
import java.util.logging.*;

public class Node {
    public Location location;

    public Node(Location loc){
        location = loc;
    }

    public void teleport(Location loc) {
        location = loc;
    }




}
