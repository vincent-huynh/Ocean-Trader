package oceantrader;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class PoliceEncounter extends JPanel implements IEncounter {

    private Police police;
    private JButton fightBtn;
    private JButton fleeBtn;
    private JButton forfeitBtn;
    private JScrollPane jScrollPane1;
    private JTextArea jTextArea1;
    private JLabel policeLbl;
    private ArrayList<Item> inventory;
    private Random rand;
    protected static Player player;
    protected static boolean fleeSuccess;
    protected static int itemPos;
    protected static Item forbidden;

    public PoliceEncounter() {
        initGUI();
    }

    private void initGUI() {

        police = new Police();
        policeLbl = new JLabel();
        forfeitBtn = new JButton();
        fleeBtn = new JButton();
        fightBtn = new JButton();
        jScrollPane1 = new JScrollPane();
        jTextArea1 = new JTextArea();

        forfeitBtn.setText("Forfeit");
        forfeitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                police.concedable();
            }
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                forfeitDisc();
            }
        });

        fleeBtn.setText("Flee");
        fleeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                police.avertable();
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
                police.fightable();
            }
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                fightDisc();
            }
        });
        doNotTouch();
    }

    private void forfeitDisc() {
        String text = "Forfeit your illegal item and continue to move on.";
        jTextArea1.setText(text);
    }

    private void fleeDisc() {
        String text = "Attempt to flee from the Police. Your success is "
                + "dependent on your Pilot Skill. If successful, your items "
                + "are safe. If unsuccessful, your illegal goods will be  "
                + "confiscated, your ship will be damaged, and you will pay a "
                + "fine for attempting to evade the Police.";
        jTextArea1.setText(text);
    }

    private void fightDisc() {
        String text = "Attempt to fight the Police. Your success is dependent "
                + "on your Fighting Skill. If you are successful, you keep "
                + "your items. If you lose, your ship will be damaged, and you"
                + " will pay a fine for fighting with the police.";
        jTextArea1.setText(text);
    }

    private void doNotTouch() {
        policeLbl.setFont(new java.awt.Font("Dialog", 1, 18));
        policeLbl.setText("The Police are here to investigate...");

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(null);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Dialog", 0, 18));
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setWrapStyleWord(true);
        jScrollPane1.setViewportView(jTextArea1);

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(GroupLayout
                        .Alignment.LEADING, false)
                        .addComponent(policeLbl)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(12, 12, 12)
                            .addGroup(layout.createParallelGroup(GroupLayout
                                .Alignment.LEADING).addComponent(jScrollPane1,
                                GroupLayout.PREFERRED_SIZE, 351, GroupLayout
                                    .PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(forfeitBtn)
                                    .addGap(63, 63, 63)
                                    .addComponent(fleeBtn)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement
                                                    .RELATED, GroupLayout.DEFAULT_SIZE,
                                            Short.MAX_VALUE)
                                    .addComponent(fightBtn)))))
                    .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(policeLbl)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, GroupLayout
                                        .PREFERRED_SIZE, 182, GroupLayout
                                        .PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement
                                        .RELATED, 29, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout
                                        .Alignment.BASELINE)
                                        .addComponent(forfeitBtn)
                                        .addComponent(fleeBtn)
                                        .addComponent(fightBtn))
                                .addContainerGap())
        );
    }

    public void updatePanel() {
        //do something here, otherwise i'll delete the implementing of the
        // interface// if not needed probably don't need it here tbh
    }

    protected void updatePlayer(Player player) {
        this.player = player;
        rand = new Random();
        inventory = player.getShip().getCargoList();
        itemPos = rand.nextInt(inventory.size());
        forbidden = inventory.get(itemPos);
        policeLbl.setText("The Police believe you stole: " + forbidden.getName());
    }
}