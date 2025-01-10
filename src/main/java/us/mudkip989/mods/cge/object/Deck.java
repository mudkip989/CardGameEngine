package us.mudkip989.mods.cge.object;

import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.util.*;

public class Deck<T extends GameObject> extends GameObject{
    private final List<T> cards = new ArrayList<>();
    private final Location deckLocation;

    public Deck(Location location) {
        super(location);
        this.deckLocation = location;

        shuffle();
    }

    private void initializeDeck() {




//        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
//        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
//        int[] customModelData = {
//                18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, // Hearts
//                34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, // Diamonds
//                50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, // Clubs
//                66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78  // Spades
//        };
//
//        int dataIndex = 0;
//        for (String suit : suits) {
//            for (String value : values) {
//                ItemStack cardItem = new ItemStack(Material.BOOK);
//                ItemMeta meta = cardItem.getItemMeta();
//                if (meta != null) {
//                    meta.setCustomModelData(customModelData[dataIndex]);
//
//                    cardItem.setItemMeta(meta);
//                }
//                Card card = new Card(cardItem, deckLocation.clone());
//                card.value = value + " of " + suit;
//                cards.add(card);
//                dataIndex++;
//            }
//        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public T draw() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null; // Deck is empty
    }

    public int size() {
        return cards.size();
    }

}