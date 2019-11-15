package oceantrader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Font;

import static oceantrader.OceanTrader.startGame;
import static oceantrader.OceanTrader.window;

public class EndGame extends JFrame {

    protected JPanel panel;
    protected JButton titleBtn;
    protected JButton endGame;

    protected EndGame() {
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
}