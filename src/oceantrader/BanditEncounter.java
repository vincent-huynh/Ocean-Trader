package oceantrader;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BanditEncounter extends JPanel {

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
        demandText.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                demandTextActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18));
        jLabel1.setText("monies!");

        payBtn.setText("Pay");
        fleeBtn.setText("Flee");
        fightBtn.setText("Fight");

        buttonDText.setEditable(false);
        buttonDText.setBackground(null);
        buttonDText.setColumns(20);
        buttonDText.setRows(5);
        jScrollPane1.setViewportView(buttonDText);

        doNotTouch();
    }

    private void doNotTouch() {
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(49, 49, 49)
                                .addComponent(payBtn)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(fleeBtn)
                                .addGap(55, 55, 55)
                                .addComponent(fightBtn)
                                .addGap(41, 41, 41))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lbl1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(demandText, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel1)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addComponent(demandText, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(lbl1)
                                                        .addComponent(jLabel1))))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(fleeBtn)
                                        .addComponent(fightBtn)
                                        .addComponent(payBtn))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }
    private void demandTextActionPerformed(ActionEvent evt) {
        // TODO add your handling code here:
    }
}