package Model;

import java.util.ArrayList;
import java.util.List;

public class Territory {
    private final String name;
    private Player owner;
    private int armyCount;
    private final List<Territory> adjacentTerritories;
    private String continent;
    private final int territoryId;
    private final String boardName;

    // boardName hinzugefügt, um individuelle AnzeigeNamen einzufügen, ohne die Nachbarschaftsverhältnisse zu beeinflussen.
    // ID hinzugefügt um besser auf Territorien in manchen Situationen zuzugreifen
    public Territory(String name, int territoryId, String boardName) {
        this.name = name;
        this.owner = null;
        this.armyCount = 0;
        this.adjacentTerritories = new ArrayList<>();
        this.territoryId = territoryId;
        this.boardName = boardName;
    }

    public String getName() {
        return name;
    }
    @Override
    public String toString() {
        return name;
    }

    public String getBoardName() {
        return this.boardName;
    }

    public int getTerritoryId(){return territoryId;}

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public int getArmyCount() {
        return armyCount;
    }

    public void addArmies(int count) {
        this.armyCount += count;
    }

    public void removeArmies(int count) {
        this.armyCount -= count;
    }

    public List<Territory> getAdjacentTerritories() {
        return adjacentTerritories;
    }

    public void addAdjacentTerritory(Territory territory) {
        if (!adjacentTerritories.contains(territory)) {
            adjacentTerritories.add(territory);
        }
    }

}
