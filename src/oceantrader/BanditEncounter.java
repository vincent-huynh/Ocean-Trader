package oceantrader;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class BanditEncounter extends JPanel implements IEncounter {

    private static int demand;
    private JFrame window = OceanTrader.window;
    private Player player;
    private ArrayList<Item> playerInventory;
    private JTextArea buttonDText;
    private JTextField demandText;
    private JButton fightBtn;
    private JButton fleeBtn;
    private JLabel jLabel1;
    private JScrollPane jScrollPane1;
    private JLabel lbl1;
    private JButton payBtn;

    public BanditEncounter() {
        initGUI();
    }

    @SuppressWarnings("unchecked")

    private void initGUI() {

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
                OceanTrader.encounterFrame.setVisible(false);
                if (player.getCurrency() >= demand) {
                    player.setCurrency(player.getCurrency() - demand);
                    OceanTrader.regionDisplay.invMarketDisplay
                            .updateCurrencyDisplay();
                    JOptionPane.showMessageDialog(window, "You lost "
                            + "currency to the bandit");
                    Travel.updateFuel((int) Travel.getCost());
                    Travel.travel();
                } else if (playerInventory.size() >= 1) {
                    playerInventory.clear();
                    OceanTrader.regionDisplay.shipDisplay
                            .updateShipDisplay(player);
                    OceanTrader.regionDisplay.invMarketDisplay
                            .updateInventory();
                    JOptionPane.showMessageDialog(window, "You didn't "
                            + "have enough currency so the bandit cleared your "
                            + "inventory.");
                    Travel.updateFuel((int) Travel.getCost());
                    Travel.travel();
                } else {
                    NPCEncounter.damageShip();
                    JOptionPane.showMessageDialog(window, "You didn't "
                            + "have any inventory so the bandit damaged your "
                            + "ship.");
                    Travel.updateFuel((int) Travel.getCost());
                    Travel.travel();
                }
            }
            public void mouseEntered(MouseEvent e) {
                payDisc();
            }
        });

        fleeBtn.setText("Flee");
        fleeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                OceanTrader.encounterFrame.setVisible(false);
                if (NPCEncounter.getOutcome(player.getSkillLevel("Pilot"))) {
                    JOptionPane.showMessageDialog(window, "You're " +
                            "able to successfully flee but you lost fuel.");
                    Travel.updateFuel((int) Travel.getCost());
                    Travel.travel();
                } else {
                    player.setCurrency(0);
                    OceanTrader.regionDisplay.invMarketDisplay
                            .updateCurrencyDisplay();
                    NPCEncounter.damageShip();
                    JOptionPane.showMessageDialog(window, "You failed "
                            + "to flee so the bandit took all of your " +
                            "currency and damaged your ship.");
                }
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
                OceanTrader.encounterFrame.setVisible(false);
                if (NPCEncounter.getOutcome(player.getSkillLevel("Fighter"))) {
                    Random rand = new Random();
                    int randomInteger =
                            rand.nextInt(1000 - 300) + 300;
                    player.setCurrency(randomInteger + player.getCurrency());
                    OceanTrader.regionDisplay.invMarketDisplay
                            .updateCurrencyDisplay();
                    JOptionPane.showMessageDialog(window, "You " +
                            "successfully defeated the bandit and got some of " +
                            "the bandit's credits as a reward");
                    Travel.updateFuel((int) Travel.getCost());
                    Travel.travel();
                } else {
                    player.setCurrency(0);
                    OceanTrader.regionDisplay.invMarketDisplay
                            .updateCurrencyDisplay();
                    NPCEncounter.damageShip();
                    JOptionPane.showMessageDialog(window, "You failed "
                            + "to fight off the bandit took all of " +
                            "your currency and damaged your ship.");
                }
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
        demandText.setText("1234");
    }

    private void payDisc() {
        String text = "The Bandit makes his demands. If you do not have"
                + " enough funds, you will forfeit all of your items to the"
                + " bandit. If you have no items, the bandit will attack you!";
        buttonDText.setText(text);
    }

    private void fleeDisc() {
        String text = "Attempt to flee to your previous region. "
                + "Your success is based on your Piloting Skills. "
                + "If you are unsuccessful in fleeing safely, the Bandit "
                + "will take all of your money and damage your ship!";
        buttonDText.setText(text);
    }

    private void fightDisc() {
        String text = "Attempt to fight the Bandit. Your success is based"
                + " on your fighting Skill. If you are successful, you receive"
                + " some of the Bandit's credits as reward. If you are"
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
        Random rand = new Random();
        if (player.getCurrency() >= 20) {
            int upperBound = (int) (1.50 * player.getCurrency());
            int lowerBound = (int) (.50 * player.getCurrency());
            int randomInteger =
                    rand.nextInt(upperBound - lowerBound) + lowerBound;
            demand = randomInteger;
            demandText.setText("" + demand);
        } else {
            int randomInteger = rand.nextInt(40 - 21) + 21;
            demand = randomInteger;
            demandText.setText("" + demand);
        }
    }
}