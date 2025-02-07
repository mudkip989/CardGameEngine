package us.mudkip989.mods.cge.object;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.util.*;
import org.bukkit.util.Vector;
import org.joml.*;

import java.lang.*;
import java.lang.Math;
import java.util.*;

public class Hand<T extends GameObject> extends GameObject {
    public Vector stackVector;
    public List<T> cards = new ArrayList<>();
    public Player owner;
    private Transformation totalTransformation;

    public Hand(Location loc, Vector vec) {
        super(loc);
        stackVector = vec;
        totalTransformation = new Transformation(
                new Vector3f(0, 0, 0),              //Translation
                new AxisAngle4f(0, 0, 0, 1),  //Left Rotation
                new Vector3f(1, 1, 1),              //Scale
                new AxisAngle4f(0, 0, 0, 0)); //Right Rotation

    }
    public Hand(Location loc){
        super(loc);
        stackVector = new Vector(0.48, 0, 0);
        totalTransformation = new Transformation(
                new Vector3f(0, 0, 0),              //Translation
                new AxisAngle4f(0, 0, 0, 1),  //Left Rotation
                new Vector3f(1, 1, 1),              //Scale
                new AxisAngle4f(0, 0, 0, 0)); //Right Rotation
    }

    public boolean add(T card) {
        cards.add(card);
        updateCardPostitions();
        return true;
    }

    public void updateCardPostitions() {

        //teleport & transform cards to hand location.
        Vector vec = stackVector.clone().rotateAroundY((-this.getLocation().getYaw()* Math.PI)/180);
        int num = cards.size()-1;
        Location tempLoc = this.getLocation().clone();
        tempLoc.add(vec.clone().multiply(-num/2f));
        for(T card: cards) {

            card.teleport(tempLoc.clone().add(vec.clone().multiply(cards.indexOf(card))));
            card.setTransform(totalTransformation);

        }

    }

    @Override
    public void setTransform(Transformation trans) {
        totalTransformation = trans;
        updateCardPostitions();
    }

    public boolean assignToPlayer(Player newOwner) {
        if (owner != null) {
            owner = newOwner;
            return true;
        }

        return false;
    }

    public boolean unassign() {
        if (owner != null) {

            owner = null;

            return true;
        }

        return false;
    }

    public boolean clear(){

        for (T card: cards){
            card.remove();
        }

        return true;
    }



}
