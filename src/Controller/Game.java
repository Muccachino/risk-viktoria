package Controller;

import Config.WinConditionDetails;
import Model.*;
import View.GUI;
import View.UseCards;

import javax.swing.*;
import java.util.*;

public class Game {
    private final Board board;
    private final Player[] players;

    private int currentPlayerIndex;
    private final Random random;
    public String boardChoice;
    GUI gui;

    private int remainingAttackers = 0;

    public Game(Player[] players, String boardChoice) {
        this.boardChoice = boardChoice;
        this.players = players;
        this.board = new Board(boardChoice);
        this.currentPlayerIndex = 0;
        this.random = new Random();
        initializeGame();
    }

    private void initializeGame() {
        addPlayerMissions();
        gui = new GUI(this);
        javax.swing.SwingUtilities.invokeLater(gui::createAndShowGUI);
    }


    public void distributeArmies(Territory territory) {
        territory.addArmies(1);
        getCurrentPlayer().removeArmies(1);
    }

    public Board getBoard() {
        return board;
    }

    public Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    public void addPlayerMissions() {

        for (Player player : this.players) {
            int missionNum = random.nextInt(3);
            String missionName = WinConditionDetails.winConditionNames[missionNum];
            switch (this.boardChoice) {
                case "board1":
                    player.addWinCondition(
                        this,
                        missionName,
                        WinConditionDetails.winConditionDescriptions1[missionNum],
                        WinConditionDetails.winConditionContinents1[random.nextInt(4)]
                ); break;
                case "board2":
                    player.addWinCondition(
                            this,
                            missionName,
                            WinConditionDetails.winConditionDescriptions2[missionNum],
                            WinConditionDetails.winConditionContinents2[random.nextInt(4)]
                    ); break;
                case "board3":
                    player.addWinCondition(
                            this,
                            missionName,
                            WinConditionDetails.winConditionDescriptions3[missionNum],
                            WinConditionDetails.winConditionContinents3[random.nextInt(6)]
                    ); break;
            }
            System.out.println(player.getWinCondition().getDescription());
        }
    }

    public boolean allTerritoriesChosen(int territoriesChosen) {
        switch (this.boardChoice) {
            case "board1":
                return territoriesChosen == 24;
            case "board2":
                if(this.players.length == 3) {
                    return territoriesChosen == 33;
                } else if (this.players.length == 4) {
                    return territoriesChosen == 32;
                } else {
                    return territoriesChosen == 34;
                }
            case "board3":
                if(this.players.length == 2 || this.players.length == 4) {
                    return territoriesChosen == 40;
                } else {
                    return territoriesChosen == 42;
                }
        }
        return false;
    }

    public void nextTurn(JFrame parent) {
        setCurrentPlayer();
        reinforcePhase();
        gui.isInitialDistribution = true;
        gui.isReinforcing = true;
        gui.enableButtons(false);
        gui.updateBoard();
        JOptionPane.showMessageDialog(parent, getCurrentPlayer().getName() + " receives " + getCurrentPlayer().getArmyCount() +
                " armies for owned territories and continents.\n" +
                "Please distribute the new armies to your territories.");

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
        int reinforcements = Math.max(3, (int)Math.floor((double) currentPlayer.getTerritories().size() / 3));

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
        JOptionPane.showMessageDialog(null, currentPlayer.getName() + ", please set your bonus armies");
        System.out.println(currentPlayer.getName() + " exchanges cards for " + bonus + " armies.");
    }

    public int[] rollDice(int numDice, boolean neutralTerritory) {
        int[] dice = new int[numDice];
        for (int i = 0; i < numDice; i++) {
            if (neutralTerritory) {
                dice[i] = random.nextInt(2) + 1;
            } else {
                dice[i] = random.nextInt(6) + 1;
            }
        }
        return dice;
    }

    public String rollDiceResult(Territory selectedFrom, Territory selectedTo, int attackArmies) {
        if (selectedFrom == null || selectedTo == null) {
            System.out.println("Fehler: Kein Angriff ausgewählt.");
        }
        int defendDiceCount;
        int[] defendDice;
        if(selectedTo != null && selectedTo.getOwner() != null) {
            defendDiceCount = Math.min(2, selectedTo.getArmyCount());
            defendDice = rollDice(defendDiceCount, false);
        } else {
            defendDiceCount = 1;
            defendDice = rollDice(defendDiceCount, true);
        }

        int[] attackDice = rollDice(attackArmies, false);

        return compareDiceResults(attackDice, defendDice, selectedFrom, selectedTo);
    }

    public String compareDiceResults (int[] attackDice, int[] defendDice, Territory selectedFrom, Territory selectedTo) {
        if (attackDice == null || defendDice == null) {
            System.out.println("Fehler: Keine Würfelzahl vorhanden.");
        }
        assert attackDice != null;
        Arrays.sort(attackDice);
        assert defendDice != null;
        Arrays.sort(defendDice);

        StringBuilder result = new StringBuilder();
        result.append("Attacker's dice: ").append(Arrays.toString(reverseArray(attackDice))).append("\n");
        result.append("Defender's dice: ").append(Arrays.toString(reverseArray(defendDice))).append("\n");

        int minComparisons = Math.min(attackDice.length, defendDice.length);
        int attackerLosses = 0;
        int defenderLosses = 0;

        for (int i = 0; i < minComparisons; i++) {
            if (attackDice[i] > defendDice[i]) {
                defenderLosses++;
            } else {
                attackerLosses++;
            }
        }

        result.append("Attacker loses ").append(attackerLosses).append(" army/armies.\n");
        result.append("Defender loses ").append(defenderLosses).append(" army/armies.\n");

        selectedFrom.removeArmies(attackerLosses);
        selectedTo.removeArmies(defenderLosses);
        remainingAttackers = attackDice.length - attackerLosses;

        return result.toString();
    }

    private int[] reverseArray(int[] array) {
        int[] reversed = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reversed[i] = array[array.length - 1 - i];
        }
        return reversed;
    }

    public void handleTerritoriesAfterBattle(Territory attackingTerritory, Territory conqueredTerritory, JFrame parent) {
        if(conqueredTerritory.getArmyCount() == 0) {
            if(conqueredTerritory.getOwner() != null) {
                conqueredTerritory.getOwner().removeTerritory(conqueredTerritory);
            }
            conqueredTerritory.setOwner(getCurrentPlayer());
            attackingTerritory.removeArmies(remainingAttackers);
            conqueredTerritory.addArmies(remainingAttackers);
            getCurrentPlayer().addTerritory(conqueredTerritory);
            getCurrentPlayer().addCard(getRandomCard());
            JOptionPane.showMessageDialog(parent, getCurrentPlayer().getName() + " conquered " + conqueredTerritory.getBoardName() + "!");
        }
        if (attackingTerritory.getArmyCount() == 0) {
            attackingTerritory.getOwner().removeTerritory(attackingTerritory);
            attackingTerritory.setOwner(null);
        }
    }

    public void fortifyTerritory(Territory from, Territory to, int armiesToMove) {
        from.removeArmies(armiesToMove);
        to.addArmies(armiesToMove);

        if(from.getArmyCount() == 0) {
            getCurrentPlayer().removeTerritory(from);
            from.setOwner(null);
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

    public Card getRandomCard() {
        int randomNum = random.nextInt(3) + 1;
        switch (randomNum) {
            case 2: return new Card("Cavalry");
            case 3: return new Card("Artillery");
            default: return new Card("Infantry");
        }
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
        Player winner = getCurrentPlayer();
        if (isGameOver()) {
            JOptionPane.showMessageDialog(null, "Game Over! " + winner.getName() + " wins!");
        }
        if(winner.getWinCondition().checkWinCondition()){
            JOptionPane.showMessageDialog(null, "Game Over! " + winner.getName() + " wins by finishing the mission!");
        }
    }
}
