package oceantrader;

import javax.swing.*;
import java.awt.Font;

import static oceantrader.OceanTrader.startGame;
import static oceantrader.OceanTrader.window;

public class EndGame extends JFrame {

    protected JPanel panel;
    protected JButton titleBtn;
    protected JButton endGame;
    protected static JLabel label;

    protected EndGame(int num) {

        textSet(num);
        initGUI();
    }

    private void initGUI() {

        panel = new JPanel();

        titleBtn = new JButton("Return to Title Screen");
        endGame = new JButton("End Game");
        setSpecs(titleBtn);
        setSpecs(endGame);

        panel.add(titleBtn);
        panel.add(endGame);

        titleBtn.addActionListener(e -> {
            window.dispose();
            dispose();
            startGame();
        });

        endGame.addActionListener(e -> {
            System.exit(0);
        });

        add(panel);
        setAlwaysOnTop(true);
        setSize(400, 400);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private static void setSpecs(JButton button) {
        button.setFont(new Font("Tahoma", Font.PLAIN, 30));
    }

    private static void textSet(int num) {
        String text;
        switch (num) {
            case 0:
                text = "Congratulations! You bought the universe!"
                        + " You beat the game!";
                break;
            case 1:
                text = "Your ship has sunken, and you've been cast to sea... "
                        + "would you like to try again?";
                break;
            default:
                text = "Welcome to the end of the game.";
                break;
        }
        label.setText(text);
    }
}