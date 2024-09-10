package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.*;
import java.util.List;

import Config.BoardCoordinates;
import Config.Helper;
import Controller.Game;
import Model.Territory;
import Model.Player;
import Model.Card;

public class GUI {
    private final Game game;
    private final JFrame frame;
    private final JPanel boardPanel;
    private final JLabel statusLabel;
    private Territory selectedFrom;
    private Territory selectedTo;
    private boolean isFortifying;
    public boolean isTerritoryChoice = true;
    public boolean isInitialDistribution = false;
    public boolean isReinforcing = false;
    public boolean isSettingCardArmies = false;
    int territoriesChosen = 0;

    JButton nextTurnButton;
    JButton fortifyButton;
    JButton useCardButton;
    JButton missionButton;

    public Map<String, JPanel> allTerritoryPanels = new HashMap<>();

    public GUI(Game game) {
        this.game = game;
        this.frame = new JFrame("Risiko");
        this.boardPanel = new JPanel();
        this.statusLabel = new JLabel("Current Player: ");
        this.selectedFrom = null;
        this.selectedTo = null;
        this.isFortifying = false;
    }

    public void createAndShowGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1300, 800);
        frame.setLayout(new BorderLayout());

        boardPanel.setLayout(new GridLayout(4, 6));
        updateBoard();

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);


        nextTurnButton = new JButton("Next Turn");
        nextTurnButton.addActionListener(e -> {
            game.nextTurn(frame);
            System.out.println("Current Player for this turn: " + game.getCurrentPlayer().getName());
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(nextTurnButton);

        // Der Button deaktiviert nun die anderen Button, sodass man beim Verschieben der Einheiten nichts anderes machen kann,
        // bis man den Button erneut gedrückt hat.
        fortifyButton = new JButton("Fortify");
        fortifyButton.addActionListener(e -> {
            isFortifying = !isFortifying;
            fortifyButton.setText(isFortifying ? "Done" : "Fortify");
            nextTurnButton.setEnabled(!isFortifying);
            useCardButton.setEnabled(!isFortifying);
            if(isFortifying) {
                statusLabel.setText("Current Player: " + game.getCurrentPlayer().getName() + " (Sending Territory: not selected | Receiving Territory: not selected)");
            }else {
                setDefaultStatusLabel();
            }
        });

        controlPanel.add(fortifyButton);

        // Button öffnet jetzt das Fenster zum Auswählen der Kartenkombinationen.
        useCardButton = new JButton("Use Cards");
        useCardButton.addActionListener(e -> useCards());

        controlPanel.add(useCardButton);

        // Button hinzugefügt, der einem die Mission des aktuellen Spielers anzeigt
        if(game.missionsActive) {
            missionButton = new JButton("Mission");
            missionButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, game.getCurrentPlayer().getWinCondition().getDescription()));

            controlPanel.add(missionButton);
        }

        enableButtons(false);
        frame.add(controlPanel, BorderLayout.NORTH);
        frame.setVisible(true);

        JOptionPane.showMessageDialog(frame, game.getCurrentPlayer().getName() + ", please choose a country to set your army.", "Territory Choice", JOptionPane.INFORMATION_MESSAGE);
    }

    // Funktion so weit abgeändert, dass sie jetzt mit Koordinaten funktioniert und dadurch halbwegs vereinheitlicht ist.
    public void updateBoard() {
        boardPanel.removeAll();
        boardPanel.setBackground(new Color(153,204,255));
        GridBagLayout boardLayout = new GridBagLayout();
        GridBagConstraints boardConstraints = new GridBagConstraints();

        Map<String, Territory> allTerritories = game.getBoard().getTerritories();

        if (game.boardChoice.equals("board1")) {

            for (Territory territory : allTerritories.values()) {
                JPanel territoryPanel = createTerritoryPanel(territory);
                boardPanel.add(territoryPanel);
            }
        }
        if (game.boardChoice.equals("board2")) {

            boardPanel.setLayout(boardLayout);
            boardLayout.columnWidths = new int[] {50,50,50,50,50,50,50,50};
            boardLayout.rowHeights = new int[] {50,50,50,50,50,50,50,50};

            for (int i = 0; i < BoardCoordinates.allCountryCoordinates2.length; i++) {
                Territory territory = Helper.getTerritoryById(allTerritories, i + 1);
                JPanel territoryPanel = createTerritoryPanel(territory);

                boardPanel.add(territoryPanel, Helper.buildBoardConstraints(boardConstraints,
                        BoardCoordinates.allCountryCoordinates2[i][0],
                        BoardCoordinates.allCountryCoordinates2[i][1],
                        BoardCoordinates.allCountryCoordinates2[i][2],
                        BoardCoordinates.allCountryCoordinates2[i][3]));
            }
        }
        if(game.boardChoice.equals("board3")) {

            boardPanel.setLayout(boardLayout);
            boardLayout.columnWidths = new int[] {80,80,80,80,80,80,80,80,80,80,80,80};
            boardLayout.rowHeights = new int[] {80,80,80,80,80,80,80,80};

            for (int i = 0; i < BoardCoordinates.allCountryCoordinates3.length; i++) {
                Territory territory = Helper.getTerritoryById(allTerritories, i + 1);
                JPanel territoryPanel = createTerritoryPanel(territory);

                boardPanel.add(territoryPanel, Helper.buildBoardConstraints(boardConstraints,
                        BoardCoordinates.allCountryCoordinates3[i][0],
                        BoardCoordinates.allCountryCoordinates3[i][1],
                        BoardCoordinates.allCountryCoordinates3[i][2],
                        BoardCoordinates.allCountryCoordinates3[i][3]));
            }
        }

        setDefaultStatusLabel();

        boardPanel.revalidate();
        boardPanel.repaint();
    }

    // Aus der updateBoard Funktion ausgelagert, um Wiederholungen zu vermeiden.
    public JPanel createTerritoryPanel(Territory territory) {
        JPanel territoryPanel = new BoardCreator(game).createBoardPanels(territory, game.getCurrentPlayer());
        territoryPanel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isLeftMouseButton(e)) {
                    handleTerritoryClick(territory);
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    highlightNeighbors(territory, true);
                }
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    highlightNeighbors(territory, false);
                }
            }
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        allTerritoryPanels.put(territory.getName(), territoryPanel);
        return territoryPanel;
    }


    private void handleTerritoryClick(Territory clickedTerritory) {
        if (clickedTerritory == null) {
            JOptionPane.showMessageDialog(frame, "Fehler: Das ausgewählte Territorium existiert nicht.");
            return;
        }
        // Abschnitt hinzugefügt, welcher aktiv ist, wenn am Anfang die Territorien ausgewählt werden.
        if(isTerritoryChoice) {
            if (clickedTerritory.getOwner() == null) {
                clickedTerritory.setOwner(game.getCurrentPlayer());
                clickedTerritory.addArmies(1);
                game.getCurrentPlayer().removeArmies(1);
                game.getCurrentPlayer().addTerritory(clickedTerritory);
                game.setCurrentPlayer();
                updateBoard();
                territoriesChosen++;
            }
            else {
                JOptionPane.showMessageDialog(frame, "Please choose an unclaimed country to set your army.", "Territory Choice", JOptionPane.INFORMATION_MESSAGE);
                return;
            }

            if (!game.allTerritoriesChosen(territoriesChosen)) {
                JOptionPane.showMessageDialog(frame, game.getCurrentPlayer().getName() + ", please choose a country to set your army.", "Territory Choice", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                isTerritoryChoice = false;
                if(!game.initialArmiesDistributed()) {
                    isInitialDistribution = true;
                    JOptionPane.showMessageDialog(frame, "Territories chosen.\nPlace your remaining armies.");
                }
                else {
                    JOptionPane.showMessageDialog(frame, "Distribution done.", "Distribution", JOptionPane.INFORMATION_MESSAGE);
                    enableButtons(true);
                }
            }
            return;
        }

        // Abschnitt abgeändert, sodass dieser am Anfang des Spiels automatisch läuft und nicht erst nach dem Klick eines Buttons.
        // Außerdem wird nicht mehr eine fixe Armeezahl verteilt (was nicht richtig funktioniert hat), sondern eine anhand der Spielerarmeen.
        if(isInitialDistribution) {
            if(game.getCurrentPlayer() == clickedTerritory.getOwner() || clickedTerritory.getOwner() == null) {
                game.distributeArmies(clickedTerritory);
                if(!isReinforcing) {
                    game.setCurrentPlayer();
                }
                updateBoard();
            }
            else {
                JOptionPane.showMessageDialog(frame, "Please choose a country you own", "Distribution", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(!game.initialArmiesDistributed()) {
                if(!isReinforcing) {
                    JOptionPane.showMessageDialog(frame, game.getCurrentPlayer().getName() + ", please choose a country to set your army.", "Distribution", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else {
                isInitialDistribution = false;
                isReinforcing = false;
                JOptionPane.showMessageDialog(frame, "Distribution done.", "Distribution", JOptionPane.INFORMATION_MESSAGE);
                enableButtons(true);
            }
            return;
        }

        // Abschnitt hinzugefügt, dass man Kartenarmeen mittels Klick verteilen kann und nicht mehr über ein Dropdown Fenster.
        if(isSettingCardArmies) {
            if (game.getCurrentPlayer() == clickedTerritory.getOwner()) {
                game.distributeArmies(clickedTerritory);
                updateBoard();
            }
            else {
                JOptionPane.showMessageDialog(frame, "Please choose a country you own", "Bonus Armies", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(game.getCurrentPlayer().getArmyCount() == 0) {
                isSettingCardArmies = false;
                JOptionPane.showMessageDialog(frame, "All bonus armies set!", "Bonus Armies", JOptionPane.INFORMATION_MESSAGE);
                enableButtons(true);
                game.checkGameOver();
                return;
            }
            return;
        }

        // Man kann das erste Territorium wieder abwählen,
        // das Statusfenster zeigt in dieser Phase die ausgewählten Territorien an,
        // Territorien können vollständig geleert und somit neutral werden
        if (isFortifying) {
            if (selectedFrom == null) {
                if (clickedTerritory.getOwner() == game.getCurrentPlayer() && clickedTerritory.getArmyCount() > 0) {
                    selectedFrom = clickedTerritory;
                    JOptionPane.showMessageDialog(frame, "Sending Territory selected: " + selectedFrom.getBoardName() + ". \nNow select the receiving territory.");
                    statusLabel.setText("Current Player: " + game.getCurrentPlayer().getName() + " (Sending Territory: " + selectedFrom.getBoardName() + " | Receiving Territory: not selected)");
                } else {
                    JOptionPane.showMessageDialog(frame, "You must select a territory that you own.");
                }
                return;
            }
            if (clickedTerritory == selectedFrom) {
                selectedFrom = null;
                JOptionPane.showMessageDialog(frame, "Sending Territory unselected");
                statusLabel.setText("Current Player: " + game.getCurrentPlayer().getName() + " (Sending Territory: not selected | Receiving Territory: not selected)");
            }
            if (selectedFrom != null && selectedTo == null && selectedFrom != clickedTerritory && clickedTerritory.getOwner() == game.getCurrentPlayer()) {
                if (!selectedFrom.getAdjacentTerritories().contains(clickedTerritory)) {
                    JOptionPane.showMessageDialog(frame, "You can only send armies to adjacent territories.");
                    return;
                }
                selectedTo = clickedTerritory;
                statusLabel.setText("Current Player: " + game.getCurrentPlayer().getName() + " (Sending Territory: " + selectedFrom.getBoardName() + " | Receiving Territory: " + selectedTo.getBoardName() + ")");

                int armiesToMove = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter number of armies to move (1-" + selectedFrom.getArmyCount() + "):"));
                if (armiesToMove > 0 && armiesToMove <= selectedFrom.getArmyCount()) {
                    game.fortifyTerritory(selectedFrom, selectedTo, armiesToMove);
                    selectedFrom = null;
                    selectedTo = null;
                    updateBoard();
                    game.checkGameOver();
                    statusLabel.setText("Current Player: " + game.getCurrentPlayer().getName() + " (Sending Territory: not selected | Receiving Territory: not selected)");
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid number of armies. Must be between 1 and" + (selectedFrom.getArmyCount() - 1) + " and not more than available.");
                }
            }
        }

        // Die Änderungen zur Fortification gelten auch beim Angriff.
        if (!isFortifying){
            if (selectedFrom == null) {
                if (clickedTerritory.getOwner() == game.getCurrentPlayer() && clickedTerritory.getArmyCount() >= 1) {
                    selectedFrom = clickedTerritory;
                    JOptionPane.showMessageDialog(frame, "Territory selected for attack: " + selectedFrom.getBoardName() + ". Now select the target territory.");
                    statusLabel.setText("Current Player: " + game.getCurrentPlayer().getName() + " (Attacking Territory: " + selectedFrom.getBoardName() + " | Defending Territory: not selected)");
                    enableButtons(false);
                } else {
                    JOptionPane.showMessageDialog(frame, "You must select a territory that you own.");
                }
                return;
            }
            if (clickedTerritory == selectedFrom) {
                selectedFrom = null;
                JOptionPane.showMessageDialog(frame, " Attacking Territory unselected");
                setDefaultStatusLabel();
                enableButtons(true);
            }
            if(selectedFrom != null && selectedTo == null && selectedFrom != clickedTerritory) {
                if (!selectedFrom.getAdjacentTerritories().contains(clickedTerritory)) {
                    JOptionPane.showMessageDialog(frame, "You can only attack adjacent territories.");
                    return;
                }
                if (clickedTerritory.getOwner() == game.getCurrentPlayer()) {
                    JOptionPane.showMessageDialog(frame, "You cannot attack your own territory.");
                    return;
                }
                selectedTo = clickedTerritory;
                statusLabel.setText("Current Player: " + game.getCurrentPlayer().getName() + " (Attacking Territory: " + selectedFrom.getBoardName() + " | Defending Territory: " + selectedTo.getBoardName() + ")");

                int attackArmies = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter number of armies to attack with (1-3):"));
                if (attackArmies >= 1 && attackArmies <= 3 && attackArmies <= selectedFrom.getArmyCount()) {

                    handleAttackPhase(attackArmies);
                    game.checkGameOver();

                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid number of armies. Must be between 1 and 3 and not more than available.");
                }
            }
        }
    }

    // Öffnet das Kartenfenster
    public void useCards() {
        Player currentPlayer = game.getCurrentPlayer();
        List<Card> cards = currentPlayer.getCards();

        if (cards.size() < 3) {
            JOptionPane.showMessageDialog(frame, "You need at least 3 cards to trade for armies.");
            return;
        }

        game.openUseCardsWindow(frame);
    }

    private void handleAttackPhase(int attackArmies) {
        String diceResult = game.rollDiceResult(selectedFrom, selectedTo, attackArmies);

        JFrame diceFrame = new JFrame("Roll Dice");
        diceFrame.setSize(300, 200);
        diceFrame.setLayout(new BorderLayout());

        JTextArea diceResultArea = new JTextArea(diceResult);
        diceResultArea.setEditable(false);
        diceFrame.add(new JScrollPane(diceResultArea), BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> {
            if (game.isGameOver()) {
                JOptionPane.showMessageDialog(frame, "Player " + game.getCurrentPlayer().getName() + " wins!");
                System.exit(0);
            }
            game.handleTerritoriesAfterBattle(selectedFrom, selectedTo, frame);
            updateBoard();
            enableButtons(true);
            selectedFrom = null;
            selectedTo = null;
            diceFrame.dispose();
        });
        diceFrame.add(okButton, BorderLayout.SOUTH);
        diceFrame.setVisible(true);
    }

    // Funktion hinzugefügt, welche die Buttons aktiviert oder deaktiviert, während einer Phase
    public void enableButtons(boolean enable) {
        nextTurnButton.setEnabled(enable);
        fortifyButton.setEnabled(enable);
        useCardButton.setEnabled(enable);
        if(game.missionsActive){
            missionButton.setEnabled(enable);
        }
    }

    // Funktion hinzugefügt um die allgemeine Statusleiste nach einem Kampf oder Verschieben von Armeen wiederherzustellen.
    public void setDefaultStatusLabel() {
        statusLabel.setText("Current Player: " + game.getCurrentPlayer().getName() +
                " | Territories: " + game.getCurrentPlayer().getTerritories().size() +
                " | Armies: " + game.getCurrentPlayer().getArmyCount() +
                " | Cards Total: " + game.getCurrentPlayer().getCards().size() +
                " | Infantry: " + game.getCurrentPlayer().getTypedCards("Infantry").size() +
                " | Cavalry: " + game.getCurrentPlayer().getTypedCards("Cavalry").size() +
                " | Artillery: " + game.getCurrentPlayer().getTypedCards("Artillery").size());
    }

    // Funktion hinzugefügt, welche beim Rechtsklick auf ein Territorium die Nachbarn hervorhebt.
    public void highlightNeighbors(Territory selectedTerritory, boolean active) {
        List<Territory> allNeighbors = game.getBoard().getTerritory(selectedTerritory.getName()).getAdjacentTerritories();
        List<JPanel> allNeighborPanels = new ArrayList<>();

        for (Territory neighbor : allNeighbors) {
            JPanel neighborPanel = allTerritoryPanels.get(neighbor.getName());
            if(active) {
                allNeighborPanels.add(neighborPanel);
            }
            else {
                neighborPanel.setBackground(neighbor.getOwner().getPlayerColor());
            }
        }

        if(active) {
            for (JPanel neighborPanel : allNeighborPanels) {
                neighborPanel.setBackground(new Color(255, 122, 113));
            }
        }
    }
}
