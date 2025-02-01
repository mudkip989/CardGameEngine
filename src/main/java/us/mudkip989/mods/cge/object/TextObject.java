package us.mudkip989.mods.cge.object;

import net.kyori.adventure.text.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.util.*;
import org.joml.*;
import us.mudkip989.mods.cge.*;

import java.util.*;

public class TextObject extends GameObject{

    private String text;
    public TextDisplay display;
    private Transformation transformation;

    public TextObject(Location loc, String text) {
        super(loc);
        this.text = text;
        display = Objects.requireNonNull(loc.getWorld()).spawn(loc, TextDisplay.class);
        display.text(Component.text(text));
        display.addScoreboardTag("cge");
        display.setTransformationMatrix(new Matrix4f(1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1));
        transformation = display.getTransformation();
        CGE.instance.RegisteredElements.add(display.getUniqueId().toString());

    }

    public void setText(String text){
        this.text = text;
    }

    public String getText(){
        return text;
    }

    public Transformation getTransformation(){
        return transformation;
    }

    public void setTransformation(Transformation transform){

        transformation = transform;

    }

}
