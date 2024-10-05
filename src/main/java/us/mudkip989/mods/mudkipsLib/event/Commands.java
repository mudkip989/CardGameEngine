package us.mudkip989.mods.mudkipsLib.event;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.jetbrains.annotations.*;
import us.mudkip989.mods.mudkipsLib.object.*;

import static us.mudkip989.mods.mudkipsLib.MudkipsLib.log;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        try {
        if(commandSender instanceof Player) {

                new Game(((Player) commandSender).getLocation());

            return true;
        }
        }catch (Exception e){
            log.error("Error: ", e);
        }

        return false;
    }
}
