package oceantrader;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

public class EncounterFrame extends JFrame {

    private JLabel oppHPLbl;
    private JProgressBar oppHealthBar;
    private JLabel oppLabel;
    private JPanel oppPanel;
    private JLabel oppSaysLbl;
    private JLabel youHPLbl;
    private JProgressBar youHealthBar;
    private JLabel youLabel;

    public EncounterFrame() {
        initGUI();
    }

    private void initGUI() {
        youLabel = new JLabel();
        oppLabel = new JLabel();
        youHPLbl = new JLabel();
        oppHPLbl = new JLabel();
        youHealthBar = new JProgressBar();
        oppHealthBar = new JProgressBar();
        oppSaysLbl = new JLabel();
        oppPanel = new JPanel();

        doNotTouchlol();
    }

    private void doNotTouchlol() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Encounter!");
        setName("Encounter");

        youLabel.setText("You:");
        oppLabel.setText("Opponent:");
        youHPLbl.setText("Health:");
        oppHPLbl.setText("Health:");
        oppSaysLbl.setText("Opponent says . . .");

        GroupLayout oppPanelLayout = new GroupLayout(oppPanel);
        oppPanel.setLayout(oppPanelLayout);
        oppPanelLayout.setHorizontalGroup(
                oppPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );
        oppPanelLayout.setVerticalGroup(
                oppPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 335, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(80, 80, 80)
                                .addComponent(youLabel)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(oppLabel)
                                .addGap(80, 80, 80))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(26, 26, 26)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(youHPLbl)
                                        .addComponent(youHealthBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(oppHPLbl)
                                        .addComponent(oppHealthBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(45, 45, 45))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(150, 150, 150)
                                .addComponent(oppSaysLbl)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(oppPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(46, 46, 46)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(youLabel)
                                        .addComponent(oppLabel))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(youHPLbl)
                                        .addComponent(oppHPLbl))
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addComponent(youHealthBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(oppHealthBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(oppSaysLbl)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(oppPanel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        pack();
    }
}