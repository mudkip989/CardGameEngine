package us.mudkip989.mods.cge.example;

import org.bukkit.*;
import org.bukkit.entity.*;
import us.mudkip989.mods.cge.event.*;
import us.mudkip989.mods.cge.object.*;

import java.util.*;
import java.util.function.Predicate;

import static us.mudkip989.mods.cge.utils.Meths.shiftLocationForwards;

public class Blackjack extends Game {
    private final List<Player> players = new ArrayList<>();
    private final List<Hand> hands = new ArrayList<>();
    private final List<Boolean> isPlaying = new ArrayList<>();
    private final List<Boolean> isStand = new ArrayList<>();

    private Hand dealerHand;
    private boolean dealerStand = false;

    private final Deck deck;
    private Interactive joinButton;
    private Interactive leaveButton;
    public Location center;
    private Integer maxPlayers = 5;

    private Boolean active = false;
    private Boolean started = false;
    
    public Blackjack(Location location) {
        super(location);

        location.setPitch(0);
        location.setYaw(15); //Temporary... Not Permanent.
        location.add(0, 0.1, 0);
        center = location.clone();
        location.setYaw(location.getYaw()-90);

        // Initialize buttons


        joinButton = new Interactive(shiftLocationForwards(location.clone(), 1), 1, 1, gameID, "JOIN");
        leaveButton = new Interactive(shiftLocationForwards(location.clone(), -1), 1, 1, gameID, "LEAVE");

        // Initialize deck
        deck = new Deck(shiftLocationForwards(center.clone(), -1));
        dealerHand = new Hand(center);
        float diff = 180f/(maxPlayers-1);
        for(int i = 0; i < maxPlayers; i++){
            players.add(null);
            hands.add(new Hand(shiftLocationForwards(location.clone(), 2)));
            isPlaying.add(false);
            location.setYaw(location.getYaw()+diff);
        }

        testTable();

    }

    private void dealCard(Player player) {
        Card card = (Card) deck.draw();
        if (card != null) {
            hands.stream().filter(hand -> hand.owner.equals(player)).forEach(hand -> hand.add((Card) deck.draw()));

        }
    }

    private void testTable(){


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
        //Check player list
            //if no players & game started, reset game
            //if players, continue

        if (this.players.stream().noneMatch(Objects::nonNull) && this.active) {
            //Reset game

            return;
        }

        //if game is inactive and start pressed, reset all decks/hands, set timer
        if (!this.active && this.started) {

        }

        //if game is active, and players all stand, tick timer.
        if (this.active) {
            //if dealer no stand, set timer, play dealer
            //if dealer stand, set timer, calc scores
        }

        // Perform periodic updates if necessary
    }

    @Override
    public void runEvent(Event event, String args, Player player) {
        switch (event) {
            case JOIN -> {
                if (!players.contains(player)) {
                    if(players.contains(null)){
                        players.set(players.indexOf(null), player);
                        player.sendMessage("You have joined the Blackjack game!");
                    }else {
                        player.sendMessage("Table is full.");
                    }

                }
            }
            case LEAVE -> {
                if (players.contains(player)) {
                    players.set(players.indexOf(player), null);
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
        BLANK           ("Blank",   "Blank",  1),

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
