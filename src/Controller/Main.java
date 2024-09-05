package Controller;

import Model.Card;
import Model.Player;
import View.StartMenu;

import javax.swing.*;
import java.awt.*;

// added new StartMenu Window to choose player colors, names and boards
public class Main {

    private final Color[] playerColors = new Color[4];

    JFrame startMenuFrame;

    int numOfPlayers = 2;

    Color playerOneColor;
    Color playerTwoColor;
    Color playerThreeColor;
    Color playerFourColor;

    String boardChoice;

    public void createStartMenu() {
        startMenuFrame = new StartMenu(this).drawStartMenuFrame();
    }

    // Next three functions set the choices for names, colors and the board from the Start Menu
    public void setNumPlayers(int num) {
        numOfPlayers = num;
    }

    public void setPlayerColor(String player, Color color) {
        switch (player) {
            case "player1":
                playerOneColor = color;
                playerColors[0] = color;
                break;
            case "player2":
                playerTwoColor = color;
                playerColors[1] = color;
                break;
            case "player3":
                playerThreeColor = color;
                playerColors[2] = color;
                break;
            case "player4":
                playerFourColor = color;
                playerColors[3] = color;
                break;
        }
    }

    public void setBoardChoice(String board) {
        boardChoice = board;
    }

    // Next three functions check if a board and the necessary player names and colors are set.
    public boolean boardChosen() {
        return boardChoice != null;
    }

    public boolean playerNamesSet(String playerOne, String playerTwo, String playerThree, String playerFour) {
        switch (numOfPlayers) {
            case 2:
                return playerOne != null && playerTwo != null;
            case 3:
                return playerOne != null && playerTwo != null && playerThree != null;
            case 4:
                return playerOne != null && playerTwo != null && playerThree != null && playerFour != null;
        }
        return false;
    }

    public boolean colorsSet() {
        switch (numOfPlayers) {
            case 2:
                return playerOneColor != null && playerTwoColor != null;
            case 3:
                return playerOneColor != null && playerTwoColor != null && playerThreeColor != null;
            case 4:
                return playerOneColor != null && playerTwoColor != null && playerThreeColor != null && playerFourColor != null;
        }
        return false;
    }

    public void startGame(String[] playerNames) {
        Player[] players = new Player[numOfPlayers];
        for (int i = 0; i < numOfPlayers; i++) {
            players[i] = new Player(playerNames[i], playerColors[i]);
            //TODO: Remove initial test cards
            players[i].addCard(new Card("Infantry"));
            players[i].addCard(new Card("Cavalry"));
            players[i].addCard(new Card("Cavalry"));
            players[i].addCard(new Card("Cavalry"));
            players[i].addCard(new Card("Artillery"));
        }

        Game game = new Game(players, boardChoice);
        startMenuFrame.dispose();
    }

    public static void main(String[] args) {

        Main mainController = new Main();
        mainController.createStartMenu();

    }
}
