package Model;

import Config.TerritoryNeighbors;

import java.util.LinkedHashMap;
import java.util.Map;

public class Board {

    // Individuelle Namen für Kontinente und Territorien hinzugefügt
    String[] continentNames1 = new String[] {"Eldoria", "Nymeris", "Vandarra", "Zaruth"};
    String[] territoryNames1 = new String[] {
            // Eldoria
            "Thalnor", "Aeryndor", "Glimwynn", "Stormhold", "Briarmoor", "Frosthelm",
            // Nymeris
            "Lythora", "Veridorn", "Astrava", "Noxmere", "Isilmar", "Thornspire",
            // Vandarra
            "Drakhar", "Emberfell", "Ravenmoor", "Blackstone", "Silverscar", "Wyrmwatch",
            // Zaruth
            "Duskveil", "Morathis", "Ironpeak", "Shadowfen", "Grimstone", "Voidreach"};

    String[] continentNames2 = new String[] {"Mythara", "Solandis", "Eldravar", "Nytheria", "Zylarion"};
    String[] territoryNames2 = new String[] {
            // Mythara
            "Valkora", "Dornhelm", "Serathis", "Kyrel", "Ostara", "Windspire", "Varduun",
            // Solandis
            "Caladorn", "Myridia", "Valythor", "Aurelis", "Falcreth", "Gorvann", "Lunaris",
            // Eldravar
            "Aranthor", "Brynmoor", "Thornhall", "Ironwood", "Wyrmscar", "Darkhaven", "Stormcrown",
            // Nytheria
            "Evershade", "Shadowglen", "Duskmire", "Frostveil", "Blightwood", "Grimreach", "Nightfall",
            // Zylarion
            "Zyraxis", "Maelstrom", "Ironspire", "Ravencrest", "Stormbreak", "Nethermoor"};

    String[] continentNames3 = new String[] {"North America", "South America", "Europe", "Africa", "Asia", "Australia"};
    String[] territoryNames3 = new String[] {
            // North America
            "Alaska", "Northwest Territory", "Greenland", "Alberta", "Ontario", "Quebec",
            "Western US", "Eastern US", "Central America",
            // South America
            "Venezuela", "Peru", "Brazil", "Argentina",
            // Europe
            "Iceland", "Scandinavia", "Ukraine", "Great Britain", "Northern Europe", "Western Europe", "Southern Europe",
            // Africa
            "North Africa", "Egypt", "Congo", "East Africa", "South Africa", "Madagascar",
            // Asia
            "Siberia", "Yakutsk", "Kamchatka", "Ural", "Irkutsk", "Mongolia", "Afghanistan",
            "China", "Japan", "Middle East", "India", "Siam",
            // Australia
            "Indonesia", "New Guinea", "Western Australia", "Eastern Australia"
    };

    private final Map<String, Territory> territories;
    private Continent[] continents;

    // Anpassung der Kontinenterstellung, da vorher nur die 4x6 genutzt werden konnte.
    public Board(String boardChoice) {
        territories = new LinkedHashMap<>();

        if(boardChoice.equals("board1")) {
            continents = new Continent[4];

            for (int i = 1; i <= 24; i++) {
                territories.put("Territory " + i, new Territory("Territory " + i, i, territoryNames1[i-1]));
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
                Territory territory = new Territory("Territory " + i, i, territoryNames2[i-1]);
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
                Territory territory = new Territory("Territory " + i, i, territoryNames3[i-1]);
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
