package Controller;

import Model.Board;
import Model.Card;
import Model.Continent;
import Model.Player;
import Model.Territory;
import View.GUI;
import View.UseCards;

import javax.swing.*;
import java.util.*;

public class Game {
    private final Board board;
    private final Player[] players;

    private int currentPlayerIndex;
    private final Random random;
    private final Map<Player, List<Card>> playerCards;
    private boolean isDistributing;
    private int armiesToDistribute;
    public String boardChoice;
    GUI gui;

    public Game(Player[] players, String boardChoice) {
        this.boardChoice = boardChoice;
        this.players = players;
        this.board = new Board(boardChoice);
        this.currentPlayerIndex = 0;
        this.random = new Random();
        this.playerCards = new HashMap<>();
        this.isDistributing = false;
        this.armiesToDistribute = 16;
        initializeGame();
    }

    private void initializeGame() {
        for (Player player : players) {
            playerCards.put(player, new ArrayList<>());
        }
        List<Territory> allTerritories = new ArrayList<>(board.getTerritories().values());
        Collections.shuffle(allTerritories);

        for (int i = 0; i < allTerritories.size(); i++) {
            Player player = players[i % players.length];
            Territory territory = allTerritories.get(i);
            territory.setOwner(player);
            territory.addArmies(1);
            player.addTerritory(territory);
            //System.out.println("Territory " + territory.getName() + " assigned to " + player.getName());
        }

        // New Card Types added
        //TODO: Switch from deck of cards to always random card, to avoid running out of cards
        List<Card> allCards = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            allCards.add(new Card("Infantry"));
            allCards.add(new Card("Cavalry"));
            allCards.add(new Card("Artillery"));
        }
        Collections.shuffle(allCards);

        gui = new GUI(this);
        javax.swing.SwingUtilities.invokeLater(gui::createAndShowGUI);
    }

    public Player[] getPlayers() {
        return players;
    }

    public boolean isDistributing() {
        return isDistributing;
    }

    public void startDistributingArmies() {
        isDistributing = true;
        armiesToDistribute = 16;
    }

    public void distributeArmies(Territory territory) {
        territory.addArmies(1);
        getCurrentPlayer().removeArmies(1);
    }
    public boolean distributeArmy(Territory territory, int armies) {
        if (!isDistributing || armies <= 0 || armies > armiesToDistribute) {
            System.out.println("is not distributing" + armies);
            return false;
        }

        Player currentPlayer = getCurrentPlayer();
        if (territory.getOwner() != currentPlayer) {
            System.out.println("gehört ihm nicht" + territory.getOwner().getName() + currentPlayer.getName());
            return false;
        }
        territory.addArmies(1);
        armiesToDistribute -= armies;

        if (armiesToDistribute <= 0) {
            isDistributing = false;
            return true;
        }

        return true;
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    public void nextTurn() {
        if (!isDistributing) {
            reinforcePhase();
            //cardReinforcementPhase();
            attackPhase();
            fortifyPhase();
            setCurrentPlayer();
            checkGameOver();
        }
    }
    public void setCurrentPlayer(){
    currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
}

    public boolean checkWinCondition() {
        for (Territory territory : board.getTerritories().values()) {
            if (territory.getOwner() != getCurrentPlayer()) {
                return false;
            }
        }
        return true;
    }

    private void reinforcePhase() {
        Player currentPlayer = getCurrentPlayer();
        int reinforcements = Math.max(3, currentPlayer.getTerritories().size() / 3);

        for (Continent continent : board.getContinents()) {
            if (currentPlayer.controlsContinent(continent)) {
                reinforcements += 3;
            }
        }

        currentPlayer.addArmies(reinforcements);
        System.out.println(currentPlayer.getName() + " receives " + reinforcements + " reinforcement armies.");
    }

    public void cardReinforcementPhase(String type) {
        Player currentPlayer = getCurrentPlayer();
        List<Card> cards = playerCards.get(currentPlayer);
        int bonus = 0;
        switch (type) {
            case "Infantry":
                bonus = 4;
                currentPlayer.removeThreeSameCards(type);
                break;
            case "Cavalry":
                bonus = 5;
                currentPlayer.removeThreeSameCards(type);
                break;
            case "Artillery":
                bonus = 6;
                currentPlayer.removeThreeSameCards(type);
                break;
            case "oneOfEach":
                bonus = 5;
                currentPlayer.removeOneOfEachCards();
        }

        currentPlayer.addArmies(bonus);
        gui.updateBoard();
        gui.isSettingCardArmies = true;
        gui.enableButtons(false);
        JOptionPane.showMessageDialog(null, currentPlayer + ", please set your bonus armies");
        System.out.println(currentPlayer.getName() + " exchanges cards for " + bonus + " armies.");
    }

    private void attackPhase() {
        Player currentPlayer = getCurrentPlayer();

        boolean territoryConquered = false;
        for (Territory from : currentPlayer.getTerritories()) {
            for (Territory to : from.getAdjacentTerritories()) {
                if (to.getOwner() != currentPlayer) {
                    int attackArmies = Math.min(3, from.getArmyCount() - 1);
                    int defendArmies = Math.min(2, to.getArmyCount());
                    attackTerritory(from, to, attackArmies, defendArmies);
                    territoryConquered = true;
                    break;
                }
            }
            if (territoryConquered) break;
        }
        if (territoryConquered) {
            playerCards.get(currentPlayer).add(new Card("Infantry"));
            if (playerCards.get(currentPlayer).size() > 5) {
                gui.useCards();
                JOptionPane.showMessageDialog(null, "You have more than 5 cards and have to use them.");
            }
        }
    }

    private void fortifyPhase() {
        Player currentPlayer = getCurrentPlayer();

        for (Territory from : currentPlayer.getTerritories()) {
            for (Territory to : from.getAdjacentTerritories()) {
                if (to.getOwner() == currentPlayer && from != to) {
                    int armiesToMove = Math.min(3, from.getArmyCount() - 1);
                    fortifyTerritory(from, to, armiesToMove);
                    System.out.println(currentPlayer.getName() + " moves " + armiesToMove + " armies from " + from.getName() + " to " + to.getName());
                    return;
                }
            }
        }
    }

    public void attackTerritory(Territory from, Territory to, int attackArmies, int defendArmies) {
        if (from.getOwner() == getCurrentPlayer() && to.getOwner() != getCurrentPlayer()) {
            int[] attackDice = rollDice(attackArmies);
            int[] defendDice = rollDice(defendArmies);

            Arrays.sort(attackDice);
            Arrays.sort(defendDice);

            int losses = 0;
            for (int i = 0; i < Math.min(attackDice.length, defendDice.length); i++) {
                if (attackDice[attackDice.length - 1 - i] > defendDice[defendDice.length - 1 - i]) {
                    to.removeArmies(1);
                } else {
                    from.removeArmies(1);
                    losses++;
                }
            }

            if (to.getArmyCount() == 0) {
                to.setOwner(from.getOwner());
                to.addArmies(attackArmies - losses);
                from.removeArmies(attackArmies - losses);
                System.out.println(from.getOwner().getName() + " conquered " + to.getName());
            }
        } else {
            System.out.println("Invalid attack!");
        }
    }

    public int[] rollDice(int numDice) {
        int[] dice = new int[numDice];
        for (int i = 0; i < numDice; i++) {
            dice[i] = random.nextInt(6) + 1;
        }
        return dice;
    }

    public void fortifyTerritory(Territory from, Territory to, int armiesToMove) {
        if (from.getOwner() == getCurrentPlayer() && to.getOwner() == getCurrentPlayer()) {
            from.removeArmies(armiesToMove);
            to.addArmies(armiesToMove);
        }
    }

    public boolean initialArmiesDistributed() {
        for (Player player : this.players) {
            if(player.getArmyCount() != 0) {
                return false;
            }
        }
        return true;
    }

    public void openUseCardsWindow(JFrame parent) {
        new UseCards(parent, this).createUseCardsDialog();
    }

    public boolean checkThreeCardCombination(String type) {
        Player currentPlayer = getCurrentPlayer();
        List<Card> allCards = currentPlayer.getCards();
        int count = 0;
        for (Card card : allCards) {
            if (card.getType().equals(type)) {
                count++;
            }
        }
        return count >= 3;
    }

    public boolean checkOneOfEachCard() {
        Player currentPlayer = getCurrentPlayer();
        List<Card> allCards = currentPlayer.getCards();
        int infantry = 0;
        int cavalry = 0;
        int artillery = 0;
        for (Card card : allCards) {
            switch (card.getType()) {
                case "Infantry": infantry++; break;
                case "Cavalry": cavalry++; break;
                case "Artillery": artillery++; break;
            }
        }
        return (infantry > 0 && cavalry > 0 && artillery > 0);
    }

    public boolean isGameOver() {
        Set<Player> activePlayers = new HashSet<>();
        for (Player player : players) {
            if (!player.getTerritories().isEmpty()) {
                activePlayers.add(player);
            }
        }
        return activePlayers.size() == 1;
    }

    public void checkGameOver() {
        if (isGameOver()) {
            Player winner = getCurrentPlayer();
            System.out.println("Controller.Game Over! " + winner.getName() + " wins!");
        }
    }
}
