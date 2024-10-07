package us.mudkip989.mods.mudkipsLib;

import com.google.gson.*;
import org.bukkit.*;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPlugin;
import us.mudkip989.mods.mudkipsLib.event.*;
import us.mudkip989.mods.mudkipsLib.event.EventListener;
import us.mudkip989.mods.mudkipsLib.object.*;

import java.util.*;

public final class MudkipsLib extends JavaPlugin {

    public static Gson gson = new GsonBuilder().create();

    public static MudkipsLib instance;

    public static HashMap<UUID, Game> games;

    public static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger("MudkipsLibs");
    @Override
    public void onEnable() {
        instance = this;

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new EventListener(), instance);
        Objects.requireNonNull(this.getCommand("mlib")).setExecutor(new Commands());
        games = new HashMap<>();

        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
