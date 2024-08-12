import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class GUI {
    private final Game game;
    private final JFrame frame;
    private final JPanel boardPanel;
    private final JLabel statusLabel;
    private Territory selectedFrom;
    private Territory selectedTo;
    private boolean isFortifying;
    private boolean isDistributing;

    public GUI(Game game) {
        this.game = game;
        this.frame = new JFrame("Risiko");
        this.boardPanel = new JPanel();
        this.statusLabel = new JLabel("Current Player: ");
        this.selectedFrom = null;
        this.selectedTo = null;
        this.isFortifying = false;
        this.isDistributing = false;
    }

    public void createAndShowGUI() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        boardPanel.setLayout(new GridLayout(6, 4));
        updateBoard();

        frame.add(boardPanel, BorderLayout.CENTER);
        frame.add(statusLabel, BorderLayout.SOUTH);

        JButton nextTurnButton = new JButton("Next Turn");
        nextTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.nextTurn();
                if (game.checkWinCondition()) {
                    JOptionPane.showMessageDialog(frame, "Player " + game.getCurrentPlayer().getName() + " wins!");
                    System.exit(0);
                }
                updateBoard();
            }
        });

        JPanel controlPanel = new JPanel();
        controlPanel.add(nextTurnButton);

        JButton fortifyButton = new JButton("Fortify");
        fortifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isFortifying = !isFortifying;
                fortifyButton.setText(isFortifying ? "Attack" : "Fortify");
                statusLabel.setText("Current Player: " + game.getCurrentPlayer().getName() + " (Fortify Mode: " + isFortifying + ")");
                isDistributing = false;
            }
        });
        controlPanel.add(fortifyButton);
        JButton distributeButton = new JButton("Distribute Armies");
        distributeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isDistributing = !isDistributing;
                distributeButton.setText(isDistributing ? "Done" : "Distribute Armies");
                statusLabel.setText("Current Player: " + game.getCurrentPlayer().getName() + " (Distribute Mode: " + isDistributing + ")");
                isFortifying = false;
                if (isDistributing) {
                    distributeInitialArmies();
                }
            }
        });
        controlPanel.add(distributeButton);
        JButton useCardButton = new JButton("Use Cards");
        useCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                useCards();
            }
        });
        controlPanel.add(useCardButton);

        frame.add(controlPanel, BorderLayout.NORTH);

        distributeInitialArmies();

        frame.setVisible(true);

    }
    private void distributeInitialArmies() {
        for (Player player : game.getPlayers()) {
            game.distributeInitialArmies(player, 20);
        }
        JOptionPane.showMessageDialog(frame, "All armies are distributed. The game begins now!");
        updateBoard();
    }
    private void updateBoard() {
        boardPanel.removeAll();
        Player currentPlayer = game.getCurrentPlayer();
        for (Territory territory : game.getBoard().getTerritories().values()) {
            JButton button = new JButton(territory.getName() + " (" + territory.getArmyCount() + ")");
            button.setBackground(territory.getOwner() == currentPlayer ? Color.GREEN : Color.RED);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleTerritoryClick(territory);
                }
            });
            boardPanel.add(button);
        }
        statusLabel.setText("Current Player: " + game.getCurrentPlayer().getName() +
                " | Territories: " + currentPlayer.getTerritories().size() + " | Armies: " + currentPlayer.getArmyCount() +
                " | Cards: " + currentPlayer.getCards().size());
        boardPanel.revalidate();
        boardPanel.repaint();
    }

    private void handleTerritoryClick(Territory clickedTerritory) {
        if (isDistributing) {
            if (clickedTerritory.getOwner() == game.getCurrentPlayer()) {
                int armiesToAdd = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter number of armies to distribute (1-20):"));
                if (armiesToAdd >= 1 && armiesToAdd <= 20) {
                    clickedTerritory.addArmies(armiesToAdd);
                    game.getCurrentPlayer().removeArmies(armiesToAdd);
                    JOptionPane.showMessageDialog(frame, armiesToAdd + " armies have been distributed to " + clickedTerritory.getName());
                    updateBoard();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid number of armies. Must be between 1 and 20.");
                }
            } else {
                JOptionPane.showMessageDialog(frame, "You can only distribute armies to your own territories.");
            }
        } else if (!isFortifying) {
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

                int attackArmies = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter number of armies to attack with (1-3):"));
                if (attackArmies >= 1 && attackArmies <= 3 && attackArmies <= selectedFrom.getArmyCount() - 1) {
                    int defendArmies = Math.min(2, selectedTo.getArmyCount());
                    game.attackTerritory(selectedFrom, selectedTo, attackArmies, defendArmies);

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
        } else {
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
        }
    }

    private void useCards() {
        Player currentPlayer = game.getCurrentPlayer();
        List<Card> cards = currentPlayer.getCards();

        if (cards.size() < 3) {
            JOptionPane.showMessageDialog(frame, "You need at least 3 cards to trade for armies.");
            return;
        }

        int response = JOptionPane.showConfirmDialog(frame, "Do you want to trade 3 cards for 5 armies?");
        if (response == JOptionPane.YES_OPTION) {
            for (int i = 0; i < 3; i++) {
                currentPlayer.useCard();
            }
            currentPlayer.addArmies(5);
            JOptionPane.showMessageDialog(frame, "You have received 5 additional armies.");
            updateBoard();
        }
    }
}
