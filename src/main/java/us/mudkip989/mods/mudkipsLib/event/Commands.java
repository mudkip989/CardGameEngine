package us.mudkip989.mods.mudkipsLib.event;

import org.bukkit.command.*;
import org.bukkit.entity.*;
import org.jetbrains.annotations.*;
import us.mudkip989.mods.mudkipsLib.*;
import us.mudkip989.mods.mudkipsLib.object.*;

public class Commands implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        try {
        if(commandSender instanceof Player) {

                new Game(((Player) commandSender).getLocation());

            return true;
        }
        }catch (Exception e){
            System.out.println(e);
        }

        return false;
    }
}
