package us.mudkip989.mods.mudkipsLib;

import com.google.gson.*;
import org.bukkit.*;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPlugin;
import us.mudkip989.mods.mudkipsLib.event.*;
import us.mudkip989.mods.mudkipsLib.object.*;

import java.util.*;

public final class MudkipsLib extends JavaPlugin {

    public static Gson gson = new GsonBuilder().create();

    public static MudkipsLib instance;

    public static List<Game> games;




    @Override
    public void onEnable() {
        instance = this;
        PluginManager pm = Bukkit.getPluginManager();
        this.getCommand("mlib").setExecutor(new Commands());
        games = new ArrayList<>();

        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
