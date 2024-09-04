package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    boolean isInitialDistribution = true;

    JButton nextTurnButton;
    JButton fortifyButton;
    JButton useCardButton;

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
        frame.setSize(1200, 800);
        frame.setLayout(new BorderLayout());

        boardPanel.setLayout(new GridLayout(4, 6));
        updateBoard();

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);

        nextTurnButton = new JButton("Next Turn");
        nextTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (game.isDistributing()) {
                    JOptionPane.showMessageDialog(frame, "Distributing phase is not finished yet.");
                } else {
                    game.nextTurn();
                    System.out.println("Current Player after turn: " + game.getCurrentPlayer().getName());
                    if (game.checkWinCondition()) {
                        JOptionPane.showMessageDialog(frame, "Player " + game.getCurrentPlayer().getName() + " wins!");
                        System.exit(0);
                    }
                    updateBoard();
                }
            }
        });

        JPanel controlPanel = new JPanel();
        nextTurnButton.setEnabled(false);
        controlPanel.add(nextTurnButton);

        fortifyButton = new JButton("Fortify");
        fortifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isFortifying = !isFortifying;
                fortifyButton.setText(isFortifying ? "Attack" : "Fortify");
                statusLabel.setText("Current Player: " + game.getCurrentPlayer().getName() + " (Fortify Mode: " + isFortifying + ")");
            }
        });
        fortifyButton.setEnabled(false);
        controlPanel.add(fortifyButton);


        useCardButton = new JButton("Use Cards");
        useCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                useCards();
            }
        });
        useCardButton.setEnabled(false);
        controlPanel.add(useCardButton);

        frame.add(controlPanel, BorderLayout.NORTH);
        frame.setVisible(true);

        JOptionPane.showMessageDialog(frame, game.getCurrentPlayer().getName() + ", please choose a country to set your army.", "Distribution", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateBoard() {
        boardPanel.removeAll();
        boardPanel.setBackground(new Color(153,204,255));
        GridBagLayout boardLayout = new GridBagLayout();
        GridBagConstraints boardConstraints = new GridBagConstraints();

        Player currentPlayer = game.getCurrentPlayer();
        Map<String, Territory> allTerritories = game.getBoard().getTerritories();
        if (game.boardChoice.equals("board1")) {
            for (Territory territory : allTerritories.values()) {
                JPanel territoryPanel = new BoardCreator(game).createBoardPanels1(territory, currentPlayer);
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
                            highlightNeigbors(territory, true);
                        }
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if(SwingUtilities.isRightMouseButton(e)) {
                            highlightNeigbors(territory, false);
                        }
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) {}
                });
                allTerritoryPanels.put(territory.getName(), territoryPanel);
                boardPanel.add(territoryPanel);
            }
        }
        if (game.boardChoice.equals("board2")) {

            boardPanel.setLayout(boardLayout);
            boardLayout.columnWidths = new int[] {50,50,50,50,50,50,50,50};
            boardLayout.rowHeights = new int[] {50,50,50,50,50,50,50,50};

            for (int i = 0; i < BoardCoordinates.allCountryCoordinates2.length; i++) {
                Territory territory = Helper.getTerritoryById(allTerritories, i + 1);
                JPanel territoryPanel = new BoardCreator(game).createBoardPanels1(territory, currentPlayer);
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
                            highlightNeigbors(territory, true);
                        }
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if(SwingUtilities.isRightMouseButton(e)) {
                            highlightNeigbors(territory, false);
                        }
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) {}
                });

                allTerritoryPanels.put(territory.getName(), territoryPanel);
                boardPanel.add(territoryPanel, Helper.buildBoardConstraints(boardConstraints,
                        BoardCoordinates.allCountryCoordinates2[i][0],
                        BoardCoordinates.allCountryCoordinates2[i][1],
                        BoardCoordinates.allCountryCoordinates2[i][2],
                        BoardCoordinates.allCountryCoordinates2[i][3]));
            }
        }
        if(game.boardChoice.equals("board3")) {

            boardPanel.setLayout(boardLayout);
            boardLayout.columnWidths = new int[] {50,50,50,50,50,50,50,50,50,50,50,50};
            boardLayout.rowHeights = new int[] {50,50,50,50,50,50,50,50};

            for (int i = 0; i < BoardCoordinates.allCountryCoordinates3.length; i++) {
                Territory territory = Helper.getTerritoryById(allTerritories, i + 1);
                JPanel territoryPanel = new BoardCreator(game).createBoardPanels1(territory, currentPlayer);
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
                            highlightNeigbors(territory, true);
                        }
                    }
                    @Override
                    public void mouseReleased(MouseEvent e) {
                        if(SwingUtilities.isRightMouseButton(e)) {
                            highlightNeigbors(territory, false);
                        }
                    }
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) {}
                });

                allTerritoryPanels.put(territory.getName(), territoryPanel);
                boardPanel.add(territoryPanel, Helper.buildBoardConstraints(boardConstraints,
                        BoardCoordinates.allCountryCoordinates3[i][0],
                        BoardCoordinates.allCountryCoordinates3[i][1],
                        BoardCoordinates.allCountryCoordinates3[i][2],
                        BoardCoordinates.allCountryCoordinates3[i][3]));
            }
        }
        statusLabel.setText("Current Player: " + game.getCurrentPlayer().getName() +
                " | Territories: " + currentPlayer.getTerritories().size() +
                " | Armies: " + currentPlayer.getArmyCount() +
                " | Cards Total: " + currentPlayer.getCards().size() +
                " | Infantry: " + currentPlayer.getTypedCards("Infantry").size() +
                " | Cavalry: " + currentPlayer.getTypedCards("Cavalry").size() +
                " | Artillery: " + currentPlayer.getTypedCards("Artillery").size());
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private void handleTerritoryClick(Territory clickedTerritory) {
        if (clickedTerritory == null) {
            JOptionPane.showMessageDialog(frame, "Fehler: Das ausgewählte Territorium existiert nicht.");
            return;
        }

        if(isInitialDistribution) {
            if(game.getCurrentPlayer() == clickedTerritory.getOwner()) {
                game.distributeInitialArmies(clickedTerritory);
                game.setCurrentPlayer();
                updateBoard();
            }
            else {
                JOptionPane.showMessageDialog(frame, "Please choose a country you own", "Distribution", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if(!game.initialArmiesDistributed()) {
                JOptionPane.showMessageDialog(frame, game.getCurrentPlayer().getName() + ", please choose a country to set your army.", "Distribution", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            else {
                isInitialDistribution = false;
                JOptionPane.showMessageDialog(frame, "Distribution done.\nGood Luck everyone.", "Distribution", JOptionPane.INFORMATION_MESSAGE);
                nextTurnButton.setEnabled(true);
                fortifyButton.setEnabled(true);
                useCardButton.setEnabled(true);
                return;
            }
        }

        if (isFortifying) {
            if (selectedFrom == null) {
                if (clickedTerritory.getOwner() == game.getCurrentPlayer()) {
                    selectedFrom = clickedTerritory;
                    JOptionPane.showMessageDialog(frame, "Territory selected for fortification: " + selectedFrom.getName() + ". Now select the target territory.");
                } else {
                    JOptionPane.showMessageDialog(frame, "You must select a territory that you own.");
                }
            } else {
                selectedTo = clickedTerritory;
                if (!selectedFrom.getAdjacentTerritories().contains(selectedTo)) {
                    JOptionPane.showMessageDialog(frame, "You can only fortify adjacent territories.");
                    return;
                }

                int armiesToMove = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter number of armies to move (1-3):"));
                if (armiesToMove >= 1 && armiesToMove <= selectedFrom.getArmyCount() - 1) {
                    game.fortifyTerritory(selectedFrom, selectedTo, armiesToMove);
                    selectedFrom = null;
                    selectedTo = null;
                    updateBoard();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid number of armies. Must be between 1 and 3 and not more than available.");
                }
            }
        } else {
            if (selectedFrom == null) {
                if (clickedTerritory.getOwner() == game.getCurrentPlayer()) {
                    selectedFrom = clickedTerritory;
                    JOptionPane.showMessageDialog(frame, "Territory selected for attack: " + selectedFrom.getName() + ". Now select the target territory.");
                } else {
                    JOptionPane.showMessageDialog(frame, "You must select a territory that you own.");
                }
            } else {
                selectedTo = clickedTerritory;
                if (!selectedFrom.getAdjacentTerritories().contains(selectedTo)) {
                    JOptionPane.showMessageDialog(frame, "You can only attack adjacent territories.");
                    return;
                }
                if (selectedTo.getOwner() == game.getCurrentPlayer()) {
                    JOptionPane.showMessageDialog(frame, "You cannot attack your own territory.");
                    return;
                }

                    System.out.println(selectedFrom.getName() + selectedTo.getName());
                int attackArmies = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter number of armies to attack with (1-3):"));
                if (attackArmies >= 1 && attackArmies <= 3 && attackArmies <= selectedFrom.getArmyCount() - 1) {
                    int defendArmies = Math.min(2, selectedTo.getArmyCount());
                    game.attackTerritory(selectedFrom, selectedTo, attackArmies, defendArmies);

                    handleAttackPhase();

                    if (selectedTo.getArmyCount() == 0) {
                        JOptionPane.showMessageDialog(frame, "Territory conquered! You receive a card.");
                        game.getCurrentPlayer().addTerritory(selectedTo);
                        game.getCurrentPlayer().addCard(new Card("Infantry"));
                    }

                    selectedFrom = null;
                    selectedTo = null;
                    updateBoard();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid number of armies. Must be between 1 and 3 and not more than available.");
                }
            }
        }
    }

    private void useCards() {
        Player currentPlayer = game.getCurrentPlayer();
        List<Card> cards = currentPlayer.getCards();

        if (cards.size() < 3) {
            JOptionPane.showMessageDialog(frame, "You need at least 3 cards to trade for armies.");
            return;
        }

        game.openUseCardsWindow(frame);
        /*int response = JOptionPane.showConfirmDialog(frame, "Do you want to trade 3 cards for 5 armies?");
        if (response == JOptionPane.YES_OPTION) {
            for (int i = 0; i < 3; i++) {
                currentPlayer.useCard();
            }
            currentPlayer.addArmies(5);
            JOptionPane.showMessageDialog(frame, "You have received 5 additional armies.");

            distributeBonusArmies(currentPlayer, 5);
            updateBoard();
        }*/
    }

    private void handleAttackPhase() {
        String diceResult = rollDiceResult();

        JFrame diceFrame = new JFrame("Roll Dice");
        diceFrame.setSize(300, 200);
        diceFrame.setLayout(new BorderLayout());

        JTextArea diceResultArea = new JTextArea(diceResult);
        diceResultArea.setEditable(false);
        diceFrame.add(new JScrollPane(diceResultArea), BorderLayout.CENTER);

        JButton okButton = new JButton("OK");
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                diceFrame.dispose();
                game.nextTurn();
                if (game.checkWinCondition()) {
                    JOptionPane.showMessageDialog(frame, "Player " + game.getCurrentPlayer().getName() + " wins!");
                    System.exit(0);
                }
                updateBoard();
            }
        });
        diceFrame.add(okButton, BorderLayout.SOUTH);
        diceFrame.setVisible(true);
    }


    private String rollDiceResult() {
        if (selectedFrom == null || selectedTo == null) {
            System.out.println("Fehler: Kein Angriff ausgewählt.");
        }
        int attackDiceCount = Math.min(3, selectedFrom.getArmyCount());
        int defendDiceCount = Math.min(2, selectedTo.getArmyCount());

        int[] attackDice = game.rollDice(attackDiceCount);
        int[] defendDice = game.rollDice(defendDiceCount);
        return compareDiceResults(attackDice, defendDice);
    }

    private String compareDiceResults (int[] attackDice, int[] defendDice){
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

        if (selectedTo.getArmyCount() == 0) {
            game.getCurrentPlayer().addTerritory(selectedTo);
            game.getCurrentPlayer().addCard(new Card("Infantry"));
            result.append("Territory conquered! ").append(selectedTo.getName()).append(" is now owned by ").append(game.getCurrentPlayer().getName()).append(".");
        }

        return result.toString();
    }
    private int[] reverseArray(int[] array) {
        int[] reversed = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            reversed[i] = array[array.length - 1 - i];
        }
        return reversed;
    }

    private void distributeBonusArmies(Player player, int armiesToDistribute) {
        while (armiesToDistribute > 0) {
            Territory selectedTerritory = (Territory) JOptionPane.showInputDialog(
                    frame,
                    player.getName() + ", select a territory to place armies:",
                    "Distribute Armies",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    player.getTerritories().toArray(),
                    player.getTerritories().get(0)
            );

            if (selectedTerritory != null) {
                int armiesToPlace = Integer.parseInt(JOptionPane.showInputDialog(
                        frame,
                        "Enter number of armies to place (1-" + armiesToDistribute + "):"
                ));

                if (armiesToPlace >= 1 && armiesToPlace <= armiesToDistribute) {
                    boolean success = game.distributeArmy(selectedTerritory, armiesToPlace);
                    if (success) {
                        armiesToDistribute -= armiesToPlace;
                        JOptionPane.showMessageDialog(frame, "You placed " + armiesToPlace + " armies at " + selectedTerritory.getName());
                        updateBoard();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Failed to place armies. Try again.");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid number of armies. Must be between 1 and " + armiesToDistribute + ".");
                }
            }
        }

        JOptionPane.showMessageDialog(frame, "All bonus armies are distributed.");
    }

    public void highlightNeigbors(Territory selectedTerritory, boolean active) {
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
