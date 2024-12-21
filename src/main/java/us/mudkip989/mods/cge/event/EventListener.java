package us.mudkip989.mods.cge.event;

import org.bukkit.entity.*;
import org.bukkit.event.*;
import org.bukkit.event.player.*;
import us.mudkip989.mods.cge.*;
import us.mudkip989.mods.cge.object.*;

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
            if (CGE.games.containsKey(UUID.fromString(id))) {
                Game game = CGE.games.get(UUID.fromString(id));
                String[] action = Game.parseValuefromEntity(victim, "event").split(":");
                String event = action[0];
                String rawargs = "";
                if(action.length > 1) {
                    rawargs = action[1];
                }
                String args = rawargs.replaceAll("<player>", player.getName());
                game.runEvent(Event.valueOf(event.toUpperCase()), args, player);
            }

        }
    }



}
