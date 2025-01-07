package us.mudkip989.mods.cge;

import com.google.gson.*;
import org.bukkit.*;
import org.bukkit.plugin.*;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.*;
import us.mudkip989.mods.cge.event.*;
import us.mudkip989.mods.cge.event.EventListener;
import us.mudkip989.mods.cge.object.*;

import java.util.*;
import java.util.logging.*;

public final class CGE extends JavaPlugin {

    public static final Gson gson = new GsonBuilder().create();

    public static CGE instance;
    public static HashMap<UUID, Game> games;
    public static Logger logger;
    public List<String> RegisteredElements;

    @Override
    public void onEnable() {
        instance = this;
        logger = this.getLogger();
        RegisteredElements = new ArrayList<>();

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new EventListener(), instance);
        Objects.requireNonNull(this.getCommand("cge")).setExecutor(new Commands());
        games = new HashMap<>();
        new BukkitRunnable() {
            @Override
            public void run() {
                for(Game game: games.values()){
                    game.tickGame();
                }
            }
        }.runTaskTimerAsynchronously(this, 100, 1);
    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    @Override
    public void onLoad() {


    }

    public static void removeElements(boolean removeInvalid){

        List<World> worlds = instance.getServer().getWorlds();
        for (World world: worlds){
            world.getEntities().stream().filter(e -> e.getScoreboardTags().contains("cge") && (!removeInvalid || !instance.RegisteredElements.contains(e.getUniqueId().toString()))).forEach(e -> {
                if(!removeInvalid) instance.RegisteredElements.remove(e.getUniqueId().toString());
                logger.log(Level.FINE, e.getType().toString());
                e.remove();
            });


        }
    }
}
//