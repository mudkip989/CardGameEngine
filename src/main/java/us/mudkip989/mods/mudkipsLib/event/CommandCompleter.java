package us.mudkip989.mods.mudkipsLib.event;

import org.bukkit.command.*;
import org.jetbrains.annotations.*;

import java.util.*;

public class CommandCompleter implements TabCompleter {

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        return List.of();
    }
}
