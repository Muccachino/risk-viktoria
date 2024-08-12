public class Main {
    private static final int NUM_PLAYERS = 2;
    private static final String[] PLAYER_NAMES = {"Player 1", "Player 2"};

    public static void main(String[] args) {
        Player[] players = new Player[NUM_PLAYERS];
        for (int i = 0; i < NUM_PLAYERS; i++) {
            players[i] = new Player(PLAYER_NAMES[i]);
        }

        Game game = new Game(players);
        GUI gui = new GUI(game);
        javax.swing.SwingUtilities.invokeLater(gui::createAndShowGUI);
    }
}
