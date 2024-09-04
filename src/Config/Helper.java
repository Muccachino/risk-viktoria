package Config;

import Model.Territory;

import java.awt.*;
import java.util.Map;

public class Helper {
    // Constraints builder for every GridBagLayout
    public static GridBagConstraints buildBoardConstraints(GridBagConstraints constraints, int row, int col, int rowspan, int colspan) {
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridy = row;
        constraints.gridx = col;
        constraints.gridwidth = colspan;
        constraints.gridheight = rowspan;
        return constraints;
    }

    public static Territory getTerritoryById(Map<String, Territory> allTerritories, int id) {
        for (Territory territory : allTerritories.values()) {
            if (territory.getTerritoryId() == id) {
                return territory;
            }
        }
        return null;
    }
}
