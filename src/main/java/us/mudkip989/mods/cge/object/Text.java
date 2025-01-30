package us.mudkip989.mods.cge.object;

import net.kyori.adventure.text.*;
import org.bukkit.*;
import org.bukkit.entity.*;

import java.util.*;

public class Text extends GameObject{

    private String text;
    public TextDisplay display;

    public Text(Location loc, String text) {
        super(loc);
        this.text = text;
        display = Objects.requireNonNull(loc.getWorld()).spawn(loc, TextDisplay.class);
        display.text(Component.text(text));
        display.addScoreboardTag("cge");
    }

    public void setText(String text){
        this.text = text;

    }

    public String getText(){
        return text;
    }
}
