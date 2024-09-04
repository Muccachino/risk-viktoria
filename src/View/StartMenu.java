package View;

import Controller.Main;
import Config.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartMenu implements ActionListener {
    public static final int HEADLINE_HEIGHT = 50;
    public static final int PLAYER_NUMBER = 50;
    public static final int NAME_HEIGHT = 150;
    public static final int BOARD_CHOICE_HEIGHT = 150;
    public static final int PLAYER_NAME_WIDTH = 300;

    public static final Color PLAYER_AREA_BORDER = new Color(135,135,135);

    JFrame frame;

    JTextField playerOneName;
    JTextField playerTwoName;
    JTextField playerThreeName;
    JTextField playerFourName;
    JButton playerOneColorButton1;
    JButton playerOneColorButton2;
    JButton playerOneColorButton3;
    JButton playerTwoColorButton1;
    JButton playerTwoColorButton2;
    JButton playerTwoColorButton3;
    JButton playerThreeColorButton1;
    JButton playerThreeColorButton2;
    JButton playerThreeColorButton3;
    JButton playerFourColorButton1;
    JButton playerFourColorButton2;
    JButton playerFourColorButton3;

    JButton board1;
    JButton board2;
    JButton board3;

    GridBagLayout startWindowLayout = new GridBagLayout();
    GridBagConstraints startWindowConstraints = new GridBagConstraints();
    JPanel startWindowPanel = new JPanel(startWindowLayout);

    private final Main controller;

    public StartMenu(Main controller) {
        this.controller = controller;
    }

    public JFrame drawStartMenuFrame() {
        frame = new JFrame("Risk");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setResizable(false);


        startWindowLayout.rowHeights = new int[] { HEADLINE_HEIGHT, PLAYER_NUMBER, NAME_HEIGHT, NAME_HEIGHT, BOARD_CHOICE_HEIGHT};
        startWindowLayout.columnWidths = new int[] { PLAYER_NAME_WIDTH, PLAYER_NAME_WIDTH};

        JLabel headline = new JLabel("Risk", JLabel.CENTER);
        headline.setFont(new Font(headline.getFont().getName(), Font.PLAIN, 20));

        JPanel playerNumberPanel = new JPanel(new GridLayout(1,2));
        JLabel playerQuestion = new JLabel("How many Players:", JLabel.CENTER);
        ButtonGroup playerNumberGroup = new ButtonGroup();
        JRadioButton twoPlayers = new JRadioButton("Two", true);
        twoPlayers.addActionListener(this);
        twoPlayers.setActionCommand("twoPlayers");
        JRadioButton threePlayers = new JRadioButton("Three");
        threePlayers.addActionListener(this);
        threePlayers.setActionCommand("threePlayers");
        JRadioButton fourPlayers = new JRadioButton("Four");
        fourPlayers.addActionListener(this);
        fourPlayers.setActionCommand("fourPlayers");
        playerNumberGroup.add(twoPlayers);
        playerNumberGroup.add(threePlayers);
        playerNumberGroup.add(fourPlayers);
        playerNumberPanel.add(playerQuestion);
        playerNumberPanel.add(twoPlayers);
        playerNumberPanel.add(threePlayers);
        playerNumberPanel.add(fourPlayers);

        JPanel playerOnePanel = new JPanel(new GridLayout(4, 1));
        playerOnePanel.setBorder(BorderFactory.createLineBorder(PLAYER_AREA_BORDER));
        JLabel playerOneHeadline = new JLabel("Player One Name: ", JLabel.CENTER);
        playerOneName = new JTextField();
        JLabel playerOneColorHeadline = new JLabel("Choose Player One Color: ", JLabel.CENTER);
        JPanel playerOneColorButtonPanel = new JPanel(new GridLayout(1, 3));
        playerOneColorButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,10,2,10));
        playerOneColorButton1 = createButton("", new Color(132, 255, 31), "playerOneColorButton1");
        playerOneColorButton2 = createButton("", new Color(107, 179, 68), "playerOneColorButton2");
        playerOneColorButton3 = createButton("", new Color(4, 125, 38), "playerOneColorButton3");
        playerOneColorButtonPanel.add(playerOneColorButton1);
        playerOneColorButtonPanel.add(playerOneColorButton2);
        playerOneColorButtonPanel.add(playerOneColorButton3);
        playerOnePanel.add(playerOneHeadline);
        playerOnePanel.add(playerOneName);
        playerOnePanel.add(playerOneColorHeadline);
        playerOnePanel.add(playerOneColorButtonPanel);

        JPanel playerTwoPanel = new JPanel(new GridLayout(4, 1));
        playerTwoPanel.setBorder(BorderFactory.createLineBorder(PLAYER_AREA_BORDER));
        JLabel playerTwoHeadline = new JLabel("Player Two Name: ", JLabel.CENTER);
        playerTwoName = new JTextField();
        JLabel playerTwoColorHeadline = new JLabel("Choose Player Two Color: ", JLabel.CENTER);
        JPanel playerTwoColorButtonPanel = new JPanel(new GridLayout(1, 3));
        playerTwoColorButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,10,2,10));
        playerTwoColorButton1 = createButton("", new Color(242, 220, 50), "playerTwoColorButton1");
        playerTwoColorButton2 = createButton("", new Color(195, 154, 60), "playerTwoColorButton2");
        playerTwoColorButton3 = createButton("", new Color(240, 235, 86), "playerTwoColorButton3");
        playerTwoColorButtonPanel.add(playerTwoColorButton1);
        playerTwoColorButtonPanel.add(playerTwoColorButton2);
        playerTwoColorButtonPanel.add(playerTwoColorButton3);
        playerTwoPanel.add(playerTwoHeadline);
        playerTwoPanel.add(playerTwoName);
        playerTwoPanel.add(playerTwoColorHeadline);
        playerTwoPanel.add(playerTwoColorButtonPanel);

        JPanel playerThreePanel = new JPanel(new GridLayout(4, 1));
        playerThreePanel.setBorder(BorderFactory.createLineBorder(PLAYER_AREA_BORDER));
        JLabel playerThreeHeadline = new JLabel("Player Three Name: ", JLabel.CENTER);
        playerThreeName = new JTextField();
        playerThreeName.setEnabled(false);
        JLabel playerThreeColorHeadline = new JLabel("Choose Player Three Color: ", JLabel.CENTER);
        JPanel playerThreeColorButtonPanel = new JPanel(new GridLayout(1, 3));
        playerThreeColorButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,10,2,10));
        playerThreeColorButton1 = createButton("", new Color(78, 59, 250), "playerThreeColorButton1");
        playerThreeColorButton2 = createButton("", new Color(133, 57, 255), "playerThreeColorButton2");
        playerThreeColorButton3 = createButton("", new Color(58, 195, 197), "playerThreeColorButton3");
        playerThreeColorButtonPanel.add(playerThreeColorButton1);
        playerThreeColorButtonPanel.add(playerThreeColorButton2);
        playerThreeColorButtonPanel.add(playerThreeColorButton3);
        playerThreePanel.add(playerThreeHeadline);
        playerThreePanel.add(playerThreeName);
        playerThreePanel.add(playerThreeColorHeadline);
        playerThreePanel.add(playerThreeColorButtonPanel);

        JPanel playerFourPanel = new JPanel(new GridLayout(4, 1));
        playerFourPanel.setBorder(BorderFactory.createLineBorder(PLAYER_AREA_BORDER));
        JLabel playerFourHeadline = new JLabel("Player Four Name: ", JLabel.CENTER);
        playerFourName = new JTextField();
        playerFourName.setEnabled(false);
        JLabel playerFourColorHeadline = new JLabel("Choose Player Four Color: ", JLabel.CENTER);
        JPanel playerFourColorButtonPanel = new JPanel(new GridLayout(1, 3));
        playerFourColorButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,10,2,10));
        playerFourColorButton1 = createButton("", new Color(242, 62, 86), "playerFourColorButton1");
        playerFourColorButton2 = createButton("", new Color(245, 83, 61), "playerFourColorButton2");
        playerFourColorButton3 = createButton("", new Color(247, 102, 49), "playerFourColorButton3");
        playerFourColorButtonPanel.add(playerFourColorButton1);
        playerFourColorButtonPanel.add(playerFourColorButton2);
        playerFourColorButtonPanel.add(playerFourColorButton3);
        playerFourPanel.add(playerFourHeadline);
        playerFourPanel.add(playerFourName);
        playerFourPanel.add(playerFourColorHeadline);
        playerFourPanel.add(playerFourColorButtonPanel);


        JPanel boardChoicePanel = new JPanel(new GridLayout(3, 1));
        JLabel choice = new JLabel("Choose a game board:", JLabel.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,20,10,20));
        board1 = createButton("Board 1", null, "board1");
        board2 = createButton("Board 2", null, "board2");
        board3 = createButton("Board 3", null, "board3");

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(this);
        startButton.setActionCommand("startButton");
        buttonPanel.add(board1);
        buttonPanel.add(board2);
        buttonPanel.add(board3);
        boardChoicePanel.add(choice);
        boardChoicePanel.add(buttonPanel);
        boardChoicePanel.add(startButton);

        startWindowPanel.add(headline, Helper.buildBoardConstraints(startWindowConstraints, 0, 0, 1, 2));
        startWindowPanel.add(playerNumberPanel, Helper.buildBoardConstraints(startWindowConstraints, 1, 0, 1, 2));
        startWindowPanel.add(playerOnePanel, Helper.buildBoardConstraints(startWindowConstraints, 2, 0, 1, 1));
        startWindowPanel.add(playerTwoPanel, Helper.buildBoardConstraints(startWindowConstraints, 2, 1, 1, 1));
        startWindowPanel.add(playerThreePanel, Helper.buildBoardConstraints(startWindowConstraints, 3, 0, 1, 1));
        startWindowPanel.add(playerFourPanel, Helper.buildBoardConstraints(startWindowConstraints, 3, 1, 1, 1));
        startWindowPanel.add(boardChoicePanel, Helper.buildBoardConstraints(startWindowConstraints, 4, 0, 1, 2));

        startWindowPanel.setBorder(BorderFactory.createLineBorder(PLAYER_AREA_BORDER));

        frame.setContentPane(startWindowPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        return frame;
    }

    public JButton createButton(String text, Color color, String actionCommand) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setBorder(BorderFactory.createLineBorder(new Color(228, 229, 227), 2));
        button.addActionListener(this);
        button.setActionCommand(actionCommand);

        return button;
    }

    // Highlights the first Button in given Array
    public void highlightButton(JButton activeButton, JButton notActiveButton1, JButton notActiveButton2) {
        notActiveButton1.setBorder(BorderFactory.createLineBorder(new Color(228, 229, 227), 2));
        notActiveButton2.setBorder(BorderFactory.createLineBorder(new Color(228, 229, 227), 2));
        activeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "twoPlayers":
                controller.setNumPlayers(2);
                playerThreeName.setEnabled(false);
                playerFourName.setEnabled(false);
                break;
            case "threePlayers":
                controller.setNumPlayers(3);
                playerThreeName.setEnabled(true);
                playerFourName.setEnabled(false);
                break;
            case "fourPlayers":
                controller.setNumPlayers(4);
                playerThreeName.setEnabled(true);
                playerFourName.setEnabled(true);
                break;
            case "playerOneColorButton1":
                highlightButton(playerOneColorButton1, playerOneColorButton2, playerOneColorButton3);
                controller.setPlayerColor("player1", playerOneColorButton1.getBackground());
                break;
            case "playerOneColorButton2":
                highlightButton(playerOneColorButton2, playerOneColorButton1, playerOneColorButton3);
                controller.setPlayerColor("player1", playerOneColorButton2.getBackground());
                break;
            case "playerOneColorButton3":
                highlightButton(playerOneColorButton3, playerOneColorButton1, playerOneColorButton2);
                controller.setPlayerColor("player1", playerOneColorButton3.getBackground());
                break;
            case "playerTwoColorButton1":
                highlightButton(playerTwoColorButton1, playerTwoColorButton2, playerTwoColorButton3);
                controller.setPlayerColor("player2", playerTwoColorButton1.getBackground());
                break;
            case "playerTwoColorButton2":
                highlightButton(playerTwoColorButton2, playerTwoColorButton1, playerTwoColorButton3);
                controller.setPlayerColor("player2", playerTwoColorButton2.getBackground());
                break;
            case "playerTwoColorButton3":
                highlightButton(playerTwoColorButton3, playerTwoColorButton1, playerTwoColorButton2);
                controller.setPlayerColor("player2", playerTwoColorButton3.getBackground());
                break;
            case "playerThreeColorButton1":
                highlightButton(playerThreeColorButton1, playerThreeColorButton2, playerThreeColorButton3);
                controller.setPlayerColor("player3", playerThreeColorButton1.getBackground());
                break;
            case "playerThreeColorButton2":
                highlightButton(playerThreeColorButton2, playerThreeColorButton1, playerThreeColorButton3);
                controller.setPlayerColor("player3", playerThreeColorButton2.getBackground());
                break;
            case "playerThreeColorButton3":
                highlightButton(playerThreeColorButton3, playerThreeColorButton1, playerThreeColorButton2);
                controller.setPlayerColor("player3", playerThreeColorButton3.getBackground());
                break;
            case "playerFourColorButton1":
                highlightButton(playerFourColorButton1, playerFourColorButton2, playerFourColorButton3);
                controller.setPlayerColor("player4", playerFourColorButton1.getBackground());
                break;
            case "playerFourColorButton2":
                highlightButton(playerFourColorButton2, playerFourColorButton1, playerFourColorButton3);
                controller.setPlayerColor("player4", playerFourColorButton2.getBackground());
                break;
            case "playerFourColorButton3":
                highlightButton(playerFourColorButton3, playerFourColorButton1, playerFourColorButton2);
                controller.setPlayerColor("player4", playerFourColorButton3.getBackground());
                break;
            case "board1":
                highlightButton(board1, board2, board3);
                controller.setBoardChoice("board1");
                break;
            case "board2":
                highlightButton(board2, board1, board3);
                controller.setBoardChoice("board2");
                break;
            case "board3":
                highlightButton(board3, board1, board2);
                controller.setBoardChoice("board3");
                break;
            case "startButton":
                if(controller.colorsSet() &&
                    controller.boardChosen() &&
                    controller.playerNamesSet(playerOneName.getText(), playerTwoName.getText(), playerThreeName.getText(), playerFourName.getText()) &&
                    !playerOneName.getText().equals(playerTwoName.getText())) {

                    controller.startGame(new String[] {playerOneName.getText(), playerTwoName.getText(), playerThreeName.getText(), playerFourName.getText()});
                }
        }
    }
}
