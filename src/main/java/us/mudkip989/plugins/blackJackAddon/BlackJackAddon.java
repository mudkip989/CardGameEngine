package us.mudkip989.plugins.blackJackAddon;

import org.bukkit.*;
import org.bukkit.plugin.java.JavaPlugin;
import us.mudkip989.mods.cge.*;
import us.mudkip989.plugins.blackJackAddon.Game.*;

public final class BlackJackAddon extends JavaPlugin {

    @Override
    public void onEnable() {

        CGE cgeInstance = (CGE) Bukkit.getServer().getPluginManager().getPlugin("CardGameEngine");
        cgeInstance.registerGame("bja:black_jack", Blackjack.class);
        // Plugin startup logic

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
