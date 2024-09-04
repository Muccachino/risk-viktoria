package Config;

import Model.Territory;

import java.util.Map;

public class TerritoryNeighbors {
    public static void setAdjacentTerritories1(Map<String, Territory> territories) {
        // Territory 1
        territories.get("Territory 1").addAdjacentTerritory(territories.get("Territory 2"));
        territories.get("Territory 1").addAdjacentTerritory(territories.get("Territory 7"));

        // Territory 2
        territories.get("Territory 2").addAdjacentTerritory(territories.get("Territory 1"));
        territories.get("Territory 2").addAdjacentTerritory(territories.get("Territory 3"));
        territories.get("Territory 2").addAdjacentTerritory(territories.get("Territory 8"));

        // Territory 3
        territories.get("Territory 3").addAdjacentTerritory(territories.get("Territory 2"));
        territories.get("Territory 3").addAdjacentTerritory(territories.get("Territory 4"));
        territories.get("Territory 3").addAdjacentTerritory(territories.get("Territory 9"));

        // Territory 4
        territories.get("Territory 4").addAdjacentTerritory(territories.get("Territory 3"));
        territories.get("Territory 4").addAdjacentTerritory(territories.get("Territory 5"));
        territories.get("Territory 4").addAdjacentTerritory(territories.get("Territory 10"));

        // Territory 5
        territories.get("Territory 5").addAdjacentTerritory(territories.get("Territory 4"));
        territories.get("Territory 5").addAdjacentTerritory(territories.get("Territory 6"));
        territories.get("Territory 5").addAdjacentTerritory(territories.get("Territory 11"));

        // Territory 6
        territories.get("Territory 6").addAdjacentTerritory(territories.get("Territory 5"));
        territories.get("Territory 6").addAdjacentTerritory(territories.get("Territory 12"));

        // Territory 7
        territories.get("Territory 7").addAdjacentTerritory(territories.get("Territory 1"));
        territories.get("Territory 7").addAdjacentTerritory(territories.get("Territory 8"));
        territories.get("Territory 7").addAdjacentTerritory(territories.get("Territory 13"));

        // Territory 8
        territories.get("Territory 8").addAdjacentTerritory(territories.get("Territory 2"));
        territories.get("Territory 8").addAdjacentTerritory(territories.get("Territory 7"));
        territories.get("Territory 8").addAdjacentTerritory(territories.get("Territory 9"));
        territories.get("Territory 8").addAdjacentTerritory(territories.get("Territory 14"));

        // Territory 9
        territories.get("Territory 9").addAdjacentTerritory(territories.get("Territory 3"));
        territories.get("Territory 9").addAdjacentTerritory(territories.get("Territory 8"));
        territories.get("Territory 9").addAdjacentTerritory(territories.get("Territory 10"));
        territories.get("Territory 9").addAdjacentTerritory(territories.get("Territory 15"));

        // Territory 10
        territories.get("Territory 10").addAdjacentTerritory(territories.get("Territory 4"));
        territories.get("Territory 10").addAdjacentTerritory(territories.get("Territory 9"));
        territories.get("Territory 10").addAdjacentTerritory(territories.get("Territory 11"));
        territories.get("Territory 10").addAdjacentTerritory(territories.get("Territory 16"));

        // Territory 11
        territories.get("Territory 11").addAdjacentTerritory(territories.get("Territory 5"));
        territories.get("Territory 11").addAdjacentTerritory(territories.get("Territory 10"));
        territories.get("Territory 11").addAdjacentTerritory(territories.get("Territory 12"));
        territories.get("Territory 11").addAdjacentTerritory(territories.get("Territory 17"));

        // Territory 12
        territories.get("Territory 12").addAdjacentTerritory(territories.get("Territory 6"));
        territories.get("Territory 12").addAdjacentTerritory(territories.get("Territory 11"));
        territories.get("Territory 12").addAdjacentTerritory(territories.get("Territory 18"));

        // Territory 13
        territories.get("Territory 13").addAdjacentTerritory(territories.get("Territory 7"));
        territories.get("Territory 13").addAdjacentTerritory(territories.get("Territory 14"));
        territories.get("Territory 13").addAdjacentTerritory(territories.get("Territory 19"));

        // Territory 14
        territories.get("Territory 14").addAdjacentTerritory(territories.get("Territory 8"));
        territories.get("Territory 14").addAdjacentTerritory(territories.get("Territory 13"));
        territories.get("Territory 14").addAdjacentTerritory(territories.get("Territory 15"));
        territories.get("Territory 14").addAdjacentTerritory(territories.get("Territory 20"));

        // Territory 15
        territories.get("Territory 15").addAdjacentTerritory(territories.get("Territory 9"));
        territories.get("Territory 15").addAdjacentTerritory(territories.get("Territory 14"));
        territories.get("Territory 15").addAdjacentTerritory(territories.get("Territory 16"));
        territories.get("Territory 15").addAdjacentTerritory(territories.get("Territory 21"));

        // Territory 16
        territories.get("Territory 16").addAdjacentTerritory(territories.get("Territory 10"));
        territories.get("Territory 16").addAdjacentTerritory(territories.get("Territory 15"));
        territories.get("Territory 16").addAdjacentTerritory(territories.get("Territory 17"));
        territories.get("Territory 16").addAdjacentTerritory(territories.get("Territory 22"));

        // Territory 17
        territories.get("Territory 17").addAdjacentTerritory(territories.get("Territory 11"));
        territories.get("Territory 17").addAdjacentTerritory(territories.get("Territory 16"));
        territories.get("Territory 17").addAdjacentTerritory(territories.get("Territory 18"));
        territories.get("Territory 17").addAdjacentTerritory(territories.get("Territory 23"));

        // Territory 18
        territories.get("Territory 18").addAdjacentTerritory(territories.get("Territory 12"));
        territories.get("Territory 18").addAdjacentTerritory(territories.get("Territory 17"));
        territories.get("Territory 18").addAdjacentTerritory(territories.get("Territory 24"));

        // Territory 19
        territories.get("Territory 19").addAdjacentTerritory(territories.get("Territory 13"));
        territories.get("Territory 19").addAdjacentTerritory(territories.get("Territory 20"));

        // Territory 20
        territories.get("Territory 20").addAdjacentTerritory(territories.get("Territory 14"));
        territories.get("Territory 20").addAdjacentTerritory(territories.get("Territory 19"));
        territories.get("Territory 20").addAdjacentTerritory(territories.get("Territory 21"));

        // Territory 21
        territories.get("Territory 21").addAdjacentTerritory(territories.get("Territory 15"));
        territories.get("Territory 21").addAdjacentTerritory(territories.get("Territory 20"));
        territories.get("Territory 21").addAdjacentTerritory(territories.get("Territory 22"));

        // Territory 22
        territories.get("Territory 22").addAdjacentTerritory(territories.get("Territory 16"));
        territories.get("Territory 22").addAdjacentTerritory(territories.get("Territory 21"));
        territories.get("Territory 22").addAdjacentTerritory(territories.get("Territory 23"));

        // Territory 23
        territories.get("Territory 23").addAdjacentTerritory(territories.get("Territory 17"));
        territories.get("Territory 23").addAdjacentTerritory(territories.get("Territory 22"));
        territories.get("Territory 23").addAdjacentTerritory(territories.get("Territory 24"));

        // Territory 24
        territories.get("Territory 24").addAdjacentTerritory(territories.get("Territory 18"));
        territories.get("Territory 24").addAdjacentTerritory(territories.get("Territory 23"));
    }

    public static void setAdjacentTerritories2(Map<String, Territory> territories) {
        // Territory 1
        territories.get("Territory 1").addAdjacentTerritory(territories.get("Territory 3"));
        territories.get("Territory 1").addAdjacentTerritory(territories.get("Territory 4"));
        territories.get("Territory 1").addAdjacentTerritory(territories.get("Territory 5"));

        // Territory 2
        territories.get("Territory 2").addAdjacentTerritory(territories.get("Territory 5"));
        territories.get("Territory 2").addAdjacentTerritory(territories.get("Territory 8"));

        // Territory 3
        territories.get("Territory 3").addAdjacentTerritory(territories.get("Territory 1"));
        territories.get("Territory 3").addAdjacentTerritory(territories.get("Territory 4"));
        territories.get("Territory 3").addAdjacentTerritory(territories.get("Territory 6"));

        // Territory 4
        territories.get("Territory 4").addAdjacentTerritory(territories.get("Territory 1"));
        territories.get("Territory 4").addAdjacentTerritory(territories.get("Territory 3"));
        territories.get("Territory 4").addAdjacentTerritory(territories.get("Territory 5"));
        territories.get("Territory 4").addAdjacentTerritory(territories.get("Territory 6"));

        // Territory 5
        territories.get("Territory 5").addAdjacentTerritory(territories.get("Territory 1"));
        territories.get("Territory 5").addAdjacentTerritory(territories.get("Territory 2"));
        territories.get("Territory 5").addAdjacentTerritory(territories.get("Territory 4"));
        territories.get("Territory 5").addAdjacentTerritory(territories.get("Territory 6"));

        // Territory 6
        territories.get("Territory 6").addAdjacentTerritory(territories.get("Territory 3"));
        territories.get("Territory 6").addAdjacentTerritory(territories.get("Territory 4"));
        territories.get("Territory 6").addAdjacentTerritory(territories.get("Territory 5"));
        territories.get("Territory 6").addAdjacentTerritory(territories.get("Territory 7"));
        territories.get("Territory 6").addAdjacentTerritory(territories.get("Territory 29"));

        // Territory 7
        territories.get("Territory 7").addAdjacentTerritory(territories.get("Territory 6"));
        territories.get("Territory 7").addAdjacentTerritory(territories.get("Territory 15"));

        // Territory 8
        territories.get("Territory 8").addAdjacentTerritory(territories.get("Territory 2"));
        territories.get("Territory 8").addAdjacentTerritory(territories.get("Territory 10"));

        // Territory 9
        territories.get("Territory 9").addAdjacentTerritory(territories.get("Territory 10"));
        territories.get("Territory 9").addAdjacentTerritory(territories.get("Territory 11"));
        territories.get("Territory 9").addAdjacentTerritory(territories.get("Territory 12"));

        // Territory 10
        territories.get("Territory 10").addAdjacentTerritory(territories.get("Territory 8"));
        territories.get("Territory 10").addAdjacentTerritory(territories.get("Territory 9"));
        territories.get("Territory 10").addAdjacentTerritory(territories.get("Territory 11"));
        territories.get("Territory 10").addAdjacentTerritory(territories.get("Territory 13"));

        // Territory 11
        territories.get("Territory 11").addAdjacentTerritory(territories.get("Territory 9"));
        territories.get("Territory 11").addAdjacentTerritory(territories.get("Territory 10"));
        territories.get("Territory 11").addAdjacentTerritory(territories.get("Territory 12"));
        territories.get("Territory 11").addAdjacentTerritory(territories.get("Territory 13"));

        // Territory 12
        territories.get("Territory 12").addAdjacentTerritory(territories.get("Territory 9"));
        territories.get("Territory 12").addAdjacentTerritory(territories.get("Territory 11"));
        territories.get("Territory 12").addAdjacentTerritory(territories.get("Territory 13"));

        // Territory 13
        territories.get("Territory 13").addAdjacentTerritory(territories.get("Territory 10"));
        territories.get("Territory 13").addAdjacentTerritory(territories.get("Territory 11"));
        territories.get("Territory 13").addAdjacentTerritory(territories.get("Territory 12"));
        territories.get("Territory 13").addAdjacentTerritory(territories.get("Territory 14"));
        territories.get("Territory 13").addAdjacentTerritory(territories.get("Territory 31"));

        // Territory 14
        territories.get("Territory 14").addAdjacentTerritory(territories.get("Territory 13"));
        territories.get("Territory 14").addAdjacentTerritory(territories.get("Territory 22"));

        // Territory 15
        territories.get("Territory 15").addAdjacentTerritory(territories.get("Territory 7"));
        territories.get("Territory 15").addAdjacentTerritory(territories.get("Territory 16"));

        // Territory 16
        territories.get("Territory 16").addAdjacentTerritory(territories.get("Territory 15"));
        territories.get("Territory 16").addAdjacentTerritory(territories.get("Territory 17"));
        territories.get("Territory 16").addAdjacentTerritory(territories.get("Territory 18"));
        territories.get("Territory 16").addAdjacentTerritory(territories.get("Territory 19"));
        territories.get("Territory 16").addAdjacentTerritory(territories.get("Territory 32"));

        // Territory 17
        territories.get("Territory 17").addAdjacentTerritory(territories.get("Territory 16"));
        territories.get("Territory 17").addAdjacentTerritory(territories.get("Territory 18"));
        territories.get("Territory 17").addAdjacentTerritory(territories.get("Territory 20"));

        // Territory 18
        territories.get("Territory 18").addAdjacentTerritory(territories.get("Territory 16"));
        territories.get("Territory 18").addAdjacentTerritory(territories.get("Territory 17"));
        territories.get("Territory 18").addAdjacentTerritory(territories.get("Territory 19"));
        territories.get("Territory 18").addAdjacentTerritory(territories.get("Territory 20"));

        // Territory 19
        territories.get("Territory 19").addAdjacentTerritory(territories.get("Territory 16"));
        territories.get("Territory 19").addAdjacentTerritory(territories.get("Territory 18"));
        territories.get("Territory 19").addAdjacentTerritory(territories.get("Territory 20"));
        territories.get("Territory 19").addAdjacentTerritory(territories.get("Territory 21"));

        // Territory 20
        territories.get("Territory 20").addAdjacentTerritory(territories.get("Territory 17"));
        territories.get("Territory 20").addAdjacentTerritory(territories.get("Territory 18"));
        territories.get("Territory 20").addAdjacentTerritory(territories.get("Territory 19"));

        // Territory 21
        territories.get("Territory 21").addAdjacentTerritory(territories.get("Territory 19"));
        territories.get("Territory 21").addAdjacentTerritory(territories.get("Territory 27"));

        // Territory 22
        territories.get("Territory 22").addAdjacentTerritory(territories.get("Territory 14"));
        territories.get("Territory 22").addAdjacentTerritory(territories.get("Territory 23"));

        // Territory 23
        territories.get("Territory 23").addAdjacentTerritory(territories.get("Territory 22"));
        territories.get("Territory 23").addAdjacentTerritory(territories.get("Territory 24"));
        territories.get("Territory 23").addAdjacentTerritory(territories.get("Territory 25"));
        territories.get("Territory 23").addAdjacentTerritory(territories.get("Territory 26"));
        territories.get("Territory 23").addAdjacentTerritory(territories.get("Territory 34"));

        // Territory 24
        territories.get("Territory 24").addAdjacentTerritory(territories.get("Territory 23"));
        territories.get("Territory 24").addAdjacentTerritory(territories.get("Territory 25"));
        territories.get("Territory 24").addAdjacentTerritory(territories.get("Territory 27"));
        territories.get("Territory 24").addAdjacentTerritory(territories.get("Territory 28"));

        // Territory 25
        territories.get("Territory 25").addAdjacentTerritory(territories.get("Territory 23"));
        territories.get("Territory 25").addAdjacentTerritory(territories.get("Territory 24"));
        territories.get("Territory 25").addAdjacentTerritory(territories.get("Territory 26"));
        territories.get("Territory 25").addAdjacentTerritory(territories.get("Territory 28"));

        // Territory 26
        territories.get("Territory 26").addAdjacentTerritory(territories.get("Territory 23"));
        territories.get("Territory 26").addAdjacentTerritory(territories.get("Territory 25"));
        territories.get("Territory 26").addAdjacentTerritory(territories.get("Territory 28"));

        // Territory 27
        territories.get("Territory 27").addAdjacentTerritory(territories.get("Territory 21"));
        territories.get("Territory 27").addAdjacentTerritory(territories.get("Territory 24"));

        // Territory 28
        territories.get("Territory 28").addAdjacentTerritory(territories.get("Territory 24"));
        territories.get("Territory 28").addAdjacentTerritory(territories.get("Territory 25"));
        territories.get("Territory 28").addAdjacentTerritory(territories.get("Territory 26"));

        // Territory 29
        territories.get("Territory 29").addAdjacentTerritory(territories.get("Territory 6"));
        territories.get("Territory 29").addAdjacentTerritory(territories.get("Territory 30"));
        territories.get("Territory 29").addAdjacentTerritory(territories.get("Territory 32"));

        // Territory 30
        territories.get("Territory 30").addAdjacentTerritory(territories.get("Territory 29"));
        territories.get("Territory 30").addAdjacentTerritory(territories.get("Territory 31"));
        territories.get("Territory 30").addAdjacentTerritory(territories.get("Territory 33"));

        // Territory 31
        territories.get("Territory 31").addAdjacentTerritory(territories.get("Territory 13"));
        territories.get("Territory 31").addAdjacentTerritory(territories.get("Territory 30"));
        territories.get("Territory 31").addAdjacentTerritory(territories.get("Territory 34"));

        // Territory 32
        territories.get("Territory 32").addAdjacentTerritory(territories.get("Territory 16"));
        territories.get("Territory 32").addAdjacentTerritory(territories.get("Territory 29"));
        territories.get("Territory 32").addAdjacentTerritory(territories.get("Territory 33"));

        // Territory 33
        territories.get("Territory 33").addAdjacentTerritory(territories.get("Territory 30"));
        territories.get("Territory 33").addAdjacentTerritory(territories.get("Territory 32"));
        territories.get("Territory 33").addAdjacentTerritory(territories.get("Territory 34"));

        // Territory 34
        territories.get("Territory 34").addAdjacentTerritory(territories.get("Territory 23"));
        territories.get("Territory 34").addAdjacentTerritory(territories.get("Territory 31"));
        territories.get("Territory 34").addAdjacentTerritory(territories.get("Territory 33"));
    }

    public static void setAdjacentTerritories3(Map<String, Territory> territories) {
        // Territory 1
        territories.get("Territory 1").addAdjacentTerritory(territories.get("Territory 2"));
        territories.get("Territory 1").addAdjacentTerritory(territories.get("Territory 4"));

        // Territory 2
        territories.get("Territory 2").addAdjacentTerritory(territories.get("Territory 1"));
        territories.get("Territory 2").addAdjacentTerritory(territories.get("Territory 3"));
        territories.get("Territory 2").addAdjacentTerritory(territories.get("Territory 5"));

        // Territory 3
        territories.get("Territory 3").addAdjacentTerritory(territories.get("Territory 2"));
        territories.get("Territory 3").addAdjacentTerritory(territories.get("Territory 6"));

        // Territory 4
        territories.get("Territory 4").addAdjacentTerritory(territories.get("Territory 1"));
        territories.get("Territory 4").addAdjacentTerritory(territories.get("Territory 5"));
        territories.get("Territory 4").addAdjacentTerritory(territories.get("Territory 7"));

        // Territory 5
        territories.get("Territory 5").addAdjacentTerritory(territories.get("Territory 2"));
        territories.get("Territory 5").addAdjacentTerritory(territories.get("Territory 4"));
        territories.get("Territory 5").addAdjacentTerritory(territories.get("Territory 6"));
        territories.get("Territory 5").addAdjacentTerritory(territories.get("Territory 7"));

        // Territory 6
        territories.get("Territory 6").addAdjacentTerritory(territories.get("Territory 3"));
        territories.get("Territory 6").addAdjacentTerritory(territories.get("Territory 5"));
        territories.get("Territory 6").addAdjacentTerritory(territories.get("Territory 8"));
        territories.get("Territory 6").addAdjacentTerritory(territories.get("Territory 14"));

        // Territory 7
        territories.get("Territory 7").addAdjacentTerritory(territories.get("Territory 4"));
        territories.get("Territory 7").addAdjacentTerritory(territories.get("Territory 5"));
        territories.get("Territory 7").addAdjacentTerritory(territories.get("Territory 8"));
        territories.get("Territory 7").addAdjacentTerritory(territories.get("Territory 9"));

        // Territory 8
        territories.get("Territory 8").addAdjacentTerritory(territories.get("Territory 6"));
        territories.get("Territory 8").addAdjacentTerritory(territories.get("Territory 7"));
        territories.get("Territory 8").addAdjacentTerritory(territories.get("Territory 9"));

        // Territory 9
        territories.get("Territory 9").addAdjacentTerritory(territories.get("Territory 7"));
        territories.get("Territory 9").addAdjacentTerritory(territories.get("Territory 8"));
        territories.get("Territory 9").addAdjacentTerritory(territories.get("Territory 10"));

        // Territory 10
        territories.get("Territory 10").addAdjacentTerritory(territories.get("Territory 9"));
        territories.get("Territory 10").addAdjacentTerritory(territories.get("Territory 11"));
        territories.get("Territory 10").addAdjacentTerritory(territories.get("Territory 12"));

        // Territory 11
        territories.get("Territory 11").addAdjacentTerritory(territories.get("Territory 10"));
        territories.get("Territory 11").addAdjacentTerritory(territories.get("Territory 12"));
        territories.get("Territory 11").addAdjacentTerritory(territories.get("Territory 13"));

        // Territory 12
        territories.get("Territory 12").addAdjacentTerritory(territories.get("Territory 10"));
        territories.get("Territory 12").addAdjacentTerritory(territories.get("Territory 11"));
        territories.get("Territory 12").addAdjacentTerritory(territories.get("Territory 13"));
        territories.get("Territory 12").addAdjacentTerritory(territories.get("Territory 21"));


        // Territory 13
        territories.get("Territory 13").addAdjacentTerritory(territories.get("Territory 11"));
        territories.get("Territory 13").addAdjacentTerritory(territories.get("Territory 12"));

        // Territory 14
        territories.get("Territory 14").addAdjacentTerritory(territories.get("Territory 6"));
        territories.get("Territory 14").addAdjacentTerritory(territories.get("Territory 15"));
        territories.get("Territory 14").addAdjacentTerritory(territories.get("Territory 17"));

        // Territory 15
        territories.get("Territory 15").addAdjacentTerritory(territories.get("Territory 14"));
        territories.get("Territory 15").addAdjacentTerritory(territories.get("Territory 16"));
        territories.get("Territory 15").addAdjacentTerritory(territories.get("Territory 18"));

        // Territory 16
        territories.get("Territory 16").addAdjacentTerritory(territories.get("Territory 15"));
        territories.get("Territory 16").addAdjacentTerritory(territories.get("Territory 18"));
        territories.get("Territory 16").addAdjacentTerritory(territories.get("Territory 30"));

        // Territory 17
        territories.get("Territory 17").addAdjacentTerritory(territories.get("Territory 14"));
        territories.get("Territory 17").addAdjacentTerritory(territories.get("Territory 18"));
        territories.get("Territory 17").addAdjacentTerritory(territories.get("Territory 19"));

        // Territory 18
        territories.get("Territory 18").addAdjacentTerritory(territories.get("Territory 15"));
        territories.get("Territory 18").addAdjacentTerritory(territories.get("Territory 16"));
        territories.get("Territory 18").addAdjacentTerritory(territories.get("Territory 17"));
        territories.get("Territory 18").addAdjacentTerritory(territories.get("Territory 20"));

        // Territory 19
        territories.get("Territory 19").addAdjacentTerritory(territories.get("Territory 17"));
        territories.get("Territory 19").addAdjacentTerritory(territories.get("Territory 20"));
        territories.get("Territory 19").addAdjacentTerritory(territories.get("Territory 21"));

        // Territory 20
        territories.get("Territory 20").addAdjacentTerritory(territories.get("Territory 18"));
        territories.get("Territory 20").addAdjacentTerritory(territories.get("Territory 19"));
        territories.get("Territory 20").addAdjacentTerritory(territories.get("Territory 22"));
        territories.get("Territory 20").addAdjacentTerritory(territories.get("Territory 36"));

        // Territory 21
        territories.get("Territory 21").addAdjacentTerritory(territories.get("Territory 12"));
        territories.get("Territory 21").addAdjacentTerritory(territories.get("Territory 19"));
        territories.get("Territory 21").addAdjacentTerritory(territories.get("Territory 22"));
        territories.get("Territory 21").addAdjacentTerritory(territories.get("Territory 23"));

        // Territory 22
        territories.get("Territory 22").addAdjacentTerritory(territories.get("Territory 20"));
        territories.get("Territory 22").addAdjacentTerritory(territories.get("Territory 21"));
        territories.get("Territory 22").addAdjacentTerritory(territories.get("Territory 23"));
        territories.get("Territory 22").addAdjacentTerritory(territories.get("Territory 24"));
        territories.get("Territory 22").addAdjacentTerritory(territories.get("Territory 36"));

        // Territory 23
        territories.get("Territory 23").addAdjacentTerritory(territories.get("Territory 21"));
        territories.get("Territory 23").addAdjacentTerritory(territories.get("Territory 22"));
        territories.get("Territory 23").addAdjacentTerritory(territories.get("Territory 24"));
        territories.get("Territory 23").addAdjacentTerritory(territories.get("Territory 25"));

        // Territory 24
        territories.get("Territory 24").addAdjacentTerritory(territories.get("Territory 22"));
        territories.get("Territory 24").addAdjacentTerritory(territories.get("Territory 23"));
        territories.get("Territory 24").addAdjacentTerritory(territories.get("Territory 26"));

        // Territory 25
        territories.get("Territory 25").addAdjacentTerritory(territories.get("Territory 23"));
        territories.get("Territory 25").addAdjacentTerritory(territories.get("Territory 26"));

        // Territory 26
        territories.get("Territory 26").addAdjacentTerritory(territories.get("Territory 24"));
        territories.get("Territory 26").addAdjacentTerritory(territories.get("Territory 25"));

        // Territory 27
        territories.get("Territory 27").addAdjacentTerritory(territories.get("Territory 28"));
        territories.get("Territory 27").addAdjacentTerritory(territories.get("Territory 30"));
        territories.get("Territory 27").addAdjacentTerritory(territories.get("Territory 31"));

        // Territory 28
        territories.get("Territory 28").addAdjacentTerritory(territories.get("Territory 27"));
        territories.get("Territory 28").addAdjacentTerritory(territories.get("Territory 29"));
        territories.get("Territory 28").addAdjacentTerritory(territories.get("Territory 32"));

        // Territory 29
        territories.get("Territory 29").addAdjacentTerritory(territories.get("Territory 1"));
        territories.get("Territory 29").addAdjacentTerritory(territories.get("Territory 28"));
        territories.get("Territory 29").addAdjacentTerritory(territories.get("Territory 32"));

        // Territory 30
        territories.get("Territory 30").addAdjacentTerritory(territories.get("Territory 16"));
        territories.get("Territory 30").addAdjacentTerritory(territories.get("Territory 27"));
        territories.get("Territory 30").addAdjacentTerritory(territories.get("Territory 31"));
        territories.get("Territory 30").addAdjacentTerritory(territories.get("Territory 33"));

        // Territory 31
        territories.get("Territory 31").addAdjacentTerritory(territories.get("Territory 27"));
        territories.get("Territory 31").addAdjacentTerritory(territories.get("Territory 30"));
        territories.get("Territory 31").addAdjacentTerritory(territories.get("Territory 32"));
        territories.get("Territory 31").addAdjacentTerritory(territories.get("Territory 34"));

        // Territory 32
        territories.get("Territory 32").addAdjacentTerritory(territories.get("Territory 28"));
        territories.get("Territory 32").addAdjacentTerritory(territories.get("Territory 29"));
        territories.get("Territory 32").addAdjacentTerritory(territories.get("Territory 31"));
        territories.get("Territory 32").addAdjacentTerritory(territories.get("Territory 34"));
        territories.get("Territory 32").addAdjacentTerritory(territories.get("Territory 35"));

        // Territory 33
        territories.get("Territory 33").addAdjacentTerritory(territories.get("Territory 30"));
        territories.get("Territory 33").addAdjacentTerritory(territories.get("Territory 34"));
        territories.get("Territory 33").addAdjacentTerritory(territories.get("Territory 36"));
        territories.get("Territory 33").addAdjacentTerritory(territories.get("Territory 37"));

        // Territory 34
        territories.get("Territory 34").addAdjacentTerritory(territories.get("Territory 31"));
        territories.get("Territory 34").addAdjacentTerritory(territories.get("Territory 32"));
        territories.get("Territory 34").addAdjacentTerritory(territories.get("Territory 33"));
        territories.get("Territory 34").addAdjacentTerritory(territories.get("Territory 38"));

        // Territory 35
        territories.get("Territory 35").addAdjacentTerritory(territories.get("Territory 32"));

        // Territory 36
        territories.get("Territory 36").addAdjacentTerritory(territories.get("Territory 20"));
        territories.get("Territory 36").addAdjacentTerritory(territories.get("Territory 22"));
        territories.get("Territory 36").addAdjacentTerritory(territories.get("Territory 33"));
        territories.get("Territory 36").addAdjacentTerritory(territories.get("Territory 37"));

        // Territory 37
        territories.get("Territory 37").addAdjacentTerritory(territories.get("Territory 33"));
        territories.get("Territory 37").addAdjacentTerritory(territories.get("Territory 36"));
        territories.get("Territory 37").addAdjacentTerritory(territories.get("Territory 38"));

        // Territory 38
        territories.get("Territory 38").addAdjacentTerritory(territories.get("Territory 34"));
        territories.get("Territory 38").addAdjacentTerritory(territories.get("Territory 37"));
        territories.get("Territory 38").addAdjacentTerritory(territories.get("Territory 39"));

        // Territory 39
        territories.get("Territory 39").addAdjacentTerritory(territories.get("Territory 38"));
        territories.get("Territory 39").addAdjacentTerritory(territories.get("Territory 40"));
        territories.get("Territory 39").addAdjacentTerritory(territories.get("Territory 41"));
        territories.get("Territory 39").addAdjacentTerritory(territories.get("Territory 42"));

        // Territory 40
        territories.get("Territory 40").addAdjacentTerritory(territories.get("Territory 39"));
        territories.get("Territory 40").addAdjacentTerritory(territories.get("Territory 41"));
        territories.get("Territory 40").addAdjacentTerritory(territories.get("Territory 42"));

        // Territory 41
        territories.get("Territory 41").addAdjacentTerritory(territories.get("Territory 39"));
        territories.get("Territory 41").addAdjacentTerritory(territories.get("Territory 40"));
        territories.get("Territory 41").addAdjacentTerritory(territories.get("Territory 42"));

        // Territory 42
        territories.get("Territory 42").addAdjacentTerritory(territories.get("Territory 39"));
        territories.get("Territory 42").addAdjacentTerritory(territories.get("Territory 40"));
        territories.get("Territory 42").addAdjacentTerritory(territories.get("Territory 41"));
    }
}
