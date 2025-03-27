package us.mudkip989.mods.cge.event;

import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import us.mudkip989.mods.cge.*;
import us.mudkip989.mods.cge.example.*;

import java.util.*;
import java.util.logging.Level;

import static us.mudkip989.mods.cge.CGE.gameLibrary;
import static us.mudkip989.mods.cge.CGE.logger;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        try {
            if (commandSender instanceof Player) {
                if (gameLibrary.keySet().contains(strings[0])) {
                    CGE.removeElements(true);
                    Object[] things = {((Player) commandSender).getLocation()};
                    gameLibrary.get(strings[0]).getDeclaredConstructor(Location.class).newInstance(things);


                }


                return true;
            }
        } catch (Exception e){
            logger.log(Level.SEVERE, String.valueOf(e));
        }

        return false;
    }
}
