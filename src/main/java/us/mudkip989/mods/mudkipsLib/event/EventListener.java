package us.mudkip989.mods.mudkipsLib.event;

import net.kyori.adventure.text.*;
import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import us.mudkip989.mods.mudkipsLib.*;
import us.mudkip989.mods.mudkipsLib.object.*;

import java.util.*;

public class EventListener implements Listener {

    @EventHandler
    public void join(PlayerJoinEvent e){

    }

    @EventHandler
    public void interact(PlayerInteractEntityEvent e){
        Entity victim = e.getRightClicked();
        Player player = e.getPlayer();
        if (victim.getScoreboardTags().contains("interactable")) {
            String id = Game.parseValuefromEntity(victim, "game");
            if (MudkipsLib.games.containsKey(UUID.fromString(id))) {
                Game game = MudkipsLib.games.get(UUID.fromString(id));
                String[] action = Game.parseValuefromEntity(victim, "event").split(":");
                String event = action[0];
                String rawargs = action[1];
                String args = rawargs.replaceAll("<player>", player.getName());
                game.runEvent(Event.valueOf(event.toUpperCase()), args, player);
            }

        }
    }

}
