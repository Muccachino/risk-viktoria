package Model;

import Config.TerritoryNeighbors;

import java.util.LinkedHashMap;
import java.util.Map;

public class Board {
    String[] continentNames1 = new String[] {"North America", "Europe", "Asia", "Africa"};
    String[] continentNames2 = new String[] {"North America", "Europe", "South America", "Asia", "Australia"};
    String[] continentNames3 = new String[] {"North America", "South America", "Europe", "Africa", "Asia", "Australia"};

    private Map<String, Territory> territories;
    private Continent[] continents;

    public Board(String boardChoice) {
        territories = new LinkedHashMap<>();

        if(boardChoice.equals("board1")) {
            continents = new Continent[4];

            for (int i = 1; i <= 24; i++) {
                territories.put("Territory " + i, new Territory("Territory " + i, i));
            }

            for (int i = 0; i < 4; i++) {
                continents[i] = new Continent(continentNames1[i]);
                for (int j = 0; j < 6; j++) {
                    Territory territory = territories.get("Territory " + (i * 6 + j + 1));
                    continents[i].addTerritory(territory);
                    territory.setContinent(continents[i].getName());
                }
            }
            TerritoryNeighbors.setAdjacentTerritories1(territories);
        }
        if(boardChoice.equals("board2")) {
            continents = new Continent[5];
            for (int i = 0; i < continents.length; i++) {
                continents[i] = new Continent(continentNames2[i]);
            }
            for (int i = 1; i <= 34; i++) {
                Territory territory = new Territory("Territory " + i, i);
                if (i <= 7) {
                    continents[0].addTerritory(territory);
                    territory.setContinent(continents[0].getName());
                }
                if (i > 7 && i <= 14) {
                    continents[1].addTerritory(territory);
                    territory.setContinent(continents[1].getName());
                }
                if (i > 14 && i <= 21) {
                    continents[2].addTerritory(territory);
                    territory.setContinent(continents[2].getName());
                }
                if (i > 21 && i <= 28) {
                    continents[3].addTerritory(territory);
                    territory.setContinent(continents[3].getName());
                }
                if(i > 28) {
                    continents[4].addTerritory(territory);
                    territory.setContinent(continents[4].getName());
                }
                territories.put("Territory " + i, territory);
            }
            TerritoryNeighbors.setAdjacentTerritories2(territories);
        }

        if(boardChoice.equals("board3")) {
            continents = new Continent[6];
            for (int i = 0; i < continents.length; i++) {
                continents[i] = new Continent(continentNames3[i]);
            }
            for (int i = 1; i <= 42; i++) {
                Territory territory = new Territory("Territory " + i, i);
                if (i <= 9) {
                    continents[0].addTerritory(territory);
                    territory.setContinent(continents[0].getName());
                }
                if (i > 9 && i <= 13) {
                    continents[1].addTerritory(territory);
                    territory.setContinent(continents[1].getName());
                }
                if (i > 13 && i <= 20) {
                    continents[2].addTerritory(territory);
                    territory.setContinent(continents[2].getName());
                }
                if (i > 20 && i <= 26) {
                    continents[3].addTerritory(territory);
                    territory.setContinent(continents[3].getName());
                }
                if(i > 26 && i <= 38) {
                    continents[4].addTerritory(territory);
                    territory.setContinent(continents[4].getName());
                }
                if(i > 38) {
                    continents[5].addTerritory(territory);
                    territory.setContinent(continents[5].getName());
                }
                territories.put("Territory " + i, territory);
            }
            TerritoryNeighbors.setAdjacentTerritories3(territories);
        }
    }


    public Territory getTerritory(String name) {
        return territories.get(name);
    }

    public Map<String, Territory> getTerritories() {
        return territories;
    }

    public Continent[] getContinents() {
        return continents;
    }
}
