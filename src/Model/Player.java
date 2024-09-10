package Model;

import Controller.Game;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Player {
    private final String name;
    private final List<Territory> territories;
    private int armyCount;
    private final List<Card> cards;
    private final Color playerColor;
    private WinCondition winCondition;

    // Spielerfarben und Missionen hinzugefügt
    public Player(String name, Color playerColor, int armyCount) {
        this.name = name;
        this.territories = new ArrayList<>();
        this.armyCount = armyCount;
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

    // Entfernen der Territorien nach einem Kampf oder dem Verschieben hinzugefügt
    public void removeTerritory(Territory territory) {
        this.territories.remove(territory);
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

    // Funktionen hinzugefügt, welche sich auf die zusätzlichen Kartentypen beziehen
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

    // Funktionen für Win Condition hinzugefügt
    public void addWinCondition(Game game, String name, String description, String[] continentNames) {
        winCondition = new WinCondition(name, description, this, game, continentNames);
    }

    public WinCondition getWinCondition() {
        return winCondition;
    }

}
