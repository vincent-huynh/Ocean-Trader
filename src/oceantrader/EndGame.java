package oceantrader;

import javax.swing.*;
import javax.swing.text.StyledDocument;
import java.awt.*;

import static oceantrader.OceanTrader.startGame;
import static oceantrader.OceanTrader.window;

public class EndGame extends JFrame {

    private JButton closeBtn;
    private JTextArea deathTxt;
    private JScrollPane jScrollPane1;
    private JButton titleBtn;

    public EndGame(int num) {

        initGUI();
        textSet(num);
    }

    private void initGUI() {
        jScrollPane1 = new JScrollPane();
        deathTxt = new JTextArea();
        titleBtn = new JButton();
        closeBtn = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jScrollPane1.setBackground(null);

        deathTxt.setEditable(false);
        deathTxt.setColumns(20);
        deathTxt.setFont(new Font("Comic Sans MS", 1, 60));
        deathTxt.setRows(5);
        deathTxt.setWrapStyleWord(true);
        deathTxt.setLineWrap(true);
        deathTxt.setBorder(null);
        deathTxt.setOpaque(false);
        jScrollPane1.setViewportView(deathTxt);



        titleBtn.setFont(new Font("Comic Sans MS", 1, 18));
        titleBtn.setText("Return to Title Screen");

        closeBtn.setFont(new Font("Comic Sans MS", 1, 24));
        closeBtn.setText("End Game");

        titleBtn.addActionListener(e -> {
            window.dispose();
            dispose();
            startGame();
        });

        closeBtn.addActionListener(e -> {
            System.exit(0);
        });

        doNotTouch();
    }

    private void doNotTouch() {
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        setAlwaysOnTop(true);
        setSize(1400,1000);
        setVisible(true);
        window.setVisible(false);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1)
                                .addContainerGap())
                        .addGroup(layout.createSequentialGroup()
                                .addGap(270, 270, 270)
                                .addComponent(titleBtn, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 294, Short.MAX_VALUE)
                                .addComponent(closeBtn, GroupLayout.PREFERRED_SIZE, 283, GroupLayout.PREFERRED_SIZE)
                                .addGap(270, 270, 270))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 600, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 247, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(titleBtn, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(closeBtn, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
                                .addGap(71, 71, 71))
        );
        pack();
    }

    private void textSet(int num) {
        String text;
        switch (num) {
            case 0:
                text = "Congratulations, you have bought the Universe! You beat the game!";
                break;
            case 1:
                text = "Your ship has sunk and you've been cast to sea... Try again?";
                break;
            default:
                text = "Welcome to the end of the game!";
                break;
        }
        deathTxt.setText(text);
    }

}
