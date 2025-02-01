package us.mudkip989.mods.cge.object;

import org.bukkit.*;
import org.bukkit.entity.*;
import us.mudkip989.mods.cge.*;

import java.util.*;

public class Interactive extends GameObject{

    public Interaction interaction;

    public Interactive(Location loc, float width, float height, UUID gameID, String event) {
        super(loc);

        interaction = Objects.requireNonNull(loc.getWorld()).spawn(loc, Interaction.class);
        interaction.setInteractionWidth(width);
        interaction.setInteractionHeight(height);



        interaction.addScoreboardTag("interactable");
        interaction.addScoreboardTag("game-"+gameID.toString());
        interaction.addScoreboardTag("event-"+event);
        interaction.addScoreboardTag("cge");
        CGE.instance.RegisteredElements.add(interaction.getUniqueId().toString());


    }
}
