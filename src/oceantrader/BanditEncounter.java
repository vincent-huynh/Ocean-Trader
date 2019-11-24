package oceantrader;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class BanditEncounter extends JPanel implements IEncounter {

    protected static int demand;
    protected static ArrayList<Item> playerInventory;
    protected static Player player;
    private Bandit bandit;
    private JTextArea buttonDText;
    private JTextField demandText;
    private JButton fightBtn;
    private JButton fleeBtn;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JLabel lbl1;
    private JButton payBtn;

    protected static Random rand = new Random();

    public BanditEncounter() {
        initGUI();
    }

    private void initGUI() {

        bandit = new Bandit();
        lbl1 = new JLabel();
        demandText = new JTextField();
        jLabel1 = new JLabel();
        payBtn = new JButton();
        fleeBtn = new JButton();
        fightBtn = new JButton();
        jScrollPane1 = new JScrollPane();
        buttonDText = new JTextArea();

        setPreferredSize(new java.awt.Dimension(400, 335));

        lbl1.setFont(new java.awt.Font("Dialog", 1, 18));
        lbl1.setText("Bandit demands");

        demandText.setEditable(false);
        demandText.setBackground(null);
        demandText.setFont(new java.awt.Font("Dialog", 1, 18));
        demandText.setBorder(null);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18));
        jLabel1.setText("monies!");

        payBtn.setText("Pay");
        payBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                bandit.concedable();
            }

            public void mouseEntered(MouseEvent e) {
                payDisc();
            }
        });

        fleeBtn.setText("Flee");
        fleeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                bandit.avertable();
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                fleeDisc();
            }
        });

        fightBtn.setText("Fight");
        fightBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                bandit.fightable();
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                fightDisc();
            }
        });

        buttonDText.setEditable(false);
        buttonDText.setBackground(null);
        buttonDText.setColumns(20);
        buttonDText.setRows(5);
        buttonDText.setFont(new java.awt.Font("Dialog", 1, 14));
        buttonDText.setWrapStyleWord(true);
        buttonDText.setLineWrap(true);

        jScrollPane1.setViewportView(buttonDText);
        doNotTouch();
    }

    public void updatePanel() {
        demandText.setText("" + demand);
    }

    private void payDisc() {
        String text = "The Bandit makes his demands. If you do not have"
                + " enough coins, you will forfeit all of your items to the"
                + " bandit. If you have no items, the bandit will attack you!";
        buttonDText.setText(text);
    }

    private void fleeDisc() {
        String text = "Attempt to flee to your previous region. "
                + "Your success is based on your Piloting Skills. "
                + "If you are unsuccessful in fleeing safely, the Bandit "
                + "will take all of your coins and damage your ship!";
        buttonDText.setText(text);
    }

    private void fightDisc() {
        String text = "Attempt to fight the Bandit. Your success is based"
                + " on your fighting Skill. If you are successful, you receive"
                + " some of the Bandit's coins as reward. If you are"
                + " unsuccessful, the Bandit steals all of your money and"
                + " damages your ship!";
        buttonDText.setText(text);
    }

    private void doNotTouch() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment
                        .LEADING).addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(payBtn)
                        .addPreferredGap(javax.swing.LayoutStyle
                                        .ComponentPlacement.RELATED,
                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                Short.MAX_VALUE)
                        .addComponent(fleeBtn)
                        .addGap(55, 55, 55)
                        .addComponent(fightBtn)
                        .addGap(41, 41, 41))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing
                                        .GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lbl1)
                                                .addPreferredGap(javax.swing
                                                        .LayoutStyle
                                                        .ComponentPlacement
                                                        .UNRELATED)
                                                .addComponent(demandText,
                                                        javax.swing.GroupLayout
                                                                .PREFERRED_SIZE,
                                                        100, javax.swing
                                                                .GroupLayout
                                                                .PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing
                                                        .LayoutStyle
                                                        .ComponentPlacement
                                                        .UNRELATED)
                                                .addComponent(jLabel1)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment
                        .LEADING).addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax
                                .swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lbl1)
                                .addComponent(jLabel1)
                                .addComponent(demandText,
                                        javax.swing.GroupLayout
                                                .PREFERRED_SIZE,
                                        javax.swing.GroupLayout
                                                .DEFAULT_SIZE, javax.swing
                                                .GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1,
                                javax.swing.GroupLayout.PREFERRED_SIZE,
                                178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax
                                .swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(fleeBtn)
                                .addComponent(fightBtn)
                                .addComponent(payBtn))
                        .addContainerGap(javax.swing.GroupLayout
                                .DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }

    protected void updatePlayer(Player player) {
        this.player = player;
        playerInventory = player.getShip().getCargoList();
        if (player.getCurrency() >= 20) {
            int upperBound = (int) (1.10 * player.getCurrency());
            int lowerBound = (int) (.20 * player.getCurrency());
            demand = rand.nextInt(upperBound - lowerBound) + lowerBound;
        } else {
            demand = rand.nextInt(40 - 21) + 21;
        }
        updatePanel();
    }
}