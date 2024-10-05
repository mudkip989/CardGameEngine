package us.mudkip989.mods.mudkipsLib.object;

import org.bukkit.*;
import org.bukkit.entity.*;
import us.mudkip989.mods.mudkipsLib.*;

import java.io.*;
import java.util.*;
import java.util.logging.*;

public class Node {
    public Location location;
    public boolean faceUp, stackShowsHeight;


    public static String parseValuefromEntity(Entity e, String key){
        Set<String> tags = e.getScoreboardTags();

        String val = null;

        for(String tag: tags){

            if(tag.startsWith(key + "-")){
                val = tag.replaceFirst(key + "-", "");
            }

        }

        return val;
    }



}
