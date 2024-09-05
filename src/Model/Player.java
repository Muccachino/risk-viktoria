package Model;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Player {
    private final String name;
    private final List<Territory> territories;
    private int armyCount;
    private List<Card> cards;
    private final Color playerColor;

    public Player(String name, Color playerColor) {
        this.name = name;
        this.territories = new ArrayList<>();
        this.armyCount = 1;
        this.cards = new ArrayList<>();
        this.playerColor = playerColor;
    }

    public String getName() {
        return name;
    }

    public Color getPlayerColor() {
        return playerColor;
    }

    public List<Territory> getTerritories() {
        return territories;
    }

    public void addTerritory(Territory territory) {
        this.territories.add(territory);
        this.territories.sort((t1, t2) -> t1.getTerritoryId() - t2.getTerritoryId());
    }

    public void addArmies(int count) {
        this.armyCount += count;
    }

    public void removeArmies(int count) {
        this.armyCount -= count;
    }

    public int getArmyCount() {
        return armyCount;
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }

    public void removeThreeSameCards(String type) {
        int count = 0;

        while(count < 3) {
            for (Card card : this.cards) {
                if (card.getType().equals(type)) {
                    this.cards.remove(card);
                    break;
                }
            }
            count++;
        }
    }

    public void removeOneOfEachCards() {
        Card infantryRemoved = null;
        Card cavalryRemoved = null;
        Card artilleryRemoved = null;

        while (infantryRemoved == null && cavalryRemoved == null && artilleryRemoved == null) {
            for (Card card : this.cards) {
                if (card.getType().equals("Infantry") && infantryRemoved == null) {
                    infantryRemoved = card;
                }
                if (card.getType().equals("Cavalry") && cavalryRemoved == null) {
                    cavalryRemoved = card;
                }
                if (card.getType().equals("Artillery") && artilleryRemoved == null) {
                    artilleryRemoved = card;
                }
            }
        }
        this.cards.remove(infantryRemoved);
        this.cards.remove(cavalryRemoved);
        this.cards.remove(artilleryRemoved);
    }

    // function added to only get cards of specific type
    public List<Card> getTypedCards(String type) {
        List<Card> chosenCards = new ArrayList<>();
        for (Card card : this.cards) {
            if (card.getType().equals(type)) {
                chosenCards.add(card);
            }
        }
        return chosenCards;
    }

    public List<Card> getCards() {
        return cards;
    }

    public boolean controlsContinent(Continent continent) {
        return new HashSet<>(territories).containsAll(continent.getTerritories());
    }
}
