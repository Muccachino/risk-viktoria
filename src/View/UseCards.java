package View;

import Config.Helper;
import Controller.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Wegen der unterschiedlichen Karten und Kombinationen habe ich ein eigenes Fenster erstellt um den Einsatz der Karten
// zu ermöglichen.

public class UseCards implements ActionListener {

    public static final int USE_CARDS_HEADLINE_HEIGHT = 80;
    public static final int USE_CARDS_OPTIONS_HEIGHT = 50;
    public static final int USE_CARDS_OUTPUT_HEIGHT = 80;
    public static final int USE_CARDS_COLUMN_WIDTH = 50;
    GridBagLayout cardLayout = new GridBagLayout();
    GridBagConstraints cardConstraints = new GridBagConstraints();
    JPanel cardPanel = new JPanel(cardLayout);

    JDialog frame;

    private final JFrame parent;
    private final Game game;

    public UseCards(JFrame parent, Game game) {
        this.parent = parent;
        this.game = game;
    }


    public void createUseCardsDialog() {
        frame = new JDialog(this.parent, "Use Cards", true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        cardLayout.rowHeights = new int[]{USE_CARDS_HEADLINE_HEIGHT, USE_CARDS_OPTIONS_HEIGHT, USE_CARDS_OUTPUT_HEIGHT};
        cardLayout.columnWidths = new int[]{USE_CARDS_COLUMN_WIDTH, USE_CARDS_COLUMN_WIDTH, USE_CARDS_COLUMN_WIDTH, USE_CARDS_COLUMN_WIDTH, USE_CARDS_COLUMN_WIDTH, USE_CARDS_COLUMN_WIDTH};

        JLabel headlineLabel = new JLabel("How do you want to use your cards?", JLabel.CENTER);

        JButton infantryButton = new JButton("3x Infantry");
        infantryButton.addActionListener(this);
        infantryButton.setActionCommand("Infantry");
        infantryButton.setEnabled(game.checkThreeCardCombination("Infantry"));

        JButton cavalryButton = new JButton("3x Cavalry");
        cavalryButton.addActionListener(this);
        cavalryButton.setActionCommand("Cavalry");
        cavalryButton.setEnabled(game.checkThreeCardCombination("Cavalry"));

        JButton artilleryButton = new JButton("3x Artillery");
        artilleryButton.addActionListener(this);
        artilleryButton.setActionCommand("Artillery");
        artilleryButton.setEnabled(game.checkThreeCardCombination("Artillery"));

        JButton oneOfEachButton = new JButton("One of each");
        oneOfEachButton.addActionListener(this);
        oneOfEachButton.setActionCommand("oneOfEach");
        oneOfEachButton.setEnabled(game.checkOneOfEachCard());

        JLabel outPutLabel = new JLabel("Output:", JLabel.CENTER);
        JLabel outPutInfantry = new JLabel("4 Armies", JLabel.CENTER);
        JLabel outPutCavalry = new JLabel("5 Armies", JLabel.CENTER);
        JLabel outPutArtillery = new JLabel("6 Armies", JLabel.CENTER);
        JLabel outPutOneOfEach = new JLabel("5 Armies", JLabel.CENTER);

        cardPanel.add(headlineLabel, Helper.buildBoardConstraints(cardConstraints, 0,0,1,5));
        cardPanel.add(infantryButton, Helper.buildBoardConstraints(cardConstraints, 1,1,1,1));
        cardPanel.add(cavalryButton, Helper.buildBoardConstraints(cardConstraints, 1,2,1,1));
        cardPanel.add(artilleryButton, Helper.buildBoardConstraints(cardConstraints, 1,3,1,1));
        cardPanel.add(oneOfEachButton, Helper.buildBoardConstraints(cardConstraints, 1,4,1,1));
        cardPanel.add(outPutLabel, Helper.buildBoardConstraints(cardConstraints, 2,0,1,1));
        cardPanel.add(outPutInfantry, Helper.buildBoardConstraints(cardConstraints, 2,1,1,1));
        cardPanel.add(outPutCavalry, Helper.buildBoardConstraints(cardConstraints, 2,2,1,1));
        cardPanel.add(outPutArtillery, Helper.buildBoardConstraints(cardConstraints, 2,3,1,1));
        cardPanel.add(outPutOneOfEach, Helper.buildBoardConstraints(cardConstraints, 2,4,1,1));

        frame.setContentPane(cardPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Infantry")) {
            game.cardReinforcementPhase("Infantry");
        }
        if(e.getActionCommand().equals("Cavalry")) {
            game.cardReinforcementPhase("Cavalry");
        }
        if(e.getActionCommand().equals("Artillery")) {
            game.cardReinforcementPhase("Artillery");
        }
        if(e.getActionCommand().equals("oneOfEach")) {
            game.cardReinforcementPhase("oneOfEach");
        }
        frame.dispose();
    }
}
