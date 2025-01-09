package us.mudkip989.mods.cge.example;

import org.bukkit.*;
import org.bukkit.entity.*;
import us.mudkip989.mods.cge.event.*;
import us.mudkip989.mods.cge.object.*;

import java.util.*;

public class Blackjack extends Game {
    private final List<Player> players = new ArrayList<>();
    private final Map<Player, Hand> hands = new HashMap<>();
    private final Deck deck;
    private Interactive joinButton;
    private Interactive leaveButton;
    public Location center;


    public Blackjack(Location location) {
        super(location);
        center = location;
        location.setPitch(0);
        location.setYaw(0); //Temporary... Not Permanent.

        // Initialize buttons
        joinButton = new Interactive(location.clone().add(2, 0, 0), 1, 1, gameID, "JOIN");
        leaveButton = new Interactive(location.clone().add(-2, 0, 0), 1, 1, gameID, "LEAVE");

        // Initialize deck
        deck = new Deck(location.clone().add(0, 1, 0));
    }

    private void dealCard(Player player) {
        Card card = deck.draw();
        if (card != null) {
            hands.computeIfAbsent(player, k -> new Hand(center)).cards.add(card);
            card.teleport(player.getLocation().add(0, 2, 0)); // Place card above the player

        }
    }

    private int calculateScore(List<Card> hand) {
        int score = 0;
        int aces = 0;
        for (Card card : hand) {
            String value = card.value;
            int numericValue = switch (value) {
                case "Jack", "Queen", "King" -> 10;
                case "Ace" -> 1;
                default -> Integer.parseInt(value);
            };
            score += numericValue;
            if (numericValue == 1) aces++;
        }
        while (aces > 0 && score <= 11) {
            score += 10; // Count Ace as 11 if it doesn't bust the score
            aces--;
        }
        return score;
    }

    @Override
    public void tickGame() {
        // Perform periodic updates if necessary
    }

    @Override
    public void runEvent(Event event, String args, Player player) {
        switch (event) {
            case JOIN -> {
                if (!players.contains(player)) {
                    players.add(player);
                    hands.put(player, new Hand(center));
                    player.sendMessage("You have joined the Blackjack game!");
                }
            }
            case LEAVE -> {
                if (players.contains(player)) {
                    players.remove(player);
                    hands.remove(player);
                    player.sendMessage("You have left the Blackjack game!");
                }
            }
            case FUNCTION -> {
                if ("deal".equals(args) && players.contains(player)) {
                    dealCard(player);
                    player.sendMessage("You were dealt a card.");
                }
            }
            default -> super.runEvent(event, args, player);
        }
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
