package us.mudkip989.mods.cge.example;

import org.bukkit.*;
import org.bukkit.entity.*;
import us.mudkip989.mods.cge.event.*;
import us.mudkip989.mods.cge.object.*;

import java.util.*;

public class Blackjack extends Game {
    private final List<Player> players = new ArrayList<>();
    private final Map<Player, List<Card>> hands = new HashMap<>();
    private final Deck deck;
    private Interactive joinButton;
    private Interactive leaveButton;


    public Blackjack(Location location) {
        super(location);

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

            card.teleport(player.getLocation().add(0, 2, 0)); // Place card above the player
            hands.computeIfAbsent(player, k -> new ArrayList<>()).add(card);
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
        while (aces > 0 && score < 11) {
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
                    hands.put(player, new ArrayList<>());
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
}
