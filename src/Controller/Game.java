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
    public final boolean missionsActive;
    private int currentPlayerIndex;
    private final Random random;
    public String boardChoice;
    GUI gui;

    private int remainingAttackers = 0;
    public int numOfFortifications = 0;

    public Game(Player[] players, String boardChoice, boolean missionsActive) {
        this.boardChoice = boardChoice;
        this.missionsActive = missionsActive;
        this.players = players;
        this.board = new Board(boardChoice);
        this.currentPlayerIndex = 0;
        this.random = new Random();
        initializeGame();
    }

    // Geändert, sodass hier nur noch die Missionen zugeteilt und die Board View erstellt werden.
    private void initializeGame() {
        if (missionsActive) {
            addPlayerMissions();
        }
        gui = new GUI(this);
        javax.swing.SwingUtilities.invokeLater(gui::createAndShowGUI);
    }

    // Verteilen von Armeen in eine Funktion zusammengefasst.
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

    // Funktion zur Verteilung der Missionen und es wird darauf geachtet, dass Spieler nicht die gleiche Kontinenteroberung bekommen.
    public void addPlayerMissions() {
        List<Integer> randomContinentsNums = new ArrayList<>();

        for (Player player : this.players) {
            int missionNum = random.nextInt(3);
            String missionName = WinConditionDetails.winConditionNames[missionNum];
            int randomContinents = random.nextInt(4);
            switch (this.boardChoice) {
                case "board1":
                    while (randomContinentsNums.contains(randomContinents)){
                        randomContinents = random.nextInt(4);
                    }
                    randomContinentsNums.add(randomContinents);
                    player.addWinCondition(
                        this,
                        missionName,
                        WinConditionDetails.winConditionDescriptions1[missionNum],
                        WinConditionDetails.winConditionContinents1[randomContinents]
                    );
                    break;
                case "board2":
                    while (randomContinentsNums.contains(randomContinents)){
                        randomContinents = random.nextInt(4);
                    }
                    randomContinentsNums.add(randomContinents);
                    player.addWinCondition(
                            this,
                            missionName,
                            WinConditionDetails.winConditionDescriptions2[missionNum],
                            WinConditionDetails.winConditionContinents2[randomContinents]
                    ); break;
                case "board3":
                    while (randomContinentsNums.contains(randomContinents)){
                        randomContinents = random.nextInt(6);
                    }
                    randomContinentsNums.add(randomContinents);
                    player.addWinCondition(
                            this,
                            missionName,
                            WinConditionDetails.winConditionDescriptions3[missionNum],
                            WinConditionDetails.winConditionContinents3[randomContinents]
                    ); break;
            }

            System.out.println(player.getWinCondition().getDescription());
        }
    }

    // Check eingebaut, der je nach Board und Spieleranzahl prüft, ob eine gleichmäßige Zahl an Territorien
    // zu Spielbeginn ausgewählt wurden.
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

    // Hier habe ich ein wenig den Inhalt geändert bzw angepasst, da Funktionen aufgerufen wurden, die keinen Sinn ergaben.
    public void nextTurn(JFrame parent) {
        setCurrentPlayer();
        reinforcePhase();
        gui.isInitialDistribution = true;
        gui.isReinforcing = true;
        numOfFortifications = 0;
        gui.enableButtons(false);
        gui.updateBoard();
        JOptionPane.showMessageDialog(parent, getCurrentPlayer().getName() + " receives " + getCurrentPlayer().getArmyCount() +
                " armies for owned territories and continents.\n" +
                "Please distribute the new armies to your territories.");

    }

    public void setCurrentPlayer(){
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
    }

    private void reinforcePhase() {
        Player currentPlayer = getCurrentPlayer();
        // Kleine Änderung, dass nach unten abgerundet wird.
        int reinforcements = Math.max(3, (int)Math.floor((double) currentPlayer.getTerritories().size() / 3));

        for (Continent continent : board.getContinents()) {
            if (currentPlayer.controlsContinent(continent)) {
                reinforcements += 3;
            }
        }

        currentPlayer.addArmies(reinforcements);
        System.out.println(currentPlayer.getName() + " receives " + reinforcements + " reinforcement armies.");
    }

    // Funktion angepasst, dass alle Kartentypen und Kombinationen möglich sind.
    // Außerdem wird die entsprechende Phase gesetzt und alle Button deaktiviert, damit man zunächst nur die Armeen setzen kann.
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

    // Die nächsten drei Funktionen wurden teils aus der GUI in den Controller verschoben und mit den diversen anderen Attack Funktionen,
    // welche sich teils in Funktionen überschnitten haben, zusammengelegt.
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
        attackDice = reverseArray(attackDice);
        assert defendDice != null;
        Arrays.sort(defendDice);
        defendDice = reverseArray(defendDice);

        StringBuilder result = new StringBuilder();
        result.append("Attacker's dice: ").append(Arrays.toString(attackDice)).append("\n");
        result.append("Defender's dice: ").append(Arrays.toString(defendDice)).append("\n");

        int minComparisons = Math.min(attackDice.length, defendDice.length);
        int attackerLosses = 0;
        int defenderLosses = 0;

        for(int dice : attackDice) {
            System.out.println(dice);
        }

        for(int dice : defendDice) {
            System.out.println(dice);
        }
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

    // Funktion ausgelagert, welche sich um die Territorien nach einem Kampf kümmert und auch das Entstehen neutraler Territorien ermöglicht.
    public void handleTerritoriesAfterBattle(Territory attackingTerritory, Territory conqueredTerritory, JFrame parent) {
        if(conqueredTerritory.getArmyCount() == 0 || conqueredTerritory.getArmyCount() == -1) {
            if(conqueredTerritory.getOwner() != null) {
                conqueredTerritory.getOwner().removeTerritory(conqueredTerritory);
                getCurrentPlayer().addCard(getRandomCard());
            }
            conqueredTerritory.setOwner(getCurrentPlayer());
            attackingTerritory.removeArmies(remainingAttackers);
            if(conqueredTerritory.getArmyCount() == -1) {
                conqueredTerritory.addArmies(remainingAttackers + 1);
            }else {
                conqueredTerritory.addArmies(remainingAttackers);
            }
            getCurrentPlayer().addTerritory(conqueredTerritory);

            JOptionPane.showMessageDialog(parent, getCurrentPlayer().getName() + " conquered " + conqueredTerritory.getBoardName() + "!");
        }
        if (attackingTerritory.getArmyCount() == 0) {
            attackingTerritory.getOwner().removeTerritory(attackingTerritory);
            attackingTerritory.setOwner(null);
        }
    }

    // Fortifiy Phase Funktion wurde gestrichen und diese Funktion so abgeändert, dass neutrale Territorien möglich sind.
    public void fortifyTerritory(Territory from, Territory to, int armiesToMove) {
        from.removeArmies(armiesToMove);
        to.addArmies(armiesToMove);

        if(from.getArmyCount() == 0) {
            getCurrentPlayer().removeTerritory(from);
            from.setOwner(null);
        }
        numOfFortifications++;
    }

    // Funktion eingefügt, welche am Anfang des Spiels prüft, ob alle Spieler ihre Startarmeen gesetzt haben.
    public boolean initialArmiesDistributed() {
        for (Player player : this.players) {
            if(player.getArmyCount() != 0) {
                return false;
            }
        }
        return true;
    }

    // Funktion eingefügt, die das Cards Window öffnet.
    public void openUseCardsWindow(JFrame parent) {
        new UseCards(parent, this).createUseCardsDialog();
    }

    // Nächste zwei Funktionen wurden eingefügt, um die möglichen Kartenkombinationen im Cards Window korrekt anzuzeigen.
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

    // Funktion hinzugefügt, um den Spielern eine der drei Kartentypen zu geben.
    public Card getRandomCard() {
        int randomNum = random.nextInt(3) + 1;
        switch (randomNum) {
            case 2: return new Card("Cavalry");
            case 3: return new Card("Artillery");
            default: return new Card("Infantry");
        }
    }

    // Eine checkWin Funktion wurde gestrichen und diese beibehalten.
    public boolean isGameOver() {
        Set<Player> activePlayers = new HashSet<>();
        for (Player player : players) {
            if (!player.getTerritories().isEmpty()) {
                activePlayers.add(player);
            }
        }
        return activePlayers.size() == 1;
    }

    // Funktion erweitert, dass sie auch das jeweilige Missionsziel prüft.
    public void checkGameOver() {
        Player winner = getCurrentPlayer();
        if (isGameOver()) {
            JOptionPane.showMessageDialog(null, "Game Over! " + winner.getName() + " wins!");
        }
        if(missionsActive && winner.getWinCondition().checkWinCondition()){
            JOptionPane.showMessageDialog(null, "Game Over! " + winner.getName() + " wins by finishing the mission!");
        }
    }
}
