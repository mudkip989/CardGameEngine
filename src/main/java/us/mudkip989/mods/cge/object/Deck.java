package us.mudkip989.mods.cge.object;

import org.bukkit.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.util.*;

public class Deck extends GameObject{
    private final List<Card> cards = new ArrayList<>();
    private final Location deckLocation;

    public Deck(Location location) {
        super(location);
        this.deckLocation = location;
        initializeDeck();
        shuffle();
    }

    private void initializeDeck() {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        int[] customModelData = {
                18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, // Hearts
                34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, // Diamonds
                50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, // Clubs
                66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78  // Spades
        };

        int dataIndex = 0;
        for (String suit : suits) {
            for (String value : values) {
                ItemStack cardItem = new ItemStack(Material.BOOK);
                ItemMeta meta = cardItem.getItemMeta();
                if (meta != null) {
                    meta.setCustomModelData(customModelData[dataIndex]);

                    cardItem.setItemMeta(meta);
                }
                Card card = new Card(cardItem, deckLocation.clone());
                card.value = value + " of " + suit;
                cards.add(card);
                dataIndex++;
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card draw() {
        if (!cards.isEmpty()) {
            return cards.remove(0);
        }
        return null; // Deck is empty
    }

    public int size() {
        return cards.size();
    }


    // All of the card types (unused right now)
    public enum Cards {
        TWO_OF_HEARTS   ("Hearts",  "2",      18),
        THREE_OF_HEARTS ("Hearts",  "3",      19),
        FOUR_OF_HEARTS  ("Hearts",  "4",      20),
        FIVE_OF_HEARTS  ("Hearts",  "5",      21),
        SIX_OF_HEARTS   ("Hearts",  "6",      22),
        SEVEN_OF_HEARTS ("Hearts",  "7",      23),
        EIGHT_OF_HEARTS ("Hearts",  "8",      24),
        NINE_OF_HEARTS  ("Hearts",  "9",      25),
        TEN_OF_HEARTS   ("Hearts",  "10",     26),
        JACK_OF_HEARTS  ("Hearts",  "Jack",   27),
        KING_OF_HEARTS  ("Hearts",  "Queen",  28),
        QUEEN_OF_HEARTS ("Hearts",  "King",   29),
        ACE_OF_HEARTS   ("Hearts",  "Ace",    30),

        TWO_OF_DIAMONDS   ("Diamonds",  "2",      34),
        THREE_OF_DIAMONDS ("Diamonds",  "3",      35),
        FOUR_OF_DIAMONDS  ("Diamonds",  "4",      36),
        FIVE_OF_DIAMONDS  ("Diamonds",  "5",      37),
        SIX_OF_DIAMONDS   ("Diamonds",  "6",      38),
        SEVEN_OF_DIAMONDS ("Diamonds",  "7",      39),
        EIGHT_OF_DIAMONDS ("Diamonds",  "8",      40),
        NINE_OF_DIAMONDS  ("Diamonds",  "9",      41),
        TEN_OF_DIAMONDS   ("Diamonds",  "10",     42),
        JACK_OF_DIAMONDS  ("Diamonds",  "Jack",   43),
        KING_OF_DIAMONDS  ("Diamonds",  "Queen",  44),
        QUEEN_OF_DIAMONDS ("Diamonds",  "King",   45),
        ACE_OF_DIAMONDS   ("Diamonds",  "Ace",    46),

        TWO_OF_CLUBS   ("Clubs",  "2",      50),
        THREE_OF_CLUBS ("Clubs",  "3",      51),
        FOUR_OF_CLUBS  ("Clubs",  "4",      52),
        FIVE_OF_CLUBS  ("Clubs",  "5",      53),
        SIX_OF_CLUBS   ("Clubs",  "6",      54),
        SEVEN_OF_CLUBS ("Clubs",  "7",      55),
        EIGHT_OF_CLUBS ("Clubs",  "8",      56),
        NINE_OF_CLUBS  ("Clubs",  "9",      57),
        TEN_OF_CLUBS   ("Clubs",  "10",     58),
        JACK_OF_CLUBS  ("Clubs",  "Jack",   59),
        KING_OF_CLUBS  ("Clubs",  "Queen",  60),
        QUEEN_OF_CLUBS ("Clubs",  "King",   61),
        ACE_OF_CLUBS   ("Clubs",  "Ace",    62),

        TWO_OF_SPADES   ("Spades",  "2",      66),
        THREE_OF_SPADES ("Spades",  "3",      67),
        FOUR_OF_SPADES  ("Spades",  "4",      68),
        FIVE_OF_SPADES  ("Spades",  "5",      69),
        SIX_OF_SPADES   ("Spades",  "6",      70),
        SEVEN_OF_SPADES ("Spades",  "7",      71),
        EIGHT_OF_SPADES ("Spades",  "8",      72),
        NINE_OF_SPADES  ("Spades",  "9",      73),
        TEN_OF_SPADES   ("Spades",  "10",     74),
        JACK_OF_SPADES  ("Spades",  "Jack",   75),
        KING_OF_SPADES  ("Spades",  "Queen",  76),
        QUEEN_OF_SPADES ("Spades",  "King",   77),
        ACE_OF_SPADES   ("Spades",  "Ace",    78);

        private final String suit;
        private final String rank;
        private final int modelData;

        Cards(String suit, String rank, int modelData) {
            this.suit = suit;
            this.rank = rank;
            this.modelData = modelData;
        }

        public String getSuit() { return this.suit; }
        public String getRank() { return this.rank; }
        public int getModelData() { return this.modelData; }
    }
}