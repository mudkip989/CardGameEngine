package us.mudkip989.mods.cge.event;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import us.mudkip989.mods.cge.*;
import us.mudkip989.mods.cge.example.*;

import java.util.logging.Level;

import static us.mudkip989.mods.cge.CGE.logger;

public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        try {
            if(commandSender instanceof Player) {
                CGE.removeElements(true);
                new Blackjack(((Player) commandSender).getLocation());
                return true;
            }
        } catch (Exception e){
            logger.log(Level.parse("error"), String.valueOf(e));
        }

        return false;
    }
}
