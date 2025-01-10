package us.mudkip989.mods.cge.object;

import org.bukkit.*;
import org.bukkit.entity.*;
import org.joml.*;

import java.util.*;

public class Hand extends GameObject {
    public Vector3d stackVector;
    public List<Card> cards = new ArrayList<>();
    public Player owner;

    public Hand(Location loc) {
        super(loc);


    }

    public boolean add(Card card) {
        cards.add(card);
        updateCardPostitions();
        return true;
    }

    public void updateCardPostitions() {

        //teleport & transform cards to hand location.


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

}
