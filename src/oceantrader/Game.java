package oceantrader;

public class Game {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                OceanTrader.startGame();
            }
        });
    }
}