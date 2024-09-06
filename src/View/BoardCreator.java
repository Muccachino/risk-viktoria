package View;

import Config.Helper;
import Controller.Game;
import Model.Player;
import Model.Territory;

import javax.swing.*;
import java.awt.*;

public class BoardCreator {

    GridBagLayout territoryLayout = new GridBagLayout();
    GridBagConstraints territoryConstraints = new GridBagConstraints();
    JPanel territoryPanel = new JPanel(territoryLayout);


    public Game controller;

    public BoardCreator(Game controller) {
        this.controller = controller;
    }


    public JPanel createBoardPanels(Territory territory, Player currentPlayer) {
        territoryLayout.rowHeights = new int[] {30,20,30};
        territoryLayout.columnWidths = new int[] {80};

        JLabel territoryName = new JLabel(territory.getBoardName(), JLabel.CENTER);
        JLabel territoryArmies = new JLabel("Armies: " + territory.getArmyCount(), JLabel.CENTER);
        JLabel territoryContinent = new JLabel(territory.getContinent(), JLabel.CENTER);
        if(territory.getOwner() == null) {
            territoryPanel.setBackground(new Color(232, 227, 224));
            territoryPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        } else {
            territoryPanel.setBackground(territory.getOwner().getPlayerColor());
            territoryPanel.setBorder(BorderFactory.createLineBorder(territory.getOwner() == currentPlayer ? Color.GREEN : Color.RED, 2));
        }
        territoryPanel.add(territoryName, Helper.buildBoardConstraints(territoryConstraints, 0,0,1,1));
        territoryPanel.add(territoryArmies, Helper.buildBoardConstraints(territoryConstraints, 1,0,1,1));
        territoryPanel.add(territoryContinent, Helper.buildBoardConstraints(territoryConstraints, 2,0,1,1));

        territoryPanel.setOpaque(true);

        return territoryPanel;
    }

}
