package us.mudkip989.mods.mudkipsLib;

import com.google.gson.*;
import org.bukkit.*;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPlugin;
import us.mudkip989.mods.mudkipsLib.event.*;
import us.mudkip989.mods.mudkipsLib.event.EventListener;
import us.mudkip989.mods.mudkipsLib.object.*;

import java.util.*;
import java.util.logging.*;

public final class MudkipsLib extends JavaPlugin {

    public static final Gson gson = new GsonBuilder().create();

    public static MudkipsLib instance;
    public static HashMap<UUID, Game> games;
    public static Logger logger;

    @Override
    public void onEnable() {
        instance = this;
        logger = this.getLogger();

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new EventListener(), instance);
        Objects.requireNonNull(this.getCommand("mlib")).setExecutor(new Commands());
        games = new HashMap<>();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
