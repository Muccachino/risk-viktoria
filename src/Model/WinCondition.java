package Model;


import Controller.Game;

import java.util.List;

public class WinCondition {

    private final String name;
    private final String description;
    private final Player player;
    private final Game game;
    private final String[] continentNames;


    public WinCondition(String name , String description, Player player, Game game, String[] continentNames) {

        this.name = name;
        this.description = description;
        this.player = player;
        this.game = game;
        this.continentNames = continentNames;
    }

    public String getDescription() {
        if(this.name.equals("ConquerXContinents")) {
            return this.description + String.join(", ", this.continentNames);
        } else {
            return this.description;
        }
    }

    public boolean checkWinCondition() {
        switch (this.name) {
            case "ConquerXTerritories": return conquerXTerritories();
            case "ConquerXContinents": return conquerXContinents(this.continentNames);
            case "ConquerXTerritoriesWithArmyCount2": return conquerXTerritoriesWithArmyCount2();
        }
        return false;
    };

    private boolean conquerXTerritories() {
        switch (game.boardChoice) {
            case "board1": return this.player.getTerritories().size() >= 14;
            case "board2": return this.player.getTerritories().size() >= 20;
            case "board3": return this.player.getTerritories().size() >= 24;
        }
        return false;
    }

    private boolean conquerXContinents(String[] continentNames) {
        Continent[] allContinents = this.game.getBoard().getContinents();
        Continent[] continentsNeeded = new Continent[continentNames.length];
        for (int i = 0; i < continentNames.length; i++) {
            if(allContinents[i].getName().equals(continentNames[i])) {
                continentsNeeded[i] = allContinents[i];
            }
        }

        int conquered = 0;
        for (Continent continent : continentsNeeded) {
            if (this.player.controlsContinent(continent)) {
                conquered++;
            }
        }
        return conquered == continentsNeeded.length;
    }

    private boolean conquerXTerritoriesWithArmyCount2() {
        int territoriesNeeded = 0;
        switch (game.boardChoice) {
            case "board1": territoriesNeeded = 10; break;
            case "board2": territoriesNeeded = 14; break;
            case "board3": territoriesNeeded = 18; break;
        }

        boolean twoArmiesInside = true;
        for (Territory territory : player.getTerritories()) {
            if(territory.getArmyCount() < 2) {
                twoArmiesInside = false;
                break;
            }
        }

        return (player.getTerritories().size() >= territoriesNeeded && twoArmiesInside);
    }

}
