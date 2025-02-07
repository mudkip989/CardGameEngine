package us.mudkip989.mods.cge.example;

import net.kyori.adventure.text.*;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.util.Vector;
import us.mudkip989.mods.cge.*;
import us.mudkip989.mods.cge.event.*;
import us.mudkip989.mods.cge.object.*;

import java.util.*;

import static us.mudkip989.mods.cge.utils.Meths.shiftLocationForwards;

public class Blackjack extends Game {

    //Players
    private final List<Player> players = new ArrayList<>();
    private final List<Hand<Card>> hands = new ArrayList<>();
    private final List<Boolean> isPlaying = new ArrayList<>();
    private final HashMap<Player, Boolean> isStand = new HashMap<>();
    private final HashMap<Player, Integer> seating = new HashMap<>();

    //Dealer
    private Hand<Card> dealerHand;
    private boolean dealerStand = false;

    //Table
    private final Deck<Card> deck;
    private Interactive joinButton;
    private Interactive leaveButton;
    public Location center;
    private final Integer maxPlayers = 5;

    //Game
    private Boolean active = false;
    private Boolean started = false;
    private Integer timer = 0;
    private List<GameObject> randoObjects;
    private Interactive hitButton;
    private TextObject hitText;
    private Interactive standButton;
    private TextObject standText;
    private Interactive startButton;


    //debug things
    private TextObject tickCounter;
    private int counter;

    /*
    --------------------------------------------------------
    HELLO!
    Please Put References to ALL Entities somewhere proper so we do not lose contact to them!
    --------------------------------------------------------
    */


    public Blackjack(Location location) {
        super(location);
        randoObjects = new ArrayList<>();
        location.setPitch(0);
        location.add(0, 0.01, 0);
        center = location.clone();
        location.setYaw(location.getYaw()-90);

        // Initialize buttons

        joinButton = new Interactive(shiftLocationForwards(location.clone(), 1f), 1, 1, gameID, "JOIN");
        leaveButton = new Interactive(shiftLocationForwards(location.clone(), -1f), 1, 1, gameID, "LEAVE");

        Location centerClone = center.clone();
//        tickCounter = new TextObject(centerClone, "tick");
        centerClone.add(new Location(centerClone.getWorld(), 0, 1, 0));
        startButton = new Interactive(centerClone, 0.5f, 0.5f, gameID, "START");

        // Initialize deck
        deck = new Deck<>(shiftLocationForwards(center.clone(), -1f));
        List<Cards> inDeck = new ArrayList<>(List.of(Cards.values()));
        inDeck.removeFirst();
        for(Cards thing: inDeck){

            ItemStack stacked = new ItemStack(Material.BOOK);
            ItemMeta meta = stacked.getItemMeta();
            meta.setCustomModelData(thing.modelData);
            stacked.setItemMeta(meta);
            Card thingy = new Card(stacked, this.center);
            thingy.value = thing.rank;
            thingy.suit = thing.suit;
            thingy.modelData = thing.modelData;
            deck.discard(thingy);

        }
        Location rotorloc = location.clone();
        rotorloc.setYaw(rotorloc.getYaw()+90);
        deck.shuffle();
        deck.updateCardPostitions();
        dealerHand = new Hand<>(rotorloc.clone());
        float diff = 180f/(maxPlayers-1);


        //hit and stand buttons
        hitButton = new Interactive(shiftLocationForwards(rotorloc.clone(), 0.5f), 0.25f, 0.25f, this.gameID, "DRAW");
        hitText = new TextObject(shiftLocationForwards(rotorloc.clone(), 0.5f),"Hit");
        standButton = new Interactive(shiftLocationForwards(rotorloc.clone(), -0.5f), 0.25f, 0.25f, this.gameID, "NEXT");
        standText = new TextObject(shiftLocationForwards(rotorloc.clone(), -0.5f),"Stand");
        rotorloc.setYaw(rotorloc.getYaw()-90);

        for(int i = 0; i < maxPlayers; i++){
            //create player slot
            players.add(null);
            //create player bool
            isPlaying.add(false);
            //create player hand at rotated location
            Location temploc = shiftLocationForwards(rotorloc.clone(), 2f);
            temploc.setYaw(temploc.getYaw()+180);
            hands.add(new Hand<>(temploc));
            new Node(temploc);

//            //templocation
//            Location temploc = rotorloc.clone();
//            temploc.setYaw(temploc.getYaw() + 90);


            //rotate for next iteration
            rotorloc.setYaw(rotorloc.getYaw()+diff);
        }





        testTable();

    }

    private void resetGame(){

        for(Hand<Card> hand: hands){
            List<Card> tempList = new ArrayList<>(hand.cards);
            tempList.forEach(card -> {
                deck.discard(card);
                hand.cards.remove(card);
            });
            hand.updateCardPostitions();
        }
        List<Card> tempList = new ArrayList<>(dealerHand.cards);
        tempList.forEach(card -> {
            deck.discard(card);
            dealerHand.cards.remove(card);

        });
        dealerStand = false;
        deck.updateCardPostitions();
        dealerHand.updateCardPostitions();

        isStand.forEach((player, val) -> isStand.put(player, false));
        for(int i = 0; i < maxPlayers; i++){
            isPlaying.set(i, false);
        }
        seating.forEach((player, seat) -> isPlaying.set(seat, true));


    }

    private void dealCard(Player player) {

        int seat = seating.get(player);
        Hand<Card> hand = hands.get(seat);
        Card card = deck.draw();
        if(card != null){
            hand.add(card);
        }
        hand.updateCardPostitions();
//        Card card = (Card) deck.draw();
//        if (card != null) {
//            hands.stream().filter(hand -> hand.owner.equals(player)).forEach(hand -> hand.add((Card) deck.draw()));
//
//        }
    }

    private void testTable(){


    }

    private int calculateScore(Hand<Card> hand) {
        List<Card> cards = hand.cards;
        if(cards.isEmpty()){
            return 0;
        }
        int score = 0;
        int aces = 0;
        for (Card card : cards) {
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
//        counter++;
//        tickCounter.setText(String.valueOf(counter));


        if (this.players.stream().noneMatch(Objects::nonNull) && this.active) {
            CGE.instance.getServer().sendMessage(Component.text("hit?"));
            resetGame();
            //Reset game
            this.active = false;

            return;
        }

        //if game is inactive and start pressed, reset all decks/hands, set timer
        if (!this.active && this.started) {
            resetGame();
            timer = 60;
            this.active = true;
            this.started = false;

        }

        //if game is active, and players all stand, tick timer.
        if (this.active && isStand.values().stream().allMatch(state -> state)) {
            timer--;
            if(timer<1){
                if(!dealerStand){
                    timer = 40;

                    int seenScore = calculateScore(dealerHand);
                    if(seenScore >16){
                        //stand
                        dealerStand = true;
                    }else{
                        //draw
                        dealerHand.add(deck.draw());
                        dealerHand.updateCardPostitions();
                        deck.updateCardPostitions();
                    }

                    //dealer logic
                }else{
                    timer = 100;
                    this.active = false;
                    //calculate score and show winners.
                }
            }
        }

        // Perform periodic updates if necessary
    }

    @Override
    public void runEvent(Event event, String args, Player player) {
        switch (event) {
            case JOIN -> {
                if (!players.contains(player) && seating.size() < maxPlayers) {
                    if(players.contains(null)){
                        int index = players.indexOf(null);
                        players.set(index, player);

                        isStand.put(player, false);
                        //find available numbers here
                        seating.put(player, index);
                        if(!this.active){
                            isPlaying.set(seating.get(player), true);
                        }

                        player.sendMessage("You have joined the Blackjack game!");
                    }else {
                        player.sendMessage("You are already part of the table.");
                    }

                }else{
                    player.sendMessage("Table is full.");
                }
            }
            case LEAVE -> {
                if (players.contains(player)) {
                    players.set(seating.get(player), null);
                    isPlaying.set(seating.get(player), false);
                    isStand.remove(player);
                    seating.remove(player);

                    player.sendMessage("You have left the Blackjack game!");
                }
            }
            case FUNCTION -> {
                if ("deal".equals(args) && players.contains(player)) {
                    dealCard(player);
                    player.sendMessage("You were dealt a card.");
                }
            }
            case DRAW -> {
                if(players.contains(player)) {
                    player.sendMessage(Component.text(isPlaying.get(seating.get(player)) + " : " + seating.get(player)));
                    if(isPlaying.get(seating.get(player))) {
                        dealCard(player);
                        player.sendMessage("You were dealt a card.");
                    }
                }
            }
            case NEXT -> {
                if(players.contains(player)) {
                    if(!isStand.get(player) && isPlaying.get(seating.get(player))) {
                        isStand.put(player, true);
                    }
                }
            }
            case START -> {
                player.sendMessage(Component.text(players.contains(player) + " : " + this.active));
                if(players.contains(player) && !this.active){
                    player.sendMessage(Component.text("Hit debug message"));
                    this.started = true;
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
