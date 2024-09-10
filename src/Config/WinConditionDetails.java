package Config;

// Klasse hinzugefügt um Details der Missionen auszulagern
public class WinConditionDetails {
    public static final String[] winConditionNames = {
            "ConquerXTerritories",
            "ConquerXContinents",
            "ConquerXTerritoriesWithArmyCount2"
    };

    public static final String[] winConditionDescriptions1 = {
            "Conquer 14 territories of your choice:",
            "Conquer the following continents:\n",
            "Conquer 10 territories of your choice and place at least two armies in each territory:"
    };

    public static final String[] winConditionDescriptions2 = {
            "Conquer 20 territories of your choice:",
            "Conquer the following continents:\n",
            "Conquer 14 territories of your choice and place at least two armies in each territory:"
    };

    public static final String[] winConditionDescriptions3 = {
            "Conquer 24 territories of your choice:",
            "Conquer the following continents:\n",
            "Conquer 18 territories of your choice and place at least two armies in each territory:"
    };


    public static final String[][] winConditionContinents1 = {
            new String[]{"Eldoria", "Nymeris"},
            new String[]{"Vandarra", "Zaruth"},
            new String[]{"Eldoria", "Vandarra"},
            new String[]{"Nymeris", "Zaruth"},
    };
    public static final String[][] winConditionContinents2 = {
            new String[] {"Mythara", "Solandis", "Zylarion"},
            new String[] {"Eldravar", "Nytheria", "Zylarion"},
            new String[] {"Solandis", "Nytheria", "Zylarion"},
            new String[] {"Mythara", "Eldravar", "Zylarion"},
    };
    public static final String[][] winConditionContinents3 = {
            new String[] {"Africa", "Asia"},
            new String[] {"North America", "Australia"},
            new String[] {"North America", "Africa"},
            new String[] {"South America", "Asia"},
            new String[] {"South America", "Europe", "Africa"},
            new String[] {"Europe", "Australia", "Africa"}
    };
}
